public class Player
{
	private int x;
	private int y;
	private int pn;
	private short color; 
	private boolean active;
	private String name;

	public Player(int pn)
	{
		this.x = 1;
		this.y = 1;
		this.pn = pn;
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getPn()
	{
		return this.pn;
	}	
	
	public short getColor()
	{
		return this.color;
	}	
	
	public boolean isActive(){
		return this.active;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public void moveLeft()
	{
		if(this.x > 0) 
		{
			this.x--;
		}
	}
	
	public void moveRight()
	{
		if(this.x < 2) 
		{
			this.x++;
		}
	}
	
	public void moveUp()
	{
		if(this.y > 0) 
		{
			this.y--;
		}
	}
	
	public void moveDown()
	{
		if(this.y < 2) 
		{
			this.y++;
		}
	}
}