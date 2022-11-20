import java.util.Scanner;
import java.util.ArrayList;

public class Order {
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
    public void printOrder() {
        System.out.println("Ваш заказ. Добавленные товары:");
        for (Dish d: dishList) {
            d.printDish();
        }
    }

    // создать список блюд заказа
    public void inputDishList() {
        Scanner scanner = new Scanner(System.in);
        String sDishName, sDishPrice;

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
            addDish(new Dish(sDishName, Double.parseDouble(sDishPrice)));  // добавляем блюдо в заказ
            System.out.println("Добавим еще одно блюдо в заказ? Введите \"Завершить\", чтобы отказаться или любой символ, чтобы продолжить");
        } while (!scanner.next().equalsIgnoreCase("завершить"));
    }
}
