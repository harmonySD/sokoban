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
	private JButton b1= new JButton("ntest1");
	private JButton b2= new JButton("ntest2");
	private GameVue fenb1;
	private String nom;



	public SelectVue(String n){
		this.nom=n;
		this.setLayout(null);

		this.setTitle ("Sokoban select your level");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

		b1.setBounds(100,150,300,100);
		this.add(b1);
		b1.addActionListener(this);

		b2.setBounds(100,250,300,100);
		this.add(b2);
		b2.addActionListener(this);
		this.setVisible(true);
	}



	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		if(arg0.getSource()==b1){
			fenb1= new GameVue(nom,"niveautest.txt");
		}
		else if(arg0.getSource()== b2){
			fenb1=new GameVue(nom,"niveautest2.txt");
		}
		
	}

}
