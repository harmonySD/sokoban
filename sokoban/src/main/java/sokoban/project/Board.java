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
		tab[2][7].changeChar(true);
		tab[2][2].setContent(new Box("red"));
		tab[4][5].setColor("red");
	}
}

    
