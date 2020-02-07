public class Playeur{
	private String nickname;
	private int score;

/* constructor*/
	public Playeur(String n){
		nickname=n;
		score=0;

	}

/*getter for score*/
	public int getscore(){
		return score;
	}
/*getter for nickname*/
	public String getNickname(){
		return nickname;
	}
/*setter for score*/
	public void setScore(int s){
		score=s;
	}



}