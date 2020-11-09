package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GameControlPanel extends JPanel{
	
	private static int CONTROL_PANEL_WIDTH = 750;
	private static int CONTROL_PANEL_HEIGHT = 180;
	
	private static final String ACCUSE_BUTTON_TEXT = "Make Accusation";
	private static final String NEXT_TURN_BUTTON_TEXT = "NEXT!";
	
	private static Board board;
	
	private static JTextField turnText, rollText;
	private static JPanel turnPanel;
	
	private static String diceRoll;
	private static Player nextPlayer;

	public GameControlPanel() {	
		//This will be removed when we integrate this.
		//probably by just calling get instance though if this is in the board class we won't have to waste the memory.
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		 
		board.initialize();
		
		diceRoll = Integer.toString(board.rollDie());
		nextPlayer = board.getNextPlayer();
		CreateLayout();
	}
	
	private void CreateLayout() {
		
		GridLayout controlPanelLayout = new GridLayout(2,1);
		this.setLayout(controlPanelLayout);
		
		// turn information section
		turnPanel = new JPanel();
		JLabel turnLabel = new JLabel("Whose turn?");
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		turnText = new JTextField(nextPlayer.getName());
		turnText.setBackground(nextPlayer.getColor());
		
		GridLayout turnPanelLayout = new GridLayout(2,1);
		turnPanel.setLayout(turnPanelLayout);
		turnPanel.add(turnLabel);
		turnPanel.add(turnText);
		
		// roll information section
		JLabel rollLabel = new JLabel("Roll:");
		rollText = new JTextField(diceRoll, 6);
		JPanel rollPanel = new JPanel();
		rollPanel.add(rollLabel);
		rollPanel.add(rollText);
		
		// buttons to make accusation and move to the next turn
		JButton accuseButton = new JButton(ACCUSE_BUTTON_TEXT);
		JButton nextTurnButton = new JButton(NEXT_TURN_BUTTON_TEXT);
		nextTurnButton.addActionListener(new nextTurnListener());
		
		// upper half of the control panel
		JPanel upperSubPanel = new JPanel();
		GridLayout upperLayout = new GridLayout(1,4);
		upperSubPanel.setLayout(upperLayout);
		upperSubPanel.add(turnPanel);
		upperSubPanel.add(rollPanel);
		upperSubPanel.add(accuseButton);
		upperSubPanel.add(nextTurnButton);
		this.add(upperSubPanel, BorderLayout.NORTH);
		
		//Guess panel. It's going to be blank for now
		JPanel guessPanel = new JPanel();
		Border guessPanelBorder = BorderFactory.createTitledBorder("guess");
		guessPanel.setBorder(guessPanelBorder);
		GridLayout guessPanelLayout = new GridLayout(1,0);
		guessPanel.setLayout(guessPanelLayout);
		guessPanel.add(new JTextField("not yet implemented"));
		
		//Guess Result panel. Nothing much is happening here either.
		JPanel resultPanel = new JPanel();
		Border resultPanelBorder = BorderFactory.createTitledBorder("result");
		resultPanel.setBorder(resultPanelBorder);
		GridLayout resultPanelLayout = new GridLayout(1,0);
		resultPanel.setLayout(resultPanelLayout);
		resultPanel.add(new JTextField("not yet implemented"));
		
		// lower half of the control panel
		JPanel lowerSubPanel = new JPanel();
		GridLayout lowerLayout = new GridLayout(1,2);
		lowerSubPanel.setLayout(lowerLayout);
		lowerSubPanel.add(guessPanel);
		lowerSubPanel.add(resultPanel);
		add(lowerSubPanel,BorderLayout.CENTER);
	}
	
	private class nextTurnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Doesn't actualy do anything but the diceRoll is stored for now incase we need it.
			diceRoll = Integer.toString(board.rollDie());
			nextPlayer = board.getNextPlayer();
			rollText.setText(diceRoll);
			turnText.setText(nextPlayer.getName());
			turnPanel.setBackground(nextPlayer.getColor());
		}
		
	}
	
	public static void main(String[] args) {
		
		GameControlPanel controlPanel = new GameControlPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(controlPanel);
		frame.setSize(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setVisible(true);
	}
}
