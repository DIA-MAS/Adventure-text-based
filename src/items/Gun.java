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
public class Gun extends Item{
    private int bullets;
    
    public Gun(String n){
        super(n);
        this.bullets = 0;
    }
    
    public void chargeGun(int nb){
        this.bullets = nb;
    }
    
    public void useGun(){
        this.bullets-=1;
    }
    
    public int getNbBullets(){
        return this.bullets;
    }
    
    public boolean hasBullets(){
        return this.bullets>0;
    }

}
