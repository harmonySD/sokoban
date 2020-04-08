package sokoban.project;
import java.io.*;

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
		level_loader("niveautest.txt");
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



	void level_loader (String filepath ){
		File test = new File(filepath); // File ou autre chose ? 
		if (!test.exists())return ; 
		try{
		Reader fichier = new FileReader(filepath);
		int data = ' ' ; // stockeur cractère par caractère 
		String temp = null; // stockeur "case par case "
		int len =  0;
		int hei = 0;
		String slen = new String();
		String shei = new String();
		do{
			data =  fichier.read();
			if (data ==-1 || (char)data==(',')) break;
			slen += (char)data;
			}
		while (data !=-1 && (char)data!=(',') );
		
		do{
			data =  fichier.read();
			if (data ==-1 || (char)data==(';'))break;
			shei += (char)data;
			}
		while (data !=-1 && (char)data!=(';') );
		//lecture de la taille du board 
		
		try {
		   len  = Integer.parseInt(slen);
		   hei  = Integer.parseInt(shei);
			}
			catch (NumberFormatException e){
		   return ;
			}
			Board  borstock = new Board(len,hei);
			Case stockeur ;
			for (int i =0;i<len;i++){
				for(int j =0;j<hei;j++){
					borstock.setCase(i,j,new Case(new Empty()));
					stockeur =borstock.getCase(i,j);  
					while (data != ',' && data !=-1){ 
					data= fichier.read(); 
						if ((char)data==('W'))stockeur.setContent(new Wall());
						else if ((char)data==('N'))stockeur.setContent(new Content());
						// le prochain caractère doit être une virgule mais  on laisse le while parcourir de lui même
						else{ 
							if((char)data==('E'))stockeur.setContent(new Empty());
							// else est-il si nécessaire que ça ? 
							// on ouvre un else car tout les caractères qui suivent admettent une ou plusieurs lettres
							//dans leurs " code de sauvegarde "
							if ((char)data==('P')){
								borstock.getChar().setX(i);
								borstock.getChar().setY(j);
								stockeur.setContent(new Empty()); 
								stockeur.setChar(true);
								}
							if ((char)data==('R'))stockeur.setColor("red");
							if ((char)data==('G'))stockeur.setColor("green");
							if ((char)data==('B'))stockeur.setColor("blue");
							if ((char)data==('r'))stockeur.setContent(new Box("red"));
							if ((char)data==('g'))stockeur.setContent(new Box("green"));
							if ((char)data==('b'))stockeur.setContent(new Box("blue"));
							if ((char)data==('+'))stockeur.setBonus(true);
						} 
					} 
				}System.out.println("Ligne  "+(i+1)+"/"+hei+" chargée "); 
			}
			fichier.close();
			this.board = borstock;
		}catch (java.io.IOException  e ){return;}
	System.out.println("Le chargement du fichier "+filepath+" a eu lieu avec succès ");
	}
}
