import java.util.*;
import java.io.*;

public class OlympiadsHomework {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final long MOD = 10000000013L;

	static long pow(long base, long exp, long mod) {
		long ret = 1;
		for (; exp > 0; exp >>= 1, base = (base * base) % mod)
			if ((exp & 1) == 1)
				ret = (ret * base) % mod;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		long n = readLong();
		
		if (n == 1) {
			System.out.println("1");
			return;
		}
		
		long a = pow(2, n - 2, 1000000013L);
		long b = pow(2, n / 2 - 1, 1000000013L);
		if (n % 4 == 2) {
			b = 0;
		}
		else if (n % 8 == 3 || n % 8 == 4 || n % 8 == 5) {
			b = -b;
		}
		long result = (1000000013L + a + b) % 1000000013L;
		
		System.out.println(result);
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
}
