/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package characters;

/**
 *
 * @author MAS
 */
public class GoodCharacter extends GameCharacter{
    public GoodCharacter(String n){
        super(n);
    }
    
    @Override
    public void speak(){
        System.out.println(this.toString() + ": YOu can find the princess in the donjon!");
    }
}
