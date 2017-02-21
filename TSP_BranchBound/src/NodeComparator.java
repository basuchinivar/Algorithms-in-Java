

import java.util.Comparator;


public class NodeComparator implements Comparator<node> {
	
	public int compare(node n1,node n2)
	{
		return n1.bound-n2.bound;
	}

}
