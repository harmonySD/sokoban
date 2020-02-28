package sokoban.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameVue extends JFrame implements KeyListener{
	public Controleur ctrl;
	public GameVue(String nom) {
		ctrl=new Controleur(this, nom);
		init();
	}
	public void init() {
		this.setTitle("Sokoban");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; c.gridy=0; c.fill=GridBagConstraints.BOTH; c.weightx=1; c.weighty=0.95;
		this.add(new BoardVue(ctrl.getModele().getBoard()),c);
		JPanel bas = new JPanel();
		bas.setLayout(new GridLayout(1,2));
		bas.add(new JLabel());
		bas.add(new JLabel());
		c.gridx=0; c.gridy=1; c.fill=GridBagConstraints.BOTH; c.weightx=1; c.weighty=0.05;
		this.add(bas,c);
		this.setVisible(true);
		this.setSize(1000,1000);
		ctrl.update();
		
	}
	public JPanel getBas() {
		return (JPanel)(this.getContentPane().getComponents()[1]);
	}
	public BoardVue getBoard() {
		return (BoardVue)this.getContentPane().getComponents()[0];
	}


	public void move(int direction){
		switch(direction){
			case KeyEvent.VK_UP :
				goUP();
				break;
			case KeyEvent.VK_DOWN :
				goDown();
				break;
			case KeyEvent.VK_RIGHT :
			 	goRight();
			 	break;
			case KeyEvent.VK_LEFT :
				goLeft();
				break;
		}
	}
	// Méthode redéfinie depuis KeyListener
    public void keyPressed(KeyEvent key){
        // touche pressée
         
        // Je test les valeurs de ma touche
         
        int codeDeLaTouche = key.getKeyCode();
         
        switch (codeDeLaTouche) // Les valeurs sont contenue dans KeyEvent. Elles commencent par VK_ et finissent par le nom de la touche
        {
            case KeyEvent.VK_UP: // si la touche enfoncée est celle du haut
                goUP();
                break;
            case KeyEvent.VK_LEFT: // si la touche enfoncée est celle de gauche
                goLeft();
                break;
            case KeyEvent.VK_RIGHT: // si la touche enfoncée est celle de droite
                goRight();
                break;
            case KeyEvent.VK_DOWN: // si la touche enfoncée est celle du bas
                goDown();
                break;
        }
    }
 
   	// Méthode redéfinie depuis KeyListener
    public void keyReleased(KeyEvent key){
        // touche relachée
    }
 
    // Méthode redéfinie depuis KeyListener
    public void keyTyped(KeyEvent key){
        // touche appuyée
    }
 
    public void goUP(){
    	ctrl.getModele().getBoard().moveCharacter('u');
    }
 
    public void goRight(){
        moveCharacter('r');
    }
 
    public void goLeft(){
       moveCharacter('l');
    }
 
    public void goDown(){
       moveCharacter('d');
    }

	
	
}
