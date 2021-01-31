/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exits;

/**
 *
 * @author MAS
 */
public class OpenExit extends Exit{
    public OpenExit(){
        super(ExitType.OPEN);
        this.open();
    }
    
    @Override
    public void close(){}

}
