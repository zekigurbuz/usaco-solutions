import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class fenceplan {

	public static void main(String[] args) throws Exception {
		new fenceplan().run();
	}

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("fenceplan.out");
		int n = file.nextInt();
		int m = file.nextInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			long a = file.nextLong();
			long b = file.nextLong();
			p[i] = new Point(i, a, b);
		}
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
		for (int i = 0; i < n; i++) hm.put(i, new HashSet<Integer>());
		for (int i = 0; i < m; i++) {
			int a = file.nextInt() - 1;
			int b = file.nextInt() - 1;
			hm.get(a).add(b);
			hm.get(b).add(a);
		}
		boolean[] v = new boolean[n];
		long ret = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (v[i]) continue;
			long xmax = Long.MIN_VALUE;
			long xmin = Long.MAX_VALUE;
			long ymax = Long.MIN_VALUE;
			long ymin = Long.MAX_VALUE;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(i);
			while (!q.isEmpty()) {
				int c = q.poll();
				v[c] = true;
				xmax = Math.max(xmax, p[c].x);
				xmin = Math.min(xmin, p[c].x);
				ymax = Math.max(ymax, p[c].y);
				ymin = Math.min(ymin, p[c].y);
				for (int next : hm.get(c)) {
					if (!v[next]) q.add(next);
				}
			}
			if (xmax != Long.MIN_VALUE && xmin != Long.MAX_VALUE && ymax != Long.MIN_VALUE && ymin != Long.MAX_VALUE)
			ret = Math.min(ret, (xmax - xmin) * 2 + (ymax - ymin) * 2);
		}
		System.out.println(ret);
		out.println(ret);
		out.flush();
		out.close();
	}
	public class Point {
		int id;
		long x;
		long y;
		public Point(int id, long x, long y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("fenceplan.in"));
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

	public static long pow(long n, long p, long mod) {
		if (p == 0)
			return 1;
		if (p == 1)
			return n % mod;
		if (p % 2 == 0) {
			long temp = pow(n, p / 2, mod);
			return (temp * temp) % mod;
		} else {
			long temp = pow(n, p / 2, mod);
			temp = (temp * temp) % mod;
			return (temp * n) % mod;

		}
	}

	public static long pow(long n, long p) {
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		if (p % 2 == 0) {
			long temp = pow(n, p / 2);
			return (temp * temp);
		} else {
			long temp = pow(n, p / 2);
			temp = (temp * temp);
			return (temp * n);

		}
	}

	public static long gcd(long x, long y) {
		if (x == 0)
			return y;
		else
			return gcd(y % x, x);
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
}
