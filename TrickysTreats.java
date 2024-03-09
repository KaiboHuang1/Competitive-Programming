import java.util.*;
import java.io.*;

public class TrickysTreats {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int houses = readInt();
		int time = readInt();
		int stop = readInt();

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<pair> l = new ArrayList<>();

		for (int i = 0; i < houses; i++) {
			l.add(new pair(readInt(), readInt()));
		}

		Collections.sort(l, (x, y) -> Integer.compare(x.p, y.p));

		int ans = 0, sum = 0;

		for (pair e : l) {
			if (e.p * 2 + stop > time) {
				break;
			}

			sum += e.val;
			pq.add(e.val);
			int k = (time - 2 * e.p) / stop;

			while (pq.size() > k) {
				sum -= pq.poll();
			}

			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}

	static class pair {
		int p, val;

		pair(int p0, int val0) {
			p = p0;
			val = val0;
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
