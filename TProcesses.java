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
   int xCoord;
   int yCoord;
   // consider moving the "choice" system to restart back over to Tconsole?
   public void playerComputer(int boardSize){
       TConsole = new TConsole(); 
       char[][] board = new char[boardSize][boardSize];
       int moves = 0;
       fillWithBlanks(board);
       xWinner = false;
       oWinner = false;
       victory = false;
       System.out.println("Human Player will play with X's, Computer with O's");
       while(victory == false){
           humanMove(board, boardSize);
           printBoard(board);
           checkVictoryX(board, xCoord, yCoord, boardSize);
           moves++;
           System.out.println("Computer's Move");
           comMove(board, boardSize);
           printBoard(board);
           checkVictoryO(board, boardSize);
           moves++;
           if(moves >= (boardSize * boardSize))
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               if(TConsole.replayChoice() == 1){
               playerComputer(boardSize);
            }
        }
        }
       if(xWinner = true){
           System.out.println("Human Player Wins!");
           if(TConsole.replayChoice() == 1){
               playerComputer(boardSize);
            }
       }
       if(oWinner = true){
           System.out.println("Computer Victory. Do not resist our synthetic overlords.");
           if(TConsole.replayChoice() == 1){
               playerComputer(boardSize);
            }
       }
        }
 
   public void twoPlayer(int boardSize){
       boardSize = TConsole.boardSize();
       char[][] board = new char[boardSize][boardSize];
       int moves = 0;
       System.out.println("Player 1 is X's, Player 2 is O's");
       fillWithBlanks(board);
       while(victory = false){
           humanMove(board, boardSize);
           checkVictoryX(board, xCoord, yCoord, boardSize);
           printBoard(board);
           moves++;
           humanMove2(board, boardSize);
           printBoard(board);
           checkVictoryO(board, boardSize);
           moves++;
           if(moves >= (boardSize ^ 2))
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               if(TConsole.replayChoice() == 1){
               twoPlayer(boardSize);
            }
       }
       }
       if(xWinner = true){
           System.out.println("Player 1 Wins! Do you wish to restart?");
            if(TConsole.replayChoice() == 1){
               twoPlayer(boardSize);
            }
       }
       if(oWinner = true){
           System.out.println("Player 2 Wins! Do you wish to restart?");
           if(TConsole.replayChoice() == 1){
               twoPlayer(boardSize);
            }
       }
    }
   public void humanMove(char[][] board, int boardSize){
     printBoard(board);
     System.out.println("Human Move, choose a X-coordinate from 0 to "+ (boardSize -1));
     xCoord = userInput.nextInt();
     if(xCoord > boardSize || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove(board, boardSize);
        }
     System.out.println("Choose a Y-Coordinate from 0 to " + (boardSize -1));
     yCoord = userInput.nextInt();
     if(yCoord > boardSize || yCoord < 0){
         System.out.println("Error, no Y-coordinate exists with that value. Remake your move");
         humanMove(board, boardSize);
        }
     if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'X';
        }
     else
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove(board, boardSize);
        }
    }
   public void humanMove2(char[][] board, int boardSize){
     printBoard(board);
     System.out.println("Player 2 Move, choose a X-coordinate from 0 to " + (boardSize -1));
     xCoord = userInput.nextInt();
     if(xCoord > 2 || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove2(board, boardSize);
        }
     System.out.println("Choose a Y-Coordinate from 0 to " + (boardSize -1));
     yCoord = userInput.nextInt();
     if(yCoord > 2 || yCoord < 0){
         System.out.println("Error, no Y-coordinate exists with that value. Remake your move");
         humanMove2(board, boardSize);
        }
     if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'O';
        }
     else
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove2(board, boardSize);
        }
    } 
   public void comMove(char[][] board, int boardSize){
       xCoord = (int) (Math.random() * boardSize);
       yCoord = (int) (Math.random() * boardSize);
       if(board[xCoord][yCoord] == '-'){
        board[xCoord][yCoord] = 'O';
        }
     else
     {
         comMove(board, boardSize);
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
    public void checkVictoryO(char[][] board, int boardSize){
        // column
        for(int i = 0; i < boardSize; i++){
            if(board[xCoord][i] != 'O'){
             break;
            }
            if(i == boardSize-1){
               xWinner = true;
               victory = true;  
            }
        }
        //row
        for(int i = 0; i < boardSize; i++){
            if(board[i][yCoord] != 'O'){
                break;
            }
            if(i == boardSize-1){
            xWinner = true;
            victory = true;
            }
        }
        // diagonal 
        if(xCoord == yCoord){
            for(int i = 0; i < boardSize; i++){
                if(board[i][i] != 'O'){
                  break;
                }
                if(i == boardSize-1){
                  xWinner = true;
                  victory = true;
                }
            }
        }
        // reverse diagonal
        int combo = xCoord + yCoord;
        if(combo == (boardSize - 1)){
            for(int i = 0; i < boardSize; i++){
                if(board[i][(boardSize-1) - i] != 'O')  {
                    break;
                }
                if(i == boardSize-1){
                   xWinner = true;
                   victory = true;
                }
            }
        }
}
    public void checkVictoryX(char[][] board, int xCoord, int yCoord, int boardSize){
        // column
        for(int i = 0; i < boardSize; i++){
            if(board[xCoord][i] != 'X'){
             break;
            }
            if(i == boardSize-1){
               xWinner = true;
               victory = true;  
            }
        }
        //row
        for(int i = 0; i < boardSize; i++){
            if(board[i][yCoord] != 'X'){
                break;
            }
            if(i == boardSize-1){
            xWinner = true;
            victory = true;
            }
        }
        // diagonal 
        if(xCoord == yCoord){
            for(int i = 0; i < boardSize; i++){
                if(board[i][i] != 'X'){
                  break;
                }
                if(i == boardSize-1){
                  xWinner = true;
                  victory = true;
                }
            }
        }
        // reverse diagonal
        int combo = xCoord + yCoord;
        if(combo == (boardSize - 1)){
            for(int i = 0; i < boardSize; i++){
                if(board[i][(boardSize-1) - i] != 'X')  {
                    break;
                }
                if(i == boardSize-1){
                   xWinner = true;
                   victory = true;
                }
            }
        }
}
}
