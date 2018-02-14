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
   public tProcesses(){
       console = new Scanner(System.in);
    }
   public void playerComputer(){
          String Achoice;
          String print;
          TConsole = new TConsole(); 
       char[][] board = new char[3][3];
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
           comMove(board);
           printBoard(board);
           checkVictoryO(board);
           moves++;
           if(moves >= 9)
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               Achoice = console.next() + " ";
                if('1' <= Achoice.charAt(0)){
                    System.out.println();
                    switch (Achoice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }
       }
       if(xWinner = true){
           System.out.println("Human Player Wins!");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               Achoice = console.next() + " ";
                if('1' <= Achoice.charAt(0)){
                    System.out.println();
                    switch (Achoice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }
       if(oWinner = true){
           System.out.println("Seriously? The computer beat you? Rethink your strategy.");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               Achoice = console.next() + " ";;
                if('1' <= Achoice.charAt(0)){
                    System.out.println();
                    switch (Achoice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }
        }
 
   public void twoPlayer(){
          String choice;
          String print;
       char[][] board = new char[3][3];
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
           if(moves >= 9)
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               choice = console.next() + " ";
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }
       }
       if(xWinner = true){
           System.out.println("Player 1 Wins! Do you wish to restart?");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               choice = console.next() + " ";
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }
       if(oWinner = true){
           System.out.println("Player 2 Wins! Do you wish to restart?");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               choice = console.next() + " ";
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }
    }
   public void humanMove(char[][] board){
     printBoard(board);
     System.out.println("Human Move, choose a X-coordinate (from 0 to 2) to place your X");
     int xCoord;
     int yCoord;
     xCoord = userInput.nextInt();
     if(xCoord > 2 || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove(board);
        }
     System.out.println("Choose a Y-Coordinate from 0 to 2");
     yCoord = userInput.nextInt();
     if(yCoord > 2 || yCoord < 0){
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
     System.out.println("Player 2 Move, choose a X-coordinate (from 0 to 2) to place your O");
     int xCoord = userInput.nextInt();
     if(xCoord > 2 || xCoord < 0){
         System.out.println("Error, no X-coordinate exists with that value. Remake your move");
         humanMove2(board);
        }
     System.out.println("Choose a Y-Coordinate from 0 to 2");
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
       System.out.println("Computer's Move");
       int xCoord = (int) (Math.random() * 3);
       int yCoord = (int) (Math.random() * 3);
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
        int n = 3;
         for(int i = 0; i < n; i++){
            for(int x = 0; x < n; x++){
            if(board[x][i] != 'O')
                break;
            if(i == n-1){
               victory = true;
               oWinner = true;
            }
        }
    }
        
        for(int i = 0; i < n; i++){
          for(int y = 0; y < n; y++){
            if(board[i][y] != 'O')
                break;
            if(i == n-1){
               victory = true;
               oWinner = true;
            }
        }
    }

            for(int i = 0; i < n; i++){
                if(board[i][i] != 'O')
                    break;
                if(i == n-1){
                  victory = true;
                  oWinner = true;
                }
            }
    for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
        if(x + y == n - 1){
            for(int i = 0; i < n; i++){
                if(board[i][(n-1)-i] != 'O')
                    break;
                if(i == n-1){
                  victory = true;
                  oWinner = true;
                }
            }
        }
}
}
}
    public void checkVictoryX(char[][] board){
        int n = 3;
         for(int i = 0; i < n; i++){
            for(int x = 0; x < n; x++){
            if(board[x][i] != 'X')
                break;
            if(i == n-1){
               victory = true;
               xWinner = true;
            }
        }
    }
        
        for(int i = 0; i < n; i++){
          for(int y = 0; y < n; y++){
            if(board[i][y] != 'X')
                break;
            if(i == n-1){
               victory = true;
               xWinner = true;
            }
        }
    }

            for(int i = 0; i < n; i++){
                if(board[i][i] != 'X')
                    break;
                if(i == n-1){
                  victory = true;
                  xWinner = true;
                }
            }
        
    for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
        if(x + y == n - 1){
            for(int i = 0; i < n; i++){
                if(board[i][(n-1)-i] != 'X')
                    break;
                if(i == n-1){
                  victory = true;
                  xWinner = true;
                }
            }
        }
}
}
}
}
