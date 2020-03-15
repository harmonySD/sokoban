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



public class MenuVue extends JFrame implements ActionListener{
	private GameVue fen2;
	private JPanel pan= new JPanel();
	private JButton b1= new JButton("Start");
	private String nom;

	public MenuVue(String n){
		this.nom=n;
		pan.setLayout(null);
		this.setTitle ("Sokoban");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		b1.setBackground(Color.cyan);
		b1.setBounds(200,150,300,200);
		pan.add(b1);
		setContentPane(pan);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; c.gridy=0; c.fill=GridBagConstraints.BOTH; c.weightx=1; c.weighty=0.95;
		JPanel bas = new JPanel();
		bas.setLayout(new GridLayout(1,2));
		bas.add(new JLabel());
		bas.add(new JLabel());
		c.gridx=0; c.gridy=1; c.fill=GridBagConstraints.BOTH; c.weightx=1; c.weighty=0.05;
		this.add(bas,c);
		b1.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		fen2= new GameVue(nom);
	}

}