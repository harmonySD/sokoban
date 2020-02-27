package sokoban.project;

public class Box extends Content {
	private int x;
	private int y;
	private String color;

    // ===================== Getter & Setter ========================
    public String getColor() { return this.color ; }
    public void setColor(String col) { this.color = col ; }

    public int 

    // ===================== Constructor ========================
    public Box(String c) {
      this.color = c;
    }
}
