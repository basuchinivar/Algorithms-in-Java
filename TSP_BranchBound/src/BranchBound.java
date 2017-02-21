

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BranchBound {

	static int cost[][] = new int[100][100];
	static int count = 0;
	int n_number=0;

	Comparator<node> nodecomparator = new NodeComparator();

	PriorityQueue<node> pq = new PriorityQueue<node>(nodecomparator);

	ArrayList<Integer> minlen = new ArrayList<>();

	static List<Integer> opttour = new ArrayList<>();

	static Scanner sc;

	static File file = new File("input1.txt");

	static int N;

	public static void Scan() throws FileNotFoundException {
		sc = new Scanner(file);
		N = sc.nextInt();
	}

	public static void ScanCost(int N) {
		int w = 1;
		int N_Squared = N * N;

		while (sc.hasNext() && w <= N_Squared) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			cost[i][j] = sc.nextInt();
			if (cost[i][j] == 0) {
				cost[i][j] = 100000;
			}
			w++;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scan();
		ScanCost(N);
		BranchBound ap = new BranchBound();
		ap.travel2(N, cost);
	}

	public int recheck() {
		if (sc.hasNext()) {
			N = sc.nextInt();
			ScanCost(N);
			travel2(N, cost);
		} else {
			return 0;
		}
		return 0;
	}

	public void travel2(int N, int[][] cost) {
		int start=(int)System.currentTimeMillis();
		node v = new node();
		v.level = 0;
		v.path.add(0);
		v.bound = Bound(v);

		int minlength = 100000;
		pq.add(v);
		while (!(pq.size() == 0)) {

			v = new node();

			v = pq.remove();
			if (v.bound < minlength) {
				node u = new node();
				u.level = v.level + 1;

				if (u.level == N - 1) {
					u.path.addAll(v.path);
					ArrayList<Integer> cities = new ArrayList<>();
					for (int i = 0; i < N; i++) {
						cities.add(i);
					}
					cities.removeAll(u.path);
					u.path.addAll(cities);
					u.path.add(0);

					int yu = length(u);
					if (yu < minlength) {

						minlength = yu;
						minlen.add(minlength);
						opttour.clear();
						opttour.addAll(u.path);

						/*for (int a1 = 0; a1 < opttour.size(); a1++)
							System.out.println("Optimal Path: " + opttour.get(a1) + "->");*/

					}

				} else {
					for (int i = 1; i < N; i++) {
						if (!(v.path.contains(i))) {
							node m = new node();
							m.level = v.level + 1;
							m.path.addAll(v.path);

							m.path.add(i);

							m.bound = Bound(m);
							if (m.bound < minlength) {
								pq.add(m);
								count++;
							}
						}
					}
				}

			}
		}
		int minimum = Collections.min(minlen);
		minlen.clear();
		int end = (int)System.currentTimeMillis();
		
		int total=(end-start)/1000;
		
		if(N<15)
		{
		System.out.println(n_number++ + " " + N + " " + " " + " " + minimum + " " + " " + count + " " + " " + total + " seconds");
		for (int a1 = 0; a1 < opttour.size(); a1++)
			System.out.println(opttour.get(a1));
		}
		
		else
		{
			System.out.println(n_number++ + " " + " " + N + " " + " " + " " + minimum + " " + " " + count + " " + " " + total + " seconds");
		}
		
		
		

		recheck();

	}

	private int length(node u) {
		int temp_cost = 0;

		for (int i = 0; i < u.path.size() - 1; i++) {

			temp_cost += cost[u.path.get(i)][u.path.get(i + 1)];

		}
		return temp_cost;
	}

	public static int Bound(node x) {

		int temp_bound = 0;
		int min;

		Integer[] ignore_row = new Integer[100];
		Integer[] ignore_column = new Integer[100];
		if (x.path.size() > 1) {
			for (int i = 0; i < x.path.size() - 1; i++) {
				temp_bound += cost[x.path.get(i)][x.path.get(i + 1)];
			}

			for (int i = 0; i < x.path.size() - 1; i++) {
				ignore_row[i] = x.path.get(i);
			}

			for (int i = 1; i < x.path.size(); i++) {
				ignore_column[i] = x.path.get(i);
			}
		}

		for (int x1 = 0; x1 < N; x1++) {
			if (!(Arrays.asList(ignore_row).contains(x1))) {
				min = 100000;
				for (int j = 0; j < N; j++) {
					if (!Arrays.asList(ignore_column).contains(j) && (x1 != j)) {

						if (cost[x1][j] < min) {
							min = cost[x1][j];
						}
					}
				}
				temp_bound = temp_bound + min;

			}

		}

		return temp_bound;

	}

}
