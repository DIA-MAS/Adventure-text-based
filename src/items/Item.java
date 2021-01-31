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
public class Item {
    private String name;
    
    public Item(String n){
        this.name = n;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
