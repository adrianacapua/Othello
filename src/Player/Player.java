/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.awt.Color;
import java.io.Serializable;

import Core.Board;

/**
 *
 * @author Adriana
 */
public class Player implements Serializable{
    
    private String strName;
    private char chField;
    
    private int intScore;
    private Color color;
    
    public Player(){
        
    }
    
    public Player(String name, char field){
        
        strName = name;
        chField = field;
        intScore = 0;
        
        if(chField=='W'){
            this.setColor(Color.WHITE);
        }else{
            this.setColor(Color.BLACK);
        }
    }
    
    public String getStrName(){
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public char getChField() {
        return chField;
    }

    public void setChField(char chField) {
        this.chField = chField;
    }

    public int getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore) {
        this.intScore = intScore;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    //METHODS
    public int getScore(Board board){ //returns the number of pieces of the player in the board
		
	int total = 0;
        for(int intCtr=0; intCtr<8; intCtr++){
			
            for(int subCtr=0; subCtr<8; subCtr++){
				
                if(board.getBoard()[intCtr][subCtr] == this.getChField())
                    total++;
            }
	}
		
	return total;
    }
}

    
