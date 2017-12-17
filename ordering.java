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
                       
			Node[] node = new Node[vertex];
			
			for(int i=0;i<node.length;i++)
				node[i] = new Node(i);

            for(int i=0;i<arrow;i++)
            {           
                String[] relation = scan.nextLine().split(" ");
                int prev = relation[0].charAt(0) - 65;
                int next = relation[1].charAt(0) - 65;
				
				node[next].addPrev(prev);
				node[prev].addNext(next);
			}

			// Arrays.sort(node);
			
			/*
			for(int i=0;i<node.length;i++)
			{
				System.out.println(node[i].id+" ,"+node[i].indegree);
			}
			*/

			//PriorityQueue<Node> searchQ = new PriorityQueue<Node>();
			Queue<Node> resultQ = new LinkedList<Node>();

			int count = 0;
			while(node[count].indegree == 0)
			{	
				searchQ.offer(node[i]);
				count++;
			}

			while(!searchQ.isEmpty())
			{

			}
			rep--;
        }               
    }
}    

class Node implements Comparable<Node>
{
	int id;
	int indegree = 0;
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
