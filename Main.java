import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        StringBuilder CODING = new StringBuilder();
        int length=0, possSymbols=0;

        System.out.println("Input the length of the secret code: ");
        if (!scr.hasNextInt()) {
            System.out.println("Error");
        } else {
            length = scr.nextInt();
        }

        System.out.println("Input the number of possible symbols in the code: ");
        if (!scr.hasNextInt()) {
            System.out.println("Error");
        } else {
            possSymbols = scr.nextInt();
        }
        if (length==0 | possSymbols==0) {
            System.out.println("Error");
        } else

        if (length > possSymbols) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + possSymbols + " unique symbols.");
        } else
        if (possSymbols > 36 | length>36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }
        else {

            int cow = 0, bull = 0, t = 1;
            String starCode = "";
            String[] dict = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            Random random = new Random();

            for (int i = 0; i < length; i++) {
                starCode = starCode + "*";
            }

            if (possSymbols <= 10) {
                do {
                    CODING.setLength(0);
                    for (int i = 0; i < length; i++) {
                        CODING.append(((int) (Math.random() * possSymbols)));
                    }
                } while (Unique(CODING) | CODING.charAt(0) == 0);
                System.out.println("The secret is prepared: " + starCode + " (0-" + (possSymbols - 1) + ").");
            } else {
                do {
                    CODING.setLength(0);
                    for (int i = 0; i < length; i++) {
                        int dictRandom = random.nextInt(dict.length);
                        CODING.append(dict[dictRandom]);
                    }
                } while (Unique(CODING));
                System.out.println("The secret is prepared: " + starCode + " (0-9, a-" + (dict[possSymbols - 1]) + ").");
            }

            String[] CODE = CODING.toString().split("");

            System.out.println("Okay, let's start a game!");

            scr.nextLine();
            while (bull != length) {
                cow = 0;
                bull = 0;

                System.out.println("Turn " + t++ + ":");

                String[] tryCode = scr.nextLine().split("");

                for (int j = 0; j < tryCode.length; j++) {
                    for (int i = 0; i < CODE.length; i++) {

                        if (tryCode[j].equals(CODE[i]) & i != j) {
                            cow++;
                        }
                        if (tryCode[j].equals(CODE[i]) & i == j) {
                            bull++;
                        }
                    }
                }
                if (cow == 0 & bull == 0) {
                    System.out.println("Grade: None.");
                } else if (cow > 0 & bull == 0) {
                    System.out.println("Grade: " + cow + " cow(s).");
                } else if (bull > 0 & cow > 0) {
                    System.out.println("Grade: " + bull + " bull(s) and " + cow + " cow(s).");
                } else if (bull > 0 & cow == 0) {
                    System.out.println("Grade: " + bull + " bull(s).");
                }
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

    public static boolean Unique(StringBuilder code) {
        for (int i = 0; i < code.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (j==i) continue;
                if (code.charAt(j) == code.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}