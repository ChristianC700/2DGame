package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4659943024124176745L;
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3;//Scaling 
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	
	//4:3 ratio
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels

		
	// FPS and Drawing intervals
	final int FPS = 60;
	final int nanosecond = 1000000000;
	final int millisecond = 1000000;
	final double drawInterval = nanosecond/FPS; //0.0166 seconds
	double nextDrawTime;
			
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //For time
	Player player = new Player(this,keyH);
	
	
	public GamePanel() {
		this.setPreferredSize(getPreferredSize());
		this.setBackground(getBackground());
		this.setDoubleBuffered(true);//Good for rendering
		this.addKeyListener(keyH);
		this.setFocusable(true); //GamePanel can be "focused" to receive key input
	}
	
	
	
	
	public Dimension getPreferredSize(){
		return new Dimension(screenWidth, screenHeight);
	}
	public Color getBackground(){
		return Color.black;
	}

	public void startGameThread() {
		gameThread = new Thread(this);//Pass Game Panel
		gameThread.start();
	}
	
	@Override
	public void run() {
		nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {//As long as gamethread exists
			

			// 1 UPDATE: update information (e.g. character positions)
			update();
			
			
			// 2 DRAW: draw screen with updated information per FPS
			repaint();
			
			
			waitForNextDrawTime();
		}
	}
	
	private void waitForNextDrawTime() {
		try {
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime = remainingTime/millisecond;
			
			
			if(remainingTime < 0) {
				remainingTime = 0;
			}
			Thread.sleep((long) remainingTime);//Long takes millisecond
			
			nextDrawTime += drawInterval;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);

		
		g2.dispose();//Dispose graphic context
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
