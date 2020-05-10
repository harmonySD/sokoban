package sokoban.project;

import java.awt.event.KeyEvent;

public class Board {
	private Case[][] tab;
	private Character ch;
	private int nb_coup;
	private int max3stars;
	private int max2stars;

	// =================Constructeurs =========================
	public Board(int szx, int szy,int max3s) {
		this(szx,szy,max3s,(int)Math.round(1.3*max3s));
		
	}
	
	public Board(int szx, int szy,int max3s,int max2s) {
		this(szx,szy);
		this.max2stars = max2s ;
		this.max3stars = max3s ;
	}
	
	public Board(int szx, int szy) {
		this.tab = new Case[szx][szy];
		ch = new Character(0, 0);
		nb_coup = 0;
	}

	public Board() {
		this(10, 10);
		this.max2stars = 14;
		this.max3stars =7;
		init();

	}

	// ================= Accesseurs et mutateurs ==============
	public int getHeight() {
		return this.tab.length;
	}

	public int getLength() {
		return this.tab[0].length;
	}

	public int getCoup() { return this.nb_coup; }

	public void setCoup(int i) { this.nb_coup = i; }
	
	int get_max3stars(){return this.max3stars;}
	int get_max2stars(){return this.max2stars;}
	
	void set_max3stars(int nmax){this.max3stars = nmax;}
	void set_max2stars(int nmax){ this.max2stars = nmax;}
	
	public void incrementeCoup() { this.setCoup(this.getCoup() + 1); }

	public Case getCase(int x, int y) {
		return tab[y][x];
	}
	
	public void setCase(int x, int y, Case c ){
		this.tab [y][x] = c ;
	}

	public Character getChar() {
		return this.ch;
	}

	// Function returning boolean depending on if character movement's possible


	public boolean askMoveBox(int x, int y, char destination) {
		switch (destination) {
		// Si les coordonnées de la nouvelle direction sont correctes
		// Et que la case destination est vide => TRUE
		// Sinon FALSE
		case 'r':
			return (y + 1 < this.getLength() && this.getCase(x, y + 1).getContent() instanceof Empty);
		case 'l':
			return (y - 1 >= 0 && this.getCase(x, y - 1).getContent() instanceof Empty);
		case 'd':
			return (x + 1 < this.getHeight() && this.getCase(x + 1, y).getContent() instanceof Empty);
		case 'u':
			return (x - 1 >= 0 && this.getCase(x - 1, y).getContent() instanceof Empty);
		}
		return false;
	}

	public void moveBox(int x, int y, char destination) {
		Case myBox = this.getCase(x, y);
		// Si c'est une box et que le mouvement est possible => TRUE Sinon FALSE
		if (myBox.getContent() instanceof Box && askMoveBox(x, y, destination)) {
			Empty vide = new Empty();
			// Effectuer le mouvement en mettant une box dans la case destination
			// Et en vidant la case de la box de départ
			switch (destination) {
			case 'r':
				this.getCase(x, y + 1).setContent(myBox.getContent());
				myBox.setContent(vide);
				break;
			case 'l':
				this.getCase(x, y - 1).setContent(myBox.getContent());
				myBox.setContent(vide);
				break;
			case 'd':
				this.getCase(x + 1, y).setContent(myBox.getContent());
				myBox.setContent(vide);
				break;
			case 'u':
				this.getCase(x - 1, y).setContent(myBox.getContent());
				myBox.setContent(vide);
				break;
			}
		}
	}

	public boolean askMoveCharacter(int x, int y, char destination) {
		switch (destination) {
		// Si les coordonnées de la nouvelle direction sont correctes
		// Et que la case destination est vide => TRUE
		// Sinon si les coordonnées de la nouvelle direction sont correctes
		// Et que la case destination contient une boite-> si askMoveBox vrai => TRUE et
		// on appel moveBox (FALSE sinon)
		// Sinon FALSE
		case 'r':
			if (y + 1 < this.getLength() && this.getCase(x, y + 1).getContent() instanceof Empty) {
				return true;
			} else if (y + 1 < this.getLength() && this.getCase(x, y + 1).getContent() instanceof Box) {
				if (askMoveBox(x, y + 1, destination)) {
					moveBox(x, y + 1, destination);
					return true;
				} else {
					return false;
				}
			} else
				return false;
		case 'l':
			if (y - 1 >= 0 && this.getCase(x, y - 1).getContent() instanceof Empty) {
				return true;
			} else if (y - 1 < this.getLength() && this.getCase(x, y - 1).getContent() instanceof Box) {
				if (askMoveBox(x, y - 1, destination)) {
					moveBox(x, y - 1, destination);
					return true;
				} else {
					return false;
				}
			} else
				return false;
		case 'd':
			if (x + 1 < this.getHeight() && this.getCase(x + 1, y).getContent() instanceof Empty) {
				return true;
			} else if (x + 1 < this.getLength() && this.getCase(x + 1, y).getContent() instanceof Box) {
				if (askMoveBox(x + 1, y, destination)) {
					moveBox(x + 1, y, destination);
					return true;
				} else {
					return false;
				}
			} else
				return false;
		case 'u':
			if (x - 1 >= 0 && this.getCase(x - 1, y).getContent() instanceof Empty) {
				return true;
			} else if (x - 1 < this.getLength() && this.getCase(x - 1, y).getContent() instanceof Box) {
				if (askMoveBox(x - 1, y, destination)) {
					moveBox(x - 1, y, destination);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public void moveCharacter(char destination) {
		int x = this.ch.getX();
		int y = this.ch.getY();
		Case myCharacter = this.getCase(x, y);
		//
		if (askMoveCharacter(x, y, destination)) {
			// Incrémenter le nombre de coups
			this.incrementeCoup();
			// Effectuer le mouvement en mettant une box dans la case destination
			// Et en vidant la case depart du character
			switch (destination) {
			case 'r':
				this.getCase(x, y + 1).setChar(true);
				ch.setY(y + 1);
				this.getCase(x, y).setChar(false);
				break;
			case 'l':
				this.getCase(x, y - 1).setChar(true);
				ch.setY(y - 1);
				this.getCase(x, y).setChar(false);
				break;
			case 'd':
				this.getCase(x + 1, y).setChar(true);
				ch.setX(x + 1);
				this.getCase(x, y).setChar(false);
				break;
			case 'u':
				this.getCase(x - 1, y).setChar(true);
				ch.setX(x - 1);
				this.getCase(x, y).setChar(false);
				break;
			}
		}
	}

	public boolean win(){
		int nbB=nbBox();
		int ok=0;
		for(int i=0; i<getHeight(); i++){
			for (int j=0;j<getLength() ;j++ ) {
				if(tab[i][j].getContent() instanceof Box && tab[i][j].getColor()=="red"){
					ok++;
				}
				if(tab[i][j].getContent() instanceof Box && tab[i][j].getColor()=="green"){
					ok++;
				}
				if(tab[i][j].getContent() instanceof Box && tab[i][j].getColor()=="blue"){
					ok++;
				}
			}
		}
		if(ok==nbB){
			return true;
		}
		return false;
	}
	public int nbBox(){
		int res=0;
		for(int i=0;i<getHeight();i++){
			for(int j=0;j<getLength();j++){
				if(tab[i][j].getContent() instanceof Box){
					res++;
				}
			}
		}
		return res;
	}
	

	// Ce init est là pour faire des test ou bien pour définir une configuration par
	// défaut

//	public void init() {
//		for (int i = 0; i < tab.length; i++) {
//			tab[i][0] = new Case(new Wall());
//			tab[i][tab.length - 1] = new Case(new Wall());
//			if (i == 0 || i == tab.length - 1) {
//				for (int j = 1; j < tab[i].length - 1; j++)
//					tab[i][j] = new Case(new Wall());
//			} else {
//				for (int j = 1; j < tab[i].length - 1; j++)
//					tab[i][j] = new Case(new Empty());
//			}
//		}
//		ch.setX(7);
//		ch.setY(2);
//		tab[2][7].setChar(true);
//		tab[2][2].setContent(new Box("red"));
//		tab[4][5].setColor("red");
//	}

	// Un Board complétement vierge
	public void init() {
		for(int i = 0; i < tab.length; i++) {
			tab[i][0] = new Case(new Wall());
			tab[i][tab.length - 1] = new Case(new Wall());
			if (i == 0 || i == tab.length - 1) {
				for (int j = 1; j < tab[i].length - 1; j++)
					tab[i][j] = new Case("n", new Wall(), false);
			} else {
				for (int j = 1; j < tab[i].length - 1; j++)
					tab[i][j] = new Case("n", new Empty(), false);
			}
		}
	}
}
