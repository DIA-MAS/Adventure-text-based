/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package characters;
import adventure.*;

/**
 *
 * @author MAS
 */
public abstract class GameCharacter {
    private String name;
    
    public GameCharacter(String n){
        this.name = n;
    }
    
    public abstract void speak();
    
    @Override
    public String toString(){
        return this.name;
    }
}
