package sokoban.project;

public class Game {
	private Player player;
	private Board board;
	private int score;

	// ===================== Constructor ========================
	public Game(Player p, Board b){
		this.player=p;
		this.board=b;
	}
	public Game(String n) {
		this(new Player(n), new Board());
	}

	// ===================== Getter & Setter ========================
	public Player getPlayer(){ return this.player; }
	public void setPlayer(Player p){ this.player=p; }

	public Board getBoard(){ return this.board; }
	public void setBoard(Board b){ this.board=b; }





}