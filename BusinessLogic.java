// ============================================================================
//     Taken From: http://programmingnotes.org/
// ============================================================================
import javax.swing.*;
import java.awt.*;
 
public class BusinessLogic
{
	public static void GetMove(int currentMove, int  currentMove2, int remainingMoves, Font font, JButton btnEmpty[][], 
			String startingPlayer)
	{// gets the current move "X" or "O" for the user & displays to screen
		btnEmpty[currentMove][currentMove2].setFont(font);
 
		if(startingPlayer.equals("L"))
		{
			if(remainingMoves % 2 != 0)
			{				
				btnEmpty[currentMove][currentMove2].setText("L");
			}
			else
			{
				btnEmpty[currentMove][currentMove2].setText("M");
			}
		}
		else
		{
			if(remainingMoves % 2 != 0)
			{
				btnEmpty[currentMove][currentMove2].setText("M");
			}
			else
			{
				btnEmpty[currentMove][currentMove2].setText("L");
			}
		}
	}// End of GetMove
	
	public static void ShowGame(JPanel pnlSouth, JPanel pnlPlayingField)
	{// shows the Playing Field
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.add(pnlPlayingField, BorderLayout.CENTER);
		pnlPlayingField.requestFocus();	
	}// End of ShowGame
	
	public static void ClearPanelSouth(JPanel pnlSouth, JPanel pnlTop, 
		 JPanel pnlNewGame, JPanel pnlPlayingField, JPanel pnlBottom, JPanel radioPanel)	
	{// clears any posible panels on screen
		pnlSouth.remove(pnlTop); 
		pnlSouth.remove(pnlBottom);
		pnlSouth.remove(pnlPlayingField);
		pnlTop.remove(pnlNewGame);
		pnlSouth.remove(radioPanel);
	}//	End of ClearPanelSouth 
}