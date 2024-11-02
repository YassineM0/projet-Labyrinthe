package model.casesSpeciales;

import model.Hero;
import model.PacmanGame;

public class Tresor {
    private int x;
    private int y;

    public Tresor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    

    public void onEnter(Hero hero, PacmanGame game) {
        System.out.println("You collected a treasure!");
        game.setGameFinished(true); 
    }


}
