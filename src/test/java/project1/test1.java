package project1;
import model.Position;
import model.casesSpeciales.Passage;
import static org.junit.jupiter.api.Assertions.*;
import model.Personnage;
import engine.Cmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test1 {
	private Position position;
    private int[][] labyrinthe;
    @BeforeEach
    public void setUp() {
        // Initialisation de la position et du labyrinthe pour chaque test
        position = new Position(2, 2);

        // Création d'un labyrinthe (0 = vide, 1 = mur, 2 = zone interdite)
        labyrinthe = new int[5][5];
        labyrinthe[0][0] = 1;  // Case accessible
        labyrinthe[1][1] = 2;  // Case interdite
        labyrinthe[2][2] = 0;  // Position de départ
        labyrinthe[3][3] = 1;  // Case accessible
    }

    @Test
    public void testMouvPossibleValid() {
        // Tester si le mouvement vers (3, 3) est possible (case accessible)
        assertTrue(position.mouvPossible(labyrinthe, 3, 3), "Le mouvement vers (3, 3) devrait être possible.");
    }
    @Test
    public void testMouvPossibleInvalid() {
        // Tester si le mouvement vers (1, 1) est bloqué (case interdite)
        assertFalse(position.mouvPossible(labyrinthe, 1, 1), "Le mouvement vers (1, 1) devrait être interdit.");
    }
    @Test
    public void testMouvPossibleOutOfBounds() {
        // Tester si le mouvement vers (5, 5) est hors des limites
        assertFalse(position.mouvPossible(labyrinthe, 5, 5), "Le mouvement vers (5, 5) devrait être hors des limites.");
    }
    @Test
    public void testPerimetre() {
        // Tester la méthode perimetre
        assertTrue(position.perimetre(3, 3, 2), "La position (3, 3) devrait être dans le périmètre de 2 cases.");
        assertFalse(position.perimetre(0, 0, 1), "La position (0, 0) ne devrait pas être dans le périmètre de 1 case.");
    }
    @Test
    public void testPositionAleatoire() {
        // Tester si la position aléatoire générée est valide
        position.positionAleatoire(labyrinthe);
        int[] coord = position.getCoord();
        assertTrue(position.mouvPossible(labyrinthe, coord[0], coord[1]), "La position aléatoire générée doit être valide.");
    }
    @Test
    public void testTestPositionPerimetreInvalid() {
        // Tester une valeur de périmètre invalide
        position.testPositionPerimetre(3, 3, labyrinthe, 8); // Périmètre invalide (plus grand que 7)
        int[] coord = position.getCoord();
        assertEquals(2, coord[0], "La position ne doit pas changer si le périmètre est invalide.");
        assertEquals(2, coord[1], "La position ne doit pas changer si le périmètre est invalide.");
    }
    @Test
    public void testTp() {
        // Initialiser la position
        Position position = new Position(2, 2);
        
        // Passage entre (2, 2) et (3, 3)
        Passage passage = new Passage(2, 2, 3, 3);
        
        // Tester la téléportation de (2, 2) à (3, 3)
        position.tp(passage);
        System.out.println("Position après téléportation : (" + position.getX() + ", " + position.getY() + ")");
        assertEquals(3, position.getX(), "La position X doit être téléportée à 3");
        assertEquals(3, position.getY(), "La position Y doit être téléportée à 3");

        // Tester la téléportation inverse (3, 3) à (2, 2)
        position.tp(passage);
        System.out.println("Position après téléportation inverse : (" + position.getX() + ", " + position.getY() + ")");
        assertEquals(2, position.getX(), "La position X doit être téléportée à 2");
        assertEquals(2, position.getY(), "La position Y doit être téléportée à 2");
    }
}
