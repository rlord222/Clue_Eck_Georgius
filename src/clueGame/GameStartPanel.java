package clueGame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameStartPanel {

	JFrame frame;
	
	public GameStartPanel(Player player) {
		frame= new JFrame();
		JOptionPane.showMessageDialog(frame,"Welcome to our game of Clue. \n"
				+ "You are playing as "+player.getName()+". \n"
				+ "Good luck discovering the murderer hiden among you.");
	}
	
	public static void main(String[] args) throws BadConfigFormatException{
		new GameStartPanel(new HumanPlayer("Test Person","Blue",0,0));
	}
}
