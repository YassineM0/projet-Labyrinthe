package model;

import java.util.ArrayList;
import java.util.Random;

import engine.Cmd;
import engine.Game;
import model.casesSpeciales.Passage;
import model.casesSpeciales.Tresor;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private boolean gameOver = true;
	private Hero hero;
	private int[][] labyrinthe;
	ArrayList<Tresor> tresors;
	ArrayList<Passage> passages;
	private ArrayList<Monster> monstres;
	private ArrayList<Fantome> fantomes;
	private boolean finished;
	Passage p = new Passage();
	 private int niveau; 

	public ArrayList<Tresor> getTresors() {
		return tresors; // Returns the list of treasures
	}

	private void genererLabyrinthe()
	{
		int width = 28;
		int height = 16;
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
		//tresor
        int x, y;
        do {
          	x = random.nextInt(labyrinthe.length);
          	y = random.nextInt(labyrinthe[0].length);
        } while (labyrinthe[x][y] != 1);
		labyrinthe[x][y] = 3;
        tresors.add(new Tresor(x, y)); 
		//case magique
		do {
			x = random.nextInt(labyrinthe.length/2);
			y = random.nextInt(labyrinthe[0].length/2);
	  } while (labyrinthe[x][y] != 1);
	  labyrinthe[x][y] = 4;
	  //passage
	  int x1, x2, y1, y2;
		do {
    		x1 = random.nextInt(labyrinthe.length/2);
    		y1 = random.nextInt(labyrinthe[0].length/2);
    		x2 = random.nextInt(labyrinthe.length);
    		y2 = random.nextInt(labyrinthe[0].length);
		} while (labyrinthe[x1][y1] != 1 || labyrinthe[x2][y2] != 1);
		p.setX1(x1);
		p.setX2(x2);
		p.setY1(y1);
		p.setY2(y2);
		labyrinthe[x1][y1] = 5;
		labyrinthe[x2][y2] = 5;	  
}
	

	

	public int[][] getLabyrinthe()
	{
		return labyrinthe;
	}

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(int x, int y, int vie, int attaque, int niveau) {
		this.finished = false;
		this.hero = new Hero(x, y, vie, attaque, false);
		this.tresors = new ArrayList<>();
		genererLabyrinthe();
		this.niveau = 1;
		this.monstres = new ArrayList<>();
		monstres.add(new Monster(13, 13, 10, 2, "Goblin"));
	
	  //  monstres.add(new Monster(6, 6, 10, 3, "Orc"));
	   //monstres.add(new Monster(10, 10, 15, 5, "Dragon"));
		this.fantomes = new ArrayList<>();
		fantomes.add(new Fantome(8, 11, 15, 7, "Ghosty"));
	//	fantomes.add(new Fantome(5, 5, 12, 6, "Spectre"));
	}

	private void verifierCase() {
		// Check for treasure collisions
		for (Tresor tresor : tresors) {
			// Debug output
			System.out.println("Checking collision: Hero at (" + hero.getX() + ", " + hero.getY() + ") with Treasure at (" + tresor.getX() + ", " + tresor.getY() + ")");
			
			if (hero.getX() == tresor.getX() && hero.getY() == tresor.getY()) {
				this.finished = true;
				this.gameOver = false;
				System.out.println("Treasure collected! Game finished: " + this.isFinished()); // Confirm game state
				break; // Exit loop after collecting
			}
		}
		for (Fantome fantome : fantomes) {
		    if (hero.getX() == fantome.getX() && hero.getY() == fantome.getY()) {
		        hero.setVie(hero.getVie() - fantome.getAttaque());
		        System.out.println("Collision avec le fantôme : " + fantome.getName());
		        System.out.println("Vie restante du héros : " + hero.getVie());

		        // Vérifier si le héros est mort
		        if (hero.getVie() <= 0) {
		            System.out.println("Le héros est mort !");
		            this.finished = true; // Terminer le jeu
		        }
		    }
		}



		if(labyrinthe[hero.getX()][hero.getY()] == 4)
		{
			hero.activateMagic();
		}
		
		if(labyrinthe[hero.getX()][hero.getY()] == 5)
		{
			if(hero.getX() == p.getX1() && hero.getY() == p.getY1())
        {
            hero.setX(p.getX2());
			hero.setY(p.getY2());
        }
		else if(hero.getX() == p.getX2() && hero.getY() == p.getY2())
		{
			hero.setX(p.getX1());
			hero.setY(p.getY1());
		}
		}
		
	}
	

	public void setGameFinished(boolean finished) {
        this.finished = finished;
    }

	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
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
	
	 public ArrayList<Fantome> getFantomes() {
	        return fantomes;
	    }
	
	
	

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		//System.out.println("Execute "+commande);
		if (hero.isMagicActive()) {  
			hero.magicMove(commande, labyrinthe);
		} else {
			hero.move(commande, labyrinthe);
		}
		verifierCase();

		if (commande == Cmd.ATTACK) {
            Monster monstreProche = detecterMonstre(); 
            if (monstreProche != null) {// Trouver un monstre à proximité
            hero.attack(monstreProche);}// Attaquer le monstre
            
		}
		verifierCase();
		for (Monster monstre : monstres) {
	        monstre.mettreAJourMonstre(hero.getX(), hero.getY(), labyrinthe);}
		//for (Monster monstre : monstres) {
//	        Cmd moveCmd = monstre.deplacerVersHero(hero.getX(), hero.getY(), labyrinthe);
	//        monstre.move(moveCmd, labyrinthe);
	  //  }
		// for (Fantome fantome : Fantome) {
	            // Logique de mouvement des fantômes
	            // Vous pouvez ici gérer leur déplacement, par exemple en les faisant se déplacer vers le héros
		verifierCase();
		for (Fantome fantome : fantomes) {
		    fantome.mettreAJourMonstre(hero.getX(), hero.getY(), labyrinthe, hero,niveau);
		}
		verifierCase();
	    
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

	public boolean isGameOver() {
		// le jeu n'est jamais fini
		return gameOver;
	}

}
