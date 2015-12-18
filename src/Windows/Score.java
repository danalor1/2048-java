package Windows;

public class Score {
	
	private int score;
	
	public Score()
	{
		this.score=0;
	}
	
	public int getScore()
	{
		return this.score;
	}
	public void updateScore(int update)
	{
		this.score=this.score+update;
	}

}
