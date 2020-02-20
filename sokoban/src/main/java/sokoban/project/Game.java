public class Game{
	private Player player;
	private Board board;
	private int score;

	// ===================== Constructor ========================
	public Game(Player p, Board b, int sc){
		this.player=p;
		this.board=b;
		this.score=sc;
	}

	// ===================== Getter & Setter ========================
	public Player getPlayer(){ return this.player; }
	public void setPlayer(Player p){ this.player=p; }

	public Board getBoard(){ return this.board; }
	public void setBoard(Board b){ this.board=b; }

	public int getScore(){ return this.score; }
	public void setScore(int s){ this.score=s; }



}