import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

  static ArrayList<Integer> humanPositions = new ArrayList<Integer>();
  static ArrayList<Integer> compPositions = new ArrayList<Integer>();


  public static void main(String[] args) {
    char[][] board = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}
    };

    printBoard(board);

    while (true){
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter your placement: ");
      int humanPlacement = scan.nextInt();

      while(humanPositions.contains(humanPlacement) ||
            compPositions.contains(humanPlacement)){
        System.out.println("Position taken. Try again");
        humanPlacement = scan.nextInt();
      }

      playerPlacement(board, humanPlacement, "human");
      String result = checkWin();
      if(result.length() > 0){
        System.out.println(result);
        break;
      }

      Random rand = new Random();
      int compPlacement = rand.nextInt(9) + 1;
      while(humanPositions.contains(compPlacement) ||
              compPositions.contains(compPlacement)){
        compPlacement = rand.nextInt(9) + 1;
      }
      playerPlacement(board, compPlacement, "computer");

      printBoard(board);

      result = checkWin();
      if(result.length() > 0){
        System.out.println(result);
        break;
      }

      System.out.println(result);
    }

  }//MAIN END


  public static void printBoard(char[][] board){
    for (char[] row : board){
      for (char x : row){
        System.out.print(x);
      }
      System.out.println();
    }
  }

  public static String checkWin(){

    List topRow    = Arrays.asList(1,2,3);
    List midRow    = Arrays.asList(4,5,6);
    List bottomRow = Arrays.asList(7,8,9);
    List leftCol   = Arrays.asList(1,4,7);
    List midCol    = Arrays.asList(2,5,8);
    List rightCol  = Arrays.asList(3,6,9);
    List across1   = Arrays.asList(1,5,9);
    List across2   = Arrays.asList(7,5,3);

    List<List> winning = new ArrayList<List>();
    winning.add(topRow);
    winning.add(midRow);
    winning.add(bottomRow);

    winning.add(leftCol);
    winning.add(midCol);
    winning.add(rightCol);

    winning.add(across1);
    winning.add(across2);

    for (List l : winning){
      if(humanPositions.containsAll(l)){
        return "Hurray! You win!";
      } else if (compPositions.containsAll(l)) {
        return "Too bad. You lost!";
      } else if(humanPositions.size() + compPositions.size() == 9){
        return "Draw!";
      }
    }

    return "";
  }

  public static void playerPlacement(char[][] board, int position, String user){

    char symbol = ' ';

    if(user.equals("human")){
      symbol = 'X';
      humanPositions.add(position);
    }else{
      symbol = 'O';
      compPositions.add(position);
    }

    switch(position){
      case 1:
        board[0][0] = symbol;
        break;
      case 2:
        board[0][2] = symbol;
        break;
      case 3:
        board[0][4] = symbol;
        break;
      case 4:
        board[2][0] = symbol;
        break;
      case 5:
        board[2][2] = symbol;
        break;
      case 6:
        board[2][4] = symbol;
        break;
      case 7:
        board[4][0] = symbol;
        break;
      case 8:
        board[4][2] = symbol;
        break;
      case 9:
        board[4][4] = symbol;
        break;
      default:
        break;
    }
  }
}
