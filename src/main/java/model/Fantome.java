package model;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.Cmd;
import java.util.Collections;
public class Fantome extends Personnage {
	    private String name;

	    public Fantome(int x,int y, int vie, int attaque,String name) {
	    	super( x, y,  vie,  attaque);
	        this.name = name;
	        
	    }
	    // Récupérer le nom du monstre
	    public String getName() {
	        return name;
	    }
	    
	    

	    // Dans votre classe Monster
	 

	    public Cmd deplacementAleatoire(int[][] labyrinthe) {
	        int monsterX = getX();
	        int monsterY = getY();
	        Random rand = new Random();
	        Cmd[] directions = {Cmd.LEFT, Cmd.RIGHT, Cmd.UP, Cmd.DOWN};
	        
	        // Mélanger les directions pour un déplacement aléatoire
	        for (int i = 0; i < directions.length; i++) {
	            int randomIndex = rand.nextInt(directions.length);
	            Cmd temp = directions[i];
	            directions[i] = directions[randomIndex];
	            directions[randomIndex] = temp;
	        }
	        
	        // Tenter de se déplacer dans une direction aléatoire
	        for (Cmd cmd : directions) {
	            int newX = monsterX;
	            int newY = monsterY;
	            
	            switch (cmd) {
	                case LEFT:  newX--; break;
	                case RIGHT: newX++; break;
	                case UP:    newY--; break;
	                case DOWN:  newY++; break;
	            }

	            // Vérifier si le mouvement est possible
	            if (mouvPossible(labyrinthe, newX, newY)) {
	                setX(newX);
	                setY(newY);
	                return cmd; // Retourner la commande effectuée
	            }
	        }

	        return Cmd.IDLE; // Si aucun mouvement n'est possible
	    }

	    public Cmd deplacerVersHero(int heroX, int heroY, int[][] labyrinthe) {
	        int monsterX = getX();
	        int monsterY = getY();

	        // Imprimer les positions pour débogage
	        System.out.println("Monstre position actuelle: (" + monsterX + ", " + monsterY + ")");
	        System.out.println("Héros position actuelle: (" + heroX + ", " + heroY + ")");

	        // Calculer les coordonnées de la nouvelle position
	        int newX = monsterX;
	        int newY = monsterY;

	        // Calculer la direction vers le héros
	        if (monsterX < heroX && mouvPossible(labyrinthe, monsterX + 1, monsterY)) {
	            newX++; // Aller à droite
	        } else if (monsterX > heroX && mouvPossible(labyrinthe, monsterX - 1, monsterY)) {
	            newX--; // Aller à gauche
	        }

	        if (monsterY < heroY && mouvPossible(labyrinthe, monsterX, monsterY + 1)) {
	            newY++; // Aller en bas
	        } else if (monsterY > heroY && mouvPossible(labyrinthe, monsterX, monsterY - 1)) {
	            newY--; // Aller en haut
	        }

	        // Vérifier si le mouvement est possible
	        if (newX != monsterX || newY != monsterY) {
	            // Déplacer le monstre
	            setX(newX);
	            setY(newY);
	            System.out.println("Monstre déplacé vers: (" + newX + ", " + newY + ")"); // Débogage
	            // Retourner la commande qui a été effectuée
	            if (newX != monsterX) {
	                return newX > monsterX ? Cmd.RIGHT : Cmd.LEFT;
	            } else {
	                return newY > monsterY ? Cmd.DOWN : Cmd.UP;
	            }
	        }

	        // Si le mouvement n'est pas possible, retourner IDLE
	        System.out.println("Mouvement impossible, reste IDLE."); // Débogage
	        return Cmd.IDLE;
	    }
	    public void mettreAJourMonstre(int heroX, int heroY, int[][] labyrinthe) {
	        int distance = Math.abs(heroX - getX()) + Math.abs(heroY - getY());

	        if (distance < 5) { // Si le héros est à moins de 5 unités, suivre le héros
	            deplacerVersHero(heroX, heroY, labyrinthe);
	        } else { // Sinon, se déplacer aléatoirement
	            deplacementAleatoire(labyrinthe);
	        }
	    }





}