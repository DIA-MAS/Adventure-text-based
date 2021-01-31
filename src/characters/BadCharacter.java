/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package characters;

import adventure.*;
/**
 *
 * @author MAS
 */
public class BadCharacter extends GameCharacter implements Attackable{
    private int healthPoints;
    private int damagePoints;
    
    public BadCharacter(String n, int health, int damage){
        super(n);
        this.healthPoints = health;
        this.damagePoints = damage;
    }
    
    @Override
    public void isAttacked(Player p){
        if(this.isAlive()){
            this.healthPoints-=p.getDamage();
            if(this.isAlive()){
                System.out.println("The " + this.toString()+" health points now are: " + this.getHealth());
            }
            else{
                System.out.println("You killed the "+ this.toString()+"!!");
            }
        }
    }
    
    
    @Override
    public void attack(Player p){
        if(this.isAlive()){
            p.isAttacked(this);
        }
    }
    
    @Override
    public boolean isAlive(){
        return this.healthPoints > 0;
    }
    
    @Override 
    public int getHealth(){
        return this.healthPoints;
    }
    
    @Override 
    public int getDamage(){
        return this.damagePoints;
    }
    
    @Override 
    public void speak(){}
}
