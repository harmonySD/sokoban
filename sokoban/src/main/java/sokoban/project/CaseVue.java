package sokoban.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;


public class CaseVue extends JPanel {
	private Case c;
	private BufferedImage bg=null;
	private BufferedImage fg;
	private String path;
	
	public CaseVue(Case c) {
		double rd = Math.random();
		this.path=System.getProperty("user.dir")+"/Textures/";
		try {
			bg=ImageIO.read(new File((rd>0.05)?path+"Ground.bmp":path+"Ground_Damaged.bmp"));
		}
		catch (IOException e) {
			System.out.println(path +" fichier introuvable");
		}
		this.c=c;
		this.setVisible(true);
	}

	public void setCase(Case maCase) { this.c = maCase; }

	public BufferedImage getFG() { return this.fg; }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0,this.getWidth(), this.getHeight(), null);
		if(fg!=null) g.drawImage(fg,0,0,this.getWidth(), this.getHeight(), null);
	}
	//using colors as placeholders for textures
	public void update() {
		if(c.getContent() == null) {
			System.out.println("la case est nulle");
		}
		if(c.getContent() instanceof Empty) {
			try {
				switch(c.getColor()) {// check if works with loaded maps
					case("red") : 
						fg=ImageIO.read(new File(path+"Objective_R.bmp"));
						break;
					case("green") : 
						fg=ImageIO.read(new File(path+"Objective_G.bmp"));
						break;
					case("blue") : 
						fg=ImageIO.read(new File(path+"Objective_B.bmp"));
						break;
					case("none") : // Necessaire pour les cases vides dans CreateVue !
						fg=ImageIO.read(new File(path+"None.jpg"));
						break;
						default:
							fg=ImageIO.read(new File(path+"Ground.bmp"));
							break;
				}
			}
			catch (IOException e) { System.out.println(path + "Fichier objectif Introuvable");}
		}
		if(c.getContent() instanceof Wall) {
			try {fg=ImageIO.read(new File(path+"Wall.bmp"));}
			catch (IOException e) { System.out.println(path+"Wall.bmp Fichier Introuvable");}
		}
		if(c.getContent() instanceof Box) {
			try {
				switch(((Box)c.getContent()).getColor()) {
					case("red") : 
						fg=ImageIO.read(new File(path+"Box_R.bmp"));
						break;
					case("green") : 
						fg=ImageIO.read(new File(path+"Box_G.bmp"));
						break;
					case("blue") :
						fg=ImageIO.read(new File(path+"Box_B.bmp"));
						break;
					default:
						fg=ImageIO.read(new File(path+"Box_R.bmp"));
						break;
				}
			}
			catch (IOException e) { System.out.println(path + "Box_R Fichier Introuvable");}
		}
		if(c.getChar()) {
			try {fg=ImageIO.read(new File(path+"Character.bmp"));}
			catch (IOException e) { System.out.println("Fichier Introuvable");}
		}
		if(!(c.getContent() instanceof Wall)&&!(c.getContent() instanceof Box)&&!(c.getContent() instanceof Empty)) {
			this.setVisible(false);
		}
	}
	
	
}
