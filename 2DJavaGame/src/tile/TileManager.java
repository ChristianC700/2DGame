package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[6];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/spawn.txt");
	}
	
	public void getTileImage() {
		try {
			//0 = grass
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
		
			//1 = wall
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
		
			//2 = water
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		
			//3 = earth
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
		
			//4 = sand
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		
			//5 = tree
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int x = 0;
		int y = 0;
		int tileNum = 0;
		
		for (int row = 0; row < gp.maxScreenRow; row++) {
		    for (int col = 0; col < gp.maxScreenCol; col++) {
		    	tileNum = mapTileNum[col][row];
		    	x = col * gp.tileSize;
		    	y = row * gp.tileSize;
		    	g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
		    }
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for (int row = 0; row < gp.maxScreenRow; row++) {
			    String line = br.readLine();
			    String numbers[] = line.split(" ");
			    
			    for (int col = 0; col < gp.maxScreenCol; col++) {
			        int num = Integer.parseInt(numbers[col]);
			        mapTileNum[col][row] = num;
			        //System.out.println(col + " " + row);
			    }
			}
		}catch(Exception e) {
			
		}
	}
}
