import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Template {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException {

		int T = readInt();

		while (T-- > 0) {
			
		}

		out.close();
	}

	static class Pair {
		int a, b;

		Pair(int a1, int b1) {
			a = a1;
			b = b1;
		}

		@Override
		public int hashCode() {
			return Objects.hash(a, b);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			return Objects.equals(a, other.a) && Objects.equals(b, other.b);
		}
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}

	public static long power(long x, long y, long p) {
		// 0^0 = 1
		long res = 1L;
		x = x % p;
		while (y > 0) {
			if ((y & 1) == 1)
				res = (res * x) % p;
			y >>= 1;
			x = (x * x) % p;
		}
		return res;
	}

	public static long power(long x, long y) {
		// 0^0 = 1
		long res = 1L;

		while (y > 0) {
			if ((y & 1) == 1)
				res = (res * x);
			y >>= 1;
			x = (x * x);
		}
		return res;
	}

	static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}

	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static int lower_bound(ArrayList<Integer> a, int x) { // find the 1st element >= x
		int lo = 0, hi = a.size() - 1, ans = a.size();
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a.get(mid) >= x) {
				ans = mid;
				hi = mid - 1;
			} else
				lo = mid + 1;
		}
		return ans;
	}

	static int upper_bound(ArrayList<Integer> a, int x) { // find the 1st element > x
		int lo = 0, hi = a.size() - 1, ans = a.size();
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a.get(mid) > x) {
				ans = mid;
				hi = mid - 1;
			} else
				lo = mid + 1;
		}
		return ans;
	}

	public static void sort(int[] arr) {

		ArrayList<Integer> ls = new ArrayList<Integer>();
		for (int x : arr)
			ls.add(x);
		Collections.sort(ls);
		for (int i = 0; i < arr.length; i++)
			arr[i] = ls.get(i);
	}
}

