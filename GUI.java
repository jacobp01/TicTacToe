// ============================================================================
//     Taken From: http://programmingnotes.org/
// ============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// CONVERT ALL btnEmpty[] to btnEmpty[][] for 2d
public class GUI extends JFrame implements ActionListener
{
    int boardSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Board Size (n x n):"));
    // setting up ALL the variables
    JFrame window = new JFrame("Jacob's Tic Tac Toe Game");

    JMenuBar mnuMain = new JMenuBar();
    JMenuItem   mnuNewGame = new JMenuItem("  New Game"), 
    mnuGameTitle = new JMenuItem("|Tic Tac Toe| "),
    mnuStartingPlayer = new JMenuItem(" Starting Player"),
   // mnuBoardSize = new JMenuItem(" Board Size"),
    mnuExit = new JMenuItem("    Quit");

    JButton btnEmpty[][] = new JButton[boardSize][boardSize];

    JPanel  pnlNewGame = new JPanel(),
    pnlNorth = new JPanel(),
    pnlSouth = new JPanel(),
    pnlTop = new JPanel(),
    pnlBottom = new JPanel(),
    pnlPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();

    int[][] board = new int[boardSize][boardSize];
    private JRadioButton SelectX = new JRadioButton("User Plays L", false);
    private  JRadioButton SelectO = new JRadioButton("User Plays M", false);
    private ButtonGroup radioGroup;
    private  String startingPlayer= "";
    final int X = 800, Y = 480, color = 190; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean btnEmptyClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Papyrus", Font.BOLD, 100);
    private int remainingMoves = 1;
    final int winCombo[][] = new int[][]        {
                {1, 2, 3},                        {1, 4, 7},                {1, 5, 9},
                {4, 5, 6},                        {2, 5, 8},                {3, 5, 7},
                {7, 8, 9},                        {3, 6, 9}
            };
    int wonNumber1 = 1, wonNumber2 = 1, wonNumber3 = 1;
    //===============================  GUI  ========================================//
    public GUI() //This is the constructor
    {
        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNorth.setBackground(new Color(0, 0, 90));
        pnlSouth.setBackground(new Color(color, color, color));

        pnlTop.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));

        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));

        // adding menu items to menu bar
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setEnabled(false);
        mnuGameTitle.setFont(new Font("Papyrus",Font.BOLD,18));

        mnuMain.add(mnuNewGame);
        mnuNewGame.setFont(new Font("Papyrus",Font.BOLD,18));
        //mnuMain.add(mnuBoardSize);
      //  mnuBoardSize.setFont(new Font("Papyrus",Font.BOLD,18));
        mnuMain.add(mnuStartingPlayer);
        mnuStartingPlayer.setFont(new Font("Papyrus",Font.BOLD,18));
        mnuMain.add(mnuExit);
        mnuExit.setFont(new Font("Papyrus",Font.BOLD,18));//---->Menu Bar Complete

        // adding X & O options to menu
        SelectX.setFont(new Font("Papyrus",Font.BOLD,18));
        SelectO.setFont(new Font("Papyrus",Font.BOLD,18));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(SelectX); // add plain to group
        radioGroup.add(SelectO);
        radioPanel.add(SelectX);
        radioPanel.add(SelectO);

        // adding Action Listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuStartingPlayer.addActionListener(this);
       //mnuBoardSize.addActionListener(this);

        // setting up the playing field
        pnlPlayingField.setLayout(new GridLayout(boardSize, boardSize, 2, 2));
        pnlPlayingField.setBackground(Color.blue);
        for(int x = 0; x < boardSize; x++)   
        {
            for(int y = 0; y < boardSize; y++){
            btnEmpty[x][y] = new JButton();
            btnEmpty[x][y].setBackground(Color.white);
            btnEmpty[x][y].addActionListener(this);
            pnlPlayingField.add(btnEmpty[x][y]);
            btnEmpty[x][y].setEnabled(setTableEnabled);
           }
        }

        // adding everything needed to pnlNorth and pnlSouth
        pnlNorth.add(mnuMain);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);

        // adding to window and Showing window
        window.add(pnlNorth, BorderLayout.NORTH);
        window.add(pnlSouth, BorderLayout.CENTER);
        window.setVisible(true);
        
    }// End GUI

    // ===========  Start Action Performed  ===============//
    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user
        Object source = click.getSource();

        // check if a button was clicked on the gameboard
        for(int currentMove = 0; currentMove < boardSize; ++currentMove) 
        {
            for(int currentMove2 = 0; currentMove2 < boardSize; currentMove2++){
            if(source == btnEmpty[currentMove][currentMove2] && remainingMoves < ((boardSize * boardSize)+ 1))  
            {
                btnEmptyClicked = true;
                BusinessLogic.GetMove(currentMove, currentMove2, remainingMoves, font, 
                    btnEmpty, startingPlayer);              
                btnEmpty[currentMove][currentMove2].setEnabled(false);
                pnlPlayingField.requestFocus();
                ++remainingMoves;
            }
        }
    }

        // if a button was clicked on the gameboard, check for a winner
        if(btnEmptyClicked) 
        {
            inGame = true;
            CheckWin();
            btnEmptyClicked = false;
        }

        // check if the user clicks on a menu item
        if(source == mnuNewGame)    
        {
            System.out.println(startingPlayer);
            BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                pnlPlayingField,pnlBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " your current game will be lost..." + "n" +"Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    RedrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == mnuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
       /* else if(source == mnuBoardSize){
            if(inGame){
                JOptionPane.showMessageDialog(null, "Cannot select a new board size "+
                    "at this time.Finish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else{
                 boardSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Board Size (n x n):"));
                 setTableEnabled = false;
                 RedrawGameBoard();
            }
       }
       */
        // select X or O player 
        else if(source == mnuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                setTableEnabled = true;
                BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                    pnlPlayingField,pnlBottom,radioPanel);

                SelectX.addActionListener(new RadioListener());
                SelectO.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(SelectX);
                radioPanel.add(SelectO);
                pnlSouth.setLayout(new GridLayout(boardSize, 1, 2, 1));
                pnlSouth.add(radioPanel);
                pnlSouth.add(pnlBottom);
            }
        }
        pnlSouth.setVisible(false); 
        pnlSouth.setVisible(true);  
    }// End Action Performed

    // ===========  Start RadioListener  ===============//  
    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton)event.getSource();
            if(theButton.getText().equals("User Plays L")) 
            {
                startingPlayer = "L";
            }
            if(theButton.getText().equals("User Plays M"))
            {
                startingPlayer = "M";
            }

            // redisplay the gameboard to the screen
            pnlSouth.setVisible(false); 
            pnlSouth.setVisible(true);          
            RedrawGameBoard();
        }
    }// End RadioListener
    /*
    ----------------------------------
    Start of all the other methods. |
    ----------------------------------
     */
    private void RedrawGameBoard()  
    {
        BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
            pnlPlayingField,pnlBottom,radioPanel);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);       

        remainingMoves = 1;

        for(int x=0; x < (boardSize); ++x)
        for(int y=0; y < boardSize; y++){
        {
            btnEmpty[x][y].setText("");
            btnEmpty[x][y].setEnabled(setTableEnabled);
        }
       }
        win = false;        
    }

    private void CheckWin() 
    {   
        int lCount = 0;
        int mCount = 0;
         // column
        for(int x = 0; x < btnEmpty.length; x++){
            lCount = 0;
            mCount = 0;
        for(int y = 0; y < btnEmpty[0].length; y++){
         if(btnEmpty[x][y].getText().equals("L")){
             lCount++;
             System.out.println("What's up" + lCount);
              System.out.println("BOARDSIZE IS THIS ONE:  " + boardSize);
             if(lCount == (boardSize)){
                 System.out.println("BOARDSIZE IS THIS ONE:  " + boardSize);
               message = "      L has won!";
                JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
              }
            }
         if(btnEmpty[x][y].getText().equals("M")){
             mCount++;
             System.out.println("donger" + mCount);
             if(mCount == boardSize){
                 message = "      M has won!";
                  JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
              }
            }
       }
     }
          
        // OLD CHECKWIN BELOW
      /* for(int x=0; x < ((boardSize * boardSize) - 1); ++x)    
        {
            if(!btnEmpty[winCombo[x][0]].getText().equals("") &&
                    btnEmpty[winCombo[x][0]].getText().equals(btnEmpty[winCombo[x][1]].getText()) &&
                    btnEmpty[winCombo[x][1]].getText().equals(btnEmpty[winCombo[x][2]].getText())
                )   
            {
                win = true;
                wonNumber1 = winCombo[x][0];
                wonNumber2 = winCombo[x][1];
                wonNumber3 = winCombo[x][2];
                btnEmpty[wonNumber1].setBackground(Color.white);
                btnEmpty[wonNumber2].setBackground(Color.white);
                btnEmpty[wonNumber3].setBackground(Color.white);
                break;
            }
        }
        if(win || (!win && remainingMoves > (boardSize * boardSize))) 
        {
            if(win) 
            {
                if(startingPlayer.equals("L"))
                {
                    if(remainingMoves % 2 == 0)
                        message = "      L has won!";
                    else
                        message = "      M has won!";
                }
                else
                {
                    if(remainingMoves % 2 == 0)
                        message = "      M has won!";
                    else    
                        message = "      L has won!";
                }
                JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);               
            }   
            else if(!win && remainingMoves > (boardSize * boardSize)) 
            {
                message = "Both players have tied!";
                JOptionPane.showMessageDialog(null, message, "Tie Game!", 
                        JOptionPane.WARNING_MESSAGE);
            }
            for(int x=1; x <= (boardSize * boardSize); ++x)   
            {
                btnEmpty[x].setEnabled(false);
            }
            win = false;
            inGame = false;
            startingPlayer = "";            
        }
  */
}
}   