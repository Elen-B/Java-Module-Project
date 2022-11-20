public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter(Counter.getPersonCount()); // создаем калькулятор
        counter.order.inputDishList();
        counter.order.printOrder();
        double dividedSum = counter.order.bill.divideBill(counter.personCount);
        System.out.printf("Каждый из %s гостей должен заплатить %s %s", counter.personCount, Utils.toStringCustomFormat(dividedSum), Utils.getRoubleSuffix(dividedSum));
    }
}