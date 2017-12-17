import java.util.*;

public class trianglepath
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
				String input = scan.nextLine();
				String[] arr1 = input.split(" ");
				for(int j=0;j<=i;j++)
				{
					arr[i][j] = Integer.parseInt(arr1[j]);
				}
			}

			int[][] cache = new int[size][size];
			
			cache[0][0] = arr[0][0];

			for(int i=1;i<size;i++)
			{
				for(int j=0;j<=i;j++)
				{
					if(j == 0)
						cache[i][j] = cache[i-1][j] + arr[i][j];
					else if(j == i)
						cache[i][j] = cache[i-1][j-1] + arr[i][j];
					else if(cache[i-1][j] > cache[i-1][j-1])
						cache[i][j] = cache[i-1][j] + arr[i][j];
					else
						cache[i][j] = cache[i-1][j-1] + arr[i][j];
				}
			}

			int max = cache[size-1][0];

			for(int j=1;j<size;j++)
			{
				if(max < cache[size-1][j])
					max = cache[size-1][j];
			}

			System.out.println(max);

			rep--;
		}
	}
}
