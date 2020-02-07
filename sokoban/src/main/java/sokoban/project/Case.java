#import Content ;

public class Case{
  // coordonees de la case
  // vraiment nécessaire si on fait juste un tableau de case ?!
  private int x;
  private int y;

  private char isObjective;
  // stocke couleur de l'objectif sous forme de Char et NULL si pas objectif
  private char bonus;
  // stocke type de bonus sous forme de Char et NULL si aucun
  Content contain;
  // le contenu de la case
  // ============ Constructor ===========
  public Case(int x, int y,char bonus,char isObjective){
    this.x = x;
    this.y = y;
    this.bonus = bonus;
    this.contain = NULL;
    this.isObjective = isObjective;
    // !! isObjective se choisit à la création !
  }

// ===================== Accesseurs & Mutateurs ========================
// ------------ Coordonees ------------------------
  public int getX() { return this.x ; }
  public void setX(int x) { this.x = x ; }

  public int getY() { return this.y ; }
  public void setY(int y) { this.y = y;}

// ------------ Bonus et objectif ------------
  public char getBonus(){return this.bonus;}
  public void setBonus(char bonus){this.bonus = bonus;}

  public char getObjective(){return this.isObjective};
  // !! il est impossible de modifier le statut isObjective d'une case après sa création !

  // ------------ Contenu ----------------
  public Content getContent(){return this.contain;}
  public void setContent(Content contain ){this.contain = contain;}

}
