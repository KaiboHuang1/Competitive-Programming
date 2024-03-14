import java.util.*;
import java.io.*;

public class Template {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException {
		
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

	static int mod = (int) 1e9 + 7;

	static String readLine() throws IOException {
		return br.readLine().trim();
	}

	static long pow(long x, long exp) {
		if (exp == 0)
			return 1;
		long t = pow(x, exp / 2);
		t = t * t % mod;
		if (exp % 2 == 0)
			return t;
		return t * x % mod;
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
}
