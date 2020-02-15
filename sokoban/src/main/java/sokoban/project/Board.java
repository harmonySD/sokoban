Public class Board {
    private Case[][] tab;
    private Content.Character character;

    =================Constructeurs =========================
    Public Board(int szx,int szy,){
	this.tab[szx][szy];
	this.character = NULL;
    }
    ================= Accesseurs et mutateurs ==============
	Public Content.Character getCharacter(){
		return this.character;
	}
	Public void setCharacter(Content.Character character){
	this.character = character; 
	}
        
	Public case getCase(int x, int y){
	return tab.[x][y];
        }

/* CE INIT INACHEVE EST POUR TESTER LE TOUT PREMIER PROTOTYPE
// Ce init est juste là pour faire des test  

	Public void init(){
	for (int i =0;i< tab.len;i++){
	    tab[i][0].content = new Content.Wall();
	    tab[i][tab.len-1]  new Content.Wall();
	    if (i ==0 || i == tab.len-1){
		for (int j =1;i<tab[i].len;i++)tab[i][j].content = new Content.Wall();
	    }
	    tab[7][3].content = Content.Character(7,3);
	    this.character = (Content.character) tab[7][3].content;
	}
    }*/
    
