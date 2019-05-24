import java.io.Serializable;

public class HighscoreEntry implements Serializable 
{
	private String name;
	private int score;

	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
}	