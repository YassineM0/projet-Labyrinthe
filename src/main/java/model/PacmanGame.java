package model;

import engine.Cmd;
import engine.Game;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private Hero hero;
	private int[][] labyrinthe;
	

	private void genererLabyrinthe()
	{
		int width = 14;
		int height = 14;
		this.labyrinthe = new int[height][width];

		for(int i = 0;i < height;i++)
		{
			for(int j = 0;j < width;j++)
			{
				if(i == 0 || i == height - 1 || j == 0 || j == width - 1)
				{
					labyrinthe[i][j] = 0;
				} else
				{
					labyrinthe[i][j] = 1;
				}
			}
		}
	}

	public int[][] getLabyrinthe()
	{
		return labyrinthe;
	}

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(int x, int y, int vie, int attaque) {
		this.hero = new Hero(x, y, vie, attaque);
		genererLabyrinthe();
	}
	public Hero getHero() {
        return hero;  // Renvoie l'instance du héros
    }

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		System.out.println("Execute "+commande);
		hero.move(commande, labyrinthe);
	}



	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
