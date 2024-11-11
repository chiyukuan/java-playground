package array;

public class Printf {

    public static void main(String[] args) throws Exception {

        double subtotal = 100.0;
        double tax = subtotal * 0.0085;  // tax rate:  8.5%
        double tip = subtotal * 0.18;    // tip rate: 18%
        double total = subtotal + tax + tip;
        System.out.printf("%15s : 123456789012\n", "- position -");
        System.out.printf("%15s : %12.2f\n", "Subtotal", subtotal);
        System.out.printf("%15s: %12.2f\n", "Tax", tax);
        System.out.printf("%15s: %12.2f\n", "Tip", tip);
        System.out.printf("%15s: %12.2f\n", "Total", total);
        System.out.printf("%15s: %-12.2f\n", "Lift align", total);
    }
}