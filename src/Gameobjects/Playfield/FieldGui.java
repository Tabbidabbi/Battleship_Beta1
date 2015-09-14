/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gameobjects.Playfield;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Tobias
 */
public class FieldGui extends JButton implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -787848770021504750L;
    private boolean active;
    private boolean isShot;
    private boolean isWater;
    private boolean isHit;
    private boolean hasShip;
    private int shipNumber;
    private String fieldStatusPlayer;
    private String fieldStatusOpponent;
    private String fieldNumber;

    public FieldGui() {
        setFont(new Font("Serif", Font.BOLD, 30 ));
        setBorder(new LineBorder(new Color(200, 214, 222)));
        setVisible(true);
        setBackground(new Color(64, 164, 223));
        this.active = true;
        
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isShot() {
		return isShot;
	}

	public void setIsShot(boolean isShot) {
		this.isShot = isShot;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setIsWater(boolean isWater) {
		this.isWater = isWater;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	public boolean getHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
	}

	@Override
    public void setText(String text) {
        super.setText(text); //To change body of generated methods, choose Tools | Templates.
    }
}
