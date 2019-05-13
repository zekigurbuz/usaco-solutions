/*
ID: zekidgu1
LANG: JAVA
TASK: blist
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class blist {

	public static void main(String[] args) throws Exception {
		new blist().run();
	}

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("blist.out");
		int n = file.nextInt();
		int[][] cows = new int[n][3];
		int[] buckets = new int[1001];
		int max = 0;
		for (int i = 0; i < n; i++) {
			cows[i][0] = file.nextInt();
			cows[i][1] = file.nextInt();
			cows[i][2] = file.nextInt();
			for (int j = cows[i][0]; j <= cows[i][1]; j++) {
				buckets[j] += cows[i][2];
				max = Math.max(max, buckets[j]);
			}
		}
		//System.out.println(Arrays.toString(buckets));
		System.out.println(max);
		out.println(max);
		out.flush();
		out.close();
	}

	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("blist.in"));
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
