/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exits;

import adventure.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAS
 */
public class Exit {
    private ExitType type;
    private boolean open;
    private List<Place> placeList = new ArrayList<>();
    
    public Exit(ExitType t){
        this.type = t;
        this.open = false;
    }
    
    public void open(){
        this.open = true;
    }
    
    public void close(){
        this.open = false;
    }
    
    public boolean isOpen(){
        return this.open;
    }
    
    @Override
    public String toString(){
        return this.type.toString().toLowerCase();
    }
    
    public ExitType getExitType(){
        return this.type;
    }
    
    public void addNeighPlace(Place p){
        placeList.add(p);
    }
    
    public boolean isNei(Place p){
        return this.placeList.contains(p);
    }
    
    public List<Place> getNeighbours(){
        return this.placeList;
    }
}
