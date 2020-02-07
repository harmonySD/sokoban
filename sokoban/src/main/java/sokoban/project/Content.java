class Content {
  private int x;
  private int y;

  // ===================== Getter & Setter ========================
  public int getX() { return this.x ; }
  public void setX(int num) { this.x = num ; }

  public int getY() { return this.y ; }
  public void setY(int num) { this.y = num ; }

  // ===================== Constructor ========================
  public Content(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // Class Character ------------------------------------------------
  public Character {
    private int texture;

    // ===================== Getter & Setter ========================
    public int getTexture() { return this.texture ; }
    public void setTexture(int tmp) { this.texture = tmp ; }

    // ===================== Constructor ========================
    public Character(int t) {
      this.texture = t;
    }
  }

  // Class Wall -----------------------------------------------------
  public Wall {
    private int texture;

    // ===================== Getter & Setter ========================
    public int getTexture() { return this.texture ; }
    public void setTexture(int tmp) { this.texture = tmp ; }

    // ===================== Constructor ========================
    public Wall(int t) {
      this.texture = t;
    }
  }

  // Class Box ------------------------------------------------------
  public Box {
    private int texture;
    private string color;

    // ===================== Getter & Setter ========================
    public int getTexture() { return this.texture ; }
    public void setTexture(int tmp) { this.texture = tmp ; }

    public string getColor() { return this.color ; }
    public void setColor(string col) { this.color = col ; }

    // ===================== Constructor ========================
    public Box(int t, string c) {
      this.texture = t;
      this.color = c;
    }
  }

}
