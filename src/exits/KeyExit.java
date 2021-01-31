/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exits;

import items.*;

/**
 *
 * @author MAS
 */
public class KeyExit extends Exit{
    private boolean lock;
    private Key key;
    
    public KeyExit(Key k){
        super(ExitType.KEY);
        this.key = k;
        this.close();
        this.lock = true;
    }
    
    @Override
    public void open(){}
    
    public void open(Key k){
        if(this.key == k){
            super.open();
            this.lock = false;
        }
    }
    
    @Override
    public void close(){
        super.close();
        this.lock = true;
    }
    
    public boolean isLocked(){
        return this.lock;
    }
}
