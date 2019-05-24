import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakeBodyElement
{
	private int x;
	private int y;
	private Image body;
	
	public SnakeBodyElement(int x, int y)
	{
		try
		{
			body = ImageIO.read(new File("images/body.png"));
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Fehler beim Laden des body-Bildes.");
		}
		
		this.x = x;
		this.y = y;
	}
	
	public void drawBodyElement(Graphics g)
	{
		g.drawImage(body, x-25,y-25, 50, 50, null);
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
}
