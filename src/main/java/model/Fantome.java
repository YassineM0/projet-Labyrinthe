package model;

import engine.Cmd;
import java.util.Random;

public class Fantome extends Personnage {
    private String name;
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
    public boolean deplacementAleatoire(int[][] labyrinthe) {
        Random rand = new Random();
        Cmd[] directions = {Cmd.LEFT, Cmd.RIGHT, Cmd.UP, Cmd.DOWN};
        int randomIndex = rand.nextInt(directions.length);
        int newX = getX();
        int newY = getY();

        switch (directions[randomIndex]) {
            case LEFT:  newX = getX() - 1; break;
            case RIGHT: newX = getX() + 1; break;
            case UP:    newY = getY() - 1; break;
            case DOWN:  newY = getY() + 1; break;
        }

        // Vérifier si la case est valide pour le déplacement
        if (newX >= 0 && newX < labyrinthe[0].length && newY >= 0 && newY < labyrinthe.length && labyrinthe[newY][newX] == 1) {
            setX(newX);
            setY(newY);
            return true;
        }
        return false;
    }

    // Déplacement vers le héros, avec gestion des murs
    public boolean deplacerVersHero(int heroX, int heroY, int[][] labyrinthe) {
        int monsterX = getX();
        int monsterY = getY();

        int newX = monsterX;
        int newY = monsterY;

        if (monsterX < heroX) {
            newX = monsterX + 1;
        } else if (monsterX > heroX) {
            newX = monsterX - 1;
        }

        if (monsterY < heroY) {
            newY = monsterY + 1;
        } else if (monsterY > heroY) {
            newY = monsterY - 1;
        }

        // Vérifier si la case est valide pour le déplacement
        if (newX >= 0 && newX < labyrinthe[0].length && newY >= 0 && newY < labyrinthe.length && labyrinthe[newY][newX] == 1) {
            setX(newX);
            setY(newY);
            return true;
        }
        return false;
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
    public void mettreAJourMonstre(int heroX, int heroY, int[][] labyrinthe, Hero hero, int niveau) {
        ajusterVitesse(niveau); // Ajuster la vitesse selon le niveau
        boolean moved = false;

        // Vérifier si le héros est adjacent et attaquer
        attaquerHero(hero);

        // Si le héros est proche, tenter de se déplacer vers lui
        if (Math.abs(heroX - getX()) + Math.abs(heroY - getY()) < 10) {
            moved = deplacerVersHero(heroX, heroY, labyrinthe);
        }

        // Si le héros est loin ou le déplacement vers lui est bloqué, déplacement aléatoire
        if (!moved) {
            deplacementAleatoire(labyrinthe);
        }
    }
}

