package model;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'd':  
			case 'D':
			case KeyEvent.VK_RIGHT:
				this.commandeEnCours = Cmd.UP;
				break;
			case 'q':  
			case 'Q':
			case KeyEvent.VK_LEFT:
				this.commandeEnCours = Cmd.DOWN;
				break;
			case 'z':  
			case 'Z':
			case KeyEvent.VK_UP :
				this.commandeEnCours = Cmd.LEFT;
				break;
			case 's':  
			case 'S':
			case KeyEvent.VK_DOWN :
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case 'a':
			case 'A':
				this.commandeEnCours = Cmd.ATTACK;
			default:
				this.commandeEnCours = Cmd.IDLE;
				break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
