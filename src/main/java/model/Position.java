package model;

public class Position {

	private int x;
	private int y;

	public Position(int x,int y) {
		this.x=x;
		this.y=y;
	}

	public boolean mouvPossible(int[][] labyrinthe, int x, int y) {
		if (x >= 0 && x < labyrinthe.length && y >= 0 && y < labyrinthe[0].length) {
			return labyrinthe[x][y] != 0; 
		}
		return false;
	}

	public boolean perimetre(int xHero, int yHero, int perimetre) {
        return Math.abs(x - xHero) <= perimetre && Math.abs(y - yHero) <= perimetre;
    }

	public void positionAleatoire(int[][] labyrinthe) {
        int nouveauX, nouveauY;
        do {
            nouveauX = (int) (Math.random() * (labyrinthe.length - 1)) + 1;
            nouveauY = (int) (Math.random() * (labyrinthe[0].length - 1)) + 1;
        } while (!mouvPossible(labyrinthe, nouveauX, nouveauY)); 
        setX(nouveauX);
        setY(nouveauY);
    }
	
	public void testPositionPerimetre(int xHero, int yHero, int[][] labyrinthe, int perimetre) {
        if (perimetre <= 7) {
            do {
                positionAleatoire(labyrinthe);
            } while (!perimetre(xHero, yHero, perimetre));
        } else {
            System.out.println("Mauvaise taille de périmètre");
        }
    }
	
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int[] getCoord() {
        return new int[]{x, y};
    }

	public void setX(int x){
		this.x=x;
	}

	public void setY(int y){
		this.y=y;
	}

	public void setCoord(int x, int y) {
        setX(Math.max(0, x));
        setY(Math.max(0, y));
    }
}