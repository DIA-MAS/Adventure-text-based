/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

/**
 *
 * @author MAS
 */
public class Food extends Item{
    private int bonus;
    
    public Food(String n, int b){
        super(n);
        this.bonus = b;
    }
    
    public int getBonus(){
        return this.bonus;
    }

}
