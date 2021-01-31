/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adventure;
import items.*;
import characters.*;
import exits.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author MAS
 */
public class Adventure {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Player player = new Player();
        
        BadCharacter zombie = new BadCharacter("Zombie", 100, 20);
        BadCharacter dragon = new BadCharacter("Dragon", 70, 15);
        BadCharacter monster = new BadCharacter("Monster",70, 15);
        GoodCharacter helper = new GoodCharacter("Hleper");
        
        
        // in locations
        Gun gun = new Gun("Gun");
        Food apple = new Food("Apple", 10);
        Food banana = new Food("Banana",15);
        Food juice = new Food("Juice", 20);
        Key key = new Key("Key");
        Box box = new Box("Box");
        
        
        // in box
        Food water = new Food("WaterBottle", 5);
        Bullet bullets = new Bullet("Bullets", 7);
        box.putItem(water);
        box.putItem(bullets);
        
        Place garden = new Place("Garden", 2);
        Place drawBridge = new Place("DrawBridge",2);
        Place castleWall = new Place("CastleWall", 2);
        Place tavern = new Place("Tavern", 3);
        Place courtyard = new Place("Courtyard",3);
        Place waterPit = new Place("WaterPit",1);
        Place donjon = new Place("Donjon",1);
        
        Exit ex1 = new OpenExit();
        Exit ex2 = new OpenExit();
        KeyExit ex3 = new KeyExit(key);
        Exit ex4 = new OpenExit();
        KeyExit ex5 = new KeyExit(key);
        Exit ex6 = new OpenExit();
        KeyExit ex7 = new KeyExit(key);
        
        final List<Place> places = Arrays.asList(garden, drawBridge, castleWall, tavern, courtyard, waterPit, donjon);
        
        garden.addExit(ex1);
        garden.addExit(ex2);
        
        drawBridge.addExit(ex1);
        drawBridge.addExit(ex3);
        
        castleWall.addExit(ex2);
        castleWall.addExit(ex4);
        
        tavern.addExit(ex3);
        tavern.addExit(ex4);
        tavern.addExit(ex5);
        
        courtyard.addExit(ex5);
        courtyard.addExit(ex6);
        courtyard.addExit(ex7);
        
        waterPit.addExit(ex6);

        donjon.addExit(ex7);
        
        garden.setNeighbour(drawBridge, ex1);
        garden.setNeighbour(castleWall, ex2);
        
        drawBridge.setNeighbour(garden, ex1);
        drawBridge.setNeighbour(tavern, ex3);
        
        castleWall.setNeighbour(garden, ex2);
        castleWall.setNeighbour(tavern, ex4);
        
        tavern.setNeighbour(castleWall, ex4);
        tavern.setNeighbour(courtyard, ex5);
        
        courtyard.setNeighbour(tavern,ex5);
        courtyard.setNeighbour(waterPit,ex6);
        courtyard.setNeighbour(donjon,ex7);
        
        donjon.setNeighbour(courtyard, ex7);
        
        
        garden.putItem(banana);
        
        castleWall.putCharacter(dragon);
        
        drawBridge.putItem(gun);
        drawBridge.putItem(key);
        drawBridge.putCharacter(monster);
        
        tavern.putItem(box);
        tavern.putItem(juice);
        tavern.putCharacter(helper);
        
        courtyard.putCharacter(zombie);
        courtyard.putItem(apple);
        
        player.setLocation(garden);
        System.out.println("\nYou are now in a garden in front of a castle, you have to find the princess to save her!\nType help to get all the commands avaialable and start your adventure!\n");
        boolean running = true;
        
        while(running){
            System.out.print("\n>");
            Scanner in = new Scanner(System.in);
            String userIn = in.nextLine();
            String[] userWords = userIn.split(" ");
            
            try{
                Command myVar = Command.valueOf(userWords[0].toUpperCase());
            }catch(IllegalArgumentException e){
                System.out.println("No such command, please write help for more information");
                continue;
            }
            Command myVar = Command.valueOf(userWords[0].toUpperCase());
            switch(myVar){
                case GO:
                    if(userWords.length>2){
                        System.out.println("Please enter only the name of the place after the command!");
                    }
                    else if(userWords.length<2){
                        System.out.println("Please enter the name of the place after the command");
                    }
                    else{
                        try{
                            Place selectedPlace = places.stream()
                                    .filter(p -> p.toString().equalsIgnoreCase(userWords[1]))
                                    .findFirst()
                                    .orElse(null);
                            player.goLoc(selectedPlace);
                        }catch(NullPointerException e){
                            System.out.println("No such place, type neighbours to get all the available places");
                            continue;
                        }
                        if(player.getLoc()==donjon){
                            System.out.println("You saved the princess! you won!!\n\t\t###The game has ended!!###");
                            running = false;
                        }
                        else if(player.getLoc()==waterPit){
                            System.out.println("\nYou fell in the water pit! You died!\n\n\t\t###GAME OVER!!###");
                            running = false;
                        }
                    }
                    break;
                    
                case TAKE:
                    if(userWords.length==1)
                        System.out.println("Please specify the thing that you want to take!");
                    else{
                        String itemBox = userWords[1];
                        Item selectedItemBox = player.findItemInPlaceByName(itemBox);
                        if(selectedItemBox instanceof Box){
                            if(userWords.length==2){
                                System.out.println("Please specify what do you want to take from the box!");
                            }
                            else{
                                for(int i =2; i< userWords.length; i++){
                                    String item = userWords[i];
                                    Item selectedItem = ((Box)selectedItemBox).findItemInBoxByName(item);
                                    if(selectedItem == null){
                                        System.out.println("There is no "+ item+" in the box!");
                                    }
                                    else{
                                        player.takeItem(selectedItem);
                                        ((Box)selectedItemBox).getItems().remove(selectedItem);
                                    }
                                }
                            }
                        }
                        else{
                            for(int i = 1; i < userWords.length; i++){
                                String item = userWords[i];
                                Item selectedItem = player.findItemInPlaceByName(item);
                                if(selectedItem == null){
                                    System.out.println("There is no " + item+ " here!");
                                }
                                else if(selectedItem instanceof Box){
                                    System.out.println("You cannot take thi box! However, you can take what is inside of it!");
                                }
                                else{
                                    player.takeItem(selectedItem);
                                    player.getLoc().getItems().remove(selectedItem);
                                }
                            }
                        }
                        System.out.println("You have now: "+player.getItems());
                    }
                    break;
                    
                case USE:
                    if(userWords.length == 1){
                        System.out.println("Please specify what do you want to use!");
                    }
                    else if(userWords.length==2){
                        String item = userWords[1];
                        Item selectedItem = player.findItemWithPlayerByName(item);
                        if(selectedItem == null)
                            System.out.println("You don't have a "+ item);
                        else
                            player.useItem(selectedItem);
                    }
                    else if(userWords.length==3){
                        String item1 = userWords[1];
                        String item2 = userWords[2];
                        Item selectedItem1 = player.findItemWithPlayerByName(item1);
                        Item selectedItem2 = player.findItemWithPlayerByName(item2);
                        if((selectedItem1 instanceof Gun) && (selectedItem2 instanceof Bullet)){
                            System.out.println("The gun is charged with the bullets! you now have " + ((Bullet)selectedItem2).getBullets() + "bullets inside the gun!");
                            ((Gun)selectedItem1).chargeGun(((Bullet)selectedItem2).getBullets());
                            player.getItems().remove(selectedItem2);
                        }
                        else if(selectedItem1 == null){
                            System.out.println("You don't have a "+item1);
                        }
                        else if(selectedItem2 == null)
                            System.out.println("You don't have a "+item2);
                        else
                            System.out.println("You can only use one item at a time! for charging your gun type 'use gun bullets'");
                    }
                    else
                        System.out.println("Please try again, the command doesn't take that much options!");
                    break;
                
                case NEIGHBOURS:
                    if(userWords.length>1){
                        System.out.println("The command neighbours doesn't take any options!");
                    }
                    else{
                        player.getLoc().displaymap();
                        System.out.print("\n");
                    }
                    break;
                
                case QUIT:
                    if(userWords.length>1)
                        System.out.println("The command quit doesn't take any options!");
                    else{
                        System.out.println("Thanks for playing!\n\n\t\t\t###The game has ended!###");
                        running = false;
                    }
                    break;
                    
                case ATTACK:
                    if(userWords.length>1)
                        System.out.println("The command attack doesn't take any options!");
                    else{
                        if(player.getLoc().hasBadCharacter()){
                            try{
                                player.attack(player.getLoc().getBadCharacter());
                            }catch(NullPointerException e){
                                System.out.println("There isn o one to attack in the "+ player.getLoc());
                                continue;
                            }
                            if(!player.isAlive()){
                                System.out.println("\t\t###Game Over!###");
                                running = false;
                                break;   
                            } 
                        }
                        else if(player.getLoc().hasGoodCharacter())
                            System.out.println("You can't attack the "+player.getLoc().getGoodCharacter()+"! He is a good guy!");
                        else
                            System.out.println("There is no one to attack in the "+ player.getLoc());
                    }
                    break;
                    
                case HELP:
                    if(userWords.length>1){
                        System.out.println("The command help doesn't take any options");
                    }
                    else{
                        System.out.println("THe commands avaialable are: "+ java.util.Arrays.asList(Command.values()));
                    }
                    break;
                    
                case LOOK:
                    if(userWords.length==1){
                        if(!player.getLoc().getItems().isEmpty())
                            System.out.println("In this room you can find : " +player.getLoc().getItems());
                        else
                            System.out.println("There is nothing in here!");
                    }
                    else{
                        for(int i = 1; i < userWords.length; i++){
                            String item = userWords[i];
                            Item selectedItem = player.findItemInPlaceByName(item);
                            if(selectedItem == null){
                                System.out.println("There is no "+ item+" here!");
                            }
                            else{
                                System.out.println("Indeed! there is a " + selectedItem + " here!");
                                if(selectedItem instanceof Box){
                                    if(!((Box)selectedItem).getItems().isEmpty()){
                                        System.out.println("In the "+ selectedItem+ " you cand find: "+ ((Box)selectedItem).getItems());
                                    }
                                    else{
                                        System.out.println("There is nothing in the box!");
                                    }
                                }
                            }
                        }
                    }
                    break;
                
                case SPEAK:
                    if(userWords.length>1){
                        System.out.println("the command speak doesn't take any options!");
                    }
                    else{
                        if(player.getLoc().hasGoodCharacter()){
                            player.getLoc().getGoodCharacter().speak();
                        }
                        else
                            System.out.println("THere is no one to speak to here!");
                    }
                    break;
            }
        }
            
    }

}

