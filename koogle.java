import java.util.*;

public class koogle
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		int rep = scan.nextInt();
		double log26 = Math.log(26)/Math.log(10);

		while(rep > 0)
		{
			int num = scan.nextInt();

			scan.nextLine();
			double max = 0;
			String str = "";
			
			for(int i=0;i<num;i++)
			{
				int A = 0;
				int B = 0;
				
				String papat = scan.nextLine();
				
				for(int j=0;j<papat.length();j++)	
				{
					if(papat.charAt(j) >= 'a')
						A++;
					else
						B++;
				}
				
				double current = log26*A + B;

				if(current > max)
				{
					max = current;
					str = papat;
				}
				else if(current == max)
				{
					if(papat.compareTo(str) < 0)
						str = papat;
				}
			}
			
			System.out.println(str);
			
			rep--;
		}
	}
}
