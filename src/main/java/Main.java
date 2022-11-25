import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Counter counter = new Counter(Counter.getPersonCount(scanner)); // создаем калькулятор
        counter.order.inputDishList(scanner);
        counter.order.printOrder();
        double dividedSum = counter.order.bill.divideBill(counter.personCount);
        System.out.printf("Каждый из %s гостей должен заплатить %s %s", counter.personCount, Utils.toStringCustomFormat(dividedSum), Utils.getRoubleSuffix(dividedSum));

        scanner.close();
    }
}