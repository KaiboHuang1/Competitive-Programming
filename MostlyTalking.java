import java.util.*;
import java.io.*;

public class MostlyTalking {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = readInt();

		int[] dist1 = new int[n + 1];
		int[] dist2 = new int[n + 1];
		List<graph>[] adj1 = new ArrayList[n + 1];
		List<graph>[] adj2 = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			dist1[i] = Integer.MAX_VALUE;
			dist2[i] = Integer.MAX_VALUE;
			adj1[i] = new ArrayList<>();
			adj2[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int src = readInt();
			int dest = readInt();
			int w = readInt();

			adj1[src].add(new graph(dest, w));
			adj2[dest].add(new graph(src, w));
		}

		PriorityQueue<graph> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		pq.add(new graph(1, 0));
		dist1[1] = 0;

		while (!pq.isEmpty()) {
			int d = pq.poll().dest;

			for (graph g : adj1[d]) {
				if (dist1[d] + g.weight < dist1[g.dest]) {
					dist1[g.dest] = dist1[d] + g.weight;
					pq.add(new graph(g.dest, g.weight));
				}
			}
		}

		pq.add(new graph(n, 0));
		dist2[n] = 0;
		
		while (!pq.isEmpty()) {
			int d = pq.poll().dest;

			for (graph g : adj2[d]) {
				if (dist2[d] + g.weight < dist2[g.dest]) {
					dist2[g.dest] = dist2[d] + g.weight;
					pq.add(new graph(g.dest, g.weight));
				}
			}
		}
		
		int a = readInt();
		int ans =dist1[n];
		for(int i = 0; i < a; i++) {
			int src = readInt();
			int dest = readInt();
			int weight = readInt();
			
			if(dist1[src] != Integer.MAX_VALUE && dist2[dest] != Integer.MAX_VALUE) {
			ans = Math.min(ans, dist1[src] + weight + dist2[dest]);
			}
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
		System.out.println(ans);
		}
	}

	static class graph {
		int dest, weight;

		graph(int d, int w) {
			dest = d;
			weight = w;
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
