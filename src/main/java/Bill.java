public class Bill {
    double sum; // сумма счета

    // добавить к счету сумму x
    public void addToBill(double aPrice) {
        sum += aPrice;
    }

    // разделить сумму счета на x посетителей
    public double divideBill(int aPersonCount) {
        return sum / aPersonCount;
    }
}
