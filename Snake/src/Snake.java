import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Snake 
{
	private int score = 0;
	
	private SnakeBodyElement snakeHead;
	private LinkedList<SnakeBodyElement> body;

	private Image head;

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = true;  
	
	public Snake() 
	{
		setSnakeHead(new SnakeBodyElement(20, 20));
		body = new LinkedList<SnakeBodyElement>();
		
		try
		{
			head = ImageIO.read(new File("images/head.png"));
		}
		catch(IOException e)
		{
			System.out.println("Fehler beim laden des Kopfes.");
		}	
	}
	 
	public void drawSnake(Graphics g) 
	{
		// alle Body-Elemente malen 
		if(body.size() >0 )
		{
			for(int i = 0; i < body.size(); i++ )
			{
				body.get(i).drawBodyElement(g);
			}
		}
		
		//Kopf malen
		g.drawImage(head, getSnakeHead().getX()-25, getSnakeHead().getY()-25, 50, 50, null);
	}
	
	public boolean checkLost()
	{
		 for(int i = 1; i < this.body.size();i++)
		 {
			if((snakeHead.getX() == (body.get(i).getX()) && (snakeHead.getY() == body.get(i).getY())))
			{
				return true;
			}
		 }
		 return false;
	}
	
	public void addBodyElement()
	{
		if(body.size() > 0)
		{
			SnakeBodyElement s = new SnakeBodyElement(body.get(body.size()-1).getX(),body.get(body.size()-1).getY());
			body.add(s);
		}
		else
		{
			SnakeBodyElement s = new SnakeBodyElement(getSnakeHead().getX(), getSnakeHead().getY());
			body.add(s);
		}
	}
	
	public void update()
	{
		int oldX = getSnakeHead().getX();
		int oldY = getSnakeHead().getY();
		
		if(up) 
		{
			getSnakeHead().setY(getSnakeHead().getY() - 40);
		}
		
		if(down)
		{
			getSnakeHead().setY(getSnakeHead().getY() + 40);
		}
		
		if(left)
		{
			getSnakeHead().setX(getSnakeHead().getX() - 40);
		}
		
		if(right)
		{
			getSnakeHead().setX(getSnakeHead().getX() + 40);
		}
		
		if(getSnakeHead().getY() < 0)
		{
			getSnakeHead().setY(780);
		}
		
		if(getSnakeHead().getY() > 800)
		{
			getSnakeHead().setY(20);
		}
		
		if(getSnakeHead().getX() < 0)
		{
			getSnakeHead().setX(780);
		}
		
		if(getSnakeHead().getX() > 800)
		{
			getSnakeHead().setX(20);
		}
		
		if(body.size() > 1)
		{
			for(int i = body.size() - 1; i >= 1; i--) 
			{
				body.get(i).setX(body.get(i-1).getX());
				body.get(i).setY(body.get(i-1).getY());
			}
			
			body.get(0).setX(oldX);
			body.get(0).setY(oldY);
		}
		else if(body.size() == 1) 
		{
			body.get(0).setX(oldX);
			body.get(0).setY(oldY);
		}
	}
	
	public void moveRight()
	{
		if(!this.left) 
		{
			this.right = true;
			this.down = false;
			this.left = false;
			this.up = false;
		}
	}

	public void moveUp() 
	{
		if(!this.down)
		{
			this.up = true;
			this.down = false;
			this.left = false;
			this.right = false;
		}
	}

	public void moveLeft() 
	{
		if(!this.right)
		{
			this.left = true;
			this.up = false;
			this.down = false;
			this.right = false;
		}
	}

	public void moveDown() 
	{
		if(!this.up)
		{
			this.down = true;
			this.left = false;
			this.up = false;
			this.right = false;
		}
	}

	public int getScore() 
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}

	public SnakeBodyElement getSnakeHead()
	{
		return snakeHead;
	}

	public void setSnakeHead(SnakeBodyElement snakeHead)
	{
		this.snakeHead = snakeHead;
	}
}
