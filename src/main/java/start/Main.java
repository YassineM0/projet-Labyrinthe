 package start;

import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.PacmanPainter;
import java.util.Scanner;;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Choisissez un niveau (De 1 à 5) : ");
        int niveau = scanner.nextInt();
        while (niveau < 1 || niveau > 5) {
            System.out.println("Niveau invalide. Veuillez choisir entre 1 et 5 : ");
            niveau = scanner.nextInt();
        }
		scanner.close();

		int x = 1;       // Position initiale X du héros
		int y = 1;       // Position initiale Y du héros
		int vie = 100;   // Points de vie du héros
		int attaque = 10;
		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame(x, y, vie, attaque,niveau);
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
