import java.util.*;

public class ordering
{
    public static void main(String[] args)
    {   
        Scanner scan = new Scanner(System.in);

        int rep = scan.nextInt();

        scan.nextLine();

        while(rep > 0)
        {       
            String[] input = scan.nextLine().split(" ");
            int vertex = Integer.parseInt(input[0]);
            int arrow = Integer.parseInt(input[1]);
                       
			LinkedList<Node> nodeList = new LinkedList<Node>();
			
			for(int i=0;i<vertex;i++)
				nodeList.add(new Node(i));
            
			for(int i=0;i<arrow;i++)
            {           
                String relation = scan.nextLine();
                int prev = relation.charAt(0)-'A';
                int next = relation.charAt(1)-'A';
				
				nodeList.get(next).addPrev(prev);
				nodeList.get(prev).addNext(next);
			}
			
			LinkedList<Integer> result = new LinkedList<Integer>();
			LinkedList<Integer> excluded = new LinkedList<Integer>();
			
			for(int i=0;i<nodeList.size();i++)
			{
				if(nodeList.get(i).indegree == 0 && nodeList.get(i).outdegree == 0)
				{
					excluded.add(nodeList.get(i).id);
					nodeList.remove(i);
					i--;
				}
			}

			int i=0;
			while(!nodeList.isEmpty())
			{
				if(nodeList.get(i).indegree == 0)
				{
					result.add(nodeList.get(i).id);
					down(nodeList.get(i),nodeList);
					nodeList.remove(i);
					i=-1;
				}
				i++;
			}

			printResult(result,excluded);
			
			rep--;
        }               
    }
	public static void printResult(LinkedList<Integer> result, LinkedList<Integer> excluded)
	{
		String merged = "";
		while(!result.isEmpty() && !excluded.isEmpty())
		{
			if(excluded.peek() < result.peek())
				merged += (char)(excluded.poll() + 'A');
			else
				merged += (char)(result.poll() + 'A');
		}
		while(!result.isEmpty())
			merged += (char)(result.poll() + 'A');
		while(!excluded.isEmpty())
			merged += (char)(excluded.poll() + 'A');

		System.out.println(merged);
	}
	public static void down(Node node,LinkedList<Node> nodeList)
	{
		for(int i=0;i<node.nextList.size();i++)
		{
			int id = node.nextList.get(i);
			search(id,nodeList).down();
		}
	}
	public static Node search(int id,LinkedList<Node> nodeList)
	{
		for(int i=0;i<nodeList.size();i++)
		{
			if(nodeList.get(i).id == id)
				return nodeList.get(i);
		}
		return null;
	}
}    

class Node implements Comparable<Node>
{
	int id;
	int indegree = 0;
	int outdegree = 0;
	LinkedList<Integer> prevList = new LinkedList<Integer>();	
	LinkedList<Integer> nextList = new LinkedList<Integer>();	

	public Node(int id){
		this.id = id;
	}

	public void addPrev(int prev)
	{
		prevList.add(prev);
		indegree++;
	}

	public void addNext(int next)
	{
		nextList.add(next);
		outdegree++;
	}

	public void down()
	{
		indegree--;
	}

	public int compareTo(Node compareNode)
	{
		if(this.indegree < compareNode.indegree)
			return -1;
		else if(this.indegree == compareNode.indegree)
			return 0;
		else
			return 1;
	}
}
