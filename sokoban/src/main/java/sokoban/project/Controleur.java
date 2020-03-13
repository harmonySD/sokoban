package sokoban.project;

import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class Controleur /*implements KeyListener */{
	private GameVue vue;
	private Game modele;
	public Controleur(GameVue g, String nom) {
		this.vue=g;
		this.modele=new Game(nom);
		
	}
	public Game getModele() {
		return modele;
	}
	public GameVue getVue() {
		return vue;
	}
	public void update(){
		for(int i=0;i<modele.getBoard().getHeight();i++) {
			for(int j=0;j<modele.getBoard().getLength();j++) {
				vue.getBoard().getCase(i, j).update();
			}
		}
		((JLabel)vue.getBas().getComponents()[0]).setText(modele.getPlayer().getNickname());
		((JLabel)vue.getBas().getComponents()[1]).setText("Score : "+Integer.toString(modele.getPlayer().getscore()));
		System.out.println(modele.getBoard().getChar().getX()+" "+modele.getBoard().getChar().getY());
		vue.repaint();

		if(modele.getBoard().win()){System.out.println("GAGNE");}
	}
}
