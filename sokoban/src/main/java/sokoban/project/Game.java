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
		this.path=System.getProperty("user.dir");// sera appelé dans les deux cas 
	}
	public Game(String n) {
		this(new Player(n), new Board());
		level_loader("niveautest.txt"); // ajoutez cette ligne pour texter la fonction de chargement  de NIVEAU 
		level_saver("niveautest2");
		//PlayerLoader("Profildetest.txt"); // ajoutez cette ligne pour tester la fonction de chargement de JOUEUR
	}

	// ===================== Getter & Setter ========================
	public Player getPlayer(){ return this.player; }
	public void setPlayer(Player p){ this.player=p; }

	public Board getBoard(){ return this.board; }
	public void setBoard(Board b){ this.board=b; }
	
	
	// ==================== level loader & saver ======================
	void level_saver (String levelname ) { // ne doit PAS contenir .txt
			try{
			PrintWriter fichier = new PrintWriter(new FileWriter(this.path+"/target/Niveaux/"+levelname+".txt"));
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
					}// il faudra l'améliorer pour l'optimiser, mais je ne vois pour l'instant comment éviter ces tests redondants (si on est sur une case VOID qui ne peut rien contenir)
					if(! (board.getCase(i,j).getContent() instanceof Empty || board.getCase(i,j).getContent() instanceof Box || board.getCase(i,j).getContent() instanceof Wall  ) ){
						fichier.print("N,"); // peut se traduire par if ! content ??
						continue ;
					}		
					if(board.getCase(i,j).getColor().equals("red") ){fichier.print("R");}
					else if(board.getCase(i,j).getColor().equals("green") ) { fichier.print("G");}
					else if(board.getCase(i,j).getColor().equals("blue") ) { fichier.print("B");}
					if (board.getCase(i,j).getBonus() ){fichier.print("+");}
					fichier.print(",");
				}	
			} 
			fichier.print(";");
			//
			fichier.print(board.get_max3stars()+","+board.get_max2stars()+";");
		fichier.close();
		}catch (IOException e){};
		System.out.println("sauvegarde réussi du fichier "+levelname+".txt");
	}

	boolean level_loader (String filename ){ // DOIT contenir .txt 
		File test = new File(this.path+"/target/Niveaux/"+filename);

		System.out.println("Tentative de chargement de niveau depuis " + this.path+"/target/Niveaux/"+filename );
		if (!test.exists()){System.out.println(" Fichier à charger introuvable, arret");return false ; }
		int nmur = 0;
		int npers = 0;
		int nvide = 0;
	    int nbox = 0;
		int nbo = 0;
		int nbpv =0;
		int casw = 0; // nb cases écrits pour compter à la fin 
		int lenw = 0;// nbl ligne réellement écrite pour test à la fin 
		try{
			Reader fichier = new FileReader(this.path+"/target/Niveaux/"+filename);
			int data = ' '; // stockeur cractère par caractère 
			String temp = null; // stockeur "case par case "
			int len =  0;
			int hei = 0;
			String slen = new String();// le string qui contient la longueur du tableau
			String shei = new String();// meme chose pour la hauteur 
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
			   return false ;
			}
				Board  borstock = new Board(len,hei);
				Case stockeur ; // variable tampon, stocke la case du tableau en cours de création 
				for (int i =0;i<len;i++){System.out.print("\n");
				lenw++; // pour savoir si le fichier n'était corrompu et nb case écrit incomplet  
					for(int j =0;j<hei;j++){
						casw++; // // pour savoir si le fichier n'était corrompu et nb case écrit incomplet  
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
			if(lenw != len  || casw != hei*len ){System.out.println("Erreur, le fichier est peut être corrompu, nombre de case écrit différent de celui attendu :  len attendu  "+ len+" vs obtenu "+lenw +" nb total écriture  attendu "+ hei*len +" vs obtenu "+casw );
				fichier.close();
				return false ;
			}
			while(data !=';' && data !=-1) { data = fichier.read(); } // il y a un ';' après les données de niveau et avant les données de score à faire  
				String sm3s = new String();// pour stocker les string obtenus avant parsage 
				String sm2s = new String();
				
				while (data !=','&& data !=-1){ 
					data = fichier.read();
					if((char)data ==',') break;// une virgule sépare le score pour 3 étoiles de celui  du deuxième
					sm3s+= (char)data;
				}
				
				while (data !=';'&& data !=-1){ // un point virgule clotûrle le fichier 
					data = fichier.read();
					if((char)data ==';') break;
					sm2s+= (char)data;
				}
				int m3s;
				int m2s;
				try{
				m3s = Integer.parseInt(sm3s);
				m2s  = Integer.parseInt(sm2s);  
				}
				catch (NumberFormatException e){
					System.out.println("NumberFormatException, nombre de coups");
				   return false ;
				}
				borstock.set_max3stars(m3s);
				borstock.set_max2stars(m2s);
				fichier.close();
				this.board = borstock;
		}catch (java.io.IOException  e ){
			System.out.println("JAVA IO EXCEPTION");
		}
		System.out.println("Le chargement du fichier "+filename+" a eu lieu avec succès ");
		System.out.println("Ont été chargés : \nVides " +nvide+"\nMurs" +nmur+"\nPersonnage "+npers+ "\nCaisses "+nbox +"\nBonus "+nbo  +"\nPoints de victoires "  +nbpv );
		System.out.println("nombre de déplacement max pour obtenir 3 étoiles : "+this.board.get_max3stars() +" pour deux étoiles  : "+this.board.get_max2stars()  );
		return true ;
	}
	
	// ==================== Player profile loader & saver ======================
	public boolean PlayerSaver(String profilename){ // ne doit PAS contenir .txt 
		try{
			PrintWriter fichier = new PrintWriter(new FileWriter(this.path+"/target/Profils/" +profilename+".txt"));
			fichier.print("PROFILESAVE \n");
			fichier.print("NAME= "+ this.player.getNickname()+"\n");
			fichier.print("SCORE= "+ this.player.getScore()+"\n" );
			fichier.close();
		}catch(IOException e ){
			System.out.println("Sauvegarde du profil " +profilename+" échouée  Exception entrée sortie");
			return false;
		}
		System.out.println("Sauvegarde du profil " +profilename+" réussie");
		return true;
	}

	
	public boolean PlayerSaver(){ // surdéfintion de player Saver
		return PlayerSaver(this.player.getNickname());
	}
	
	public boolean PlayerLoader(String filename){ // filename DOIT contenir ".txt "
		File test = new File(this.path+"/target/Profils/"+filename);
		System.out.println("Tentative de chargement de niveau depuis " + this.path+"/target/Profils/"+filename );
		if (!test.exists()){System.out.println(" Fichier de profil à  charger introuvable, arret");return false;}
		String fcheck = new String(); // pour vérifier que le format de sauvegarde est correct
		int data = 0;
		try{
			Reader fichier = new FileReader(this.path +"/target/Profils/"+filename);
			for(int i =0;i<19;i++){ // 19 est la longueur de  "PROFILESAVE \nNAME= " /!\ attention à l'espace !
				data = fichier.read();
				if(data ==-1){fichier.close(); System.out.println("Le fichier de profil  chargé est corrompu, arrêt 1 "); return false;}
				fcheck += (char)data;
			}
			if (0 !="PROFILESAVE \nNAME= ".compareTo(fcheck)){ // il est attendu que le fichier de profil comporte au début ceci
				fichier.close();
				System.out.println("PROFILESAVE \nNAME= ".compareTo(fcheck) + fcheck);
				System.out.println("Le fichier chargé n'est pas celui d'un profil, arrêt  ");
				return false;
			}
			fcheck = new String();
			String sname = new String();
			String sscore = new String();
			while((char)data != '\n'){
				data = fichier.read();
				if(data ==-1){fichier.close(); System.out.println("Le fichier de profil  chargé est corrompu, fichier incomplet "); return false;}
				sname += (char)data;
			}
			
			Player stockp = new Player(sname.substring(0,sscore.length() -1));// substring  car le \n à la fin a été aussi recopié !
			fcheck = new String();
			for(int i =0;i<7;i++){ // 6 est la longueur de "SCORE= "/!\ attention à l'espace !
				data = fichier.read();
				if(data ==-1){fichier.close(); System.out.println("Le fichier de profil  chargé est corrompu, fichier incomplet "); return false;}
				fcheck+= (char)data;
			}
			
			if (0 !="SCORE= ".compareTo( fcheck)){ // la suite de ce à quoi doit ressembler le fichier après le pseudo et le \n
				fichier.close();
				System.out.println("Le fichier de profil  chargé est corrompu, arrêt  4");
				return false;
			}
			while((char)data != '\n'){
				data = fichier.read();
				if(data ==-1){fichier.close(); System.out.println("Le fichier de profil  chargé est corrompu, arrêt lors de la lecture du score "); return false;}
				sscore += (char)data;
			}
			try{ stockp.setScore( Integer.parseInt(sscore.substring(0,sscore.length() -1))); // car le caractère \n à la fin a été lu, il faut le retirer 
			
			}catch(NumberFormatException nfe){

				System.out.println(" Erreur dans la lecture du score : NumberFormatException");
			   return false ;
			}
			fichier.close();
			this.player = stockp;
		}catch(IOException e){System.out.println("Erreur d'entrée sortie  lors de la lecture ");
			return false;
		}
		
		return true;
	}
}
