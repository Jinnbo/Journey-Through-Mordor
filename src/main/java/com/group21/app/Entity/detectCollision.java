package com.group21.app.Entity;

import javax.swing.plaf.metal.MetalBorders.ScrollPaneBorder;

import com.group21.app.Screen.*;

/**
 * This class creates the detect collision logic for the character
 * This class is called everytime the character moves and contains
 * if statements for detecting collision with enemies, barriers, and punishments.
 * It also contains the logic for decrementing the character score and displaying the 
 * lose and win screen upon win and lose conditions
 */

public class detectCollision {

    ScreenPanel screen;
    UI ui = UI.getInstance();

    /** 
     * This method passes in the screen to the detectCollision
     * class so it can access screen attributes like delete instance
     * 
     * @param screen
     * @author Jimmy
     */    
    public detectCollision(ScreenPanel screen) {
        this.screen = screen;
    }

    
    /** 
     * This class contains the main logic for detecting every collision
     * with the character. It also includes incrementing or decrementing the 
     * character's health and calling the lose and win screen
     * 
     * @param entity
     * @see ScreenPanel
     * @see UI
     * @author Jimmy
     * @author jeffreywong
     */
    public void checkCell(Entity entity) {

        int[] newPos = new int[2];

        switch (entity.direction) {
            case "left":
                newPos[0] = entity.position.x - 1;
                newPos[1] = entity.position.y;
                break;

            case "right":
                newPos[0] = entity.position.x + 1;
                newPos[1] = entity.position.y;
                break;

            case "up":
                newPos[0] = entity.position.x;
                newPos[1] = entity.position.y - 1;
                break;

            case "down":
                newPos[0] = entity.position.x;
                newPos[1] = entity.position.y + 1;
                break;
        }
        // If character moves into a rock
        if (screen.cellM.map[newPos[1]][newPos[0]] == 2 || screen.cellM.map[newPos[1]][newPos[0]] == 3) {
            entity.collisionOn = true;
        }
        // If character moves into lava
        if (screen.cellM.map[newPos[1]][newPos[0]] == 1) {
            screen.character.score -= 50;
        }

        // If character moves into spider web
        if (screen.cellM.map[newPos[1]][newPos[0]] == 24) {
            screen.character.score -= 50;
        }

        // if character walks into eye of sauron cells
        if ((screen.cellM.map[newPos[1]][newPos[0]] == 20) || (screen.cellM.map[newPos[1]][newPos[0]] == 21)
                || (screen.cellM.map[newPos[1]][newPos[0]] == 22) || (screen.cellM.map[newPos[1]][newPos[0]] == 23)) {
            screen.character.score -= 100;
        }

        // If character moves into mount doom
        if (screen.cellM.map[newPos[1]][newPos[0]] == 4) {

            // Check if character collected all 20 rewards
            if (screen.character.rewardsCollected >= 20) {
                ui.makeWinWindow();
                ui.disposeGameWindow();
                ScreenPanel.deleteInstance();
                Enemy.playerFound = true;
            } else {
                ui.makeLoseWindow("rewards");
                ui.disposeGameWindow();
                ScreenPanel.deleteInstance();
            }
        }

        // if chracter moves into a cell with a bow
        if (screen.cellM.map[newPos[1]][newPos[0]] == 8) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 50;
            screen.character.rewardsCollected++;
        }
        // if chracter moves into a cell with bread
        if (screen.cellM.map[newPos[1]][newPos[0]] == 9) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 20;
            screen.character.rewardsCollected++;
        }
        // if chracter moves into a cell with a dagger
        if (screen.cellM.map[newPos[1]][newPos[0]] == 10) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 50;
            screen.character.rewardsCollected++;
        }
        // if chracter moves into a cell with a spear
        if (screen.cellM.map[newPos[1]][newPos[0]] == 11) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 50;
            screen.character.rewardsCollected++;
        }
        // if chracter moves into a cell with water
        if (screen.cellM.map[newPos[1]][newPos[0]] == 12) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 10;
            screen.character.rewardsCollected++;
        }
        // if chracter moves into a cell with gandalf (bonus)
        if (screen.cellM.map[newPos[1]][newPos[0]] == 13) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 100;
            screen.gandalf.token = true;
        }
        // if chracter moves into a cell with sam (bonus)
        if (screen.cellM.map[newPos[1]][newPos[0]] == 14) {
            screen.cellM.map[newPos[1]][newPos[0]] = 0;
            screen.character.score += 100;
            screen.sam.token = true;
        }

        // if character score drops below 0
        if (screen.character.score < 0) {
            ui.makeLoseWindow("score");
            ui.disposeGameWindow();
        }
    }
}
