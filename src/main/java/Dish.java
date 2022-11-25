public class Dish {
    String name;
    double price;

    Dish(String aName, double aPrice) {
        name = aName;
        price = aPrice;
    }

    // контроль корректного наименование блюда
    public static boolean isCorrectName(String aStr) {
        return !aStr.trim().isEmpty();
    }

    // контроль корректной цены
    public static boolean isCorrectPrice(String aStr) {
        try {
            return Double.parseDouble(aStr) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // вывод описания блюда
    public void printDish() {
        System.out.printf("%s, стоимость %s %s", name, Utils.toStringCustomFormat(price), Utils.getRoubleSuffix(price));
        System.out.println();
    }
}