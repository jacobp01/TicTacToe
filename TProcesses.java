import java.util.*;
/**
 * Write a description of class TProcesses here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tProcesses
{
   private Scanner console;
   private TConsole TConsole;
   Scanner userInput = new Scanner(System.in);
   boolean victory = false;
   boolean xWinner = false;
   boolean oWinner = false;
   int boardSize = 3;
   // consider moving the "choice" system to restart back over to Tconsole?
   public void playerComputer(){
       TConsole = new TConsole(); 
       boardSize = TConsole.boardSize();
       char[][] board = new char[boardSize][boardSize];
       int moves = 0;
       fillWithBlanks(board);
       xWinner = false;
       oWinner = false;
       victory = false;
       System.out.println("Human Player will play with X's, Computer with O's");
       while(victory == false){
           humanMove(board);
           printBoard(board);
           checkVictoryX(board);
           moves++;
           System.out.println("Computer's Move");
           comMove(board);
           printBoard(board);
           checkVictoryO(board);
           moves++;
           if(moves >= (boardSize ^ 2))
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               if(TConsole.replayChoice() == 1){
               playerComputer();
            }
        }
        }
       if(xWinner = true){
           System.out.println("Human Player Wins!");
           if(TConsole.replayChoice() == 1){
               playerComputer();
            }
       }
       if(oWinner = true){
           System.out.println("Computer Victory. Do not resist our synthetic overlords.");
           if(TConsole.replayChoice() == 1){
               playerComputer();
            }
       }
        }
 
   public void twoPlayer(){
       boardSize = TConsole.boardSize();
       char[][] board = new char[boardSize][boardSize];
       int moves = 0;
       System.out.println("Player 1 is X's, Player 2 is O's");
       fillWithBlanks(board);
       while(victory = false){
           humanMove(board);
           checkVictoryX(board);
           printBoard(board);
           moves++;
           humanMove2(board);
           printBoard(board);
           checkVictoryO(board);
           moves++;
           if(moves >= (boardSize ^ 2))
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               if(TConsole.replayChoice() == 1){
               twoPlayer();
            }
       }
       }
       if(xWinner = true){
           System.out.println("Player 1 Wins! Do you wish to restart?");
            if(TConsole.replayChoice() == 1){
               twoPlayer();
            }
       }
       if(oWinner = true){
           System.out.println("Player 2 Wins! Do you wish to restart?");
           if(TConsole.replayChoice() == 1){
               twoPlayer();
            }
       }
    }
   public void humanMove(char[][] board){
     printBoard(board);
     System.out.println("Human Move, choose a X-coordinate from 0 to "+ (boardSize -1));
     int xCoord;
     int yCoord;
     xCoord = userInput.nextInt();
     if(xCoord > boardSize || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove(board);
        }
     System.out.println("Choose a Y-Coordinate from 0 to " + (boardSize -1));
     yCoord = userInput.nextInt();
     if(yCoord > boardSize || yCoord < 0){
         System.out.println("Error, no Y-coordinate exists with that value. Remake your move");
         humanMove(board);
        }
     if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'X';
        }
     else
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove(board);
        }
    }
   public void humanMove2(char[][] board){
     printBoard(board);
     System.out.println("Player 2 Move, choose a X-coordinate from 0 to " + (boardSize -1));
     int xCoord = userInput.nextInt();
     if(xCoord > 2 || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove2(board);
        }
     System.out.println("Choose a Y-Coordinate from 0 to " + (boardSize -1));
     int yCoord = userInput.nextInt();
     if(yCoord > 2 || yCoord < 0){
         System.out.println("Error, no Y-coordinate exists with that value. Remake your move");
         humanMove2(board);
        }
     if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'O';
        }
     else
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove2(board);
        }
    } 
   public void comMove(char[][] board){
       int xCoord = (int) (Math.random() * boardSize);
       int yCoord = (int) (Math.random() * boardSize);
       if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'O';
        }
     else
     {
         comMove(board);
        }
    }
   public void fillWithBlanks(char[][] board){
              for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++){
             board[row][col] = '-';
            }
        }
    }
   public void printBoard(char[][] board){
        for(int row = 0; row < board.length; row++)
        {
        System.out.println();
            for(int col = 0; col < board[0].length; col++){
            System.out.print(board[row][col]);
        }
    }
    System.out.println();
}
    public void checkVictoryO(char[][] board){
        char a = board[0][0];
        char b = board[0][1];
        char c = board[0][2];
        char d = board[1][0];
        char e = board[1][1];
        char f = board[1][2];
        char g = board[2][0];
        char h = board[2][1];
        char i = board[2][2];
        if(a != '-'){
            if(a == b && b == c){
                oWinner = true;
                victory = true;
            }
            if(a == d && d == g){
                oWinner = true;
                victory = true;
            }
            if(a == e && e == i){
                oWinner = true;
                victory = true;
            }
        }
        if(i != '-'){
            if(i == h && h == g){
                oWinner = true;
                victory = true;
            }
            if(i == e && e == a){
                oWinner = true;
                victory = true;
            }
            if(i == f && f == c){
                oWinner = true;
                victory = true;
            }
        }
        if(e != '-'){
            if(b == e && e == h){
                oWinner = true;
                victory = true;
            }
            if(d == e && e == f){
                oWinner = true;
                victory = true;
            }
        }
}
    public void checkVictoryX(char[][] board){
        char a = board[0][0];
        char b = board[0][1];
        char c = board[0][2];
        char d = board[1][0];
        char e = board[1][1];
        char f = board[1][2];
        char g = board[2][0];
        char h = board[2][1];
        char i = board[2][2];
        if(a != '-'){
            if(a == b && b == c){
                xWinner = true;
                victory = true;
            }
            if(a == d && d == g){
                xWinner = true;
                victory = true;
            }
            if(a == e && e == i){
                xWinner = true;
                victory = true;
            }
        }
        if(i != '-'){
            if(i == h && h == g){
                xWinner = true;
                victory = true;
            }
            if(i == e && e == a){
                xWinner = true;
                victory = true;
            }
            if(i == f && f == c){
                xWinner = true;
                victory = true;
            }
        }
        if(e != '-'){
            if(b == e && e == h){
                xWinner = true;
                victory = true;
            }
            if(d == e && e == f){
                xWinner = true;
                victory = true;
            }
        }
}
}
