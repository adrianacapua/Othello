/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Core.Board;

/**
 *
 * @author Adriana
 */
public class AI extends Player implements Serializable{
    
    private int xPos;
    private int yPos;
    
    private boolean difficulty;
    
    public AI(){
        
    }
    
    public AI(String name, char field, boolean difficulty){
        super(name,field);
        this.difficulty = difficulty;
    }
    
    //GETTES AND SETTER

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(boolean difficulty) {
        this.difficulty = difficulty;
    }
    
    //METHODS
    public void move(Board game){
        if(difficulty){
            minimaxDecision(game);
        }else{
            getRandomMove(game);
        }
    }
    
    public void getRandomMove(Board game){
        
        int[] moveX = new int[60];
        int[] moveY = new int[60];
        
        int numMoves = 0;
        
        numMoves = game.getMoveList(moveX,moveY);
        
        if(numMoves==0){
            yPos=-1;
            xPos=-1;
        }else{
            
            int i = (int)Math.random() % numMoves;
            
            xPos = moveX[i];
            yPos = moveY[i];
        }
    }
    
    
    private int getCurrentScore(char piece, Board game){
        int total = 0;
        
        char[][] board = game.getBoard();
        
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(board[x][y] == piece)
                    total++;
            }
        }
        
        return total;
    }

    
    public void minimaxDecision(Board game){
        
        int[] moveX = new int[60];
        int[] moveY = new int[60];
        int numMoves = 0;
        
        numMoves = game.getMoveList(moveX, moveY);
        
        if(numMoves == 0){
            xPos = -1;
            yPos = -1;
        }else{
            //START AS MAXIMIXING PLAYER
           
        	int bestMoveValue = -Integer.MAX_VALUE;
            int bestX = moveX[0];
            int bestY = moveY[0];
            
            for(int i=0; i<numMoves; i++){
                int val=0;
                
                Board temp = (Board) deepClone(game);
                
                temp.makeMove(moveX[i],moveY[i]);
                temp.changePlayer();
                
                val = alphaBeta(temp,3,-Integer.MAX_VALUE,Integer.MAX_VALUE,false,game.getCurrentField());
//                val = minimaxValue(temp,game.getCurrentField(),1);
            
                if(val > bestMoveValue){
                    bestMoveValue = val;
                    bestX = moveX[i];
                    bestY = moveY[i];
                }
            }
            
            xPos = bestX;
            yPos = bestY;
        }
    }
    
    private int alphaBeta(Board game, int depth, int alpha, int beta, boolean maximingPlayer, char origTurn){
    	
    	if(depth==0 || game.gameOver()){
    		return game.getHeuristic(origTurn);
    	}
    	
    	int[] moveX = new int[60];
        int[] moveY = new int[60];
        
        int numMoves = 0;
        
        char opponent = game.getOpponentField();
        
        numMoves = game.getMoveList(moveX, moveY);
        
//        if(numMoves==0){
//            
//            OthelloBoard temp = (OthelloBoard) deepClone(game);
//            temp.changePlayer();
//            
//            return minimaxValue(temp,origTurn,search+1);
//        }
    	
        
    	if(maximingPlayer){
    		for(int i=0; i<numMoves; i++){
                
                Board child = (Board) deepClone(game);
                child.makeMove(moveX[i], moveY[i]);
                child.changePlayer();
                
                alpha = Math.max(alpha, alphaBeta(child,depth-1,alpha,beta,false,origTurn));
                
                if(alpha>=beta)
                	break;
            }
    		
    		return alpha;
    	}else if(!maximingPlayer){
    		for(int i=0; i<numMoves; i++){
                
                Board child = (Board) deepClone(game);
                child.makeMove(moveX[i], moveY[i]);
                child.changePlayer();
                
                beta = Math.min(beta, alphaBeta(child,depth-1,alpha,beta,true,origTurn));
                
                if(alpha>=beta)
                	break;
            }
    		
    		return beta;
    	}
    	return - 1;
    }
    
    ////
    
    private int minimaxValue(Board game, char origTurn, int search){
        if(search==5 || game.gameOver()){
            return game.getHeuristic(origTurn);
        }
        
        int[] moveX = new int[60];
        int[] moveY = new int[60];
        
        int numMoves = 0;
        
        char opponent = game.getOpponentField();
        
        numMoves = game.getMoveList(moveX, moveY);
        
        if(numMoves==0){
            
            Board temp = (Board) deepClone(game);
            temp.changePlayer();
            
            return minimaxValue(temp,origTurn,search+1);
        }else{
        	
        	
            ////
            int bestMoveValue = -Integer.MAX_VALUE;
            if(origTurn!=game.getCurrentField()){
                bestMoveValue = Integer.MAX_VALUE;
            }
            
            for(int i=0; i<numMoves; i++){
                
                Board temp = (Board) deepClone(game);
                temp.makeMove(moveX[i], moveY[i]);
                temp.changePlayer();
                int val = minimaxValue(temp,origTurn,search+1);
                
                if(origTurn == game.getCurrentField()){
                    if(val>bestMoveValue)
                        bestMoveValue = val;
                }else{
                    if(val<bestMoveValue)
                        bestMoveValue = val;
                }
            }
            return bestMoveValue;
        }
    }
    
    private static Object deepClone(Object object){
        
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
	}catch(Exception e){
			
            e.printStackTrace();
            return null;
	}
    }
    
    
}
