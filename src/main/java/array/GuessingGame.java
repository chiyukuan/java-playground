import java.util.Random;
import java.util.Scanner;

// Name: Michelle Kuan
// VUnetID: kuanmc
// Email: michelle.c.kuan@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 2
// Date: 10/31/2024
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
// Program description: a guessing game, you enter seed and amount of times you can guess
//                      then you keep guessing until you run out of moves or you win

public class GuessingGame {
    // Constant for biggest number that the user can guess.
    private static final int MAX = 100;  // DO NOT CHANGE

    public static void main(String[] args) {
        /***** DO NOT EDIT THE CODE BELOW *****/

        Scanner console = new Scanner(System.in);
        Random random = new Random();

        printHeading("Game setup");

        // Get seed from user.
        int seed = getSeed(console);

        // Set seed for random number generator.
        random.setSeed(seed);

        /***** DO NOT EDIT THE CODE ABOVE *****/

        // Get the maximum number of times the user can guess.
        int maxGuesses = maxGuess(console);

        // Display instructions.
        System.out.println();
        System.out.println("I'll pick a number between 1-" + MAX + ". You try to guess it.");
        System.out.println("If you don't guess it right, I'll give you a hint to help you.");
        System.out.printf("You get %d %s. Let's play!%n", (Math.abs(maxGuesses)),
                guessOrGuesses(maxGuesses));
        System.out.println();
        System.out.println("I am thinking of a number between 1 and 100.");
        System.out.println();

        int hiddenNum = random.nextInt(100) + 1;

        guessing(maxGuesses,hiddenNum,console);



    }

    /**
     * Prints a heading based on the passed string.
     *
     * THIS METHOD PROVIDED TO YOU. DO NOT MODIFY.
     *
     * @param  heading The heading to print.
     */
    public static void printHeading(String heading) {
        System.out.println(heading);

        for (int i = 0; i < heading.length(); ++i) {
            System.out.print("=");
        }

        System.out.println();
        System.out.println();
    }

    /**
     * Prompts and returns the random number generator seed.
     *
     * THIS METHOD PROVIDED TO YOU. DO NOT MODIFY.
     *
     * @param  console A Scanner object for console (or keyboard) input.
     * @return         The seed to set the random number generator.
     */
    public static int getSeed(Scanner console) {
        System.out.print("Enter a seed: ");
        // int seed = console.nextInt();
        int seed = getNextInt(console, "Enter a seed: ");
        return seed;
    }

    public static int getNextInt(Scanner console, String prompt) {
        while (console.hasNextLine()) {
            String line = console.nextLine();
            if (! line.isEmpty()) {
                Scanner lineScanner = new Scanner(line);
                if (lineScanner.hasNextInt()) {
                    return lineScanner.nextInt();
                }
            }
            System.out.print(prompt);
        }
        return 0; // never happen
    }
    /**
     *
     * @param console
     * @return
     */
    public static int maxGuess( Scanner console){
        System.out.print("Enter maximum guesses allowed: ");
        int guess = getNextInt(console, "Not in the range 1-100, try again: ");
        // while (!console.hasNextInt()){
        //    System.out.print("Not in the range 1-100, try again: ");
        //    console.next();
        // }
        // int guess = console.nextInt();
        // console.nextLine();
        guess = checkRange(guess, console);

        System.out.println("\n" + "Play game\n" + "=========");
        return guess;
    }

    /**
     * loops the game until it ends
     *
     * @param maxGuess the amount of times they can guess
     * @param hiddenNum the actual number
     * @param console scanner to get user input
     */
    public static void guessing (int maxGuess, int hiddenNum, Scanner console){
        int guess = 1;
        int number;
        boolean guessRight = false;
        do {
            System.out.print("Guess " + guess + ", enter your guess: ");
            number = getNextInt(console, "Not in the range 1-100, try again: ");
            // while (! console.hasNextInt()) {
            //     System.out.print("Not in the range 1-100, try again: ");
            //     console.nextLine();
            // }
            // number = console.nextInt();
            // console.nextLine();

            number = checkRange(number, console);
            guessRight = isEqual(number, hiddenNum, guess, maxGuess, guessRight);
            guess++;
        } while (!guessRight);
    }

    /**
     * checks if the guess is greater or less than the actual number
     *
     * @param hiddenNum, the actual number
     * @param number, the users guess
     */
    public static void compareNum (int hiddenNum, int number){
        System.out.println("Sorry, that guess is incorrect.");
        if (number < hiddenNum) {
            System.out.println("The number I am thinking of is higher.");
        } else {
            System.out.println("The number I am thinking of is lower.");
        }
    }

    /**
     * checks if the number is in the 1-100 range
     *
     * @param number the guess the user makes
     * @param console to ask again if it is not in range
     */
    public static int checkRange (int number, Scanner console) {
        while (!(number <= 100 && number >= 1)) {
            System.out.print("Not in the range 1-100, try again: ");
            number = getNextInt(console, "Not in the range 1-100, try again: ");
            // number = console.nextInt();
        }
        return number;
    }

    /**
     * checks if the number is right, and if the game should end and what message
     * to display
     *
     * @param number the number that the user guesses
     * @param hiddenNum the actual number
     * @param guess which guess the user is on
     * @param maxGuess the amount of times they can guess
     * @param endGame boolean that default is false
     * @return boolean endGame - if the user guessed right
     */
    public static boolean isEqual (int number, int hiddenNum, int guess,
                                   int maxGuess, boolean endGame){
        if (number != hiddenNum){
            if (guess== maxGuess){
                System.out.printf("Sorry, you lose. " +
                        "The number I was thinking of was %d.%n",hiddenNum);
                endGame = true;
            }else {
                compareNum(hiddenNum,number);
            }
        }else{
            endGame = true;
            System.out.printf("Correct! You got it in %d %s.%n", (guess),guessOrGuesses(guess));
        }
        return endGame;
    }

    /**
     * checks to use guess or guesses
     *
     * @param n how many __ is being displayed
     * @return a string of guess or guesses
     */
    public static String guessOrGuesses (int n){
        if (n==1) {
            return "guess";
        }else {
            return "guesses";
        }
    }



}
