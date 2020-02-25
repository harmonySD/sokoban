import java.awt.Color;

import javax.swing.JPanel;

public class CaseVue extends JPanel {
	private Case c;
	public CaseVue(Case c) {
		this.c=c;
		this.setVisible(true);
		update();
	}
	
	//using colors as placeholders for textures
	public void update() {
		if(c.getContent() instanceof Empty) {
			this.setBackground(Color.white);
			//might show something different depending on the color of c
		}
		if(c.getContent() instanceof Wall) {
			this.setBackground(Color.black);
		}
		if(c.getContent() instanceof Box) {
			this.setBackground(Color.red);
			//color should change according to the color of the box
		}
		if(c.getContent() instanceof Character) {
			this.setBackground(Color.BLUE);
		}
	}
	
	
}