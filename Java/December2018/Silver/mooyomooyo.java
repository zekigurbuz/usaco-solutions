/*
ID: zekidgu1
LANG: JAVA
TASK: mooyomooyo
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class mooyomooyo {

	public static void main(String[] args) throws Exception {
		new mooyomooyo().run();
	}
	int n;
	int k;
	char[][] mat;
	boolean[][] vis;
	ArrayList<int[]> bales;
	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("mooyomooyo.out");
		n = file.nextInt();
		k = file.nextInt();
		mat = new char[n][10];
		vis = new boolean[n][10];
		boolean deleted = false;
		for (int i = 0; i < n; i++) {
			mat[i] = file.nextLine().trim().toCharArray();
		}
		while (true) {
			deleted = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 10; j++) {
					if (!vis[i][j] && mat[i][j] != '0') {
						bales = new ArrayList<int[]>();
						recur(i, j, mat[i][j]);
						if (bales.size() >= k) {
							System.out.println(bales.size() + " " + mat[i][j]);
							for (int[] d : bales) {
								mat[d[0]][d[1]] = '0';
							}
							deleted = true;
						}
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if(mat[j][i] != '0') {
						int x = j + 1;
						while (x < n && mat[x][i] == '0') x++;
						if (x-1 == j) continue;
						x--;
						mat[x][i] = mat[j][i];
						mat[j][i] = '0';
					}
				}
			}
			vis = new boolean[n][10];
			if (!deleted) break;
		}
		for (char[] c : mat) {
			System.out.println(c);
			out.println(c);
		}
		out.flush();
		out.close();
	}
	public void recur(int x, int y, char c) {
		if (x < 0 || y < 0 || x >= n || y >= 10 || mat[x][y] != c || vis[x][y]) return;
		bales.add(new int[] {x,y});
		vis[x][y] = true;
		recur(x + 1, y, c);
		recur(x - 1, y, c);
		recur(x, y + 1, c);
		recur(x, y - 1, c);
		//vis[x][y] = false;
	}
	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("mooyomooyo.in"));
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
