package model.casesSpeciales;

import model.Hero;
import model.PacmanGame;

public abstract class Case {
    private int x, y;
    
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void onEnter(Hero hero, PacmanGame game);
}

