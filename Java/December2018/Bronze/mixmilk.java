/*
ID: zekidgu1
LANG: JAVA
TASK: mixmilk
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class mixmilk {

	public static void main(String[] args) throws Exception {
		new mixmilk().run();
	}

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("mixmilk.out");
		long c1 = file.nextInt();
		long m1 = file.nextInt();
		long c2 = file.nextInt();
		long m2 = file.nextInt();
		long c3 = file.nextInt();
		long m3 = file.nextInt();
		for (int i = 0; i < 100; i++) {
			long avai;
			switch (i%3) {
			case 0:
				avai = c2 - m2;
				if (avai >=m1) {
					m2 += m1;
					m1 = 0;
				}
				else {
					m1 -= avai;
					m2 = c2;
				}
				break;
			case 1:
				avai = c3 - m3;
				if (avai >=m2) {
					m3 += m2;
					m2 = 0;
				}
				else {
					m2 -= avai;
					m3 = c3;
				}
				break;
			case 2:
				avai = c1 - m1;
				if (avai >=m3) {
					m1 += m3;
					m3 = 0;
				}
				else {
					m3 -= avai;
					m1 = c1;
				}
				break;
			}
			//System.out.println(m1 + " " + m2 + " " + m3);
		}
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		out.println(m1);
		out.println(m2);
		out.println(m3);
		out.flush();
		out.close();
	}

	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("mixmilk.in"));
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
