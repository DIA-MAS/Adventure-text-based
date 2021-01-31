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
public class Bullet extends Item{
    private int nbBullets;
    
    public Bullet(String n, int nbBu){
        super(n);
        this.nbBullets = nbBu;
    }
    
    public int getBullets(){
        return this.nbBullets;
    }
}
