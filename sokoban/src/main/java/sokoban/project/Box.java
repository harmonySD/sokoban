
public class Box extends Content {
	private String color;

    // ===================== Getter & Setter ========================
    public String getColor() { return this.color ; }
    public void setColor(String col) { this.color = col ; }

    // ===================== Constructor ========================
    public Box(String c) {
      this.color = c;
    }
}
