package array;
import java.util.Scanner;

public class TestScanner {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Press Enter to continue...");

            // Check if there's another line of input
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    System.out.println("You pressed Enter!");
                } else {
                    System.out.println("You entered: " + input);
                }
            } else {
                System.out.println("No input available.");
            }

            scanner.close();
        }
}
