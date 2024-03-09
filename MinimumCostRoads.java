import java.util.*;
import java.io.*;

public class MinimumCostRoads {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static List<int[]> adj[];
	static boolean rem[];

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();

		long cost = 0;
		rem = new boolean[M + 1];
		List<int[]> edge = new ArrayList<>();
		adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int u = readInt();
			int v = readInt();
			int w = readInt();
			int c = readInt();
			
			edge.add(new int[] { u, v, w, c, i });
			cost += c;
			
			adj[u].add(new int[] { v, w, i });
			adj[v].add(new int[] { u, w, i });
		}

		Collections.sort(edge, (x, y) -> -Integer.compare(x[3], y[3]));
		
		for (int[] x : edge) {
			int u = x[0], v = x[1], w = x[2], c = x[3], i = x[4];
			rem[i] = true;
			if (spfa(u, v) > w)
				rem[i] = false;
			else
				cost -= c;
		}
		
		System.out.println(cost);
	}

	static long spfa(int st, int ed) {
		
		long dis[] = new long[N + 1];
		boolean inq[] = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		Arrays.fill(dis, (long) 1e15);
		
		q.add(st);
		dis[st] = 0;
		inq[st] = true;
		
		while (!q.isEmpty()) {
			int u = q.poll();
			inq[u] = false;
			
			for (int[] x : adj[u]) {
				int v = x[0], w = x[1], id = x[2];
				if (rem[id]) {
					continue;
				}
				
				if (dis[v] > dis[u] + w) {
					dis[v] = dis[u] + w;
					if (!inq[v]) {
						inq[v] = true;
						q.add(v);
					}
				}
			}
		}
		
		return dis[ed];
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
