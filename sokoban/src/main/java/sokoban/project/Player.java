package sokoban.project;

public class Player{
	private String nickname;
	private int score;

/* constructor*/
	public Player(String n){
		nickname=n;
		score=0;

	}

/*getter for score*/
	public int getscore(){
		return this.score;
	}
/*getter for nickname*/
	public String getNickname(){
		return this.nickname;
	}
/*setter for score*/
	public void setScore(int s){
		this.score=s;
	}



}