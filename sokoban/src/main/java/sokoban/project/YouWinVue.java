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
import javax.swing.SwingConstants;
import java.awt.*;

public class YouWinVue extends JFrame implements ActionListener{
	private MenuVue fenb1;
	private SelectVue fenb2;
	private JButton b1= new JButton("Back to menu");
	private JButton b2= new JButton("Select other Level");
	private JLabel lab1= new JLabel();
	private String n;
	// constructeur de menuVue. 
	//creer une fenetre avec un fond et 3 boutons qui redirigeront vers une classe associée
	public YouWinVue(String name, int score){
		this.n=name;
		lab1.setText("GAGNE ! Ton score est de : "+score);
		lab1.setHorizontalAlignment(JLabel.CENTER);
		this.setTitle ("Sokoban");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		b1.setBounds(100,150,300,100);
		this.add(b1);
		b1.addActionListener(this);
		lab1.setVerticalAlignment(SwingConstants.TOP);
		lab1.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		lab1.setOpaque(true);
		lab1.setBackground(Color.WHITE);
		lab1.setForeground(Color.BLUE);
		this.add(lab1);
		

		b2.setBounds(100,250,300,100);
		this.add(b2);
		b2.addActionListener(this);






		this.setVisible(true);
	}


	//En premier on "ferme" cette fenetre
	//si l'utilisateur clique sur le bouton Start -> rediriger vers une fenetre de jeu 
	//si l'utilisatuer clique sur le bouton Select level-> rediriger vers une fenetre pour choix de niveau 
	//si l'utilisateur clique sue le bouton Option -> rediriger vers une fenetr epour choisir profil etc 
	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		if(arg0.getSource()==b1){
			fenb1= new MenuVue(n);
		}
		else if(arg0.getSource()== b2){
			fenb2= new SelectVue(n);
		}
	}

}

