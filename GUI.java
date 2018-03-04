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
    mnuComputer = new JMenuItem(" Computer Move"),
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
    final int X = 1000, Y = 600, color = 190; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean btnEmptyClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Papyrus", Font.BOLD, 100);
    private int remainingMoves = 1;
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
        mnuMain.add(mnuComputer);
        mnuComputer.setFont(new Font("Papyrus", Font.BOLD,18));
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
        mnuComputer.addActionListener(this);
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
    public void comMove(){

    }
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
        else if(source == mnuComputer){
            if(inGame){
                int currentMove = -1;
                int currentMove2 = -1;
                int lCount = 0;
                int mCount = 0;
                // rows
                for(int x = 0; x < btnEmpty.length; x++){
                    lCount = 0;
                    mCount = 0;
                    for(int y = 0; y < btnEmpty[0].length; y++){
                        if(btnEmpty[x][y].getText().equals("L")){
                            lCount++;
                            if(lCount == (boardSize - 1)){
                                for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[x][z].getText().equals("")){
                                        currentMove = x;
                                        currentMove2 = z;
                                    }
                                }
                            }
                        }
                        if(btnEmpty[x][y].getText().equals("M")){
                            mCount++;
                            if(mCount == boardSize - 1){
                                currentMove = x;
                                currentMove2 = y;
                            }
                        }
                    }
                }
                // columns
                for(int y = 0; y < btnEmpty.length; y++){
                    lCount = 0;
                    mCount = 0;
                    for(int x = 0; x < btnEmpty[0].length; x++){
                        if(btnEmpty[x][y].getText().equals("L")){
                            lCount++;
                            if(lCount == (boardSize - 1)){
                                for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][y].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = y;
                                    }
                                }
                            }
                        }
                        if(btnEmpty[x][y].getText().equals("M")){
                            mCount++;
                            if(mCount == boardSize - 1){
                                for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][y].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = y;
                                    }
                                }
                            }
                        }
                    }
                }
                // diagnol normal
                lCount = 0;
                mCount = 0;
                for(int x = 0; x < btnEmpty.length; x++){
                    if(btnEmpty[x][x].getText().equals("L")){
                        lCount++;
                        if(lCount == (boardSize - 1)){
                          for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][z].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = z;
                                    }
                                }  
                        }
                    }
                    if(btnEmpty[x][x].getText().equals("M")){
                        mCount++;
                        if(mCount == boardSize - 1){
                            for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][z].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = z;
                                    }
                                }  
                        }
                    }
                }
                // diagnol reverse
                lCount = 0;
                mCount = 0;
                for(int x = 0; x < btnEmpty.length; x++){
                    if(btnEmpty[x][(boardSize - 1) - x].getText().equals("L")){
                        lCount++;
                        if(lCount == (boardSize)){
                            for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][((boardSize - 1) - z)].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = ((boardSize - 1) - z);
                                    }
                                }
                        }
                    }
                    if(btnEmpty[x][(boardSize - 1) - x].getText().equals("M")){
                        mCount++;
                        if(mCount == (boardSize - 1)){
                            for(int z = 0; z < boardSize; z++){
                                    if(btnEmpty[z][((boardSize - 1) - z)].getText().equals("")){
                                        currentMove = z;
                                        currentMove2 = ((boardSize - 1) - z);
                                    }
                                }
                        }
                    }
                }
                // if last turn make sure it doesn't infinite loop
                if(currentMove == -1){
                    currentMove = (int)(Math.random() * boardSize);
                    currentMove2 = (int)(Math.random() * boardSize);
                }
                while(!btnEmpty[currentMove][currentMove2].getText().equals("")){
                    currentMove = (int)(Math.random() * boardSize);
                    currentMove2 = (int)(Math.random() * boardSize);
                }

                BusinessLogic.GetMove(currentMove, currentMove2, remainingMoves, font, 
                    btnEmpty, startingPlayer);              
                btnEmpty[currentMove][currentMove2].setEnabled(false);
                pnlPlayingField.requestFocus();
                ++remainingMoves;

            }

            else{
                JOptionPane.showMessageDialog(null, "Cannot computer move "+
                    "You are not in a game "+
                    "U BREAK MY CODE", "PLZ GET IN GAME", JOptionPane.INFORMATION_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            CheckWin();
        }

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

    /* private void comMove(){
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
    if(btnEmpty[xCoord][yCoord] == ""){
    btnEmpty[xCoord][yCoord] = 'O';
    }
    else
    {
    comMove(board, boardSize);
    }
    }
    }
     */    
    private void CheckWin() 
    {   
        int lCount = 0;
        int mCount = 0;
        // rows
        for(int x = 0; x < btnEmpty.length; x++){
            lCount = 0;
            mCount = 0;
            for(int y = 0; y < btnEmpty[0].length; y++){
                if(btnEmpty[x][y].getText().equals("L")){
                    lCount++;
                    if(lCount == (boardSize)){
                        message = "      L has won!";
                        JOptionPane.showMessageDialog(null, message, "Congrats!", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(btnEmpty[x][y].getText().equals("M")){
                    mCount++;
                    if(mCount == boardSize){
                        message = "      M has won!";
                        JOptionPane.showMessageDialog(null, message, "Congrats!", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        // columns
        for(int y = 0; y < btnEmpty.length; y++){
            lCount = 0;
            mCount = 0;
            for(int x = 0; x < btnEmpty[0].length; x++){
                if(btnEmpty[x][y].getText().equals("L")){
                    lCount++;
                    if(lCount == (boardSize)){
                        message = "      L has won!";
                        JOptionPane.showMessageDialog(null, message, "Congrats!", 
                            JOptionPane.INFORMATION_MESSAGE);
                        inGame = false;
                        startingPlayer = "";
                    }
                }
                if(btnEmpty[x][y].getText().equals("M")){
                    mCount++;
                    if(mCount == boardSize){
                        message = "      M has won!";
                        JOptionPane.showMessageDialog(null, message, "Congrats!", 
                            JOptionPane.INFORMATION_MESSAGE);
                        inGame = false;
                        startingPlayer = "";
                    }
                }
            }
        }
        // diagnol normal
        lCount = 0;
        mCount = 0;
        for(int x = 0; x < btnEmpty.length; x++){
            if(btnEmpty[x][x].getText().equals("L")){
                lCount++;
                if(lCount == (boardSize)){
                    message = "      L has won!";
                    JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
                    inGame = false;
                    startingPlayer = "";
                }
            }
            if(btnEmpty[x][x].getText().equals("M")){
                mCount++;
                if(mCount == boardSize){
                    message = "      M has won!";
                    JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
                    inGame = false;
                    startingPlayer = "";
                }
            }
        }
        // diagnol reverse
        lCount = 0;
        mCount = 0;
        for(int x = 0; x < btnEmpty.length; x++){
            if(btnEmpty[x][(boardSize - 1) - x].getText().equals("L")){
                lCount++;
                if(lCount == (boardSize)){
                    message = "      L has won!";
                    JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
                    inGame = false;
                    startingPlayer = "";
                }
            }
            if(btnEmpty[x][(boardSize - 1) - x].getText().equals("M")){
                mCount++;
                if(mCount == boardSize){
                    message = "      M has won!";
                    JOptionPane.showMessageDialog(null, message, "Congrats!", 
                        JOptionPane.INFORMATION_MESSAGE);
                    inGame = false;
                    startingPlayer = "";
                }
            }
        }

    }
}   