/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.io.Serializable;

/**
 *
 * @author Adriana
 */
public class Human extends Player implements Serializable{
    
    public Human(){
        
    }
    
    public Human(String name, char field){
        super(name,field);
    }
    
}
