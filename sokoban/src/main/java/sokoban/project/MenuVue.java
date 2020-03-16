package sokoban.project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class MenuVue extends JFrame implements ActionListener{
	private GameVue fen2;
	private JPanel pan= new FondVue();
	private JButton b1= new JButton("Start");
	private String nom;
	private String path;

	public MenuVue(String n){
		this.nom=n;
		pan.setLayout(null);
		this.setTitle ("Sokoban");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		b1.setBounds(200,150,300,200);
		pan.add(b1);
		setContentPane(pan);
		b1.addActionListener(this);
		this.setVisible(true);
	}


/*
		this.path=System.getProperty("user.dir")+"/Textures/";
		try {
			bg=ImageIO.read(new File(path+"fond.bmp"));
		}
		catch (IOException e) {
			System.out.println("fichier introuvable");
		}
	}
*/

	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		fen2= new GameVue(nom);
	}

}