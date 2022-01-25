
package banking;

import java.util.*;

public class Bank {
    static String cardNum;
    static String pin;
    static String checkCardNum;
    static String checkPin;
    static int action;
    static int value;

    static void chooseAct() {

        System.out.println();
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");

        Scanner sc = new Scanner(System.in);
        action = sc.nextInt();
        switch (action) {
            case 0:
                System.out.println();
                System.out.println("Bye!");
                break;
            case 1:
                createCard();
                chooseAct();
                break;
            case 2:
                logIntoAccount();
                break;
            default:
                break;
        }
        System.out.println();
    }

    static void createCard() {

        Random rnd = new Random();
        cardNum = String.format((Locale)null,"400000%02d%04d%04d",
                rnd.nextInt(10),
                rnd.nextInt(10000),
                rnd.nextInt(10000));

        pin = String.format((Locale)null, "%04d",
                rnd.nextInt(10000));

        if (checkLuhn()) {
            System.out.println();
            Connect.createTable();
            Connect.insert();
            System.out.println("Your card has been created");
            System.out.println("Your card number:" + "\n" + cardNum);
            System.out.println("Your card PIN:" + "\n" + pin);
        } else {
            createCard();
        }
    }

    static boolean checkLuhn() {
        int nDigits = cardNum.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int j = cardNum.charAt(i) - '0';
            if (isSecond) {
                j = j * 2;
            }

            nSum += j / 10;
            nSum += j % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
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
        value = 0;

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
            case 0:
                System.out.println();
                System.out.println("Bye!");
                break;
            default:
                break;
        }
    }
}
