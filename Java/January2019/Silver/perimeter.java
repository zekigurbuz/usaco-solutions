/*
ID: zekidgu1
LANG: JAVA
TASK: perimeter
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class perimeter {

	public static void main(String[] args) throws Exception {
		new perimeter().run();
	}

	static int n;
	static char[][] mat;
	static boolean[][] vis;
	static int a;
	static int p;
	static Queue<Blob> q;
	
	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("perimeter.out");
		n = Integer.parseInt(file.nextLine().trim());
		mat = new char[n][n];
		vis = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			mat[i] = file.nextLine().trim().toCharArray();
		}
		q = new PriorityQueue<Blob>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (vis[i][j] || mat[i][j] != '#') continue;
				a = 0;
				p = 0;
				recur(i, j);
				q.add(new Blob(a, p));
			}
		}
		Blob best = q.poll();
		String s = best.area + " " + best.perimeter;
		System.out.println(s);
		out.println(s);
		out.flush();
		out.close();
	}

	public static void recur(int x, int y) {
		if (!inBounds(x, y) || vis[x][y] || mat[x][y] != '#') return;
		vis[x][y] = true;
		a++;
		if (inBounds(x + 1, y) && mat[x+1][y] != '#') p++;
		else if (!inBounds(x + 1, y)) p++;
		if (inBounds(x - 1, y) && mat[x-1][y] != '#') p++;
		else if (!inBounds(x - 1, y)) p++;
		if (inBounds(x, y + 1) && mat[x][y+1] != '#') p++;
		else if (!inBounds(x, y + 1)) p++;
		if (inBounds(x, y - 1) && mat[x][y-1] != '#') p++;
		else if (!inBounds(x, y - 1)) p++;
		recur(x + 1, y);
		recur(x - 1, y);
		recur(x, y + 1);
		recur(x, y - 1);
	}
	
	public static class FastIO {

		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("perimeter.in"));
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

	public static class Blob implements Comparable<Blob> {
		int area;
		int perimeter;
		
		public Blob(int area, int perimeter) {
			this.area = area;
			this.perimeter = perimeter;
		}
		
		public int compareTo(Blob o) {
			if (area > o.area) return -1;
			if (area < o.area) return 1;
			if (perimeter < o.perimeter) return -1;
			if (perimeter > o.perimeter) return 1;
			return 0;
		}
	}
	
	public static boolean inBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
}

