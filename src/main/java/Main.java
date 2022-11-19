import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sPersonCount, sDishName, sDishPrice;

        System.out.println("На сколько человек необходимо разделить счёт?");
        while (true) {
            sPersonCount = scanner.next();
            if (!Utils.isInt(sPersonCount) || !Counter.checkPersonCount(Integer.parseInt(sPersonCount))) {
                System.out.println("Введите целое число больше 1");
            } else {
                break;
            }
        }
        Counter counter = new Counter(Integer.parseInt(sPersonCount)); // создаем калькулятор

        do {
            do {
                System.out.print("Введите непустое наименование блюда: ");
                sDishName = scanner.next();
            } while (!Dish.isCorrectName(sDishName));

            while(true) {
                System.out.print("Введите стоимость блюда в формате рубли.копейки: ");
                sDishPrice = scanner.next();
                if (Dish.isCorrectPrice(sDishPrice))
                    break;
                System.out.println("Некорректная стоимость блюда");
            }

            counter.order.addDish(new Dish(sDishName, Double.parseDouble(sDishPrice)));  // добавляем блюдо в заказ
            System.out.println("Добавим еще одно блюдо в заказ? Введите \"Завершить\", чтобы отказаться или любой символ, чтобы продолжить");
        } while (!scanner.next().equalsIgnoreCase("завершить"));

        counter.order.printOrder();
        double dividedSum = counter.order.bill.divideBill(counter.personCount);
        System.out.printf("Каждый из %s гостей должен заплатить %s %s", counter.personCount, Utils.toStringCustomFormat(dividedSum), Utils.getRoubleSuffix(dividedSum));
    }


}

class Utils {
    // контроль строки, является ли она целым числом
    static boolean isInt(String aStr) {
        try {
            Integer.parseInt(aStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // получение строки дробного числа с разделителем . и двумя знаками после запятой
    static String toStringCustomFormat(double aDoubleNumber) {
        return String.format("%.2f", aDoubleNumber).replace(',', '.');
    }

    // получить написание "рубль" с правильным окончанием
    static String getRoubleSuffix(double aSumRouble) {
        int mod = (int) (aSumRouble % 10);
        switch (mod) {
            case 1: return "рубль";
            case 2:
            case 3:
            case 4: return "рубля";
            default: return "рублей";
        }
    }
}

// Калькулятор
class Counter {
    int personCount;
    Order order;

    Counter() {
        order = new Order();
    }

    Counter(int aPersonCount) {
        this();
        personCount = aPersonCount;
    }

    // контроль количества посетителей x
    static boolean checkPersonCount(int aInt) {
        return aInt > 1;
    }
}

// заказ
class Order {
    Bill bill;
    ArrayList<Dish> dishList;

    Order() {
        bill = new Bill();
        dishList = new ArrayList<>();
    }

    // добавить блюдо в заказ
    void addDish(Dish aDish) {
        dishList.add(aDish);
        bill.addToBill(aDish.price);
        System.out.printf("В заказ добавлено блюдо \"%s\" стоимостью %s %s", aDish.name, Utils.toStringCustomFormat(aDish.price), Utils.getRoubleSuffix(aDish.price));
        System.out.println();
    }

    // распечатать заказ
    void printOrder() {
        System.out.println("Ваш заказ. Добавленные товары:");
        for (Dish d: dishList) {
            d.printDish();
        }
    }
}

// счет
class Bill {
    double sum; // сумма счета

    // добавить к счету сумму x
    void addToBill(double aPrice) {
        sum += aPrice;
    }

    // разделить сумму счета на x посетителей
    double divideBill(int aPersonCount) {
        return sum / aPersonCount;
    }
}

// блюдо
class Dish {
    String name;
    double price;

    Dish(String aName, double aPrice) {
        name = aName;
        price = aPrice;
    }

    // контроль корректного наименование блюда
    static boolean isCorrectName(String aStr) {
        return !aStr.trim().isEmpty();
    }

    // контроль корректной цены
    static boolean isCorrectPrice(String aStr) {
        try {
            return Double.parseDouble(aStr) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // вывод описания блюда
    void printDish() {
        System.out.printf("%s, стоимость %s %s", name, Utils.toStringCustomFormat(price), Utils.getRoubleSuffix(price));
        System.out.println();
    }
}