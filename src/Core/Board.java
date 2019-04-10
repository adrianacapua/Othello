/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Player.AI;
import Player.Human;
import Player.Player;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;

/**
 *
 * @author Adriana
 */
public class Board implements Serializable{
    
    private char[][] board = new char[8][8];
    private char chCurrentPlayer;
    
    private Player plCurrentPlayer;
    private Player playerOne;
    private Player playerTwo;
    
    
//    public ArrayList<Point> points = new ArrayList<Point>();
    
//    private Human humanPlayer;
//    private AI aiPlayer
    
    public Board(){
        
    }
    
    public Board(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    
    //GETTER AND SETTERS
//    public ArrayList<Point> getFlipped(){
//    	ArrayList<Point> temp = points;
//    	points.clear();
//    	return temp;
//    }
    
    public char[][] getBoard(){
        return board;
    }
    
    public void setBoard(char[][] board){
        this.board = board;
    }
    
    public char getCurrentField(){
        return chCurrentPlayer;
    }
    
    public char getOpponentField(){
        
        char opposite = 'W';
        if(chCurrentPlayer=='W')
            opposite = 'B';
        
        return opposite;
    }
    
    public Player getCurrentPlayer(){
        return plCurrentPlayer;
    }
    
    public Player getOpponentPlayer(){
        if(plCurrentPlayer == playerOne)
            return playerTwo;
        else
            return playerOne;
    }
    
    public void setCurrentPlayer(Player player){
        plCurrentPlayer = player;
        chCurrentPlayer = player.getChField();
    }
    
    public Player getPlayerOne(){
    	return playerOne;
    }
    
    public Player getPlayerTwo(){
    	return playerTwo;
    }
    
    //METHODS
    
    public void initializeBoard(){
        
        for(int x=0; x<=7; x++){
            for(int y=0; y<=7; y++){
               
                board[x][y] = '.';
            }
        }
        
        board[3][3] = 'B';
        board[3][4] = 'W';
        board[4][3] = 'W';
        board[4][4] = 'B';
    }
    
    public void changePlayer(){
        
        Player tempOppo = getOpponentPlayer();
        char chOppo = getOpponentField();
        
        setCurrentPlayer(tempOppo);
//        setCurrentPlayer(chOppo);
    }
    
    private boolean withinRange(int num){
        if((num>=0) && (num<8))
            return true;
        
        return false;
    }
    
    private boolean checkDirection(int x, int y, int toX, int toY){
        
        char opponent = getOpponentField();
        char current = getCurrentField();
        
        if(!(withinRange(x) && withinRange(y)))
            return false;
        else{
            
            if(board[x][y] == opponent){
                
                while(withinRange(x) && withinRange(y)){
                    
                    x+=toX;
                    y+=toY;
                    
                    if(withinRange(x) && withinRange(y)){
                        
                        if(board[x][y] == '.')
                            return false;
                        
                        if(board[x][y] == current)
                            return true;
                        
                        
                    }
                }
            }
        }
        return false;
    }
    
    private void flip(int x, int y, int toX, int toY){
        
    	
        while(board[x][y] == getOpponentField()){
            board[x][y] = chCurrentPlayer;
//            points.add(new Point(x,y));
            
            x += toX;
            y += toY;
            
        }
    }
    
    public void makeMove(int x, int y){
        
    	
        board[x][y] = getCurrentField();
        
        //east
	if(checkDirection(x-1,y,-1,0))
            flip(x-1,y,-1,0);
				
	//west
	if(checkDirection(x+1,y,1,0))
            flip(x+1,y,1,0);
				
	//south
	if(checkDirection(x,y-1,0,-1))
            flip(x,y-1,0,-1);
				
	//north
	if(checkDirection(x,y+1,0,1))
            flip(x,y+1,0,1);
				
	//south-east
	if(checkDirection(x-1,y-1,-1,-1))
            flip(x-1,y-1,-1,-1);
				
	//south-west
	if(checkDirection(x+1,y-1,1,-1))
            flip(x+1,y-1,1,-1);
				
	//north-east
	if(checkDirection(x-1,y+1,-1,1))
            flip(x-1,y+1,-1,1);
				
	//north-west
        if(checkDirection(x+1,y+1,1,1))
            flip(x+1,y+1,1,1);
    }
    
    public boolean isValidMove(int x, int y){
        
        if(board[x][y]!='.')
            return false;
        
        //east
	if(checkDirection(x-1,y,-1,0))
            return true;
				
	//west
	if(checkDirection(x+1,y,1,0))
            return true;
				
        //south
	if(checkDirection(x,y-1,0,-1))
            return true;
		
	//north
	if(checkDirection(x,y+1,0,1))
            return true;
		
	//south-east
	if(checkDirection(x-1,y-1,-1,-1))
            return true;
		
	//south-west
	if(checkDirection(x+1,y-1,1,-1))
            return true;
		
        //north-east
	if(checkDirection(x-1,y+1,-1,1))
            return true;
		
        //north-west
	if(checkDirection(x+1,y+1,1,1))
            return true;
				
	return false;
    }
    
    public int getMoveList(int moveX[], int moveY[]){
        int numMoves = 0;
        
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(isValidMove(x,y)){
                    moveX[numMoves] = x;
                    moveY[numMoves] = y;
                    numMoves++;
                }
            }
        }
        
        return numMoves;
    }

    public boolean gameOver(){
        int[] Wmovex = new int[60];
        int[] Wmovey = new int[60];
        int[] Bmovex = new int[60];
        int[] Bmovey = new int[60];
        
        int numWmoves=0, numBmoves=0;
        
        numWmoves = getMoveList(Wmovex,Wmovey);
        System.out.println("White moves: " + numWmoves);
        changePlayer();
        numBmoves = getMoveList(Bmovex,Bmovey);
        System.out.println("Black moves: " + numBmoves);
        changePlayer();
        
        if((numWmoves==0) && (numBmoves==0)){
            return true;
        }
        
        return false;
    }
    //DISPLAY BOARD
    public void displayBoard(){
    	
    	System.out.println("  0 1 2 3 4 5 6 7");
        for(int intCtr=0; intCtr<=7; intCtr++){ //x
			
            System.out.print(intCtr);
	    for(int intSubCtr=0; intSubCtr<=7; intSubCtr++){//y
		System.out.print(" " + board[intCtr][intSubCtr]);
            }
            System.out.println();
	}
	
    }
    
    public int getHeuristic(char piece){
        int currentScore = getScore(piece);
//      
        char opponent = 'B';
        if(piece=='B')
            opponent = 'W';
        
        int opponentScore = getScore(opponent);
        return(currentScore - opponentScore);
    }
    
    public int getScore(char piece){ //returns the number of pieces of the player in the board
		
	int total = 0;
		
	for(int intCtr=0; intCtr<8; intCtr++){
			
            for(int subCtr=0; subCtr<8; subCtr++){
				
                if(board[intCtr][subCtr] == piece)
                    total++;
            }
	}
		
	return total;
    }
    

}
