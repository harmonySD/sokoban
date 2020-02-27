package sokoban.project;

public class Case{
	private boolean character;
  private String color;
  // le contenu de la case
  private Content contain;
  private boolean bonus;
  
  // ============ Constructor ===========
  public Case(String coul, Content cont, boolean b){
    this.color = coul;
    this.contain = cont;
    this.bonus = b;
    character=false;
  }
  
  public Case (Content cont){
    this("",cont,false);
  }
  public Case() {
	  this("",new Content(), false);
  }

// ===================== Accesseurs & Mutateurs ========================
// ------------ Color ------------------------
  public String getColor() { return this.color; }
  public void setColor(String  s) { this.color = s; }

// ------------ Bonus ------------
  public boolean getBonus(){return this.bonus;}
  public void setBonus(boolean bonus){this.bonus = bonus;}

  // ------------ Contenu ----------------
  public Content getContent(){return this.contain;}
  public void setContent(Content contain ){this.contain = contain;}
  //-------------Personnage------------------
  public boolean getChar() {return character;}
  public void setChar(boolean b) {character=b;}

}
