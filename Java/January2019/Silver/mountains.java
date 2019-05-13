/*
ID: zekidgu1
LANG: JAVA
TASK: mountains
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class mountains {

	public static void main(String[] args) throws Exception {
		new mountains().run();
	}

	static int n;
	
	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("mountains.out");
		
		n = file.nextInt();
		Mount[] m = new Mount[n];
		for (int i = 0; i < n; i++) {
			m[i] = new Mount(file.nextInt(), file.nextInt());
		}
		
		Arrays.sort(m);
		boolean[] invis = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			int ind = i - 1;
			while (ind >= 0 && m[i].contains(m[ind])) {
				invis[ind] = true;
				ind--;
			}
			ind = i + 1;
			while (ind < n && m[i].contains(m[ind])) {
				invis[ind] = true;
				ind++;
			}
		}
		int ans = 0;
		for (int i = 0; i < invis.length; i++) {
			if (invis[i]) continue;
			ans++;
		}
		
		System.out.println(ans);
		out.println(ans);
		out.flush();
		out.close();
	}

	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("mountains.in"));
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

	public static class Mount implements Comparable<Mount> {
		int x;
		int y;
		
		public Mount(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Mount o) {
			if (x < o.x) return -1;
			if (x > o.x) return 1;
			if (y > o.y) return -1;
			if (y < o.y) return 1;
			return 0;
		}
		
		public boolean contains(Mount o) {
			return -Math.abs(o.x - x) + y >= o.y;
		}
	}
}
