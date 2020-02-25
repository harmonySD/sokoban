package sokoban.project;

public class Board {
    private Case[][] tab;
    private Character ch;

   //=================Constructeurs =========================
    public Board(int szx,int szy){
		this.tab=new Case[szx][szy];
		ch=new Character(0,0);
    }
    public Board() {
    	this(10,10);
    	init();
    }
    //================= Accesseurs et mutateurs ==============
    public int getHeight() {
    	return this.tab.length;
    }
    public int getLength() {
    	return this.tab[0].length;
    }
	
        
	public Case getCase(int x, int y){
		return tab[x][y];
    }
	
	public Character getChar() {
		return this.ch;
	}
	
	
	
	// Ce init est là pour faire des test ou bien pour définir une configuration par défaut

	public void init(){
		for (int i =0;i< tab.length;i++){
			tab[i][0]=new Case(new Wall());
			tab[i][tab.length-1]=new Case(new Wall());
			if (i ==0 || i == tab.length-1){
				for (int j =1;j<tab[i].length-1;j++)tab[i][j]=new Case(new Wall());
			}
			else {
				for (int j =1;j<tab[i].length-1;j++)tab[i][j]=new Case(new Empty());
			}
		}
		ch.setX(7);
		ch.setY(2);;
		tab[2][2].setContent(new Box("red"));
		tab[4][9].setColor("red");
	}
}

    
