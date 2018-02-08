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
   private TProcesses tProcesses;
   public TConsole(){
       console = new Scanner(System.in);
    }
   public void menu(){
       String choice;
       String print;
       tProcesses = new TProcesses();
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
