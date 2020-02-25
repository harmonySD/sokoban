import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardVue extends JPanel {
	
	public BoardVue(Board b) {
		this.setLayout(new GridLayout(b.getHeight(),b.getLength()));
		for(int i=0;i<b.getHeight();i++) {
			for(int j=0;j<b.getLength();j++) {
				this.add(new CaseVue(b.getCase(i, j)));
			}
		}
	}
	public CaseVue getCase(int i, int j) {
		return (CaseVue)((JPanel)this.getComponents()[i]).getComponents()[j];
	}
	
	
}
