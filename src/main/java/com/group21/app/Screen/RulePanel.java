package com.group21.app.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * This class creates instantiates the rule panel and positions
 * all the images in it
 * 
 * @author Jimmy
 */
public class RulePanel extends JPanel implements ActionListener,KeyListener {

    UI ui = UI.getInstance();
    public static JFrame gameWindow;

    /**
     * The constructor creates the images on the rule panel
     * and connects the continue button to the game window
     * 
     * @param window
     * @author Jimmy
     */
    public RulePanel(JFrame window){
        // Set size of screen
        setPreferredSize(new Dimension(1350, 675));

        // create JButton for playBTN
        JButton continueBTN = new JButton(new ImageIcon("src/main/resources/images/continue.png"));
        continueBTN.setFocusable(false); // to prevent button from taking focus in window
        continueBTN.setOpaque(true);
        continueBTN.setBorderPainted(true);
        continueBTN.setBounds(460,590, 400,70);
        setLayout(null);

        // Add playBTN
        add(continueBTN);
        continueBTN.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                // Go to game window if playBTN is pressed
                ui.makeGameWindow();
                JComponent comp = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(comp);
                window.dispose();
            }
        });
    }
    
    
    /** 
     * Paints the background image onto the rule screen
     * 
     * @param graphic
     * @author Jimmy
     */
    @Override
    public void paintComponent(Graphics graphic) {

        Image background = new ImageIcon("src/main/resources/images/ruleScreen.png").getImage();
        super.paintComponent(graphic);
        graphic.drawImage(background,0, 0, this.getWidth(), this.getHeight(), null);
    }

    // won't be implementing this
    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
