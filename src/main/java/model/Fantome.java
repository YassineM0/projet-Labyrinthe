package model;

import java.util.Random;

import engine.Cmd;

public class Fantome extends Personnage {
	private String name;
    private int deplacementCooldown = 0; // Compteur pour ralentir le d�placement
    private final int cooldownMax = 3;  // Nombre de cycles avant que le monstre puisse bouger

    private int monsterSpeed = 2; // Le monstre se d�place de 2 cases au lieu de 1 (vitesse r�duite)
    private int moveCounter = 0;  // Compteur pour r�guler la fr�quence de d�placement du monstre
    private int vitesse; // Vitesse du fantôme (nombre de cycles avant chaque mouvement)

    public Fantome(int x, int y, int vie, int attaque, String name) {
        super(x, y, vie, attaque);
        this.name = name;
        this.vitesse = 5; // Par défaut, vitesse initiale
    }

    public String getName() {
        return name;
    }

    // Ajuster la vitesse du fantôme en fonction du niveau
    public void ajusterVitesse(int niveau) {
        this.vitesse = Math.max(1, 5 - niveau); // Plus le niveau est élevé, plus les fantômes sont rapides
    }

    // Déplacement aléatoire avec gestion des murs

    public Cmd deplacementAleatoire(int[][] labyrinthe) {
        if (deplacementCooldown < cooldownMax) {
            deplacementCooldown++;
            return Cmd.IDLE;  // Rester immobile si le cooldown n'est pas �coul�
        }

        // R�initialiser le cooldown apr�s un mouvement
        deplacementCooldown = 0;

        int monsterX = getX();
        int monsterY = getY();
        Random rand = new Random();
        Cmd[] directions = {Cmd.LEFT, Cmd.RIGHT, Cmd.UP, Cmd.DOWN};

        // M�langer les directions pour un d�placement al�atoire
        for (int i = 0; i < directions.length; i++) {
            int randomIndex = rand.nextInt(directions.length);
            Cmd temp = directions[i];
            directions[i] = directions[randomIndex];
            directions[randomIndex] = temp;
        }

        // Tenter de se d�placer dans une direction al�atoire
        for (Cmd cmd : directions) {
            int newX = monsterX;
            int newY = monsterY;

            switch (cmd) {
                case LEFT:  newX--; break;
                case RIGHT: newX++; break;
                case UP:    newY--; break;
                case DOWN:  newY++; break;
            }

            setX(newX);
            setY(newY);
            return cmd; // Retourner la commande effectu�e
            
        }

        return Cmd.IDLE; // Si aucun mouvement n'est possible
    }
    
    // D�placement vers le h�ros avec un facteur de vitesse
    public Cmd deplacerVersHero(int heroX, int heroY, int[][] labyrinthe) {
        int monsterX = getX();
        int monsterY = getY();
        int perimetreChasse = 5;  // P�rim�tre de chasse du monstre
        
        if (deplacementCooldown < cooldownMax) {
            deplacementCooldown++;
            return Cmd.IDLE;  // Rester immobile si le cooldown n'est pas �coul�
        }

        // R�initialiser le cooldown apr�s un mouvement
        deplacementCooldown = 0;

        if (Math.abs(monsterX - heroX) <= perimetreChasse && Math.abs(monsterY - heroY) <= perimetreChasse) {
            // Si le monstre est dans le p�rim�tre de chasse, il se d�place vers le h�ros avec une vitesse r�duite
            if (moveCounter % monsterSpeed == 0) {
                int newX = monsterX;
                int newY = monsterY;

                if (monsterX < heroX && mouvPossible(labyrinthe, monsterX + 1, monsterY)) {
                    newX++; // Aller � droite
                } else if (monsterX > heroX && mouvPossible(labyrinthe, monsterX - 1, monsterY)) {
                    newX--; // Aller � gauche
                }

                if (monsterY < heroY && mouvPossible(labyrinthe, monsterX, monsterY + 1)) {
                    newY++; // Aller en bas
                } else if (monsterY > heroY && mouvPossible(labyrinthe, monsterX, monsterY - 1)) {
                    newY--; // Aller en haut
                }

                // V�rifier si le mouvement est possible
                if (newX != monsterX || newY != monsterY) {
                    setX(newX);
                    setY(newY);
                    moveCounter = 0; // R�initialiser le compteur apr�s un d�placement
                    if (newX != monsterX) {
                        return newX > monsterX ? Cmd.RIGHT : Cmd.LEFT;
                    } else {
                        return newY > monsterY ? Cmd.DOWN : Cmd.UP;
                    }
                }
            }

            moveCounter++;  // Incr�menter le compteur de d�placements
        }

        return Cmd.IDLE; // Si le h�ros est hors du p�rim�tre, le monstre ne se d�place pas
    }

    // Mettre � jour le mouvement du fantome (poursuite+attaque ou  déplacement aléatoire)
    public void mettreAJourMonstre(int heroX, int heroY, Hero hero, int[][] labyrinthe) {
        int distance = Math.abs(heroX - getX()) + Math.abs(heroY - getY());

        if (distance < 5) {  // Si le h�ros est � moins de 5 unit�s, suivre le h�ros
            deplacerVersHero(heroX, heroY, labyrinthe);
        } else {  // Sinon, se d�placer al�atoirement
            deplacementAleatoire(labyrinthe);
        }
        // Vérifier si le héros est adjacent et attaquer
        attaquerHero(hero);
        
        // Vérifier si la vie du monstre est inférieure ou égale à 0, et s'il doit disparaître
        if (getVie() <= 0) {
            disparaître();  // Appeler la méthode qui gère la disparition du monstre
            // Suppression du monstre de la liste des monstres
            PacmanGame.getMonstres().remove(this);  // Suppression de l'objet du monstre
        }
    }
    public void disparaître() {
        System.out.println(getName() + " a disparu !");
        // Ici, on peut ajouter des effets visuels ou sonores si nécessaire
    }
    // Attaquer le héros si adjacent
    public void attaquerHero(Hero hero) {
        int distanceX = Math.abs(hero.getX() - getX());
        int distanceY = Math.abs(hero.getY() - getY());

        // Vérifier si le héros est adjacent au fantôme
        if ((distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)) {
            hero.setVie(hero.getVie() - getAttaque()); // Réduit les points de vie du héros
            System.out.println("Fantôme " + name + " attaque le héros ! Vie restante du héros : " + hero.getVie());

            // Vérifier si le héros est mort
            if (hero.getVie() <= 0) {
                System.out.println("Le héros est mort !");
            }
        }
    }

    // Mise à jour du fantôme
  
    
}

