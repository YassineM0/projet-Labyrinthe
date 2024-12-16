 package project1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.Cmd;
import model.Hero;
import model.PacmanGame;
import model.Personnage;




class test_2 {
	private Personnage personnage;
    private int[][] labyrinthe;
    @BeforeEach
    public void setUp() {
        // Initialisation du personnage avant chaque test
        personnage = new Personnage(1,100, 10, 5);

        // Création d'un labyrinthe (0 = vide, 1 = mur, 2 = obstacle bloquant)
        labyrinthe = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
    }

    @Test
    public void testMoveUp() {
        personnage.move(Cmd.UP, labyrinthe);
        assertArrayEquals(new int[]{1, 2}, personnage.getCoordonnees(), "Le personnage doit se déplacer vers le haut.");
    }
	
    @Test
    public void testMoveRight() {
        personnage.move(Cmd.RIGHT, labyrinthe);
        assertArrayEquals(new int[]{2, 1}, personnage.getCoordonnees(), "Le personnage doit se déplacer à droite.");
    }
    @Test
    public void testMoveLeft() {
        personnage.move(Cmd.LEFT, labyrinthe);
        assertArrayEquals(new int[]{1, 1}, personnage.getCoordonnees(), "Le personnage doit se déplacer à droite.");
    }
    @Test
    public void testEstMort() {
        personnage.setVie(0);
        assertTrue(personnage.estMort(), "Le personnage doit être considéré comme mort si sa vie est à 0.");
    }
    @Test
    public void testEstVivant() {
        personnage.setVie(5);
        assertFalse(personnage.estMort(), "Le personnage ne doit pas être considéré comme mort s'il a des points de vie.");
    }
    @Test
    public void testInitialization() {
        PacmanGame game = new PacmanGame(1, 1, 3, 2,1);
        
        // Vérifie que le héros est initialisé à la position (1, 1)
        assertEquals(1, game.getHero().getX(), "Le héros doit être initialisé à la position X=1");
        assertEquals(1, game.getHero().getY(), "Le héros doit être initialisé à la position Y=1");
        
        // Vérifie que le labyrinthe a bien la bonne taille
        assertEquals(16, game.getLabyrinthe().length, "Le labyrinthe doit avoir 16 lignes");
        assertEquals(28, game.getLabyrinthe()[0].length, "Le labyrinthe doit avoir 28 colonnes");
        
        // Vérifie qu'il y a au moins un trésor
        assertTrue(game.getTresors().size() > 0, "Le jeu doit contenir au moins un trésor");
    }

    @Test
    public void testActivateMagic() {
        Hero hero = new Hero(1, 1, 100, 20, false);  // Initialisation du héros sans magie activée

        assertFalse(hero.getIsMagicActive(), "La magie du héros ne doit pas être active au départ");

        hero.activateMagic();  // Activation de la magie
        assertTrue(hero.getIsMagicActive(), "La magie du héros doit être activée après l'appel de activateMagic()");
    }
   
    @Test
    public void testMove() {
        Hero hero = new Hero(1, 1, 100, 20, false);
      //  int[][] labyrinthe = new int[5][5];  // Labyrinthe fictif

        // Déplacer le héros vers le bas
        hero.move(Cmd.DOWN, labyrinthe);
        assertEquals(1, hero.getY(), "Le héros doit se déplacer vers le bas (Y+1)");

        // Déplacer le héros vers la gauche
        hero.move(Cmd.LEFT, labyrinthe);
        assertEquals(1, hero.getX(), "Le héros doit se déplacer vers la gauche (X-1)");
    }

}





