package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GamePlay extends JFrame implements KeyListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private int moves = 0;
	
	private ImageIcon rightMouse;
	private ImageIcon leftMouse;
	private ImageIcon upMouse;
	private ImageIcon downMouse;
	
	private int lengthOfSnake = 3;
	
	private int score = 0;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeimage;
	
	private int [] enemyypos = {25, 50, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
								475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	
	private int [] enemyxpos = {75, 100, 125, 150,175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 
								500, 525, 550, 575, 600, 625};
	
	private ImageIcon enemyimage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private ImageIcon titleImage;

	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
	
		if(moves ==0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		//draw the title  image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw the title image
		titleImage = new ImageIcon("title.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 877);
		
		//draw backgound for the gameplay
		g.setColor(Color.black);
		g.fillRect(24, 75, 850, 875);
		
		//draw a score board
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Length "+ lengthOfSnake, 780, 30);
		
		//length of snake
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Scores "+ score, 780, 50);
		
		rightMouse = new ImageIcon("1.png");
		rightMouse.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a < lengthOfSnake; a++) {
			if(a == 0 && right) {
				rightMouse = new ImageIcon("1.png");
				rightMouse.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a == 0 && left) {
				leftMouse = new ImageIcon("2.png");
				leftMouse.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a == 0 && down) {
				downMouse = new ImageIcon("3.png");
				downMouse.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a == 0 && up) {
				upMouse = new ImageIcon("4.png");
				upMouse.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a != 0) {
				snakeimage = new ImageIcon("tail.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		
		enemyimage = new ImageIcon("tail.png");
		
		if((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0])) {
			score++;
			lengthOfSnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int b = 1; b < lengthOfSnake; b++) {
			if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game over ", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART ", 350, 340);
			}
		}
		
		g.dispose();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int r =lengthOfSnake-1; r >= 0; r--) {
				snakeylength[r-1] = snakeylength[r];
			}
			for(int r = lengthOfSnake; r >= 0; r--) {
				if(r == 0) {
					snakexlength[r] =snakexlength[r] + 25;
				}else {
					snakexlength[r] =snakexlength[r];
				}
				if(snakexlength[r] > 850) {
					snakexlength[r] = 25;
					
				}
			}
			repaint();
		}
		
		if(left) {
			for(int r =lengthOfSnake-1; r >= 0; r--) {
				snakeylength[r-1] = snakeylength[r];
			}
			for(int r = lengthOfSnake; r >= 0; r--) {
				if(r == 0) {
					snakexlength[r] =snakexlength[r] - 25;
				}else {
					snakexlength[r] =snakexlength[r];
				}
				if(snakexlength[r] < 25) {
					snakexlength[r] = 850;
					
				}
			}
			repaint();
		}
		
		if(up) {
			for(int r =lengthOfSnake-1; r >= 0; r--) {
				snakexlength[r-1] = snakexlength[r];
			}
			for(int r = lengthOfSnake; r >= 0; r--) {
				if(r == 0) {
					snakeylength[r] =snakeylength[r] - 25;
				}else {
					snakeylength[r] =snakeylength[r];
				}
				if(snakeylength[r] < 75) {
					snakeylength[r] = 625;
					
				}
			}
			repaint();
		}
		
		if(down) {
			for(int r =lengthOfSnake-1; r >= 0; r--) {
				snakexlength[r-1] = snakexlength[r];
			}
			for(int r = lengthOfSnake; r >= 0; r--) {
				if(r == 0) {
					snakeylength[r] =snakeylength[r] + 25;
				}else {
					snakeylength[r] =snakeylength[r];
				}
				if(snakeylength[r] < 625) {
					snakeylength[r] = 825;
					
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//when spacebar is pressed
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		//right key
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			right = true;
			if(!left) {
				right = true;
			}else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		
		//left key
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moves++;
			left = true;
			if(!right) {
				left = true;
			}else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		
		//up key
		if(e.getKeyCode() == KeyEvent.VK_UP){
			moves++;
			up = true;
			if(!down) {
				up = true;
			}else {
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		
		//down key
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moves++;
			down = true;
			if(!up) {
				down = true;
			}else {
				down = false;
				up = true;
			}
			
			left = false;
			right = false;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
