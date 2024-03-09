package lvl3HW8Winter;

import java.util.*;
import java.io.*;

public class PaintingRoads {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PrintWriter out = new PrintWriter(System.out);

	static List<Integer>[] adj;
	static boolean[] vis;
	static HashMap<Integer, Pair> order = new HashMap<>();
	static HashMap<Pair, Boolean> color = new HashMap<>();

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = readInt();

		adj = new ArrayList[n + 1];
		vis = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			int src = readInt();
			int dest = readInt();

			adj[src].add(dest);
			adj[dest].add(src);

			Pair p = new Pair(Math.min(src, dest), Math.max(src, dest));
			order.put(i, p);
		}

		for (int i = 1; i <= n; i++) {
			if (!vis[i]) {
				dfs(i, false);
			}
		}
		StringBuilder str = new StringBuilder("");

		for (int i = 1; i <= m; i++) {
			Pair p = order.get(i);

			if (color.containsKey(p)) {

				if (color.get(p) == false) {
					str.append('R');
				} else {
					str.append('B');
				}
			} else {

				str.append('G');

			}
		}

		out.println(str.toString());
		out.close();

	}

	// false red, true blue
	static void dfs(int cur, boolean C) {
		vis[cur] = true;

		for (int nxt : adj[cur]) {
			if (!vis[nxt]) {

				Pair p = new Pair(Math.min(cur, nxt), Math.max(cur, nxt));

				color.put(p, C);
				dfs(nxt, !C);
			}
		}

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

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

}
