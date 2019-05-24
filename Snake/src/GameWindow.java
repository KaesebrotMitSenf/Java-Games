// um auf Benutzereingaben zu reagieren m�ssen diese Klassen eingef�gt werden
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// f�r Bilder 
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// um Dateien zu laden
import java.io.File;
import java.io.IOException;

// um ein Fenster anzuzeigen und darauf zu malen
import java.awt.Frame;
import java.awt.Canvas;
import java.awt.Graphics;
 
public class GameWindow extends Canvas implements WindowListener, MouseListener, KeyListener, Runnable
{
	//Hintergrund Bild deklarieren
	private Image hintergrund;				
	
	// um die Spielschleife zu verlassen muss exit auf true gesetzt werden
	private boolean exit = false;

	// der Fensterrahmen
	private Frame f;

	// und ein Thread der die ganze Zeit l�uft und das Spiel ver�ndert
	private Thread gameLoop;

	// die Schlange und der Apfel
	private Snake snake;
	private Apple apple;
	
	//Highscore Tabelle anlegen
	private Highscore high;
	
	//der Player Name
	private String name;
	
	private boolean showHighscore = false;
	
	// damit das Bild nicht flackert braucht es ein Bild auf das zuerst gemalt werden kann
	private Image offscreenImage;
	// und das Grafik-Objekt dieses Bildes
	private Graphics offscreenGraphics;
	
	public GameWindow() 
	{
		// dies ist der FensterRahmen mit dem Titel
		f = new Frame ("Raupi McRippsen");	
		
		// die Leinwand soll 800 x 800 punkte gro� sein
		this.setSize(800, 800);
		
		// die Leinwand bekommt die beiden Schnittstellen dieser Klasse �bergeben
		this.addMouseListener((MouseListener)this);
		this.addKeyListener((KeyListener)this);
		
		try
		{
			// das Hintergrundbild laden
			hintergrund = ImageIO.read(new File("images/bg.jpg"));
		}
		catch(IOException e)
		{
			System.out.println("Das Hintergrundbild konnte nicht geladen werden.");
		}
		
		// und die Schlange und der Apfel hinzugef�gt werden
		snake = new Snake();
		apple = new Apple();
				
		// und das Fenster darf nicht mehr ver�ndert werden
		f.setResizable(false);
		// dem Fensterrahmen wird die Leinwand hinzugef�gt  
		f.add(this); 
		// jetzt kann die Leinwand auf den Rahmen gespannt werden (Titelleiste verdeckt nicht den Canvas)
		f.pack();
		// das Fenster in die Bilschirmmitte setzen
		f.setLocationRelativeTo(null);
		
		// das Fenster muss auf den Schliessen Knopf reagieren
		f.addWindowListener((WindowListener)this);
		
		// jetzt kann alles angezeigt werden
		f.setVisible(true);
		this.requestFocus();

		
		// der Thread wird gestartet
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	// damit das Bild nicht flackert muss update �berschrieben werden
	@Override
	public void update(Graphics g) 
	{
		paint(g);
	}
	 
	// die Leinwand hat eine Paint-Methode.. in dieser wird alles gemalt
	@Override
	public void paint(Graphics g) 
	{ 
		// ein neues bild erstellen 800 x 800
		offscreenImage = createImage(800, 800);
		// und das Grafik-Object holen
		offscreenGraphics = offscreenImage.getGraphics();
		
		//jetzt kann der Hintergrund gemalt werden
		offscreenGraphics.drawImage(hintergrund, 0, 0, 800, 800, null);
		
		if(!showHighscore)
		{
			// darauf die Schlange
			snake.drawSnake(offscreenGraphics);
		
			// und der Apfel
			apple.drawApple(offscreenGraphics);
		
		}
		else
		{
			high.drawHighscore(offscreenGraphics);
		}

		// jetzt alles auf dem Bildschirm ausgeben
		g.drawImage(offscreenImage, 0, 0, null);

	}
	
	
	@Override
	public void run() 
	{
		 name = JOptionPane.showInputDialog("Bitte einen Namen eingeben"); 
		
		// solange Niemand exit auf true setzt l�uft diese Schleife im Hintergrund
		while(!exit)
		{
			// jedesmal die Schlange aktualisieren
			snake.update();
			
			// l�uft der Kopf �ber den Apfel wird er gefressen und die Schlange bekommt ein neus Element
			if((apple.getX() == snake.getSnakeHead().getX()) && (snake.getSnakeHead().getY() == apple.getY()))
			{
				// die Schlange bekommt einen Punkt
				snake.setScore(snake.getScore() + 1);
				
				// und ein neues K�rperteil
				snake.addBodyElement();
				
				//und der Apfel eine neue Position
				apple.newPos();
			}
			
			if(snake.checkLost())
			{
				 System.out.println("Verloren!!");
				 
				 high = Highscore.loadHighscore();
				 
				 if(high == null)
				 {
					 high = new Highscore();
				 }
				 
				 high.setHighscoreEntry(name,snake.getScore());
				 
				 Highscore.saveHighscore(high);
				 
				 showHighscore = true;
			}
			
			// dem Fenstersystem sagen das es sich neu malen soll
			repaint();
			
			try 
			{
				int pause = 63;
				
				if(snake.getScore() < 10)
				{
					pause = 300;
				}
				
				if(snake.getScore() < 20)
				{
					pause = 200;
				}
				
				if(snake.getScore() < 30)
				{
					pause = 100;
				}
				
				Thread.sleep(pause);	
			}
			catch(InterruptedException e)
			{
				System.err.println("Thread Fehler: " + e.getMessage());
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		//System.out.println("keyCode: " + e.getKeyCode());
		
		if(e.getKeyCode() == 39) 
		{
			snake.moveRight();
		}
		
		if(e.getKeyCode() == 38) 
		{
			snake.moveUp();
		}
		
		if(e.getKeyCode() == 37) 
		{
			snake.moveLeft();
		}
		
		if(e.getKeyCode() == 40) 
		{
			snake.moveDown();
		}
	}
	
	public static void main(String[] args)
	{
		new GameWindow();
	}
	
	public void windowClosing(WindowEvent e)
	{
		exit = true;
		System.exit(0);
	}

	// alle Interface Methoden die nicht benutzt werden bleiben leer
	public void mousePressed(MouseEvent e) {}
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
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
	