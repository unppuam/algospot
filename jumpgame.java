import java.util.*;

public class jumpgame
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		int rep = scan.nextInt();

		while(rep > 0)
		{
			int size = scan.nextInt();
			scan.nextLine();
			int[][] arr = new int[size][size];
			
			for(int i=0;i<size;i++)
			{
				String[] input = scan.nextLine().split(" ");	
				for(int j=0;j<size;j++)
				{
					arr[i][j] = Integer.parseInt(input[j]);
				}
			}

			boolean[][] cache = new boolean[size][size];
			
			cache[0][0] = true;

			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					int step = arr[i][j];
					if(cache[i][j] == true)
					{
						if(i+step < size)
							cache[i+step][j] = true;
						if(j+step < size)
							cache[i][j+step] = true;
					}
				}
			}

			if(cache[size-1][size-1] == true)
				System.out.println("YES");
			else
				System.out.println("NO");
			rep--;
		}
	}
}
