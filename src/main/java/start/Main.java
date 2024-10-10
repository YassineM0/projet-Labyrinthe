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

		int x = 1;       
		int y = 1;     
		int vie = 100;  
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
