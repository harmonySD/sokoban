package sokoban.project;

import java.awt.event.KeyListener;

import javax.swing.JLabel;

import java.util.*; 
import java.lang.*; 

public class Controleur /*implements KeyListener */{
	private GameVue vue;
	private Game modele;
	public Controleur(GameVue g, String nom,String leveln) {
		this.vue=g;
		this.modele=new Game(nom,leveln);////
		
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
		((JLabel)vue.getBas().getComponents()[1]).setText("Score : "+Integer.toString(modele.getPlayer().getScore()));
		System.out.println(modele.getBoard().getChar().getX()+" "+modele.getBoard().getChar().getY());
		vue.repaint();

		if(modele.getBoard().win()){
			int nb_coup = this.getModele().getBoard().getCoup();
			int m2s = this.getModele().getBoard().get_max2stars();
			int m3s = this.getModele().getBoard().get_max3stars();
			int etoile = (nb_coup <m3s) ? 3 : (nb_coup < m2s) ? 2 : 1 ;
			System.out.println("GAGNE. VOUS ETES A " + etoile + " ETOILES  POUR CE NIVEAU ! ");
			this.getModele().getPlayer().setScore(this.getModele().getPlayer().getScore() + etoile * 10);


			//System.out.println("GAGNE");
			//System.exit(0);
			vue.stopSoundPlease();
			vue.dispose();
			new YouWinVue(modele.getPlayer().getNickname(),etoile*10);
		}
	}
}
