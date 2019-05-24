import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Frame;
import javax.imageio.ImageIO; 

public class GameWindow extends Canvas implements WindowListener, MouseListener
{
	Image meinBild;				//Hintergrund Bild deklarieren
	Image Player1;
	Image Player2;
	Image Logo;
	Image pokal;
	int p1;
	int p2;

	Frame f;					//Rahmen deklarieren
	Canvas c;					//Leinwand deklarieren
	
	TicTacToe game;				// Bezeichner Game der Klasse TicTacToe
	
	int winner = 0;
	
	public GameWindow() 
	{
		// rahmen erstellen
		f = new Frame ("TicTacToe");	
				
		// leinwandgroesse setzen
		this.setSize(800, 800);
		
		// der leinwand eine mausabfrage hinzufügen
		this.addMouseListener((MouseListener)this);
	
		// bilder laden
		try
		{
			meinBild = ImageIO.read(new File("images/bg.jpg"));
			Player1 = ImageIO.read(new File("images/Player1.png"));
			Player2 = ImageIO.read(new File("images/Player2.png"));
			Logo = ImageIO.read(new File("images/Headline.png"));
			pokal = ImageIO.read(new File("images/Pokal.png"));
		}
		catch(IOException e){}
		
		// die leinwand dem rahmen hinzufügen
		f.add(this); 

		// alles schön verpacken
		f.setResizable(false);
		f.pack();
		
		// in die mitte des Bildschirms setzen
		f.setLocationRelativeTo(null);
		
		// und die Close X Taste aktivieren
		f.addWindowListener((WindowListener)this);
		
		game = new TicTacToe();
		
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) 
	{ 
		//Hintergrund malen
		g.drawImage(meinBild, 0, 0, 800, 800, null);
		g.drawImage(Logo, 0, 0,800,400, null);
		
		//spielfeld & steine malen
		game.drawField(g);
		
		// normaler Zug	
		if(winner==0)
			{
			if(game.getActivePlayer() == 1) {
				g.drawImage(Player1, 10, 700, null);
			}	
			else
			{
				g.drawImage(Player2, 500, 700, null);
			}
		}
		
		//Spieler hat gewonnen
		if (winner == 1)
		{
			p1++; 
			g.drawImage(Player1, 250, 0,300,200, null);
			 
			g.drawImage(pokal, 300, 200,200,400, null);
			 
			this.drawCoolText(g, "Player 1 hatt "+p1+" mal gewonnen!!", 100, 300, 48);
			 
		}
		
		// KI hat gewonnen
		if (winner == 2)
		{
			 g.drawImage(Player2, 250, 0,300,200, null);
			 p2++;
			 g.drawImage(pokal, 300, 200,200,400, null);
			 
			 this.drawCoolText(g, "Player 2 hatt "+p2+" mal gewonnen!!", 100, 300, 48);
			 
			  
		}
		
		// Unentschieden
		if (winner == 4)
		{
			g.drawImage(Player1, 250, 0,300,200, null); 
			g.drawImage(Player2, 250, 500,300,200, null);
			
			 g.drawImage(pokal, 300, 200,200,400, null);
		}
	}

	public void drawCoolText(Graphics g, String str, int x, int y, int size)
	 {
		 g.setColor(Color.BLACK);
		 g.setFont(new Font("TimesRoman", Font.PLAIN, size));
		 g.drawString(str, x+5, y+5);
		 g.setColor(Color.WHITE);
		 g.setFont(new Font("TimesRoman", Font.PLAIN, size));
		 g.drawString(str, x, y);
	 }

	public void mousePressed(MouseEvent e)
	{
		if(winner == 0)
		{
			// mausposition berechnen
			int x = e.getX()-100;
			int y = e.getY()-100;
			
			// wenn die maus innerhalb des spielfeldes ist...
			if((x >0) && (x<600) && (y>0) && (y<600)) 
			{
				int i = x/200;
				int j = y/200;
				
				// ..und das feld noch leer ist..
				if(game.getField(i, j) ==0)
				{
					// ... setze einen spielstein an diese position
					game.setField(i, j, game.getActivePlayer());
					winner = game.checkWin();
					
					// und der nächste ist dran
					game.switchPlayer();
				}
				repaint();
			}
			
			// hat noch keiner gewonnen? ...
			if(winner == 0) 
			{
				// und die KI ist am zug?
				if(game.getActivePlayer() == 2) 
				{
					// KI macht ihren zug
					game.kiMakeMove();
					winner = game.checkWin();
					
					// und der spieler ist wieder dran
					game.switchPlayer();
					repaint();
					
				}
			}
		}
		else
		{
			// ist das spiel vorbei und jmd hat die maustaste gedrückt?..
			// ... dann alles zurücksetzen und neu beginnen
			game.reset();
			winner = 0;
		}
		
	}
	
	public static void main(String[] args)
	{
		new GameWindow();
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void windowDeiconified(WindowEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void windowOpened(WindowEvent e)	{}
	public void windowClosed(WindowEvent e)	{}
	public void windowIconified(WindowEvent e) {}
}