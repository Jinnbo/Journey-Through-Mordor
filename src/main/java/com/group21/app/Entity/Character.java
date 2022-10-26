package com.group21.app.Entity;

import com.group21.app.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.security.Key;

public class Character extends Entity implements KeyListener {
    // main character attributes
    private Image characterLeft;
    private Image characterRight;
    public Point position;
    private String imageDirection;

    Screen screen;

    // character constructor
    public Character(Screen screen) {
        loadImage();
        position = new Point(1,1);
        xPos = 1; yPos = 1;
        imageDirection = "right";

        this.screen = screen;
    }

    // load image method
    private void loadImage () {
        // load main character's images
        characterLeft = new ImageIcon("src/main/resources/images/frodo_left.png").getImage();
        characterRight = new ImageIcon("src/main/resources/images/frodo_right.png").getImage();
    }

    // draw image method
    // checks the direction of the character and draws the appropriate image
    public void draw(Graphics graphic) {
        Image img = null;

        switch(imageDirection) {
            case "left":
                img = characterLeft;
                break;
            case "right":
                img = characterRight;
                break;
        }

        graphic.drawImage(img, position.x*Screen.cellSize, position.y*Screen.cellSize, null);
    }

    // have to include this method's definition, but we won't be using it
    @Override
    public void keyTyped(KeyEvent e) {}

    // move character along x and y axes depending on user input
    // move character 1 cell at a time
    // for left and right keys, update the direction of the character
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // for reading keyboard input from user
        if(key == KeyEvent.VK_UP) {
            direction = "up";
        }
        if(key == KeyEvent.VK_DOWN) {
            direction = "down";
        }
        if(key == KeyEvent.VK_LEFT) {
            direction = "left";
            imageDirection = "left";
        }
        if(key == KeyEvent.VK_RIGHT) {
            direction = "right";
            imageDirection = "right";
        }

        // Check cell collision
        collisionOn = false;
        screen.cChecker.checkCell(this);

        // if collision is false player can move
        if (collisionOn == false){
            switch(direction){
                case "up":
                    xPos -= 1;
                    position.translate(0, -1);
                    break;
                case "down":
                    xPos +=1;
                    position.translate(0, 1);
                    break;
                case "left":
                    yPos -=1;
                    position.translate(-1, 0);
                    break;
                case "right":
                    yPos +=1;
                    position.translate(1, 0);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}