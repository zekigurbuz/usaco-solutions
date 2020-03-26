/*
ID:	zekidgu1
LANG: JAVA
TASK: timeline
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class timeline {

	public static void main(String[] args) throws Exception {
		new timeline().run();
	}
	
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	static int n, m, c, s[], r[][], topo[];

	public void run() throws Exception {
		FastIO file = new FastIO();
		n = file.nextInt();
		m = file.nextInt();
		c = file.nextInt();
		
		s = new int[n];
		for (int i = 0; i < n; i++) 
			s[i] = file.nextInt();
		
		r = new int[c][3];
		for (int i = 0; i < c; i++)
			for (int j = 0; j < 3; j++)
				r[i][j] = j < 2 ? file.nextInt() - 1 : file.nextInt();
		
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());
		for (int i = 0; i < c; i++)
			adj.get(r[i][0]).add(r[i][1]);
		
		topo = new int[n];
		topoSort();
//		System.out.println(Arrays.toString(topo));
		
		ArrayList<ArrayList<Pair<Integer, Integer>>> upds = new ArrayList<>();
		for (int i = 0; i < n; i++)
			upds.add(new ArrayList<>());
		
		for (int i = 0; i < c; i++)
			upds.get(r[i][1]).add(new Pair<Integer, Integer>(r[i][0], r[i][2]));
		
		for (int i = 0; i < n; i++)
			for (Pair<Integer, Integer> q : upds.get(topo[i]))
				s[topo[i]] = Math.max(s[topo[i]], s[q.fi] + q.se);
		
//		for (int i = 0; i < c; i++)
//			s[r[i][1] - 1] = Math.max(s[r[i][1] - 1], s[r[i][0] - 1] + r[i][2]);
		
		for (int i = 0; i < n; i++) {
//			System.out.println(s[i]);
			file.out.println(s[i]);
		}
		file.out.flush();
		file.out.close();
	}
	
	public static void topoSort() {
		Stack<Integer> st = new Stack<>();
		boolean[] vis = new boolean[n];
		for (int i = 0; i < n; i++)
			if (!vis[i])
				helper(i, vis, st);
		
		int ind = 0;
		while (!st.isEmpty())
			topo[ind++] = st.pop();
	}
	
	public static void helper(int v, boolean[] vis, Stack<Integer> st) {
		vis[v] = true;
		for (int i : adj.get(v))
			if (!vis[i])
				helper(i, vis, st);
		
		st.push(v);
	}

	public static long mod(long n, long mod) {
		return (n % mod + mod) % mod;
	}

	public static long pow(long n, long p, long mod) {
		if (p == 0L)
			return mod(1L, mod);
		if (p == 1L)
			return mod(n, mod);
		long t = mod(pow(n, p >> 1, mod), mod);
		if (p % 2L == 0L) {
			return mod(t * t, mod);
		}
		t = mod(t * t, mod);
		t = mod(t * n, mod);
		return mod(t, mod);
	}

	public static long pow(long n, long p) {
		return pow(n, p, Long.MAX_VALUE);
	}

	public static long gcd(long x, long y) {
		if (x == 0)
			return y;
		return gcd(y % x, x);
	}

	public static long lcm(long x, long y) {
		return x / gcd(x, y) * y;
	}

	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}

	public static class Pair<A, B> implements Comparable {
		public A fi;
		public B se;

		public Pair(A fi, B se) {
			this.fi = fi;
			this.se = se;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Pair<?, ?> p = (Pair<?, ?>) o;
			if (!fi.equals(p.fi))
				return false;
			return se.equals(p.se);
		}

		@Override
		public int hashCode() {
			return 31 * fi.hashCode() + se.hashCode();
		}

		@Override
		public String toString() {
			return fi.toString() + " " + se.toString();
		}

		public static <A, B> Pair<A, B> of(A a, B b) {
			return new Pair<A, B>(a, b);
		}

		public int compareTo(Object o) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			if (fi.equals(p.fi))
				return ((Comparable) se).compareTo(p.se);
			return ((Comparable) fi).compareTo(p.fi);
		}
	}

	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;
		PrintWriter out;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader(new File("timeline.in")));
			out = new PrintWriter(new File("timeline.out"));
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

		void print(Object o) {
			out.print(o);
		}

		void println(Object o) {
			out.println(o);
		}

		void printf(String s, Object... o) {
			out.printf(s, o);
		}
	}
}
