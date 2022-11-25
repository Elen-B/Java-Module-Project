public class Utils {
    // контроль строки, является ли она целым числом
    public static boolean isInt(String aStr) {
        try {
            Integer.parseInt(aStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // получение строки дробного числа с разделителем . и двумя знаками после запятой
    public static String toStringCustomFormat(double aDoubleNumber) {
        return String.format("%.2f", aDoubleNumber).replace(',', '.');
    }

    // получить написание "рубль" с правильным окончанием
    public static String getRoubleSuffix(double aSumRouble) {
        int mod = (int) (Math.floor(aSumRouble) % 100);
        if (mod > 10 && mod < 15) {
            return "рублей";
        } else {
            switch (mod % 10) {
                case 1: return "рубль";
                case 2:
                case 3:
                case 4: return "рубля";
                default: return "рублей";
            }
        }
    }
}
