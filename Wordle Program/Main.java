import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

/* 
   the directions for creating wordle are in the pdf linked from the calendar, 
   the directions for creating a list of words that are 5 long are below
*/

class Main {
  public static void main(String[] args) {
    // create an arrayList of allTheWords

    ArrayList<String> fiveLet = fiveLetterWords(importFile("englishwords.txt"));
    Scanner keyboard = new Scanner(System.in);
    String answer = fiveLet.get((int) (Math.random() * fiveLet.size() + 1));
    // System.out.println(answer);
    System.out.print("Enter a guess: ");
    String guess = keyboard.next();
    String[][] show = new String[12][5];
    int guessNum = 0;
    play(fiveLet, guess, keyboard, answer, show, guessNum);

    // guessing(fiveLet, guess, keyboard, answer, show);
  }

  public static String[][] play(ArrayList<String> fiveLet, String guess,
      Scanner keyboard, String answer, String[][] show, int guessNum) {
    if (guessNum < 10) {
      if (!fiveLet.contains(guess)) {
        System.out.println("Please enter a valid 5 letter word:");
        String guessagain = keyboard.next();
        play(fiveLet, guessagain, keyboard, answer, show, guessNum);
      } else if (!guess.equals(answer)) {
        for (int i = 0; i < 5; i++) {
          show[guessNum][i] = guess.substring(i, i + 1);
          if (guess.substring(i, i + 1).equals(answer.substring(i, i + 1))) {
            show[guessNum + 1][i] = "*";
          } else if (answer.contains(guess.substring(i, i + 1))) {
            show[guessNum + 1][i] = "@";
          } else {
            show[guessNum + 1][i] = "x";
          }
        }
        guessNum += 2;
        printMe(show);
        System.out.print("Guess again: ");
        String guessagain = keyboard.next();
        play(fiveLet, guessagain, keyboard, answer, show, guessNum);

      } else {
        for (int i = 0; i < 5; i++) {
          show[guessNum][i] = guess.substring(i, i + 1);
          if (guess.substring(i, i + 1).equals(answer.substring(i, i + 1))) {
            show[guessNum + 1][i] = "*";
          } else if (answer.contains(guess.substring(i, i + 1))) {
            show[guessNum + 1][i] = "@";
          } else {
            show[guessNum + 1][i] = "x";
          }
        }
        printMe(show);

        System.out.print("Winner, winner chicken dinner! You won on " + ((guessNum / 2) + 1));

      }
    } else if (guessNum == 10) {
      if (!fiveLet.contains(guess)) {
        System.out.println("Please enter a valid 5 letter word:");
        String guessagain = keyboard.next();
        play(fiveLet, guessagain, keyboard, answer, show, guessNum);
      } else {
        for (int i = 0; i < 5; i++) {
          show[guessNum][i] = guess.substring(i, i + 1);
          if (guess.substring(i, i + 1).equals(answer.substring(i, i + 1))) {
            show[guessNum + 1][i] = "*";
          } else if (answer.contains(guess.substring(i, i + 1))) {
            show[guessNum + 1][i] = "@";
          } else {
            show[guessNum + 1][i] = "x";
          }
        }
      }
      guessNum += 2;
      printMe(show);
      if (answer.equals(guess)) {
        System.out.println("Winner, winner chicken dinner! You won on " + guessNum / 2);
      } else {
        System.out.println("Sorry, you didn't get it. The word was: " + answer);

      }
    }
    return show;
  }

  public static void printMe(String[][] a) {
    for (int r = 0; r < a.length; r++) {
      for (int c = 0; c < a[r].length; c++) {

        System.out.print(a[r][c] + "\t\t");

      }
      System.out.println();
    }
  }

  public static ArrayList<String> fiveLetterWords(ArrayList<String> words) {

    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).length() != 5) {
        words.remove(i);
        i--;
      }
    }
    return words;
  }

  /*
   * how can you import all of the words from englishwords to create an
   * array (or arraylist) of words that are exactly 5 letters? Look at the
   * code, work a little, you have all the skills to do this without any more
   * direction from Nelson.
   * 
   * You might not understand each line, but together you should understand
   * enough to figure out what's happening, and how to use this method.
   */
  public static ArrayList<String> importFile(String fileName) {
    ArrayList<String> words = new ArrayList<>();
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        words.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return words;
  }
}