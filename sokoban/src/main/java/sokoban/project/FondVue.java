package sokoban.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javax.swing.JButton;
public class FondVue extends JPanel {
	private BufferedImage bg=null;
	private String path;


	public FondVue() {
		this.path=System.getProperty("user.dir")+"/Textures/";
		try {
			bg=ImageIO.read(new File(path+"fond.bmp"));
		}
		catch (IOException e) {
			System.out.println("fichier introuvable");
		}
		this.setVisible(true);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0,this.getWidth(), this.getHeight(), null);
		
	}
}