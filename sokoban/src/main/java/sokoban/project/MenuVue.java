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
	private GameVue fenb1;
	private CreateVue fenb4;
	//private SelectVue fenb2;
	//private OptionVue fenb3;
	private JPanel pan= new FondVue(); // pour avoir l'image de fond 
	private JButton b1= new JButton("Start");
	private JButton b2= new JButton("Select Level");
	private JButton b3= new JButton("Option");
	private JButton b4= new JButton("Create your level");
	private String nom;
	private String path;

	// constructeur de menuVue. 
	//creer une fenetre avec un fond et 3 boutons qui redirigeront vers une classe associée
	public MenuVue(String n){
		this.nom=n;
		pan.setLayout(null);
		this.setTitle ("Sokoban");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		b1.setBounds(100,150,300,100);
		pan.add(b1);
		b4.setBounds(100,250,300,100);
		pan.add(b4);
		setContentPane(pan);
		b1.addActionListener(this);
		b4.addActionListener(this);

		//b2.setBounds(100,350,300,100);
		//pan.add(b2);
		//b2.addActionListener(this);
		//b3.setBounds(100,450,300,100);
		//pan.add(b3);
		//b3.addActionListener(this);
		this.setVisible(true);
	}


	//En premier on "ferme" cette fenetre
	//si l'utilisateur clique sur le bouton Start -> rediriger vers une fenetre de jeu 
	//si l'utilisatuer clique sur le bouton Select level-> rediriger vers une fenetre pour choix de niveau 
	//si l'utilisateur clique sue le bouton Option -> rediriger vers une fenetre pour choisir profil etc
	//si l'utilisateur clique sue le bouton Create your level -> rediriger vers une fenetre pour créer son niveau
	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		if(arg0.getSource()==b1){
			fenb1= new GameVue(nom);
		}
		else if(arg0.getSource()== b2){
			//fenb2= new SelectVue();
		}
		else if (arg0.getSource() ==b3){
			//fenb3= new OptionVue();
		}
		else if (arg0.getSource() ==b4){
			fenb4= new CreateVue(nom);
		}
	}

}