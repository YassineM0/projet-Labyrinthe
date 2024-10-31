package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {
	private PacmanGame game;
	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 700;
	protected static final int HEIGHT = 700;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame game) {
		this.game = game;
	}

	/**
	 * Importation des images
	 */

	Image herosImg;
	Image fondImg;
	Image murImg;
	Image fantomeImg;
	Image monstreImg;
	Image trouImg;
	Image piegeImg;
	Image tresorImg;
	Image bonusImg;
	Image magieImg;

	void images(){
	try {
		herosImg = ImageIO.read(new File("src/main/java/img/Heros.png"));
		fondImg = ImageIO.read(new File("src/main/java/img/Blanc.png"));
		murImg = ImageIO.read(new File("src/main/java/img/Mur.png"));
		monstreImg = ImageIO.read(new File("src/main/java/img/Monstre.png"));
		fantomeImg = ImageIO.read(new File("src/main/java/img/Fantome.png"));
		trouImg = ImageIO.read(new File("src/main/java/img/Passage.png"));
		piegeImg = ImageIO.read(new File("src/main/java/img/Piege.png"));
		tresorImg = ImageIO.read(new File("src/main/java/img/Tresor.png"));
		bonusImg = ImageIO.read(new File("src/main/java/img/Bonus.png"));
		magieImg = ImageIO.read(new File("src/main/java/img/Magic.png"));
	}
	catch(IOException exc) {
		exc.printStackTrace();
	}}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */

	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		int[][] labyrinthe = game.getLabyrinthe();
		images();
		
		for (int i = 0; i < labyrinthe.length; i++) {
			for (int j = 0; j < labyrinthe[i].length; j++) {
				if (labyrinthe[i][j] == 1) {
					crayon.drawImage(fondImg, j * 50, i * 50, 50, 50, null);
				}else if (labyrinthe[i][j] == 2) {
					crayon.drawImage(murImg, j * 50, i * 50, 50, 50, null);
				}
				 else { // Wall
					crayon.drawImage(murImg, j * 50, i * 50, 50, 50, null);
				}
			}
		}
	
		drawHero(crayon);
		for (Monster monstre : game.getMonstres()) {
	        drawMonster(crayon, monstre);
	    }
	}
	private void drawHero(Graphics2D crayon) {
        Hero hero = game.getHero();  
        int[] heroCoord = hero.getCoordonnees();
        
        crayon.drawImage(herosImg,heroCoord[1] * 50 + 10, heroCoord[0] * 50 + 10, 30, 30,null); 
    }
	
	private void drawMonster(Graphics2D crayon, Monster monstre) {
	    int[] monsterCoord = monstre.getCoordonnees();
	    crayon.drawImage(monstreImg,monsterCoord[1] * 50 + 10, monsterCoord[0] * 50 + 10, 30, 30,null);
	}
	
	
	
	
	
	
	

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
