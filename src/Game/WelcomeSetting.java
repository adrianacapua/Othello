package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

import Player.AI;
import Player.Human;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class WelcomeSetting extends JPanel{
	
	private Main frame;

	private JPanel pnlWelcome;
	
	private JTextField tfPlayerName;
	private JTextField tfOpponentName;
	private JRadioButton rdbtnWhite;
	private JRadioButton rdbtnBlack;
	private JRadioButton rdbtnComputer;
	private JRadioButton rdbtnHuman;
	private JRadioButton rdbtnAI;
	private JRadioButton rdbtnRandom;
	private JLabel lblOpponent;
	
	private ButtonGroup field = new ButtonGroup();
	private ButtonGroup player = new ButtonGroup();
	private ButtonGroup ai = new ButtonGroup();

	public WelcomeSetting(Main frame) {
		
		this.frame = frame;
		
		pnlWelcome = new JPanel();
		
		pnlWelcome.setLayout(null);
		
		//PNLWELCOME 
		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(50, 45, 639, 180);
		lblTitle.setIcon(new ImageIcon(getClass().getClassLoader().getResource("title.png")));
		pnlWelcome.add(lblTitle);
		
		JButton btnPlay = new JButton("PLAY!");
		btnPlay.setForeground(Color.BLACK);
		btnPlay.setFont(new Font("Modern No. 20", Font.BOLD, 54));
		btnPlay.setBounds(515, 402, 224, 73);
		btnPlay.setBorderPainted(false); 
		btnPlay.setContentAreaFilled(false); 
		btnPlay.setFocusPainted(false); 
		btnPlay.setOpaque(false);
		btnPlay.addMouseListener(new MouseAdapter(){
			Color color = btnPlay.getForeground();
			
			public void mouseEntered(MouseEvent e){

				btnPlay.setForeground(Color.white);
			}
			
			public void mouseExited(MouseEvent e){
				btnPlay.setForeground(color);
			}
		});
		btnPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnPlayAction();
				
			}
			
		});
		pnlWelcome.add(btnPlay);
		
		JPanel pnlSetting = new JPanel();
		pnlSetting.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlSetting.setBounds(39, 225, 466, 250);
		pnlSetting.setBackground(new Color(39, 175, 84));
		pnlWelcome.add(pnlSetting);
		pnlSetting.setLayout(null);
		
		JLabel lblPlayerTwoBlack = new JLabel("Your opponent is player: ");
		lblPlayerTwoBlack.setForeground(Color.BLACK);
		lblPlayerTwoBlack.setFont(new Font("Modern No. 20", Font.PLAIN, 29));
		lblPlayerTwoBlack.setBounds(70, 101, 293, 42);
		pnlSetting.add(lblPlayerTwoBlack);
		
		JLabel lblWName = new JLabel("Enter your name:");
		lblWName.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblWName.setBounds(86, 49, 141, 25);
		pnlSetting.add(lblWName);
		
		tfPlayerName = new JTextField();
		tfPlayerName.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		tfPlayerName.setBounds(219, 50, 191, 25);
		pnlSetting.add(tfPlayerName);
		tfPlayerName.setColumns(10);
		
		tfOpponentName = new JTextField();
		tfOpponentName.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		tfOpponentName.setColumns(10);
		tfOpponentName.setBounds(182, 139, 173, 25);
		pnlSetting.add(tfOpponentName);
		
		JLabel lblGiveAName = new JLabel("Give a name: ");
		lblGiveAName.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblGiveAName.setBounds(86, 138, 105, 25);
		pnlSetting.add(lblGiveAName);
		
		rdbtnWhite = new JRadioButton("White");
		rdbtnWhite.setFont(new Font("Modern No. 20", Font.PLAIN, 19));
		rdbtnWhite.setBounds(253, 20, 88, 23);
		rdbtnWhite.setBorderPainted(false); 
		rdbtnWhite.setContentAreaFilled(false); 
		rdbtnWhite.setFocusPainted(false); 
		rdbtnWhite.setOpaque(false);
		rdbtnWhite.addItemListener(new Field());
		pnlSetting.add(rdbtnWhite);
		
		rdbtnBlack = new JRadioButton("Black");
		rdbtnBlack.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		rdbtnBlack.setBounds(330, 21, 80, 23);
		rdbtnBlack.setBorderPainted(false); 
		rdbtnWhite.setContentAreaFilled(false); 
		rdbtnBlack.setFocusPainted(false); 
		rdbtnBlack.setOpaque(false);
		rdbtnBlack.addItemListener(new Field());
		pnlSetting.add(rdbtnBlack);
		
		
		field.add(rdbtnWhite);
		field.add(rdbtnBlack);
		
		JLabel lblPlayerOneWhite = new JLabel("You are player:");
		lblPlayerOneWhite.setBounds(67, 11, 346, 42);
		pnlSetting.add(lblPlayerOneWhite);
		lblPlayerOneWhite.setForeground(Color.WHITE);
		lblPlayerOneWhite.setFont(new Font("Modern No. 20", Font.PLAIN, 29));
		
		JLabel lbl2 = new JLabel("");
		lbl2.setBounds(10, 84, 66, 73);
		pnlSetting.add(lbl2);
		lbl2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("white.png")));
		
		JLabel lbl1 = new JLabel("");
		lbl1.setBounds(10, 0, 66, 73);
		pnlSetting.add(lbl1);
		lbl1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("black.png")));
		
		lblOpponent = new JLabel("");
		lblOpponent.setFont(new Font("Modern No. 20", Font.PLAIN, 29));
		lblOpponent.setBounds(364, 101, 100, 42);
		pnlSetting.add(lblOpponent);
		
		rdbtnHuman = new JRadioButton("Human");
		rdbtnHuman.setOpaque(false);
		rdbtnHuman.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		rdbtnHuman.setFocusPainted(false);
		rdbtnHuman.setBorderPainted(false);
		rdbtnHuman.setBounds(183, 172, 88, 23);
		rdbtnHuman.addItemListener(new Player());
		pnlSetting.add(rdbtnHuman);
		
		rdbtnComputer = new JRadioButton("Computer");
		rdbtnComputer.setOpaque(false);
		rdbtnComputer.setFont(new Font("Modern No. 20", Font.PLAIN, 19));
		rdbtnComputer.setFocusPainted(false);
		rdbtnComputer.setContentAreaFilled(false);
		rdbtnComputer.setBorderPainted(false);
		rdbtnComputer.setBounds(79, 171, 102, 23);
		rdbtnComputer.addItemListener(new Player());
		pnlSetting.add(rdbtnComputer);
		
		player.add(rdbtnHuman);
		player.add(rdbtnComputer);
		
		JLabel lblIfComputer = new JLabel("If computer, :");
		lblIfComputer.setForeground(Color.BLACK);
		lblIfComputer.setFont(new Font("Modern No. 20", Font.PLAIN, 29));
		lblIfComputer.setBounds(10, 202, 191, 42);
		pnlSetting.add(lblIfComputer);
		
		rdbtnAI = new JRadioButton("AI");
		rdbtnAI.setActionCommand("AI");
		rdbtnAI.setOpaque(false);
		rdbtnAI.setFont(new Font("Modern No. 20", Font.PLAIN, 19));
		rdbtnAI.setFocusPainted(false);
		rdbtnAI.setContentAreaFilled(false);
		rdbtnAI.setBorderPainted(false);
		rdbtnAI.setBounds(171, 205, 66, 39);
		rdbtnAI.setEnabled(false);
		pnlSetting.add(rdbtnAI);
		
		rdbtnRandom = new JRadioButton("Randomizer");
		rdbtnRandom.setOpaque(false);
		rdbtnRandom.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		rdbtnRandom.setFocusPainted(false);
		rdbtnRandom.setBorderPainted(false);
		rdbtnRandom.setBounds(239, 202, 135, 42);
		rdbtnRandom.setEnabled(false);
		pnlSetting.add(rdbtnRandom);
		
		ai.add(rdbtnAI);
		ai.add(rdbtnRandom);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 764, 501);
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bg.png")));
		pnlWelcome.add(lblNewLabel);

		
		add(pnlWelcome);
		pnlWelcome.setPreferredSize(lblNewLabel.getPreferredSize());
		setPreferredSize(pnlWelcome.getPreferredSize());
	}

	public JPanel getPnlWelcome() {
		return pnlWelcome;
	}

	private class Field implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource() == rdbtnWhite){
				
				lblOpponent.setText("Black");
				lblOpponent.setForeground(Color.BLACK);
				
			}else if(e.getSource() == rdbtnBlack){
				
				lblOpponent.setText("White");
				lblOpponent.setForeground(Color.white);
			}
		}
	}
	
	private class Player implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource() == rdbtnComputer){
				
				rdbtnAI.setEnabled(true);
				rdbtnRandom.setEnabled(true);
				
			}else if(e.getSource() == rdbtnHuman){
				
				rdbtnAI.setEnabled(false);
				rdbtnRandom.setEnabled(false);
			}
		}
	}
	
	private void btnPlayAction(){
		
		boolean complete = checkIfSettingComplete();
		
		if(complete){
			char playerField = (rdbtnWhite.isSelected()) ? 'W' : 'B';
			char oppoPlayerField = (playerField=='W') ? 'B' : 'W';
			
			String playerName = tfPlayerName.getText();
			String oppoPlayerName = tfOpponentName.getText();
			
			if(rdbtnComputer.isSelected()){
				boolean difficulty = (rdbtnAI.isSelected()) ? true : false;
				
				Human human = new Human(playerName,playerField);
				AI ai = new AI(oppoPlayerName,oppoPlayerField,difficulty);
				
				frame.getBoard().newGame(human, ai);
			}else{
				
				Human human1 = new Human(playerName,playerField);
				Human human2 = new Human(oppoPlayerName,oppoPlayerField);
				
				frame.getBoard().newGame(human1, human2);
			}
			
			clearSetting();
			CardLayout cl = (CardLayout)(frame.getMAIN().getLayout());
		    cl.show(frame.getMAIN(), Main.BOARDPANEL);
		}else{
			JOptionPane.showMessageDialog(this, "Please complete the information needed!");
		}
		
		
	}
	
	private boolean checkIfSettingComplete(){
		
		boolean complete = true;
		
		if(!rdbtnWhite.isSelected() && !rdbtnBlack.isSelected()){
			complete = false;
		}
		
		if(tfPlayerName.getText()=="" ){
			complete = false;
		}
		
		if(tfOpponentName.getText()==""){
			complete = false;
		}
		
		if(!rdbtnComputer.isSelected() && !rdbtnHuman.isSelected()){
			complete = false;
		}else{
			if(rdbtnComputer.isSelected()){
				
				if(!rdbtnAI.isSelected() && !rdbtnRandom.isSelected()){
					complete = false;
				}
			}
		}
		
		return complete;
	}

	private void clearSetting(){
		field.clearSelection();
		player.clearSelection();
		ai.clearSelection();
		rdbtnAI.setEnabled(false);
		rdbtnRandom.setEnabled(false);
		lblOpponent.setText("");
		tfPlayerName.setText("");
		tfOpponentName.setText("");
	}
}
