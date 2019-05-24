import java.awt.Point;
import java.util.LinkedList;

public class PatternChecker
{
	public static Point checkKiPattern(int maske[][], int spiel[][])
	{
		LinkedList<Integer> list = new LinkedList<Integer>();

		for(int i = 0;i < maske.length; i++)
		{
			for(int j = 0; j<maske[i].length;j++)
			{
				if(maske[j][i] == 1)
				{
					list.add(spiel[i][j]);
				}
			}
		}

		if(list.size() > 0)
		{
			int c = list.get(0);
			
			if(c != 0)
			{
				for(int i= 0;i< list.size();i++)
				{
					//System.out.print(list.get(i) + " ");

					if(c != list.get(i))
					{
						return null;
					}
				}
				
				for(int k = 0; k < maske.length; k++)
				{
					for(int m = 0; m < maske[k].length; m++)
					{
						if(maske[k][m] == 2) 
						{
							Point position = new Point();
							position.setLocation(m, k);
							if(spiel[m][k] == 0)
							{
								return position;
							}
							else
							{
								return null;
							}
						}
					}
				}
				
			}
		}
		return null;
	}

	public static boolean checkPattern(int maske[][], int spiel[][])
	{
		LinkedList<Integer> list = new LinkedList<Integer>();

		for(int i = 0;i < maske.length; i++)
		{
			for(int j = 0; j<maske[i].length;j++)
			{
				if(maske[j][i] == 1)
				{
					list.add(spiel[i][j]);
				}
			}
		}

		if(list.size() > 0)
		{
			int c = list.get(0);
			
			if(c != 0)
			{
				for(int i= 0;i< list.size();i++)
				{
					//System.out.print(list.get(i) + " ");

					if(c != list.get(i))
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}