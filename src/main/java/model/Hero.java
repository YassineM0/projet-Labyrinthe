package model;

import java.util.Scanner;

import engine.Cmd;

public class Hero extends Personnage {

    public Hero(int x,int y, int vie, int attaque) {
        super(x, y, vie, attaque);
    }

    public Cmd positionHeros() {
        System.out.print("Saisissez direction héros (z,q,s,d) : ");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine().toLowerCase();

        // Convertir l'entrée en commande Cmd
        switch (cmd) {
            case "z":
                System.out.println("test z");
                return Cmd.UP;
            case "q":
                return Cmd.LEFT;
            case "s":
                return Cmd.DOWN;
            case "d":
                return Cmd.RIGHT;
            default:
                System.out.println("Commande invalide, veuillez réessayer.");
                return Cmd.IDLE;  
        }
    }

    @Override
    public void move(Cmd direction, int[][] labyrinthe) {
        if (direction != Cmd.IDLE) {
            super.move(direction, labyrinthe);
        }
    }
    

    public void deplacementHero(int[][] labyrinthe) {
        Cmd direction;
        do {
            direction = positionHeros(); 
            move(direction, labyrinthe);   
        } while (direction != Cmd.IDLE);  
    }
}