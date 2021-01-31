/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adventure;

import exits.*;
import items.*;
import characters.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MAS
 */
public class Place {
    private String name;
    private int nbExit;
    private List<Exit> exList = new ArrayList<>();
    private Map <String,Exit> exitsMap = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private GameCharacter c;
    
    public Place(String n, int nbExit){
        this.name = n;
        this.nbExit = nbExit;
    }
    
    public void addExit(Exit ex){
        if(exList.size() < this.nbExit && !this.exList.contains(ex)){
            this.exList.add(ex);
        }
    }
    
    public void setNeighbour(Place p, Exit ex){
        if(this.exList.contains(ex) && !this.exitsMap.containsValue(ex)){
            this.exitsMap.put(p.toString(),ex);
            ex.addNeighPlace(this);
        }
    }
    
    public void displaymap(){
        System.out.println("The neighbours to the " + this.name + " are: ");
        exitsMap.forEach((k,v) ->{
            System.out.println(k);
        });  
    }
    
    public KeyExit getKeyExit(){
        KeyExit exit = null;
        for(Exit e : exList){
            if(e.getExitType() == ExitType.KEY && !e.isOpen()){
                exit = (KeyExit)e;
            }
        }
        return exit;
    }
    
    public Exit getExitFromPlace(Place p){
        return this.exitsMap.get(p.toString());
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    public boolean isNeighbour(Place p){
        return exitsMap.containsKey(p.toString());
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
    
    public void putCharacter(GameCharacter c){
        this.c = c;
    }
    
    public BadCharacter getBadCharacter(){
        try{
            if(this.c instanceof BadCharacter){
                if(((BadCharacter)this.c).isAlive()){
                    return ((BadCharacter)this.c);
                }
                else{
                    this.c = null;
                    return((BadCharacter)this.c);
                }
            }
            else{
                return null;
            }
        }catch(NullPointerException e){
            return null;
        }
    }
    
    public boolean hasBadCharacter(){
        if(this.c instanceof BadCharacter){
            return this.c!=null;
        }
        
        else{
            return false;
        }
    }
    
    public boolean hasGoodCharacter(){
        if(this.c instanceof GoodCharacter){
            return true;
        }
        else{
            return false;
        }
    }
    
    public GoodCharacter getGoodCharacter(){
        if(this.hasGoodCharacter()){
            return (GoodCharacter)this.c;
        }
        else{
            return null;
        }
    }
    

}
