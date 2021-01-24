package wheaterservice.frontend;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private Scanner scanner = new Scanner(System.in);

    int retrievesInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.reset();
                scanner.nextLine();
                System.out.println(Colors.RED + " \nPlease enter a number using only the digits 1 to 4");
            }
        }
    }


    String retrievesString() {
        while (true) {
            try {
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.reset();
                scanner.nextLine();

            }
        }
    }


}
