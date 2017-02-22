package kanpsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack 
{
	static int n;
    int inc;

	static int[] profit=new int[100];
	 static int[] weight=new int[100];
	static File file = new File("input.txt");
	static Scanner sc;
	int W;
	public void intialize_scanner() throws FileNotFoundException
	{
		sc = new Scanner(file);
	}

    public void calculate(int profit[], int weight[], int total_weight) throws FileNotFoundException
    {
		int start=(int)System.currentTimeMillis();

    	ArrayList include = new ArrayList();
    	
        int dynamicKnapsack[][] = new int[profit.length+1][total_weight+1];
        for(int i=0; i <= profit.length; i++){
            for(int j=0; j <= total_weight; j++){
                if(i == 0 || j == 0){
                    dynamicKnapsack[i][j] = 0;
                    continue;
                }
                if(j - weight[i-1] >= 0){
                    dynamicKnapsack[i][j] = Math.max(dynamicKnapsack[i-1][j], dynamicKnapsack[i-1][j-weight[i-1]] + profit[i-1]);
                    include.add(0);
                    
                }
                else
                {
                	dynamicKnapsack[i][j] = dynamicKnapsack[i-1][j];
                	include.add(1);
                }
            }
        }
		int end=(int)System.currentTimeMillis();
		int total=(end-start)/1000;
		
        System.out.println(n + "   " + dynamicKnapsack[profit.length][total_weight] + "  " + total +"  seconds");
        
        W=total_weight;
        for(int i=0;i<W;i++)
        {
        	if(include.get(i).equals(1))
        	{
        		System.out.println( weight[i] +"   "+ profit[i]);
        		W=W-weight[i];
        	}else
        	{
        		continue;
        	}
        }
        
        
        
        recheck();

    }


    public static int scan() throws FileNotFoundException
    {
        n=sc.nextInt();
        int total_weight=sc.nextInt();        
		return total_weight;
        
    }
    public static void scancost()
    {
    	int w=1;
    	int i=0;
    	
    	
    	while(sc.hasNext() && w<=n)
    	{
    		weight[i]=sc.nextInt();
    		profit[i]=sc.nextInt();
    		i++;w++;
    	}
    	
    }
    public void recheck() throws FileNotFoundException
    {
    	if(sc.hasNext())
    	{
    	int total_weight=scan();
    	scancost();
    	calculate(profit, weight, total_weight);
    	}

    }

    	public static void main(String[] args) throws FileNotFoundException
    	{
            Knapsack k = new Knapsack();
            k.intialize_scanner();
            int total_weight=scan();
            scancost();
            k.calculate(profit, weight, total_weight);
            
    	}
}
