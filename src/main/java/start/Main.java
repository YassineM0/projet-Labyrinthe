 package start;

import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.PacmanPainter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		int x = 1;       // Position initiale X du héros
		int y = 1;       // Position initiale Y du héros
		int vie = 100;   // Points de vie du héros
		int attaque = 10;
		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame(x, y, vie, attaque);
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
