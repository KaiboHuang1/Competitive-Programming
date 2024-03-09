import java.util.*;
import java.io.*;

public class YuniformityChallenge {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int n = readInt();
		int q = readInt();

		int[] a = new int[n + 1];
		int[] psa = new int[n + 1];
		int[] last = new int[n + 1];
		HashMap<Integer, Integer> d = new HashMap<>();

		for (int i = 1; i <= n; i++) {
			a[i] = readInt();
		}

		for (int i = 1; i <= n; i++) {
			psa[i] = psa[i - 1] + (a[i] == a[i - 1] ? 1 : 0);
			d.put(a[i], i);
			
			last[i] = last[i - 1];
			
			if (d.containsKey(-a[i])) {
				last[i] = Math.max(last[i], d.get(-a[i]));
			}
		}

		for (int j = 0; j < q; j++) {

			int l = readInt();
			int r = readInt();

			System.out.println((psa[r] - psa[l] == r - l || last[r] >= l) ? "YES" : "NO");
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
}
