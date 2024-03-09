import java.io.*;
import java.util.*;

public class PhonomenalReviews {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, m, s = (int) 1e5 + 5;
	static List<Integer>[] adj = new ArrayList[s];
	static boolean[] isRest = new boolean[s];
	static int max, u, total_node;
	static int[] dis = new int[s];
	static boolean[] vis = new boolean[s];
	
	static {
		for (int i = 0; i < s; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		
		n = readInt();
		m = readInt();
		for (int i = 0; i < m; i++) {
			u = readInt();
			isRest[u] = true;
		}
		for (int i = 0; i < n - 1; i++) {
			int a = readInt(), b = readInt();
			adj[a].add(b);
			adj[b].add(a);
		}

		total_node = n;
		dfs(u, -1);
		System.out.println((total_node - 1) * 2 - d(u));
	}

	static int d(int i) {
		dis = new int[s];
		vis = new boolean[s];
		max = 1;
		u = -1;
		BFS(i);
		dis = new int[s];
		vis = new boolean[s];
		max = 1;
		BFS(u);
		return max;
	}

	static void BFS(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		vis[i] = true;
		dis[i] = 0;
		u = i;
		max = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt : adj[cur]) {
				if (!vis[nxt] && isRest[nxt]) {
					queue.add(nxt);
					vis[nxt] = true;
					dis[nxt] = dis[cur] + 1;
					if (dis[nxt] > max) {
						u = nxt;
						max = dis[nxt];
					}
				}
			}
		}
	}

	static boolean dfs(int v, int pa) {
		for (int nxt : adj[v]) {
			if (nxt != pa) {
				isRest[v] |= dfs(nxt, v);
			}
		}
		if (!isRest[v])
			total_node--;
		return isRest[v];
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
