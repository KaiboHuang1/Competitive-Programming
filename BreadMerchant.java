import java.util.*;
import java.io.*;

public class BreadMerchant {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[] P, outdeg, ans;
	static List<Integer>[] g, rg;
	static boolean[] done;
	
	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();

		N = sc.readInt();
		M = sc.readInt();

		P = new int[N];
		outdeg = new int[N];
		ans = new int[N];
		g = new List[N];
		rg = new List[N];
		done = new boolean[N];

		for (int i = 0; i < N; i++) {
			g[i] = new ArrayList<>();
			rg[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++)
			P[i] = sc.readInt();

		for (int i = 0; i < M; i++) {
			int a = sc.readInt() - 1;
			int b = sc.readInt() - 1;
			g[a].add(b);
			rg[b].add(a);
			outdeg[a]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (outdeg[i] == 0) {
				done[i] = true;
				ans[i] = 1;
				tryPush(i, q);
			}
		}

		while (!q.isEmpty()) {
			int c = q.poll();
			if (done[c])
				continue;
			done[c] = true;

			if (P[c] != 0) {
				for (int to : g[c]) {
					ans[c] |= ans[to];
				}
			} else {
				ans[c] = 1;
				for (int to : g[c]) {
					ans[c] &= ans[to];
				}
			}

			tryPush(c, q);
		}

		for (int i = 0; i < N; i++) {
			System.out.print(ans[i] + (i == N - 1 ? "\n" : " "));
		}
	}
	 static void tryPush(int c, Queue<Integer> q) {
	        if (ans[c] != 0) {
	            for (int rto : rg[c]) {
	                if ((--outdeg[rto] == 0) || (P[rto] != 0)) {
	                    q.add(rto);
	                }
	            }
	        } else {
	            for (int rto : rg[c]) {
	                if (--outdeg[rto] == 0) {
	                    q.add(rto);
	                }
	            }
	        }
	    }
	 static class Reader {
			final private int BUFFER_SIZE = 1 << 16;
			private DataInputStream din;
			private byte[] buffer;
			private int bufferPointer, bytesRead;

			public Reader() {
				din = new DataInputStream(System.in);
				buffer = new byte[BUFFER_SIZE];
				bufferPointer = bytesRead = 0;
			}

			public Reader(String file_name) throws IOException {
				din = new DataInputStream(new FileInputStream(file_name));
				buffer = new byte[BUFFER_SIZE];
				bufferPointer = bytesRead = 0;
			}

			public String readLine() throws IOException {
				byte[] buf = new byte[64];
				int cnt = 0, c;
				while ((c = read()) != -1) {
					if (c == '\n') {
						if (cnt != 0) {
							break;
						} else {
							continue;
						}
					}
					buf[cnt++] = (byte) c;
				}
				return new String(buf, 0, cnt);
			}

			public int readInt() throws IOException {
				int ret = 0;
				byte c = read();
				while (c <= ' ') {
					c = read();
				}
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (neg)
					return -ret;
				return ret;
			}

			public long readLong() throws IOException {
				long ret = 0;
				byte c = read();
				while (c <= ' ')
					c = read();
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (neg)
					return -ret;
				return ret;
			}

			public double readDouble() throws IOException {
				double ret = 0, div = 1;
				byte c = read();
				while (c <= ' ')
					c = read();
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (c == '.') {
					while ((c = read()) >= '0' && c <= '9') {
						ret += (c - '0') / (div *= 10);
					}
				}
				if (neg)
					return -ret;
				return ret;
			}

			private void fillBuffer() throws IOException {
				bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
				if (bytesRead == -1)
					buffer[0] = -1;
			}

			private byte read() throws IOException {
				if (bufferPointer == bytesRead)
					fillBuffer();
				return buffer[bufferPointer++];
			}

			public void close() throws IOException {
				if (din == null)
					return;
				din.close();
			}
		}

		static String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine().trim());
			return st.nextToken();
		}

		static char readCharacter() throws IOException {
			return next().charAt(0);
		}
}
