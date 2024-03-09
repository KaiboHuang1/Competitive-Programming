import java.util.*;
import java.io.*;

public class ConvexHull {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int k = readInt();
		int n = readInt();
		int m = readInt();

		ArrayList<graph>[] adj = new ArrayList[n + 1];
		int[][] dist = new int[n + 1][k];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
			for(int j = 0; j <k; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < m; i++) {
			int src = readInt();
			int dest = readInt();
			int w = readInt();
			int h = readInt();

			adj[src].add(new graph(dest, w, h));
			adj[dest].add(new graph(src, w, h));
		}

		int src = readInt();
		int dest = readInt();

		PriorityQueue<graph> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		pq.add(new graph(src, 0, 0));
		dist[src][0] = 0;

		while (!pq.isEmpty()) {
			graph e = pq.poll();
			
		
				for (graph next : adj[e.dest]) {
					if(next.hull + e.hull < k) {
						
						if(e.weight + next.weight < dist[next.dest][next.hull + e.hull]) {
							dist[next.dest][next.hull + e.hull] = e.weight + next.weight;
							pq.add(new graph(next.dest, dist[next.dest][next.hull + e.hull], next.hull + e.hull));
						}
					}
				}
			

			
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < k; i++) {
			ans = Math.min(ans, dist[dest][i]);
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? "-1": ans);

	}

	static class graph {
		int dest, weight, hull;

		graph(int d, int w, int h) {
			dest = d;
			weight = w;
			hull = h;
		}
	}

	final private static int BUFFER_SIZE = 1 << 20;
	private static DataInputStream din = new DataInputStream(System.in);
	private static byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static String readLine() throws IOException {
		byte[] buf = new byte[BUFFER_SIZE]; // line length
		int cnt = 0, c;
		while ((c = Read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}

	public static String read() throws IOException {
		byte[] ret = new byte[BUFFER_SIZE];
		int idx = 0;
		byte c = Read();
		while (c <= ' ') {
			c = Read();
		}
		do {
			ret[idx++] = c;
			c = Read();
		} while (c != -1 && c != ' ' && c != '\n' && c != '\r');
		return new String(ret, 0, idx);
	}

	public static int readInt() throws IOException {
		int ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (neg)
			return -ret;
		return ret;
	}

	public static long readLong() throws IOException {
		long ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public static double readDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = Read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}

		if (neg)
			return -ret;
		return ret;
	}

	private static void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if (bytesRead == -1)
			buffer[0] = -1;
	}

	private static byte Read() throws IOException {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}

	public void close() throws IOException {
		if (din == null)
			return;
		din.close();
	}

	static void print(Object o) {
		pr.print(o);
	}

	static void println(Object o) {
		pr.println(o);
	}

	static void flush() {
		pr.flush();
	}

	static void println() {
		pr.println();
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}
