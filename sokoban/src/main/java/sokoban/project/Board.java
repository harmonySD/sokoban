public class Board {
    private Case[][] tab;
    private Character character;

   //=================Constructeurs =========================
    public Board(int szx,int szy){
		this.tab=new Case[szx][szy];
		this.character = null;
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
	public Character getCharacter(){
		return this.character;
	}
	public void setCharacter(Character character){
		this.character = character; 
	}
        
	public Case getCase(int x, int y){
		return tab[x][y];
    }

// CE INIT INACHEVE EST POUR TESTER LE TOUT PREMIER PROTOTYPE
	// Ce init est juste là pour faire des test  

	public void init(){
		/*	
	for (int i =0;i< tab.len;i++){
	    tab[i][0].content = new Content.Wall();
	    tab[i][tab.len-1]  new Content.Wall();
	    if (i ==0 || i == tab.len-1){
		for (int j =1;i<tab[i].len;i++)tab[i][j].content = new Content.Wall();
	    }
	    tab[7][3].content = Content.Character(7,3);
	    this.character = (Content.character) tab[7][3].content;
	    */
	}
}
    
