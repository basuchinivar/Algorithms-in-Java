package Travelling_sales_man;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class TravellingSalesManBruteForce {
	File file = new File("input1.txt");
	int N;
	Scanner sc;
	int x = 0;
	static int[][] cost = new int[100][100];
	HashSet<Integer> set = new HashSet<Integer>();
	int min_cost = 100000;
	int cheapest_path[] = new int[10000];
	int Num=1;

	public void Scan() {
		try
		{
			sc = new Scanner(file);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		N = sc.nextInt();
		ScanCost(N);
		bruteforce(N);
	}

	public void ScanCost(int N) {
		int w = 1;
		int N_Squared = N * N;

		while (sc.hasNext() && w <= N_Squared) 
		{
			int i = sc.nextInt();
			int j = sc.nextInt();
			cost[i][j] = sc.nextInt();
			w++;
		}
	}

	public void bruteforce(int n) {
		int i;
		int[] cities = new int[50000];
		int start=(int) System.currentTimeMillis();
		
		for (i = 0; i < N; i++)
			cities[i] = i;
		min_cost=1000000;
		permutation(cities, n);
		for (int r = 0; r <= N; r++)
			cheapest_path[i] = 0;
		int end=(int) System.currentTimeMillis();		
		System.out.print(Num + " " + N + " " + Collections.min(set) + " " + Collections.max(set) + " ");
		System.out.println(((end - start)/1000)+" ");
		System.out.println(0);
		for (i = 0; i < N-1; i++)
			System.out.println(cheapest_path[i]);
		set.clear();
		Num=Num+1;
		recheck();
	}

	public int recheck() 
	{
		if (sc.hasNext()) 
		{
			N = sc.nextInt();			
			ScanCost(N);
			bruteforce(N);	
		} else 
		{
			return 0;
		}
		return 0;
	}

	public void permutation(int cities[], int n) {
		if (n == 0) {

			int temp_cost = 0;
			cities[N] = cities[0];
			for (int i = 1; i <= N; i++) {
				temp_cost += cost[cities[i - 1]][cities[i]];
			}
			set.add(temp_cost);
			if (temp_cost < min_cost) {
				min_cost = temp_cost;
				for (int i = 0; i <= N; i++)
					cheapest_path[i] = cities[i];
			}

			return;
		} else {
			for (int i = 0; i < n; i++) {
				int temp = cities[i];
				cities[i] = cities[n - 1];
				cities[n - 1] = temp;
				permutation(cities, n - 1);
				temp = cities[i];
				cities[i] = cities[n - 1];
				cities[n - 1] = temp;
			}
		}
	}

	public static void main(String[] args) 
	{
		TravellingSalesManBruteForce t = new TravellingSalesManBruteForce();
		t.Scan();
	}

}
