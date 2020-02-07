public class Content {

  private int texture;

  // ===================== Getter & Setter ========================
  public int getTexture() { return this.texture ; }
  public void setTexture(int tmp) { this.texture = tmp ; }

  // ===================== Constructor ========================
  public Content(int t) {
    this.texture = t;
  }

  // Class Wall -----------------------------------------------------
  public Wall {
    // ===================== Constructor ========================
    public Wall(int t) {
      super(t);
    }
  }

  // Class Box ------------------------------------------------------
  public Box {
    private string color;

    // ===================== Getter & Setter ========================
    public string getColor() { return this.color ; }
    public void setColor(string col) { this.color = col ; }

    // ===================== Constructor ========================
    public Box(int t, string c) {
      super(t);
      this.color = c;
    }
  }

  // Class Character -----------------------------------------------
  public Character {
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
    public Character(int t, int x, int y) {
      super(t);
      this.x = x;
      this.y = y;
      this.bonus = 0;
    }

    public Character(int t, int x, int y, int bonus) {
      super(t);
      this.x = x;
      this.y = y;
      this.bonus = bonus;
    }
  }

}
