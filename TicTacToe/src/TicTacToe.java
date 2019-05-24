// damit wir die namen der spieler eingeben können
import java.util.Arrays;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TicTacToe
{
	Image circle;
	Image cross;
	Image tile;
	
	// erster und zweiter spieler 
	Player p1; 
	Player p2;
	
	Player cp;
	Player pp;
	
	
	// win-Situationen als 3 x 3 Muster gespeichert.
	int [][][] patterns = {
		{
		 {1,1,1},
		 {0,0,0},
		 {0,0,0}
		},		{
		 {0,0,0},
		 {1,1,1},
		 {0,0,0}
		},		{
		 {0,0,0},
		 {0,0,0},
		 {1,1,1}
		},		{
		 {1,0,0},
		 {1,0,0},
		 {1,0,0}
		},		{
		 {0,1,0},
		 {0,1,0},
		 {0,1,0}
		},		{
		 {0,0,1},
		 {0,0,1},
		 {0,0,1}
		},		{
		 {1,0,0},
		 {0,1,0},
		 {0,0,1}
		},		{
		 {0,0,1},
		 {0,1,0},
		 {1,0,0}
		}
	};
	
	
	
	int [][][] kiPatterns = {
			{
			 {1,2,1},
			 {0,0,0},
			 {0,0,0}
			},		{
			 {0,0,1},
			 {0,0,2},
			 {0,0,1}
			},		{
			 {0,0,0},
			 {0,0,0},
			 {1,2,1}
			},		{
			 {1,0,0},
			 {2,0,0},
			 {1,0,0}
			},		{
			 {1,1,2},
			 {0,0,0},
			 {0,0,0}
			},		{
			 {0,0,0},
			 {1,1,2},
			 {0,0,0}
			},		{
			 {0,0,0},
			 {0,0,0},
			 {1,1,2}
			},		{
			 {0,0,0},
			 {2,1,1},
			 {0,0,0}
			 },		{
			 {1,0,0},
			 {1,0,0},
			 {2,0,0}
			},		{
			 {2,0,0},
			 {1,0,0},
			 {1,0,0}
			},		{
			 {0,2,0},
			 {0,1,0},
			 {0,1,0}
			},		{
			 {0,0,2},
			 {0,0,1},
			 {0,0,1}
			 },    {
			 {1,0,0},
			 {0,1,0},
			 {0,0,2}
			},		{
			 {2,0,0},
			 {0,1,0},
			 {0,0,1}
			},		
			{
				 {0,0,2},
				 {0,1,0},
				 {1,0,0}
			},		
			{
				 {0,0,1},
				 {0,1,0},
				 {2,0,0}
			}
		};
	
	
	// die x und y Koordinaten des Spielfeldes (oben links)
	int x;
	int y;
	
	// die Feldgröße
	int f = 200;

	// das Spielfeld (3 x 3)
	int[][] tic = new int[3][3];
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////
//Bilder Laden Falls vorhanden//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	public TicTacToe()
	{
		try
		{
			circle = ImageIO.read(new File("images/circle.png"));
			cross= ImageIO.read(new File("images/cross.png"));
			tile= ImageIO.read(new File("images/tile.png"));
		}
		catch(IOException e)
		{
			
		}
//////////////////////////////////////////////////////////////////////////////////////////////
//Player instanzieren und zuweisen////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
		this.p1 = new Player(1);
		this.p2 = new Player(2);
		
		this.p1.setActive(true);
		this.p2.setActive(false);
				
		cp = this.p1;
		pp = this.p2;

	}
	
	public void kiMakeMove()
	{
		boolean set = false;
		
		if(tic[1][1] == 0) {
			tic[1][1] = 2;
			set = true;
 		}
		
		if(!set)
		{
			for(int i = 0; i < kiPatterns.length;i ++) {
				Point p = PatternChecker.checkKiPattern(kiPatterns[i], tic);
				if(p != null)
				{
					
					tic[p.x][p.y] = 2;
					set = true;
					break; //for
				}
			}
		}
		
	
		while(!set) {
			int x = (int) Math.round((Math.random()*2));
			int y = (int) Math.round((Math.random()*2));
			//System.out.println("ahhh ich bin noch in der Schleife!!!");
			if (tic[x][y] ==0)
			{
				this.tic[x][y] = 2;
				set = true;
			}
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////	
////// diese Methode malt das Spielfeld in die Konsole////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

	public void drawField(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++) 
			{
				g2d.drawImage(tile, 100+i*f,100+j*f, 200, 200, null);
			}
		}

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
//////////////////////////////////////////////////////////////////////////////////////////////
/////////// die Spielsteine in das Feld zeichnen//////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i< this.tic.length;i++)
		{
			for(int j = 0; j< this.tic[i].length;j++)
			{
				if(this.tic[i][j] == 1)
				{
					//int x= i*this.f+(this.f/2);
					//int y= j*this.f+(this.f/2);
					int x= 100+i * this.f;
					int y= 100+j * this.f;
					
					g2d.drawImage(cross, x, y,200,200, null);
				}
				
				if(this.tic[i][j] == 2)
				{
					int x= 100+i * this.f;
					int y= 100+j * this.f;
					g2d.drawImage(circle, x, y,200,200, null);			
				}
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////
////////// je nachdem welcher Spieler dran ist werden nun die cp und pp gesetzt//////////////
/////////////////////////////////////////////////////////////////////////////////////////////
	public void switchPlayer()
	{
		cp.setActive(false);
		pp.setActive(true);
		

		if(this.p1.isActive())
		{
			this.cp = this.p1;
			this.pp = this.p2;
		}

		if(this.p2.isActive())
		{
			this.cp = this.p2;
			this.pp = this.p1;
		}
	}
	
	public int getActivePlayer()
	{
		return cp.getPn();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
///////Playernummer wird im Array gesetzt/////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

	public void setField(int i, int j, int player)
	{
		this.tic[i][j] = player;
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////zum auslesen der Playernummer/////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public int getField(int i, int j)
	{
		return this.tic[i][j];
	}

//////////////////////////////////////////////////////////////////////////////////////////////
///// prüft nach den angegebenen winPattern ob ein Spieler gewonnen hat///////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public int checkWin()
	{
		for(int i = 0; i < 8; i++)
		{
			if(PatternChecker.checkPattern(this.patterns[i], this.tic))
			{
				return cp.getPn();
			}			
		}
		
		
		// sind alle Felder belegt aber niemand hat gewonnen...
		int c = 0;
		for(int i = 0; i < 3; i++) 
		{	
			for(int j = 0; j < 3; j++)
			{
				if(this.tic[i][j] == 0)
				{
					c++;
				}
			}	
		
		}
		
		// ... dann ist unentschieden
		if(c ==0)
		{
			return 4; //unentschieden
		}
		return 0;
	}
/*
	public static void main(String[] args) 
	{
		if(System.console() == null) 
		{
			System.err.println("Fehler, keine brauchbare Konsole");
			System.exit(0);
		}
		
		// das game erstellen:
		TicTacToe game = new TicTacToe();
		
		// und unsere Konsole anlegen
		//game.graphic = new ConsoleGraphics();

		// spieler 1 namen eingeben
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 1, bitte Namen eingeben!");
		String name1 =scan.nextLine();
		
		// spieler 2 namen eingeben
		System.out.println("Player 2, bitte Namen eingeben!");
		String name2 =scan.nextLine();
		
/*
		// die 2 spieler erstellen
		
		// und den ersten spieler aktiv setzen
		
		// da die spieler andauernd wechseln, setzen wir sie einfach als "CurrentPlayer - cp" und als "PausedPlayer - pp"
		
		// der Tastatur-Code den wir abfangen:
		int keyCode = 0;
		
		// solange niemand ESC drückt:
		while(keyCode != 27)
		{
			//Bildschirm löschen
			//game.graphic.cls();
			
			//Spielfeld malen
			//game.drawField(game.graphic);
			
			//prüfen ob ein Spieler gewonnen hat
			for(int i = 0; i < 8; i++)
			{
				//game.checkWin(game.tic, game.patterns[i], thiscp, pp);
				
			}
			
			
			// ausgeben welcher Spieler grad dran ist
//			game.graphic.drawText(cp.getName() + " du bist dran!", 2, 2, cp.getColor());
			
			// und ein Rechteck in seiner Farbe an die aktuelle Spielerpostion setzen
//			game.graphic.drawRect(45+cp.getX()*14, 3+cp.getY()*7, 14,7, cp.getColor());
			
			// auf Eingabe warten :)
//			keyCode = game.graphic.getch();
			
			// wurden die Pfeiltasten gedrückt?
			//if(keyCode == 224)
			//{
				// das nächste Zeichen sagt uns welche Pfeiltaste es war:
//				int key = game.graphic.getch();
				/*char keyC = (char)key;
				
				//UP
				if(keyC == 'H') 
				{
					cp.moveUp();
				}
				
				// LEFT
				if(keyC == 'K') 
				{
					cp.moveLeft();
				}
				
				//DOWN
				if(keyC == 'P') 
				{
					cp.moveDown();
				}
				
				//RIGHT
				if(keyC == 'M') 
				{
					cp.moveRight();
				}
			}
			
			// wurde ENTER gedrückt?
			if(keyCode == 13)
			{	
				// wenn das Feld noch frei ist ...
				if(game.tic[cp.getX()][cp.getY()] == 0)
				{
					// ...  setze einen Spielstein an diese Position
					game.tic[cp.getX()][cp.getY()] = cp.getPn();
					
					//und der nächste Spieler ist dran
					cp.setActive(false);
					pp.setActive(true);
				}
//			}
			
		}
	}*/

	public void reset() {
		this.p1.setActive(true);
		this.p2.setActive(false);
				
		cp = this.p1;
		pp = this.p2;
		
		Arrays.fill(tic[0], 0);
		Arrays.fill(tic[1], 0);
		Arrays.fill(tic[2], 0);		
	}

}