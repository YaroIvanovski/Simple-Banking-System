
package banking;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static String cardNum;
    static String pin;
    static String checkCardNum;
    static String checkPin;
    static HashMap<String, String> account;

    public static void main(String[] args) {

        account = new HashMap<>();
        chooseAct();
    }

    static void chooseAct() {

        System.out.println();
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");

        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();
        switch (action) {
            case 1:
                createAccount();
                chooseAct();
                break;
            case 2:
                logIntoAccount();
                break;
            default:
                System.out.println();
                System.out.println("Bye!");
                break;
            }
            System.out.println();
    }

    static void createAccount() {

        System.out.println();
        System.out.println("Your card has been created");

        Random rnd = new Random();
        cardNum = String.format((Locale)null,
                "400000%02d%04d%04d",
                rnd.nextInt(10),
                rnd.nextInt(10000),
                rnd.nextInt(10000));

        System.out.println("Your card number:" + "\n" + cardNum);

        pin = String.format((Locale)null,
                "%04d",
                rnd.nextInt(10000));

        System.out.println("Your card PIN:" + "\n" + pin);

        account.put(cardNum, pin);
    }

    static void logIntoAccount() {

        System.out.println();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your card number:");
        checkCardNum = sc.nextLine();

        System.out.println("Enter your PIN:");
        checkPin = sc.nextLine();

        if (cardNum.equals(checkCardNum) && pin.equals(checkPin)) {
            System.out.println();
            System.out.println("You have successfully logged in!");
            balance();
        } else {
            System.out.println();
            System.out.println("Wrong card number or PIN!");
            chooseAct();
        }
    }

    static void balance() {

        System.out.println("1. Balance\n" +
                "2. Log out\n" +
                "0. Exit");
        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();
        switch (action) {
            case 1:
                System.out.println();
                System.out.println("Balance: 0");
                System.out.println();
                balance();
                break;
            case 2:
                System.out.println();
                System.out.println("You have successfully logged out!");
                chooseAct();
                break;
            default:
                System.out.println();
                System.out.println("Bye!");
                break;
        }
    }
}