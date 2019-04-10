package Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Player.AI;
import Player.Human;
import Player.Player;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import Core.Board;

import javax.swing.border.BevelBorder;
import java.awt.Font;

public class OthelloBoard extends JPanel implements ActionListener {
	
	//MAIN OBJECT
	private Board game;
	
	//BUTTONS
	private JButton[][] btnBoard = new JButton[8][8];
	
	//FIELDS
	private final ImageIcon white = new ImageIcon(getClass().getClassLoader().getResource("white.png"));
	private final ImageIcon black = new ImageIcon(getClass().getClassLoader().getResource("black.png"));
	
	//CONTAINERS
	private JPanel contentPane;
	private JPanel pnlBoard;

	//JLABEL
	private JLabel lblWhite;
	private JLabel lblWhitePlayer;
	private JLabel lblBlackPlayer;
	private JLabel lblBlack;
	private JLabel lblVS;
	private JLabel lblWhiteScore;
	private JLabel lblBlackScore;
	
	//TEXTFIELD
	private JTextField tfStatus;

	//FRAME
	private Main frame;
	
	public void newGame(Player playerOne,Player playerTwo){
		game = new Board(playerOne,playerTwo);
		game.initializeBoard();
		game.setCurrentPlayer(playerOne);
	}

	public OthelloBoard(Main frame) {
		
		this.frame = frame;
		
		//INITIALIZE OBJECT
		
		setLayout(new BorderLayout());

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(160, 82, 45));
		pnlMenu.setBounds(5, 5, 240, 501);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		lblWhitePlayer = new JLabel("White Player",SwingConstants.CENTER);
		lblWhitePlayer.setForeground(Color.BLACK);
		lblWhitePlayer.setFont(new Font("Modern No. 20", Font.PLAIN, 35));
		lblWhitePlayer.setBounds(10, 29, 220, 28);
		pnlMenu.add(lblWhitePlayer);
		
		lblWhite = new JLabel("  X");
		lblWhite.setForeground(new Color(0, 0, 0));
		lblWhite.setFont(new Font("Modern No. 20", Font.PLAIN, 34));
		lblWhite.setBounds(20, 55, 114, 62);
		lblWhite.setIcon(new ImageIcon(getClass().getClassLoader().getResource("white.png")));
		pnlMenu.add(lblWhite);
		
		lblBlackPlayer = new JLabel("Black Player", SwingConstants.CENTER);
		lblBlackPlayer.setForeground(Color.BLACK);
		lblBlackPlayer.setFont(new Font("Modern No. 20", Font.PLAIN, 35));
		lblBlackPlayer.setBounds(10, 241, 220, 38);
		pnlMenu.add(lblBlackPlayer);
		
		lblBlack = new JLabel("  X");
		lblBlack.setForeground(Color.BLACK);
		lblBlack.setFont(new Font("Modern No. 20", Font.PLAIN, 34));
		lblBlack.setBounds(20, 277, 114, 62);
		lblBlack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("black.png")));
		pnlMenu.add(lblBlack);
		
		lblVS = new JLabel("VS", SwingConstants.CENTER);
		lblVS.setForeground(Color.BLACK);
		lblVS.setFont(new Font("Modern No. 20", Font.PLAIN, 99));
		lblVS.setBounds(10, 121, 220, 109);
		pnlMenu.add(lblVS);
	
		
		pnlBoard = new JPanel(new GridLayout(8,8));
		pnlBoard.setForeground(new Color(0, 0, 0));
		pnlBoard.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlBoard.setBackground(new Color(39, 175, 84));
		pnlBoard.setBounds(248, 5, 522, 473);
		contentPane.add(pnlBoard);
/////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
		
		lblWhiteScore = new JLabel("0");
		lblWhiteScore.setBackground(new Color(255, 255, 255));
		lblWhiteScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhiteScore.setFont(new Font("Modern No. 20", Font.BOLD, 62));
		lblWhiteScore.setBounds(144, 55, 73, 62);
		pnlMenu.add(lblWhiteScore);
		
		lblBlackScore = new JLabel("0");
		lblBlackScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlackScore.setFont(new Font("Modern No. 20", Font.BOLD, 62));
		lblBlackScore.setBounds(144, 277, 73, 76);
		pnlMenu.add(lblBlackScore);
		
		JButton btnBackToMain = new JButton("Main Menu");
		btnBackToMain.setForeground(new Color(255, 255, 255));
		btnBackToMain.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
		btnBackToMain.setBackground(Color.DARK_GRAY);
		btnBackToMain.setBounds(0, 439, 240, 62);
		btnBackToMain.setBorderPainted(false); 
		btnBackToMain.setContentAreaFilled(false); 
		btnBackToMain.setFocusPainted(false); 
		btnBackToMain.setOpaque(false);
		pnlMenu.add(btnBackToMain);
		btnBackToMain.setRolloverEnabled(true);
		btnBackToMain.addMouseListener(new MouseAdapter(){
			Color color = btnBackToMain.getForeground();
			
			public void mouseEntered(MouseEvent e){

				btnBackToMain.setForeground(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e){
				btnBackToMain.setForeground(color);
			}
		});
		btnBackToMain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnBackToMainAction();
				
			}
			
		});
		
		tfStatus = new JTextField();
		tfStatus.setFont(new Font("Modern No. 20", Font.BOLD, 18));
		tfStatus.setBounds(248, 481, 522, 25);
		contentPane.add(tfStatus);
		tfStatus.setColumns(10);
		
		JLabel lblBgForPanel = new JLabel("New label");
		lblBgForPanel.setBounds(0, -20, 240, 410);
		pnlMenu.add(lblBgForPanel);
		lblBgForPanel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bgForPanel.png")));
		
		initializeBoard();
		add(contentPane,BorderLayout.CENTER);
		setPreferredSize(contentPane.getPreferredSize());
	}
	
	private void initializeBoard(){
		
		for(int x=0; x<=7; x++){
			for(int y=0; y<=7; y++){
				
				btnBoard[x][y] = new JButton();
				btnBoard[x][y].setBackground(new Color(39, 175, 84));
				btnBoard[x][y].putClientProperty("column",x);
				btnBoard[x][y].putClientProperty("row",y);
				btnBoard[x][y].addActionListener(this);
				
				pnlBoard.add(btnBoard[x][y]);
			}
		}
		
		btnBoard[3][3].setIcon(black);
		btnBoard[3][4].setIcon(white);
		btnBoard[4][3].setIcon(white);
		btnBoard[4][4].setIcon(black);
	}
	
	private void resetBoard(){
		
		for(int x=0; x<=7; x++){
			for(int y=0; y<=7; y++){
				
				btnBoard[x][y].setIcon(null);
			}
		}
		
		btnBoard[3][3].setIcon(black);
		btnBoard[3][4].setIcon(white);
		btnBoard[4][3].setIcon(white);
		btnBoard[4][4].setIcon(black);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		game.displayBoard();
		
		if(!game.gameOver()){
			
			//IF HUMAN
			if(Human.class.isInstance(game.getCurrentPlayer())){
				System.out.println("Current Player: " + game.getCurrentPlayer().getStrName());
				
				JButton btn = (JButton) e.getSource();
				
				int xPos = Integer.parseInt(btn.getClientProperty("column").toString());
				int yPos = Integer.parseInt(btn.getClientProperty("row").toString());
				
				if(game.isValidMove(xPos,yPos)){
					game.makeMove(xPos, yPos);
					game.displayBoard();
					updateGUI(game.getBoard());
					//UPDATE BOARD
					game.changePlayer();
					tfStatus.setText("It's " + game.getCurrentPlayer().getStrName() + "'s turn");
					
				}else{
					tfStatus.setText("Invalid move!");
					System.out.println("Invalid move!");
				}
				
				
			}
			
			if(game.gameOver()){
				gameOver();
				return;
			}
			
			//IF AI
			if(AI.class.isInstance(game.getCurrentPlayer())){
				SwingWorker myWorker= new SwingWorker<Void, Void>() {
				    @Override
				    protected Void doInBackground() throws Exception {
				    	
						System.out.println("Current Player: " + game.getCurrentPlayer().getStrName());
						AI ai = (AI) game.getCurrentPlayer();
						ai.move(game);
						
						int xPos = ai.getxPos();
						int yPos = ai.getyPos();
						
						game.makeMove(xPos, yPos);
						game.displayBoard();
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						updateGUI(game.getBoard());
						if(game.gameOver()){
							gameOver();
						}
						game.changePlayer();
						tfStatus.setText("It's " + game.getCurrentPlayer().getStrName() + "'s turn");
						//UPDATE GUI
						return null;
						
//				        return null;
				   
				    }
				};
				
				myWorker.execute();
			}
			
			
			if(game.getMoveList(new int[60], new int[60]) == 0){
				System.out.println("No available moves. Change player.");
                game.changePlayer();
                tfStatus.setText("It's " + game.getCurrentPlayer().getStrName() + "'s turn");
			}
			
			if(game.gameOver()){
				gameOver();
			}
			
			
		}else{
			gameOver();
		}
		
	}
	
	private void gameOver(){
		tfStatus.setText("GAME OVER!!!");
		System.out.println("GAME OVER!");
		Player playerOne = game.getPlayerOne();
		Player playerTwo = game.getPlayerTwo();
		
		int scoreOne = playerOne.getScore(game);
		int scoreTwo = playerTwo.getScore(game);
		System.out.println("Player " + playerOne.getStrName() + " score: " + scoreOne);
		System.out.println("Player " + playerTwo.getStrName() + " score: " + scoreTwo);
		
		JOptionPane.showMessageDialog(this, "GAME OVER\n"
				+ "\n\n"
				+ "Player 1 (" + game.getPlayerOne().getStrName() + " ): " + scoreOne
				+ "\n"
				+ "Player 2: (" + game.getPlayerTwo().getStrName() + "): "  + scoreTwo,"Result",JOptionPane.OK_OPTION);
		
		backToWelcome();
	}
	
	private void updateGUI(char[][] board){
		
		for(int x=0; x<=7; x++){
			for(int y=0; y<=7; y++){
				
				if(board[x][y]=='W'){
					btnBoard[x][y].setIcon(white);
				}else if(board[x][y]=='B'){
					btnBoard[x][y].setIcon(black);
				}
				
			}
		}
		
		reloadScore();
	}
	
	private void btnBackToMainAction(){
		
		int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to end the game?","???",JOptionPane.OK_OPTION);
		
		if(result==JOptionPane.YES_OPTION){
			backToWelcome();
		}
	}
	
	private void backToWelcome(){
		game = null;
		resetBoard();
		lblWhiteScore.setText("0");
		lblBlackScore.setText("0");
		tfStatus.setText("");
		CardLayout cl = (CardLayout)(frame.getMAIN().getLayout());
	    cl.show(frame.getMAIN(), Main.WELCOMEPANEL);
	}

	private void reloadScore(){
		int whiteScore = game.getScore('W');
		int blackScore = game.getScore('B');
		
		lblWhiteScore.setText(""+whiteScore);
		lblBlackScore.setText(""+blackScore);
	}
}
