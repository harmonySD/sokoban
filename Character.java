class Character {

    private int x;
    private int y;
    private int bonus;

    // ===================== Getter & Setter ========================
    public int getX() { return this.x ; }
    public void setX(int x) { this.x = x ; }

    public int getY() { return this.y ; }
    public void setY(int y) { this.y = y;}

    public int getBonus() { return this.y ; }
    public void setBonus(int y) { this.y = y;}

    // Functions manipulating Bonus attribute
    public void incrementBonus() { this.setBonus(this.getBonus() + 1); }
    public void decrementBonus() { this.setBonus(this.getBonus() - 1); }

    // ===================== Constructor ========================
    public Content(int x, int y, int bonus) {
        this.x = x;
        this.y = y;
        this.bonus = bonus;
    }
}