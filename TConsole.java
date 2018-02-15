import java.util.*;
/**
 * Write a description of class TTTInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TConsole
{
   private Scanner console;
   private tProcesses tProcesses;
   public TConsole(){
       console = new Scanner(System.in);
    }
   public int replayChoice(){
       String choice;
       do{
        System.out.println("Do you wish to replay the game?");
           System.out.println("(1): Restart Game");
               System.out.println("(Q) Quit");
               System.out.println();
               System.out.print("Choice -> ");
               choice = console.next() + " ";;
                if('1' <= choice.charAt(0)){
                    System.out.println();
                    switch (choice.charAt(0)){
                case '1':
                return 1;
            }
        }
    }while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
    return -1;
    }
   public int boardSize(){
       int size = 3;
       do{
          System.out.println("Enter what size of board you would like to play on (n by n)");
          System.out.println();
          System.out.print("Choice -> ");
          size = console.nextInt();
          break;
        }while(size != 0);
       return size;
    }
   public void menu(){
       String choice;
       String print;
       tProcesses = new tProcesses();
       do{
           System.out.println("Welcome to Tic Tac Toe, designed by Jacob Pawlak, APCS Period 3");
           System.out.println("Select a play mode:");
           System.out.println("(1) Player vs. Computer");
           System.out.println("(2) Player vs. Player");
           System.out.println("(Q) Quit");
           System.out.println();
           System.out.print("Choice -> ");
           choice = console.next() + " ";
           if('1' <= choice.charAt(0) && choice.charAt(0) <= '2'){
            System.out.println();
            switch (choice.charAt(0)){
            case '1':
            tProcesses.playerComputer();
            break;
            case '2':
            tProcesses.twoPlayer();
            break;
            }
        }
    }while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
    }
}
