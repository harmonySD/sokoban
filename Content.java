class Content {

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

}
