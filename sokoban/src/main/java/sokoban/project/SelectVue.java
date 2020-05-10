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


public class SelectVue extends JFrame implements ActionListener{
	private JButton [] button=new JButton[100000];
	//private JButton b2= new JButton("ntest2");
	private GameVue fenb1;
	private String nom;
	private File levelPath = new File(System.getProperty("user.dir")+"/target/Niveaux"); 
	private String [] listeDeNiveaux = levelPath.list();
	int nbnivx=nbn(listeDeNiveaux);

	public int nbn(String []l){
		int nbn=0;
		File levelPath = new File(System.getProperty("user.dir")+"/target/Niveaux"); 
		String [] listeDeNiveaux = levelPath.list();
		for(int i = 0; i < listeDeNiveaux.length;i++){
			System.out.println(listeDeNiveaux[i]);
			nbn++;
		}
		return nbn;
	}
	
	public SelectVue(String n){
		int nbniv=nbn(listeDeNiveaux);
		this.nom=n;
		this.setLayout(new GridLayout(nbniv,0,0,0));
		for (int i=0; i<nbniv;i++ ) {
			button[i]=new JButton(listeDeNiveaux[i]);
			this.add(button[i]);

			//button[i].addActionListener(this);
		}

		this.setTitle ("Sokoban select your level");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

		//b1.setBounds(100,150,300,100);
		//this.add(b1);
		//b1.addActionListener(this);

		//b2.setBounds(100,250,300,100);
		//this.add(b2);
		//b2.addActionListener(this);
		this.setVisible(true);
	}



	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		for (int i=0;i<listeDeNiveaux.length ;i++ ) {
			if (arg0.getSource()==button[i]) {
				fenb1= new GameVue(nom,listeDeNiveaux[i]);
			}
		}
		
	}

}
