import java.util.*;

public class josephus
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int rep = scan.nextInt();
		scan.nextLine();
		
		while(rep > 0)
		{
			Queue<Integer> queue = new LinkedList<Integer>();
			
			String input = scan.nextLine();
			String[] parts = input.split(" ");
			int people = Integer.parseInt(parts[0]);
			int jump = Integer.parseInt(parts[1]);
			
			for(int i=0;i<people;i++)
			{
				queue.add(i+1);
			}
			queue.poll();
			while(queue.size() > 2)
			{
				for(int i=0;i<(jump-1);i++)
					queue.add(queue.poll());
				queue.poll();
			}

			int one = queue.poll();
			int two = queue.poll();
			
			if(one < two)
				System.out.println(one+" "+two);
			else
				System.out.println(two+" "+one);
			
			rep--;
		}		
	}
}
