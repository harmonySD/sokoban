package sokoban.project;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardVue extends JPanel {
	int len;
	private Board b;
	public BoardVue(Board b) {////
		this.b = b;
		len=b.getLength();
		this.setLayout(new GridLayout(b.getHeight(),len));
		for(int i=0;i<b.getHeight();i++) {
			for(int j=0;j<b.getLength();j++) {
				this.add(new CaseVue(b.getCase(i, j)));////
			}
		}
	}

	public Board getBoard() { return this.b; }
	public CaseVue getCase(int i, int j) {
		
		return (CaseVue)((JPanel)this.getComponents()[i*len+j]);
	}
	
	
}
