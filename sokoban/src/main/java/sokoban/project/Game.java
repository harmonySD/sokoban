package sokoban.project;

public class Game {
	private Player player;
	private Board board;
	private int score;

	// ===================== Constructor ========================
	public Game(Player p, Board b){
		this.player=p;
		this.board=b;
	}
	public Game(String n) {
		this(new Player(n), new Board());
	}

	// ===================== Getter & Setter ========================
	public Player getPlayer(){ return this.player; }
	public void setPlayer(Player p){ this.player=p; }

	public Board getBoard(){ return this.board; }
	public void setBoard(Board b){ this.board=b; }
	
	
	void level_saver (String levelname ) {
			try{
			PrintWriter fichier = new PrintWriter(new FileWriter(levelname+".txt"));
			int brdlen = this.board.getLength();
			int brdhei = this.board.getHeight();
			fichier.print(brdlen+","+brdhei+";");
			for (int i =0;i<brdlen;i++){
				for (int j = 0;j<brdhei;j++){
					if (board.getCase(i,j).getContent() instanceof Wall){
						fichier.print("W,");
						continue;
					}
					if(board.getCase(i,j).getContent() instanceof Box){
						if ( ( (Box)board.getCase(i,j).getContent() ).getColor().equals("red")){
							fichier.print("r");
						}
						if ( ( (Box)board.getCase(i,j).getContent() ).getColor().equals("green")){
							fichier.print("g");
						}
						if ( ( (Box)board.getCase(i,j).getContent() ).getColor().equals("blue")){
							fichier.print("b");
						}
					}
					if(board.getCase(i,j).getContent() instanceof Empty){// soit il n'y a rien, soit il y a le personnage   
						if (board.getCase(i,j).getChar()){
							fichier.print("P");
						}
						else{
							fichier.print("E");
						}
					}
					if(! (board.getCase(i,j).getContent() instanceof Empty || board.getCase(i,j).getContent() instanceof Box || board.getCase(i,j).getContent() instanceof Wall  ) )	{fichier.print("N,"); // il faudra l'améliorer pour l'optimiser, mais je ne vois pour l'instant comment éviter ces tests redondants (si on est sur une case VOID qui ne peut rien contenir)
		continue ;}		if(board.getCase(i,j).getColor().equals("red") ){fichier.print("R");}
					else if(board.getCase(i,j).getColor().equals("green") ) { fichier.print("G");}
					else if(board.getCase(i,j).getColor().equals("blue") ) { fichier.print("B");}
					if (board.getCase(i,j).getBonus() ){fichier.print("+");}
					fichier.print(",");
				}	
			// choisi de faire une fonction d'objet et pas de classe 
			// car on ne devrait pas avoir plusieurs objets  game ?
			} 
			fichier.print(";");
		fichier.close();
		}catch (IOException e){};
	}
}






}