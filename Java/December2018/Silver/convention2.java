
/*
ID: zekidgu1
LANG: JAVA
TASK: convention2
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class convention2 {

	public static void main(String[] args) throws Exception {
		new convention2().run();
	}

	long time;

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("convention2.out");
		int n = file.nextInt();
		Cow[] c = new Cow[n];
		for (int i = 0; i < n; i++) {
			long a = file.nextLong();
			long b = file.nextLong();
			c[i] = new Cow(i, a, b);
		}
		Arrays.sort(c);
		for (int i = 0; i < n; i++)
			c[i].ind = i;
		long max = 0l;
		PriorityQueue<Cow> q = new PriorityQueue<Cow>();
		q.add(c[0]);
		time = c[0].arr;
		int ind = 1;
		while (ind < n || !q.isEmpty()) {
			if (q.isEmpty()) {
				long t = c[ind].arr;
				while (ind < n && c[ind].arr == t) {
					q.add(c[ind]);
					time = c[ind].arr;
					ind++;
				}
			}
			Cow cur = q.poll();
			if (time >= cur.arr) {
				max = Math.max(max, time - cur.arr);
				//System.out.println(time + " " +  cur.arr);
				time += cur.time2;
			} else {
				time = cur.arr + cur.time2;
			}
			ind = Math.max(ind, cur.ind + 1);
			while (ind < n && c[ind].arr <= time) {
				q.add(c[ind++]);
				//System.out.println(q);
			}
		}
		//System.out.println(time);
		System.out.println(max);
		out.println(max);
		out.flush();
		out.close();
	}

	public class Cow implements Comparable<Cow> {
		long rank;
		long arr;
		long time2;
		int ind;
		public String toString() {
			return rank + " " + arr + " " + time2 + " " + ind;
		}
		public Cow(long rank, long arr, long time2) {
			this.rank = rank;
			this.arr = arr;
			this.time2 = time2;
		}

		public int compareTo(Cow o) {
			if (time >= arr && time >= o.arr) {
				if (rank < o.rank) return -1;
				if (rank > o.rank) return 1;
				if (arr < o.arr) return -1;
				if (arr > o.arr) return 1;
				return 0;
			}
			else if (arr < o.arr) return -1;
			else if (arr > o.arr) return 1;
			else if (rank < o.rank) return -1;
			else if (rank > o.rank) return 1;
			return 0;
		}
	}

	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("convention2.in"));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
