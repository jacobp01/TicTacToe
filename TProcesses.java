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
       // Find move that the CPU can make to win - Highest Priority
       // Find move to block a player win - Next Priority
       // Make random move - Lowest Priority
       int[] humanCoord = canWin(board, boardSize);
       int[] comCoord = canComWin(board, boardSize);
       if(humanCoord[0] != -1){
          xCoord = humanCoord[0];
          yCoord = humanCoord[1];
          board[xCoord][yCoord] = 'O';
       }
       
       else if(comCoord[0] != -1){
          xCoord = comCoord[0];
          yCoord = comCoord[1];
          board[xCoord][yCoord] = 'O';
           }
       else{
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
}
   public int[] canWin(char[][] board, int boardSize){
      // checks if Human Player can win, returns coordinates to make move, -1 if not
      // needs to check that the space is open
      int[] coord = new int[2];
      int xCom = -1;
      int yCom = -1;
      // check for if the human can win, then block it
      // vertical win
      // NOTE: Not Tested
      // NOTE: only works if the X player is going from top to bottom, doesn't work for bot to top
      // or if they go bot, top, then randomly through middle
      for(int i = 0; i < boardSize; i++){
            if(board[xCoord][i] != 'X'){
             break;
            }
            if(i == boardSize-2){
               xCom = i;
               yCom = i;
            }
        }
      // horizonal win
      // diag
      // reverse diag
      coord[0] = xCom;
      coord[1] = yCom;
      return coord;
    }
   public int[] canComWin(char[][] board, int boardSize){
       // checks if Computer player can win returns coordinates to make move, -1 if not
       // needs to check that the space is open
       int[] coord = new int[2];
       int xCom = -1;
       int yCom = -1;
       // check for if the computer can win
       
       coord[0] = xCom;
       coord[1] = yCom;
       return coord;
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
               oWinner = true;
               victory = true;  
            }
        }
        //row
        for(int i = 0; i < boardSize; i++){
            if(board[i][yCoord] != 'O'){
                break;
            }
            if(i == boardSize-1){
            oWinner = true;
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
                  oWinner = true;
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
                   oWinner = true;
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
