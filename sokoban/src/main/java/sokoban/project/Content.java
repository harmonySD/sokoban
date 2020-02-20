public class Content {

	public Content(){}

  // Class Empty ----------------------------------------------------
  public class Empty{}
  
  // Class Wall -----------------------------------------------------
  public class Wall {}

  // Class Box ------------------------------------------------------
  public class Box {
    private String color;

    // ===================== Getter & Setter ========================
    public String getColor() { return this.color ; }
    public void setColor(String col) { this.color = col ; }

    // ===================== Constructor ========================
    public Box(String c) {
      this.color = c;
    }
  }

  // Class Character -----------------------------------------------
  public class Character {
    private int x;
    private int y;
    private int bonus;

    // ===================== Getter & Setter ========================
    public int getX() { return this.x ; }
    public void setX(int x) { this.x = x ; }

    public int getY() { return this.y ; }
    public void setY(int y) { this.y = y ; }

    public int getBonus() { return this.bonus ; }
    public void setBonus(int y) { this.bonus = bonus ; }

    public void incrementBonus(int y) { this.setBonus(this.getBonus() + 1); }


    // ===================== Constructor ========================
    public Character(int x, int y) {
      this.x = x;
      this.y = y;
      this.bonus = 0;
    }

    public Character(int x, int y, int bonus) {
      this.x = x;
      this.y = y;
      this.bonus = bonus;
    }
  }

}
