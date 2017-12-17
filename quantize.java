import java.util.*;

public class quantize
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		int rep = scan.nextInt();
		scan.nextLine();
		
		while(rep > 0)
		{
			String[] input1 = scan.nextLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			int s = Integer.parseInt(input1[1]);

			String[] input2 = scan.nextLine().split(" ");
			int[] number = new int[n];
			
			for(int i=0;i<n;i++)
			{
				number[i] = Integer.parseInt(input2[i]);	
			}
			
			for(int i=0;i<number.length;i++)
			{
				System.out.print("("+number[i]+")");
			}

			System.out.println();

			Arrays.sort(number);

			for(int i=0;i<number.length;i++)
			{
				System.out.print("("+number[i]+")");
			}
			System.out.println();

			Distance[] arr = new Distance[n-1];
			
			for(int i=0;i<arr.length;i++)
			{
				arr[i] = new Distance(i, number[i+1] - number[i]);
			}

			Arrays.sort(arr);
	
			for(int i=0;i<arr.length;i++)
			{
				System.out.print("["+arr[i].index+":"+arr[i].distance+"]__");
			}
			rep--;
		}
	}
}
class Distance implements Comparable<Distance>
{
	int index;
	int distance;

	public Distance(int index, int distance)
	{
		this.index = index;
		this.distance = distance;
	}

	public int compareTo(Distance other)
	{
		if(distance == other.distance)
			return 0;
		else if(distance > other.distance)
			return 1;
		else
			return -1;
	}
}
