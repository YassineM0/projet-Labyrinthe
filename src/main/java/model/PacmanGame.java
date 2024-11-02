package model;

import java.util.ArrayList;
import java.util.Random;

import engine.Cmd;
import engine.Game;
import model.casesSpeciales.Tresor;

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
	ArrayList<Tresor> tresors;
	private ArrayList<Monster> monstres;
	private boolean finished;

	public ArrayList<Tresor> getTresors() {
		return tresors; // Returns the list of treasures
	}

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
					labyrinthe[i][j] = 2;
				} else
				{
					labyrinthe[i][j] = (random.nextInt(100) < 10) ? 0 : 1;
				}
			}
		}
		labyrinthe[1][1] = 1;
		this.tresors = new ArrayList<>();

    	for (int i = 0; i < 1; i++) {
        	int x, y;
        	do {
          	  x = random.nextInt(labyrinthe.length);
          	  y = random.nextInt(labyrinthe[0].length);
        	} while (labyrinthe[x][y] != 1);
			labyrinthe[x][y] = 3;
        	tresors.add(new Tresor(x, y)); 
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
		this.finished = false;
		this.hero = new Hero(x, y, vie, attaque);
		this.tresors = new ArrayList<>();
		genererLabyrinthe();
		this.monstres = new ArrayList<>();
	//	monstres.add(new Monster(3, 3, 10, 2, "Goblin"));
	  //  monstres.add(new Monster(6, 6, 10, 3, "Orc"));
	    monstres.add(new Monster(10, 10, 15, 5, "Dragon"));
	}

	private void verifierCase() {
		// Check for treasure collisions
		for (Tresor tresor : tresors) {
			// Debug output
			System.out.println("Checking collision: Hero at (" + hero.getX() + ", " + hero.getY() + ") with Treasure at (" + tresor.getX() + ", " + tresor.getY() + ")");
			
			if (hero.getX() == tresor.getX() && hero.getY() == tresor.getY()) {
				this.finished = true;
				System.out.println("Treasure collected! Game finished: " + this.isFinished()); // Confirm game state
				break; // Exit loop after collecting
			}
		}
	}
	

	public void setGameFinished(boolean finished) {
        this.finished = finished;
    }

	
	public Monster trouverMonstreProche() {
        // Simplification : on suppose que le monstre le plus proche est le premier de la liste
        return monstres.isEmpty() ? null : monstres.get(0);
    }
	public Hero getHero() {
        return hero;  // Renvoie l'instance du héros
    }
	
	public ArrayList<Monster> getMonstres() {
	    return monstres;
	}
	
	
	
	
	

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		//System.out.println("Execute "+commande);
		hero.move(commande, labyrinthe);

		verifierCase();

		if (commande == Cmd.ATTACK) {
            Monster monstreProche = detecterMonstre(); 
            if (monstreProche != null) {// Trouver un monstre à proximité
            hero.attack(monstreProche);}// Attaquer le monstre
            
		}
		for (Monster monstre : monstres) {
	        monstre.mettreAJourMonstre(hero.getX(), hero.getY(), labyrinthe);}
		//for (Monster monstre : monstres) {
//	        Cmd moveCmd = monstre.deplacerVersHero(hero.getX(), hero.getY(), labyrinthe);
	//        monstre.move(moveCmd, labyrinthe);
	  //  }
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
		return finished;
	}

}
