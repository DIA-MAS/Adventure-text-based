/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author MAS
 */
public class Box extends Item{
    private List<Item> items = new ArrayList<>();
    
    public Box(String n){
        super(n);
    }
    
    public void putItem(Item i){
        this.items.add(i);
    }
    
    public boolean hasItem(Item i){
        return this.items.contains(i);
    }
    
    public List<Item> getItems(){
        return this.items;
    }
    
    public Item findItemInBoxByName(String n){
        Item selectedItem = this.getItems().stream()
                .filter(it -> it.toString().equalsIgnoreCase(n))
                .findFirst()
                .orElse(null);
        return selectedItem;
    }
}
