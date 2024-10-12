package model;


public class Monster extends Personnage {
	    private String name;
	    private int vie;
	    private int attaque;

	    public Monster(int x,int y, int vie, int attaque,String name) {
	    	super( x, y,  vie,  attaque);
	        this.name = name;
	        
	    }
	    // Récupérer le nom du monstre
	    public String getName() {
	        return name;
	    }

	
}

