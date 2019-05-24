// für Dateien
import java.io.File;
import java.io.IOException;

// für Bilder 
import java.awt.Image;
import javax.imageio.ImageIO;

// zum malen
import java.awt.Graphics;

public class Apple 
{
	private int x;
	private int y;
	private Image Apple;

	public Apple() 
	{
		this.x = (int) (Math.random() * 18 + 1) * 40 - 20;
		this.y = (int) (Math.random() * 18 + 1) * 40 - 20;
		
		try
		{
			Apple = ImageIO.read(new File("images/Apple1.png"));
		}
		catch(IOException e)
		{
			System.out.println("Bild: Apple nicht gefunden.");
		}	
	}
	
	public void newPos() 
	{
		this.x = (int) (Math.random() * 18 + 1) * 40 - 20;
		this.y = (int) (Math.random() * 18 + 1) * 40 - 20;
	}
	 
	public void drawApple(Graphics g) 
	{
		// den Apfel malen
		g.drawImage(Apple, this.x-30, this.y-30, 60, 60, null);
	}
	
	// Getter und Setter
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
