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
import javax.swing.JList;
import javax.swing.event.*;

public class SelectVue extends JFrame implements ListSelectionListener{/*ActionListener*/
	private JButton [] button=new JButton[100000];
	//private JButton b2= new JButton("ntest2");
	private GameVue fenb1;
	private String nom;
	private File levelPath = new File(System.getProperty("user.dir")+"/target/Niveaux"); 
	private String [] listeDeNiveaux = levelPath.list();
	private JList liste;
	int nbnivx=nbn(listeDeNiveaux);
	private JLabel lab1= new JLabel();
	private String n;

	public int nbn(String []l){
		int nbn=0;
		File levelPath = new File(System.getProperty("user.dir")+"/target/Niveaux"); 
		String [] listeDeNiveaux = levelPath.list();
		/*
		for(int i = 0; i < listeDeNiveaux.length;i++){
			System.out.println(listeDeNiveaux[i]);
			nbn++;
		}*/
		return nbn;
	}
	
	public SelectVue(String n){
		int nbniv=nbn(listeDeNiveaux);
		this.nom=n;
		this.liste = new JList(listeDeNiveaux);
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		contenu.add(liste);
		liste.addListSelectionListener(this);
		this.setTitle ("Sokoban select your level");
		this.setSize(480,360);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	
		lab1.setText("Cliquez sur un nom de fichier pour charger un niveau  ");
		lab1.setHorizontalAlignment(JLabel.CENTER);
		lab1.setVerticalAlignment(SwingConstants.BOTTOM);
		lab1.setFont(new java.awt.Font("Arial", Font.ITALIC, 15));
		lab1.setOpaque(true);

		lab1.setForeground(Color.BLACK);
		this.add(lab1);
		this.setVisible(true);
	}
	public void valueChanged(ListSelectionEvent e){
		if(e.getValueIsAdjusting()){return;}
		this.dispose();
		this.fenb1= new GameVue(nom,(String)liste.getSelectedValue());
	}

}
