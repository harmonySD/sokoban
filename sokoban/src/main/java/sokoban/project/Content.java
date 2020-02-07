class Content {

  // Class Wall -----------------------------------------------------
  public Wall {
  // ===================== Constructor ========================
    public Wall(){}
   
  }

  // Class Box ------------------------------------------------------
  public Box {
    private string color;

    // ===================== Getter & Setter ========================
    public string getColor() { return this.color ; }
    public void setColor(string col) { this.color = col ; }

    // ===================== Constructor ========================
    public Box(string c) {
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
