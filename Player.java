public class Playeur{
	private String nickname;
	private int score;

	public Playeur(String n){
		nickname=n;
		score=0;

	}


	public int getscore(){
		return score;
	}
	public String nickname(){
		return nickname;
	}
	public void setScore(int s){
		score=s;
	}



}