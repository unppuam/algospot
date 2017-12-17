import java.util.*;

public class fortress
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		int rep = scan.nextInt();

		while(rep > 0)
		{
			int num = scan.nextInt();
			scan.nextLine();
			
			Circle[] arr = new Circle[num];
			
			for(int i=0;i<num;i++)
			{
				String[] input = scan.nextLine().split(" ");
				
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				int radius = Integer.parseInt(input[2]);
				
				arr[i] = new Circle(x,y,radius);
			}

			Arrays.sort(arr, Collections.reverseOrder());
			
			for(int i=1;i<arr.length;i++)
			{
				position(arr[i],arr[0]);
			}
			
			getDistance(arr[0]);
			
			/*
			for(int i=0;i<arr.length;i++)
			{
				System.out.println(arr[i]);
			}*/
			
			Circle cursor = arr[0];
			int max = cursor.distance;

			while(!cursor.isChildrenEmpty())
			{
				int temp = getNextDistanceGivenNextCircle(cursor);
				if(max < temp)
					max = temp;
				
				cursor = next(cursor);
			}

			System.out.println(max);

			rep--;
		}
	}
	public static void position(Circle circle, Circle upper)
	{
		if(upper.isInclude(circle) == false)
			return;
		if(upper.getUpper(circle) == null)
			upper.add(circle);
		else
			position(circle,upper.getUpper(circle));
	}
	public static int getDistance(Circle circle)
	{
		if(circle.isChildrenEmpty())
		{
			circle.distance = 0;
			return circle.distance;
		}

		int max = 0; 
		for(int i=0;i<circle.children.size();i++)
		{
			int temp = getDistance(circle.children.elementAt(i));
			if(max < temp)
				max = temp;
		}

		circle.distance = max+1;

		return max + 1;
	}
	public static Circle next(Circle circle)
	{
		if(circle.isChildrenEmpty())
			return null;

		int max = 0;
		int count = 0;
		for(int i=0;i<circle.children.size();i++)
		{
			int temp = circle.children.elementAt(i).distance;
			if(temp > max)
				count = i;
		}

		return circle.children.elementAt(count);
	}
	public static int getNextDistanceGivenNextCircle(Circle circle)
	{
		int[] arr = new int[circle.children.size()];
		
		for(int i=0;i<circle.children.size();i++)
		{
			arr[i] = circle.children.elementAt(i).distance+1;
		}

		Arrays.sort(arr);
		
		int sum;

		if(arr.length == 1)
			sum = -1;
		else
			sum = arr[arr.length-1]+arr[arr.length-2];

		return sum;
	}
}
class Circle implements Comparable<Circle>
{
	int x;
	int y;
	int radius;
	int distance;
	Vector<Circle> children;

	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius; 
		children = new Vector<Circle>();
	}
	public boolean isChildrenEmpty()
	{
		if(children.size() == 0)
			return true;
		else
			return false;
	}
	public Circle getUpper(Circle circle)
	{
		for(int i=0;i<children.size();i++)
		{
			if(children.get(i).isInclude(circle) == true)
				return children.get(i);
		}
		return null;
	}
	public boolean isInclude(Circle other)
	{
		int pd = (int)(Math.pow((other.x-x),2)+Math.pow((other.y-y),2));
		int pr = (int)Math.pow(radius,2);

		//System.out.println("pd : ("+pd+") pr : ("+pr+")");
		
		if(pr < pd)
			return false;
		else
			return true;
	}
	public void add(Circle child)
	{
		children.addElement(child);
	}
	public int compareTo(Circle other)
	{
		if(radius == other.radius)
			return 0;
		else if(radius > other.radius)
			return 1;
		else
			return -1;
	}
	public String toString()
	{
		String values = "("+x+", "+y+") :  "+radius+" [distance :: "+distance+" //  children : [";
		for(int i=0;i<children.size();i++)
		{
			values += children.get(i).radius+",";
		}
		values += "]";

		return values;
	}
}
