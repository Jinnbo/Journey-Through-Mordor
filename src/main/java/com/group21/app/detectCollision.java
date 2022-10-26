package com.group21.app;

import com.group21.app.Entity.*;
import java.util.Map;
import com.group21.app.Cell.*;

public class detectCollision {

    Screen screen;

    public detectCollision(Screen screen){
        this.screen = screen;
    }

    public void checkCell(Entity entity){

        /* IDEA
         * GET PLAYER POSITION
         * GET CELL MAP AT PLAYER POSITION
         * IF cellmap at player position == 2 then collosionOn = true so the character cannot go in that direction
        */
        int[] newPos = new int[2];

        switch(entity.direction){
            case "left":
                newPos[0] = entity.xPos;
                newPos[1] = entity.yPos-1;
                if (screen.cellM.map[newPos[0]][newPos[1]] == 2 || screen.cellM.map[newPos[0]][newPos[1]] == 3){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                newPos[0] = entity.xPos;
                newPos[1] = entity.yPos+1;
                if (screen.cellM.map[newPos[0]][newPos[1]] == 2 || screen.cellM.map[newPos[0]][newPos[1]] == 3){
                    entity.collisionOn = true;
                }
                break;
            case "up":
                newPos[0] = entity.xPos-1;
                newPos[1] = entity.yPos;
                if (screen.cellM.map[newPos[0]][newPos[1]] == 2 || screen.cellM.map[newPos[0]][newPos[1]] == 3){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                newPos[0] = entity.xPos+1;
                newPos[1] = entity.yPos;
                if (screen.cellM.map[newPos[0]][newPos[1]] == 2 || screen.cellM.map[newPos[0]][newPos[1]] == 3){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}