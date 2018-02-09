import java.util.*;
/**
 * Write a description of class TProcesses here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TProcesses
{
   Scanner userInput = new Scanner(System.in);
   public void playerComputer(){
       char[][] board = new char[3][3];
       int moves = 0;
       boolean victory = false;
       fillWithBlanks(board);
       System.out.println("Human Player will play with X's, Computer with O's");
       while(victory = false){
       humanMove(board);
       printBoard(board);
       moves++;
       comMove(board);
       printBoard(board);
       moves++;
    }
    }
   public void twoPlayer(){
       char[][] board = new char[3][3];
       System.out.println("Player 1 is X's, Player 2 is O's");
       
    }
   public void humanMove(char[][] board){
     printBoard(board);
     System.out.println("Human Move, choose a X-coordinate (from 0 to 2) to place your X");
     int xCoord = userInput.nextInt();
     if(xCoord > 2 || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove(board);
        }
     System.out.println("Choose a Y-Coordinate from 0 to 2");
     int yCoord = userInput.nextInt();
     if(yCoord > 2 || yCoord < 0){
         System.out.println("Error, no Y-coordinate exists with that value. Remake your move");
         humanMove(board);
        }
     if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'X';
        }
     if(board[xCoord][yCoord] != '-')
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove(board);
        }
    }
   public void comMove(char[][] board){
       System.out.println("Computer's Move");
       int xCoord = (int) Math.random() * 3;
       int yCoord = (int) Math.random() * 3;
       if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'O';
        }
     if(board[xCoord][yCoord] != '-')
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
}
