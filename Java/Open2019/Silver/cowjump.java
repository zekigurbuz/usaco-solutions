import java.util.*;


import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class cowjump {

	public static void main(String[] args) throws Exception {
		new cowjump().run();
	}

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("cowjump.out");
		int n = file.nextInt();
		Point[] p = new Point[n*2];
		Point[][] a = new Point[n][2];
		for (int i = 0; i < n * 2; i++) {
			p[i] = new Point(file.nextLong(), file.nextLong(), i/2, i%2);
		}
		for (int i = 0; i < n * 2; i++) {
			a[i/2][i%2] = p[i];
		}
		Arrays.sort(p);
		for (int i = 0; i < n; i += 2) {
			Point aa = a[i][0];
			Point bb = a[i][1];
			if (aa.compareTo(bb) > 0) {
				Point temp = aa;
				a[i/2][0] = a[i/2][1];
				a[i][1] = temp;
				a[i][0].ab = 0;
				a[i][1].ab = 1;
			}
		}
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; i++) hm.put(i, 0);
		for (int i = 0; i < n * 2; i++) {
			Point aa = p[i];
			for (int j = i + 1; j < n * 2; j++) {
				Point bb = p[j];
				if (aa.id == bb.id) continue;
				if (intersects(aa, a[aa.id][aa.ab==1?0:1], bb, a[bb.id][bb.ab==1?0:1])) {
					hm.put(aa.id, hm.get(aa.id) + 1);
					hm.put(bb.id, hm.get(bb.id) + 1);
				}
				if (bb.y != aa.y) {
					for (int x = j + 1; x < j + 10 && x < n * 2; x++) {
						bb = p[x];
						if (aa.id == bb.id) continue;
						if (intersects(aa, a[aa.id][aa.ab==1?0:1], bb, a[bb.id][bb.ab==1?0:1])) {
							hm.put(aa.id, hm.get(aa.id) + 1);
							hm.put(bb.id, hm.get(bb.id) + 1);
						}
					}
					break;
				}
			}
		}	
		int ret = 0;
		int ind = 0;
		for (int i = 0; i < n; i++) {
			if (hm.get(i) > ret) {
				ret = hm.get(i);
				ind = i;
			}
		}
		ind++;
		System.out.println(ind);
		out.println(ind);
		out.flush();
		out.close();
	}

	public class Point implements Comparable<Point> {
		long x;
		long y;
		int id;
		int ab;
		public Point(long x, long y, int id, int ab) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.ab = ab;
		}
		public int compareTo(Point o) {
			return x < o.x ? -1 : x > o.x ? 1 : y < o.y ? -1 : y > o.y ? 1 : 0;
		}
	}
	public boolean on(Point a, Point b, Point c) {
		return b.x <= Math.max(a.x, c.x) && b.x >= Math.min(a.x, c.x) && b.y <= Math.max(a.y, c.y) && b.y >= Math.min(a.y, c.y);
	}
	public int state(Point a, Point b, Point c) {
		long state = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
		if (state == 0) return 0;
		if (state > 0) return 1;
		return 2;
	}
	public boolean intersects(Point a, Point b, Point c, Point d) {
		int _a = state(a,b,c);
		int _b = state(a,b,d);
		int _c = state(c,d,a);
		int _d = state(c,d,b);
		if (_a != _b && _c != _d) return true;
		if (_a == 0 && on(a,c,b)) return true;
		if (_b == 0 && on(a,d,b)) return true;
		if (_c == 0 && on(c,a,d)) return true;
		if (_d == 0 && on(c,b,d)) return true;
		return false;
	}
	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("cowjump.in"));
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
