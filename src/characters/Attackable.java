/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package characters;

import adventure.Player;

/**
 *
 * @author MAS
 */
public interface Attackable {
    public void attack(Player p);
    public void isAttacked(Player p);
    public boolean isAlive();
    public int getDamage();
    public int getHealth();
}
