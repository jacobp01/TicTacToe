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
   boolean victory = false;
   boolean xWinner = false;
   boolean oWinner = false;
   String choice;
   public void playerComputer(){
       char[][] board = new char[3][3];
       int moves = 0;
       fillWithBlanks(board);
       System.out.println("Human Player will play with X's, Computer with O's");
       while(victory = false){
           humanMove(board);
           printBoard(board);
           moves++;
           comMove(board);
           printBoard(board);
           moves++;
           checkVictory(board);
           if(moves >= 9)
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
       }
       if(xWinner = true){
           System.out.println("Human Player Wins!");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
       if(oWinner = true){
           System.out.println("Seriously? The computer beat you? Rethink your strategy.");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                playerComputer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
        }

   public void twoPlayer(){
       char[][] board = new char[3][3];
       int moves = 0;
       System.out.println("Player 1 is X's, Player 2 is O's");
       fillWithBlanks(board);
       while(victory = false){
           humanMove(board);
           printBoard(board);
           moves++;
           humanMove2(board);
           printBoard(board);
           moves++;
           checkVictory(board);
           if(moves >= 9)
           {
               System.out.println("The game is a Tie, do you wish to restart?");
               System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
       }
       if(xWinner = true){
           System.out.println("Player 1 Wins! Do you wish to restart?");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
       if(oWinner = true){
           System.out.println("Player 2 Wins! Do you wish to restart?");
            System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               int contChoice = userInput.nextInt();
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                twoPlayer();
                break;
            }
        }
       }  while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
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
     if(board[xCoord][yCoord] != '-')
     {
         System.out.println("Error, that coordinate already has an X or O inside of it");
         System.out.println("Please remake your move");
         humanMove2(board);
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
    public void checkVictory(char[][] board){
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
        
        for(i = 0; i < n; i++){
          for(int y = 0; y < n; y++){
            if(board[i][y] != 'X')
                break;
            if(i == n-1){
               victory = true;
               xWinner = true;
            }
        }
    }
        for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
        if(x == y){
            for(i = 0; i < n; i++){
                if(board[i][i] != 'X')
                    break;
                if(i == n-1){
                  victory = true;
                  xWinner = true;
                }
            }
        }
    }
}
    for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
        if(x + y == n - 1){
            for(i = 0; i < n; i++){
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
}