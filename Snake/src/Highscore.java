import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Highscore implements Serializable
{
	// wird nicht mitgespeichert
	private transient Image h;
	
	// wird gespeichert
	private HighscoreEntry [] scoreTable = new HighscoreEntry[5] ;

	public Highscore()
	{
		try
		{
			// das Hintergrundbild laden
			h = ImageIO.read(new File("images/Highscore.png"));
		}
		catch(IOException e)
		{
			System.out.println("Das Highscorebild konnte nicht geladen werden.");
		}
		
		HighscoreEntry empty = new HighscoreEntry();
		empty.setName("unnamed");
		empty.setScore(0);
		
		for(int i = 0; i < scoreTable.length; i++)
		{
			scoreTable[i] = empty;
		}
	}
	
	public static Highscore loadHighscore() {
		FileInputStream fis;
		
		try {
			fis = new FileInputStream("datei.dat");
			ObjectInputStream ois;
			
				ois = new ObjectInputStream(fis);
				Highscore hc= (Highscore)ois.readObject();
				ois.close();
				
				return hc;
			} catch (IOException e) 
			{
				System.out.println("Datei konnte nicht geladen werden.");
			} catch (ClassNotFoundException e)
			{
			
			}
		return null;
	}
	public static void saveHighscore(Highscore h)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("datei.dat");
		
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(h);
			oos.close();
		} catch (IOException e) 
		{
			System.out.println("Datei konnte nicht gespeichert werden.");
			e.printStackTrace();
		}
	}
	
	public void setHighscoreEntry(String name, int punkte) 
	{
		HighscoreEntry x = new HighscoreEntry();
		x.setName(name);
		x.setScore(punkte);
	
		
		for( int i = 0; i < scoreTable.length;i++)
		{
			
			if(scoreTable[i].getScore() < x.getScore())
			{

				for(int k = scoreTable.length-2; k > i ;k--) 
				{
					scoreTable[k+1] = scoreTable[k];
				}
				
				scoreTable[i] = x;
				
				break;
			}
		}
	}
	
	public void drawCoolText(Graphics g, String str, int x, int y, int size)
	 {
		 g.setColor(Color.BLACK);
		 g.setFont(new Font("TimesRoman", Font.BOLD, size));
		 g.drawString(str, x+2, y+2);
		 g.setColor(Color.WHITE);
		 g.setFont(new Font("TimesRoman", Font.BOLD, size));
		 g.drawString(str, x, y);
	 }

	public void drawHighscore(Graphics g)
	{
		g.drawImage(this.h,0 , 0 , 800, 200 ,null );
		for(int i = 0; i < scoreTable.length; i++) {
			drawCoolText(g, "Name: " + scoreTable[i].getName(), 100, 220+i*50, 38);
			drawCoolText(g, "Score:" + scoreTable[i].getScore(), 550, 220+i*50, 38);	
			
		}
	}
}

