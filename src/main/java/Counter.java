import java.util.Scanner;

public class Counter {
    int personCount;
    Order order = new Order();

    Counter(int aPersonCount) {
        personCount = aPersonCount;
    }

    // контроль количества посетителей x
    static boolean checkPersonCount(int aInt) {
        return aInt > 1;
    }

    // получить допустимое количество гостей
    public static int getPersonCount() {
        Scanner scanner = new Scanner(System.in);
        String sPersonCount;

        System.out.println("На сколько человек необходимо разделить счёт?");
        while (true) {
            sPersonCount = scanner.next();
            if (!Utils.isInt(sPersonCount) || !checkPersonCount(Integer.parseInt(sPersonCount))) {
                System.out.println("Введите целое число больше 1");
            } else {
                return Integer.parseInt(sPersonCount);
            }
        }
    }
}
