/*
ID: zekidgu1
LANG: JAVA
TASK: backforth
*/
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.lang.Math.*;

public class backforth {

	public static void main(String[] args) throws Exception {
		new backforth().run();
	}

	public void run() throws Exception {
		FastIO file = new FastIO();
		PrintWriter out = new PrintWriter("backforth.out");
		int[] a = new int[10];
		int[] b = new int[10];
		for (int i = 0; i < 10; i++) a[i] = file.nextInt();
		for (int i = 0; i < 10; i++) b[i] = file.nextInt();
		TreeSet<Integer> nums = new TreeSet<>();
		for (int i : a) nums.add(i);
		for (int i : b) nums.add(i);
		ArrayList<int[]> combs = new ArrayList<>();
		for (int i : nums) {
			for (int j : nums) {
				for (int k : nums) {
					for (int l : nums) {
						combs.add(new int[] {i,j,k,l});
					}
				}
			}
		}
		ArrayList<Integer> aa = new ArrayList<>();
		ArrayList<Integer> bb = new ArrayList<>();
		for (int i : a) aa.add(i);
		for (int i : b) bb.add(i);
		TreeSet<Integer> ans = new TreeSet<Integer>();
		for (int i = 0; i < combs.size(); i++) {
			if (isValid(combs.get(i), (ArrayList<Integer>)aa.clone(), (ArrayList<Integer>)bb.clone())) {
				//System.out.println(Arrays.toString(combs.get(i)));
				ans.add(1000 - combs.get(i)[0] + combs.get(i)[1] - combs.get(i)[2] + combs.get(i)[3]);
			}
		}
		//System.out.println(ans);
		System.out.println(ans.size());
		out.println(ans.size());
		out.flush();
		out.close();
	}
	public boolean isValid(int[] arr, ArrayList<Integer> a, ArrayList<Integer> b) {
		if (!a.contains(arr[0])) return false;
		b.add(arr[0]);
		a.remove(new Integer(arr[0]));
		if (!b.contains(arr[1])) return false;
		a.add(arr[1]);
		b.remove(new Integer(arr[1]));
		if (!a.contains(arr[2])) return false;
		b.add(arr[2]);
		a.remove(new Integer(arr[2]));
		if (!b.contains(arr[3])) return false;
		a.add(arr[3]);
		b.remove(new Integer(arr[3]));
		return true;
	}
	public static class FastIO {
		BufferedReader br;
		StringTokenizer st;

		public FastIO() throws Exception {
			br = new BufferedReader(new FileReader("backforth.in"));
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
