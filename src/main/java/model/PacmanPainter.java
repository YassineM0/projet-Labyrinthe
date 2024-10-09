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
		for(int i = 0;i < labyrinthe.length;i++)
		{
			for(int j = 0;j < labyrinthe[i].length;j++)
			{
				if(labyrinthe[i][j] == 0){
				crayon.setColor(Color.GRAY);
				crayon.fillRect(i * 50, j * 50, 50, 50);
				} else 
				{
					crayon.setColor(Color.WHITE);
					crayon.fillRect(i * 50, j * 50, 50, 50);
				}
			} 
		}
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
