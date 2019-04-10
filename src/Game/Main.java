package Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame{
	

	final static String WELCOMEPANEL = "WelcomeSetting";
	final static String BOARDPANEL = "OthelloBoard";
	
	private CardLayout layout;
	private OthelloBoard board;
	private WelcomeSetting setting;
	private JPanel MAIN;
	
	public Main(){
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790,550);
//		setResizable(false);
		setLocationRelativeTo(null);
		
		layout = new CardLayout();
		MAIN = new JPanel(layout);
		
		setting = new WelcomeSetting(this);
		board = new OthelloBoard(this);
		
		MAIN.add(setting,WELCOMEPANEL);
		MAIN.add(board,BOARDPANEL);
		
		add(MAIN,BorderLayout.CENTER);
	}

	public static String getWelcomepanel() {
		return WELCOMEPANEL;
	}

	public static String getBoardpanel() {
		return BOARDPANEL;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public OthelloBoard getBoard() {
		return board;
	}

	public WelcomeSetting getSetting() {
		return setting;
	}

	public JPanel getMAIN() {
		return MAIN;
	}
	
	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
}
