

import java.util.ArrayList;

public class node implements Comparable<node>

{
              int level;
              int bound;
              ArrayList<Integer> path= new ArrayList<>();
              
			@Override
			public int compareTo(node nodeIn) {
				if(this.bound < nodeIn.bound) return -1;
				else if(this.bound > nodeIn.bound) return 1;
				else return 0;				
			}
			
              
}
 
