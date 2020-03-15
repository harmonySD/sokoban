package sokoban.project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuVue extends JFrame implements ActionListener{
	private GameVue fen2;
	private JPanel pan= new JPanel();
	private JButton b1= new JButton("Start");

	public MenuVue(){
		pan.setLayout(null);
		this.setTitle ("Sokoban");
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		b1.setBackground(Color.cyan);
		b1.setBounds(200,150,300,200);
		pan.add(b1);
		setContentPane(pan);
		b1.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0){
		this.dispose();
		fen2= new GameVue("kk");
	}

}