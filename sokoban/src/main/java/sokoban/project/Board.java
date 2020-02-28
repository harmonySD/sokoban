package sokoban.project;
import java.awt.event.KeyEvent;
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
/*
	public void movePlayer(int direction){
		switch(direction){
			case KeyEvent.VK_UP :
				goUP();
				break;
			case KeyEvent.VK_DOWN :
				goDown();
				break;
			case KeyEvent.VK_RIGHT :
			 	goRight();
			 	break;
			case KeyEvent.VK_LEFT :
				goLeft();
				break;
		}
	}
	
    // Méthode redéfinie depuis KeyListener
    public void keyPressed(KeyEvent key){
        // touche pressée
         
        // Je test les valeurs de ma touche
         
        int codeDeLaTouche = key.getKeyCode();
         
        switch (codeDeLaTouche) // Les valeurs sont contenue dans KeyEvent. Elles commencent par VK_ et finissent par le nom de la touche
        {
            case KeyEvent.VK_UP: // si la touche enfoncée est celle du haut
                goUP();
                break;
            case KeyEvent.VK_LEFT: // si la touche enfoncée est celle de gauche
                goLeft();
                break;
            case KeyEvent.VK_RIGHT: // si la touche enfoncée est celle de droite
                goRight();
                break;
            case KeyEvent.VK_DOWN: // si la touche enfoncée est celle du bas
                goDown();
                break;
        }
    }
 
   // Méthode redéfinie depuis KeyListener
    public void keyReleased(KeyEvent key){
        // touche relachée
    }
 
    // Méthode redéfinie depuis KeyListener
    public void keyTyped(KeyEvent key){
        // touche appuyée
    }
 
    public void goUP(){
  		if (tab[char.getX()][char.getY()].getContent() instanceof Empty) {
  			char.setX()=-1;
  		}
  		if (tab[char.getX()][char.getY()].getContent() instanceof Box) {
  			
  		}
    }
 
    public void goRight(){
        
    }
 
    public void goLeft(){
       
    }
 
    public void goDown(){
       
    }*/

    //Function returning boolean depending on if character movement's possible
    public boolean askMoveCharacter(int x, int y,char destination){
    	switch(destination){
    		// Si les coordonnées de la nouvelle direction sont correctes
			// Et que la case destination est vide => TRUE
    		//Sinon si les coordonnées de la nouvelle direction sont correctes
    		//Et que la case destination contient une boite-> si askMoveBox vrai => TRUE et on appel moveBox (FALSE sinon)
			// Sinon FALSE
    		case'r':
    			if (y+1 < this.getLength() && this.getCase(x, y+1).getContent() instanceof Empty) {return true;}
    			else if(y+1 < this.getLength() && this.getCase(x, y+1).getContent() instanceof Box){
    				if(askMoveBox(x,y,destination)) {
    					moveBox(x,y,destination);
    					return true;
    				}
    				else {return false;}
    			}
    		case'l':
    			if (y-1 < this.getLength() && this.getCase(x, y-1).getContent() instanceof Empty) {return true;}
    			else if(y-1 < this.getLength() && this.getCase(x, y-1).getContent() instanceof Box){
    				if(askMoveBox(x,y,destination)) {
    					moveBox(x,y,destination);
    					return true;
    				}
    				else {return false;}
    			}
    		case'd':
    			if (x+1 < this.getLength() && this.getCase(x+1, y).getContent() instanceof Empty) {return true;}
    			else if(x+1 < this.getLength() && this.getCase(x+1, y).getContent() instanceof Box){
    				if(askMoveBox(x,y,destination)) {
    					moveBox(x,y,destination);
    					return true;
    				}
    				else {return false;}
    			}
    		case'u':
    			if (x-1 < this.getLength() && this.getCase(x-1, y).getContent() instanceof Empty) {return true;}
    			else if(x-1 < this.getLength() && this.getCase(x-1, y).getContent() instanceof Box){
    				if(askMoveBox(x,y,destination)) {
    					moveBox(x,y,destination);
    					return true;
    				}
    				else {return false;}
    			}
    	}
    	return false;
    }

    //Function moving the character if it's possible
    public void moveCharacter(int x, int y, char destination){
    	Case myCharacter=this.getCase(x,y);
    	//
    	if (myCharacter.getChar()==true && askMoveCharacter(x,y, destination)) {
			//Effectuer le mouvement en mettant une box dans la case destination 
			//Et en vidant la case depart du character
			switch(destination){
				case'r':
					this.getCase(x, y+1).setChar(true);
					ch.setY(y+1);
					this.getCase(x, y).setChar(false);
					break;
				case'l':
					this.getCase(x, y-1).setChar(true);
					ch.setY(y-1);
					this.getCase(x, y).setChar(false);
					break;
				case'd':
					this.getCase(x+1,y).setChar(true);
					ch.setX(x+1);
					this.getCase(x,y).setChar(false);
					break;
				case'u': 
					this.getCase(x-1,y).setChar(true);
					ch.setX(x-1);
					this.getCase(x,y).setChar(false);
					break;
			}
    	}
    }

    // Function returning boolean depending on if box movement's possible
	public boolean askMoveBox(int x, int y, char destination) {
		switch(destination) {
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

	// Function moving the box if possible
	public void moveBox(int x, int y, char destination) {
		Case myBox = this.getCase(x, y);
		// Si c'est une box et que le mouvement est possible => TRUE Sinon FALSE
		if(myBox.getContent() instanceof Box && askMoveBox(x, y, destination)) {
			Empty vide = new Empty();
			// Effectuer le mouvement en mettant une box dans la case destination
			// Et en vidant la case de la box de départ
			switch(destination) {
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
		ch.setY(2);
		tab[2][7].setChar(true);
		tab[2][2].setContent(new Box("red"));
		tab[4][5].setColor("red");
	}
}

    
