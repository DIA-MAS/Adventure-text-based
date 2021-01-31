/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adventure;
import items.*;
import exits.*;
import characters.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAS
 */
public class Player {
    private Place location;
    private int healthPoints;
    private int damagePoints;
    private List<Item> items = new ArrayList<>();
    private boolean gunIsHold;
    
    public Player(){
        this.healthPoints = 150;
        this.damagePoints = 15;
        this.gunIsHold = false;
    }
    
    public void attack(BadCharacter c){
        if(this.isAlive()){
            for(Item i : items){
                if(i instanceof Gun){
                    if(this.gunIsHold){
                        if(!((Gun)i).hasBullets()){
                            System.out.println("Your gun is out of bullets! You will not be able to attack using it without reloading!\nYour attack will be less powerful now");
                            this.setDamagePoints(10);
                            this.gunIsHold = false;
                        }
                        else{
                            ((Gun)i).useGun();
                            this.setDamagePoints(25);
                            System.out.println("You are now attacking with the gun! the number of bullets left: "+((Gun) i).getNbBullets());
                        }
                    }
                }
            }
            System.out.println("You attacked the "+ c.toString() + " With " + this.getDamage() + " Points!");
            c.isAttacked(this);
            c.attack(this);
        }
    }
    
    public void isAttacked(BadCharacter c){
        if(this.isAlive()){
            this.healthPoints-= c.getDamage();
            if(this.isAlive()){
                System.out.println("The " + c.toString()+" attacked you with "+ c.getDamage()+" Points!\nYour health points now are:" + this.getHealth());
            }
            else{
                System.out.println("The "+ c.toString() +" killed you!");
            }
        }
    }
    
    public int getDamage(){
        return this.damagePoints;
    }
    
    public int getHealth(){
        return this.healthPoints;
    }
    
    public boolean isAlive(){
        return this.healthPoints > 0;
    }
    
    public void setDamagePoints(int nb){
        this.damagePoints = nb;
    }
    
    public void goThroughExit(Exit ex){
        if(this.canGo(ex)){
            System.out.println("The exit is open!");
            ex.open();
        }
        else{
            System.out.println("The exit is locked! you need a key to go through it!");
        }
    }
    
    public boolean canGo(Exit ex){
        return ex.isOpen();
    }
    
    public void goLoc(Place p){
        if(this.location.isNeighbour(p)){
            goThroughExit(this.location.getExitFromPlace(p));
            if(canGo(this.location.getExitFromPlace(p))){
                this.setLocation(p);
                System.out.println("You are now in the "+ p.toString());
                if(p.hasGoodCharacter()){
                    System.out.println("#"+p.getGoodCharacter().toString()+" has appeared! He might help you! Type 'Speak' to let him speak to you!");
                }
                else if(p.hasBadCharacter()){
                    System.out.println("#"+p.getBadCharacter().toString()+" has appeared! You should kill him!");
                }
            }
            else if(this.location == p){
                System.out.println("You are already in the "+ p.toString());
            }
            else{
                System.out.println("the "+ p.toString() + " is not found next to your current place (" + this.location + ")");
            }
        }
    }
    
    
    public void setLocation(Place p){
        this.location = p;
    }
    
    public Place getLoc(){
        return this.location;
    }
    
    public void takeItem(Item i) {
        items.add(i);
        System.out.println("You took the "+i+"!");
    }
    
    public List<Item> getItems(){
        return this.items;
    }
    
    public void useItem(Item i){
        if(i instanceof Key){
            KeyExit e = this.getLoc().getKeyExit();
            if(e!=null && !e.isOpen()){
                e.open((Key)i);
                System.out.println("Key used, the exit is open!");
            }
            else{
                System.out.println("there is no locked door to use the key!");
            }
        }
        
        else if(i instanceof Gun){
            if(((Gun)i).hasBullets()){
                System.out.println("You are holding the gun now! Your attack will now be stronger with the gun!");
                this.gunIsHold = true;
            }
            else{
                System.out.println("Your gun has no bullets! Please reload it!");
            }
        }
        else if(i instanceof Bullet){
            System.out.println("You cannot use the bullets alone! You need to charge your gun with them! Type 'use gun bullets' to charge the gun!");
        }
        else if(i instanceof Food){
            System.out.println("You ate the "+ i.toString() + "! Your health increased of " + ((Food)i).getBonus()+ " points!");
            this.healthPoints+= ((Food)i).getBonus();
            System.out.println("Your health now:" + this.getHealth());
            this.items.remove(i);
        }
    }
    
    public Item findItemWithPlayerByName(String n){
        Item selectedItem = this.getItems().stream()
                .filter(it -> it.toString().equalsIgnoreCase(n))
                .findFirst()
                .orElse(null);
        return selectedItem;
    }
    
    public Item findItemInPlaceByName(String n){
        Item selectedItem = this.getLoc().getItems().stream()
                .filter(it -> it.toString().equalsIgnoreCase(n))
                .findFirst()
                .orElse(null);
        return selectedItem;
    }
}