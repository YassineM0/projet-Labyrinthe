package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		int[][] labyrinthe = game.getLabyrinthe();
		
		for (int i = 0; i < labyrinthe.length; i++) {
			for (int j = 0; j < labyrinthe[i].length; j++) {
				if (labyrinthe[i][j] == 1) {
					crayon.setColor(Color.WHITE);
				}else if (labyrinthe[i][j] == 2) {
					crayon.setColor(Color.BLACK);
				}
				 else { // Wall
					crayon.setColor(Color.GRAY);
				}
				crayon.fillRect(j * 50, i * 50, 50, 50);
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
        
        
        crayon.setColor(Color.BLUE);  
        crayon.fillRect(heroCoord[1] * 50 + 10, heroCoord[0] * 50 + 10, 30, 30); 
    }
	
	private void drawMonster(Graphics2D crayon, Monster monstre) {
	    int[] monsterCoord = monstre.getCoordonnees();
	    crayon.setColor(Color.RED); // Couleur pour les monstres
	    crayon.fillRect(monsterCoord[1] * 50 + 10, monsterCoord[0] * 50 + 10, 30, 30);
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
