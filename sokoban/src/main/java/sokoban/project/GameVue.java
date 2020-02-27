package sokoban.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameVue extends JFrame {
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
	
}