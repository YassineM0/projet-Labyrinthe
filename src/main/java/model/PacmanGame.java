package model;

import java.util.ArrayList;
import java.util.Random;

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
	private ArrayList<Monster> monstres;
	

	private void genererLabyrinthe()
	{
		int width = 14;
		int height = 14;
		this.labyrinthe = new int[height][width];
		Random random = new Random();

		for(int i = 0;i < height;i++)
		{
			for(int j = 0;j < width;j++)
			{
				if(i == 0 || i == height - 1 || j == 0 || j == width - 1)
				{
					labyrinthe[i][j] = 0;
				} else
				{
					labyrinthe[i][j] = (random.nextInt(100) < 10) ? 0 : 1;
				}
			}
		}
		labyrinthe[1][1] = 1;
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
		this.monstres = new ArrayList<>();
	}
	public Monster trouverMonstreProche() {
        // Simplification : on suppose que le monstre le plus proche est le premier de la liste
        return monstres.isEmpty() ? null : monstres.get(0);
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
		if (commande == Cmd.ATTACK) {
            Monster monstreProche = detecterMonstre();  // Trouver un monstre à proximité
            hero.attack(monstreProche);}// Attaquer le monstre
	}
	public Monster detecterMonstre() {
        for (Monster monstre :monstres) {
            int[] positionMonstre = monstre.getCoord();
            int xMonstre = positionMonstre[0];
            int yMonstre = positionMonstre[1];

            if (Math.abs(hero.getX() - xMonstre) <= 1 && Math.abs(hero.getY() - yMonstre) <= 1) {
                return monstre;
            }
        }
        return null;
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
