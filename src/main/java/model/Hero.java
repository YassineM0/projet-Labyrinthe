package model;

import java.util.Scanner;

import engine.Cmd;

public class Hero extends Personnage {

    private boolean isMagicActive;

    public Hero(int x,int y, int vie, int attaque, boolean isMagicActive) {
        super(x, y, vie, attaque);
        this.isMagicActive = false;
    }
    
    public void activateMagic() {
        this.setVie(this.getVie() + 10);
    }
    
    


    public boolean getIsMagicActive()
    {
        return this.isMagicActive;
    }
    public Cmd positionHeros() {
        System.out.print("Saisissez direction héros (z,q,s,d) ou  attaquer (a) : ");
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
            case "a":  // Touche pour attaquer 
                return Cmd.ATTACK;
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
 // Méthode pour attaquer un monstre
    public void attack(Monster monstre) {
        if (monstre != null) {
            int damage = getAttaque();  // Utilise getAttaque() hérité de Personnage
            monstre.setVie(monstre.getVie() - 5);  // Réduire la vie du monstre
            System.out.println("Vous avez infligé " + damage + " points de dégâts à " + monstre.getName());

            // Si le monstre est mort (vie <= 0)
            if (monstre.getVie() <= 0) {
                System.out.println(monstre.getName() + " est vaincu !");
               
            }
        } else {
            System.out.println("Il n'y a pas de monstre à attaquer.");
        }
    }

    public boolean isMagicActive() {
        return isMagicActive;
    }

    public void setMagicActive(boolean isMagicActive) {
        this.isMagicActive = isMagicActive;
    }
    public Monster detectMonsterAround(Monster monstre) {

    	int distanceX = Math.abs(monstre.getX() - getX());
    	int distanceY = Math.abs(monstre.getY() - getY());
    	if ((distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)) {
                return monstre;
            }
        return null;
    }

    // public void executeAttack(Monster monstres) {
    //     Monster monster = detectMonsterAround(monstres);
    //     if (monster != null) {
    //         attack(monster);
    //         Monster.getVie();
    //         System.out.println("Vous avez infligé" + Monster.getVie()+"de dégat au monstre");
    //     } else {
    //         System.out.println("Aucun monstre à attaquer !");
    //     }
    // }

    // public void regagnerVie(int montant) {
    //     // Ajouter la régénération de vie avec une limite maximale de vie
    //     this.vie = Math.min(100, this.vie + 50);  // Assurez-vous que la vie ne dépasse pas 100
    //     System.out.println("Le héros a régénéré " + 50 + " points de vie. Vie actuelle : " + this.vie);
    // }


    
    
}