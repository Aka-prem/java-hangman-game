import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class HangmanGame {

    public static void main(String[] args) {
        //accessing words from the file
        String filepath = "src/words.txt";
        ArrayList<String> arr = new ArrayList<>();
        Random rd = new Random();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line ;
            while((line = br.readLine()) != null){
                arr.add(line);
            }
            if(arr.isEmpty()){
                System.out.println("No words available!");
                return;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }


        // variables
        int wrongGuesses = 0;
        String str = arr.get(rd.nextInt(arr.size()));
        char guess;

        Scanner sc = new Scanner(System.in);
        ArrayList<Character> word = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            word.add('_');
        }

        //welcome board
        System.out.println("**********************************");
        System.out.println("WELCOME TO THE JAVA HANGMAN GAME!!");
        System.out.println("**********************************");


        // actual game
        while (wrongGuesses < 6) {
            if (!word.contains('_')) {
                System.out.println("congratulations! You won the game!");
                System.out.println("thanks for playing!");
                break;
            }
            System.out.println(wrongGuess(wrongGuesses));
            System.out.print("word: ");
            for (var c : word) {
                System.out.print(c + " ");
            }

            System.out.print("\nguesses: ");
            guess = sc.next().toLowerCase().charAt(0);

            if (str.indexOf(guess) != -1) {
                System.out.println("correct guess");
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == guess) {
                        word.set(i, guess);
                    }
                }
            } else {
                wrongGuesses++;
                System.out.println("wrong guess");
            }

        }

        if (wrongGuesses == 6) {
            System.out.println(wrongGuess(wrongGuesses));
            System.out.println("you lost the game!");
            System.out.println("better luck next time!");
            System.out.println("actual word: " + str);
        }
//        System.out.println(word);
    }

    static String wrongGuess(int wrongGuesses) {
        return switch (wrongGuesses){
            case 0 -> """
                        
                        
                        
                      """;
            case 1 -> """
                        o
                        
                        
                      """;
            case 2 -> """
                        o
                        | 
                        
                      """;
            case 3 -> """
                        o
                      / | 
                        
                      """;

            case 4 -> """
                        o
                      / | \\
                       / 
                      """;
            case 5 -> """
                        o
                      / | \\
                       / \\
                      """;

            default -> "";
        };
    }
}
