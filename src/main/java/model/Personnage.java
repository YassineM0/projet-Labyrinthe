package model;

import engine.Cmd;

public class Personnage extends Position {


	private static int vie;
	private int attaque;

	public Personnage (int x,int y, int vie,int attaque) {
		super(x, y);
		this.vie=vie;
		this.attaque=attaque;
	}

	public void move(Cmd cmd, int[][] labyrinthe) {
		int newX = getX();
		int newY = getY();
	
		System.out.println("Current Position: (" + newX + ", " + newY + ")");
		System.out.println("Command received: " + cmd);
	
		switch (cmd) {
			case UP:
				newY += 1; 
				break;
			case DOWN:
				newY -= 1; 
				break;
			case LEFT:
				newX -= 1; 
				break;
			case RIGHT:
				newX += 1; 
				break;
			case IDLE:
				System.out.println("Hero is idle.");
				return;  
		}
	
		// Check if the move is possible
		if (mouvPossible(labyrinthe, newX, newY)) {
			setCoord(newX, newY);
			System.out.println("Hero moved to: (" + newX + ", " + newY + ")" + "Labyrinte valeur:" +  labyrinthe[newX][newY]);
		} else {
			System.out.println("Move blocked by wall or out of bounds. valeur labyrinte:" + labyrinthe[newX][newY]);
		}
	}
	public void magicMove(Cmd cmd, int[][] labyrinthe) {
		int newX = getX();
		int newY = getY();
	
		System.out.println("Current Position: (" + newX + ", " + newY + ")");
		System.out.println("Command received: " + cmd);
	
		switch (cmd) {
			case UP:
				newY += 2; 
				break;
			case DOWN:
				newY -= 2; 
				break;
			case LEFT:
				newX -= 2; 
				break;
			case RIGHT:
				newX += 2; 
				break;
			case IDLE:
				System.out.println("Hero is idle.");
				return;  
		}
	
		// Check if the move is possible
		if (mouvPossible(labyrinthe, newX, newY)) {
			setCoord(newX, newY);
			System.out.println("Hero moved to: (" + newX + ", " + newY + ")" + "Labyrinte valeur:" +  labyrinthe[newX][newY]);
		} else {
			System.out.println("Move blocked by wall or out of bounds. valeur labyrinte:" + labyrinthe[newX][newY]);
		}
	}
	
	public int[] getCoordonnees() {
        return new int[]{getX(), getY()};
    }

	public static int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public boolean estMort() {
        return this.vie <= 0;
    }
}