///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.'
// * Author: Sean Casaus
// */

 package CSE360;

 import javax.swing.*;

 public class Team4Ghost extends JLabel implements Runnable {
   

 	public Team4Ghost() { //Constructor
 		ImageIcon newImage = new ImageIcon("Pac-Man-Ghost-PNG-Transparent-Image.png"); //gets the ghost image
 		setIcon(new ImageIcon(newImage.getImage().getScaledInstance(29, 34, 0))); //scales the image
 	}

 	//Basic Algorithm that moves the ghost along the x axis at the bottom of a panel
 	@Override
 	public void run() {
 		int x = 0;
 		int y = 50;
 		int Speed = 3; //Change Speed of Ghost
 		int xSpeed = Speed;
 		int width = 61; //Difference between width of panel(90) and width of ghost
 		int height = 56; //Difference between height of panel(90) and height of ghost
 		
 		while (true) {
 			setBounds(x, y, width, height);
 			if (x < 0) {
 				xSpeed = Speed;
 			}
 			if (x > width) {
 				xSpeed = -Speed;
 			}
 			x += xSpeed;
 			try {
 				Thread.sleep(50);
 			} catch (InterruptedException e) {
 				System.out.println("Error");
 			}
 		}
 	}
 }
