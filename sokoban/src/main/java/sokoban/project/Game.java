package sokoban.project;
import java.io.*;

public class Game {
	private Player player;
	private Board board;
	private int score;
	private String path;

	// ===================== Constructor ========================
	public Game(Player p, Board b){
		this.player=p;
		this.board=b;
	}
	public Game(String n) {////
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
		this.path=System.getProperty("user.dir")+"/target/niveautest.txt";
		File test = new File(path); // File ou autre chose ? 
		if (!test.exists()){System.out.println("Fichier de chargement introuvable, arret");return; }
		int nmur = 0;
		int npers = 0;
		int nvide = 0;
	    int nbox = 0;
		int nbo = 0;
		int nbpv =0;
		try{
			Reader fichier = new FileReader(path);
			int data = ' ' ; // stockeur cractère par caractère 
			String temp = null; // stockeur "case par case "
			int len =  0;
			int hei = 0;
			String slen = new String();// le string qui contient la longueur du tableau
			String shei = new String();// me chose hauteur 
			do{
				data =  fichier.read();
				if (data ==-1 || (char)data==(',')) break; // si fin de fichier 
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
			try {// tentative de conversion des informations lues dans le fichier 
			   len  = Integer.parseInt(slen);// en  un nombre longueur 
			   hei  = Integer.parseInt(shei); // hauteur 
			}
			catch (NumberFormatException e){
				System.out.println("NumberFormatException");
			   return ;
			}
				Board  borstock = new Board(len,hei);
				Case stockeur ; // variable tampon, stocke la case du tableau en cours de création 
				for (int i =0;i<len;i++){System.out.print("\n");
					for(int j =0;j<hei;j++){
						borstock.setCase(i,j,new Case(new Empty())); // création d'une case 
						stockeur =borstock.getCase(i,j);   // récupération de la case pour travailler dessus
						do{// tant qu'on n'a pas atteint la fin du fichier et que l'on est entrain de traiter une case 
						data= fichier.read(); // lecture 
						System.out.print((char)data);
							if ((char)data==('W')){stockeur.setContent(new Wall()); nmur++;} 
							else if ((char)data==('N')){stockeur.setContent(new Content());nvide ++;}
							// le prochain caractère doit être une virgule mais  on laisse le while parcourir de lui même
							else{  // else est-il si nécessaire que ça ? 
								if((char)data==('E'))stockeur.setContent(new Empty());
								// on ouvre un else car tous les caractères qui suivent admettent une ou plusieurs lettres
								//dans leurs " code de sauvegarde "
								if ((char)data==('P')){
									borstock.getChar().setX(i);
									borstock.getChar().setY(j);
									stockeur.setContent(new Empty()); 
									stockeur.setChar(true);
									npers ++;
									}
								if ((char)data==('R')){stockeur.setColor("red");nbpv ++;}
								if ((char)data==('G')){stockeur.setColor("green");nbpv ++;}
								if ((char)data==('B')){stockeur.setColor("blue");nbpv ++;}
								if ((char)data==('r')){stockeur.setContent(new Box("red"));nbox++;}
								if ((char)data==('g')){stockeur.setContent(new Box("green"));nbox++;}
								if ((char)data==('b')){stockeur.setContent(new Box("blue"));nbox++;}
								if ((char)data==('+')){stockeur.setBonus(true);nbo++;}
							} 
						}while ((char)data != ',' && data !=-1);
					}System.out.println("Ligne  "+(i+1)+"/"+hei+" chargée "); 
			}
			fichier.close();
			this.board = borstock;
		}catch (java.io.IOException  e ){
			System.out.print("JAVA IO EXCEPTION");
			return;}
	System.out.println("Le chargement du fichier "+filepath+" a eu lieu avec succès ");
	System.out.println("Ont été chargés : \nVides " +nvide+"\nMurs" +nmur+"\nPersonnage "+npers+ "\nCaisses "+nbox +"\nBonus "+nbo  +"\nPoints de victoires "  +nbpv);
	}
	
	
	

}
