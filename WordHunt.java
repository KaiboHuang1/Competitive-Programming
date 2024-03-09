import java.util.*;
import java.io.*;

public class WordHunt {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static String word;
	static int R, C;
	static char g[][];

	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		word = readLine();
		R = readInt();
		C = readInt();

		g = new char[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				g[i][j] = readChar();
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (g[i][j] == word.charAt(0)) {
					for (int k = 0; k < 8; k++) {
						int ni = i + dir[k][0];
						int nj = j + dir[k][1];

						if (good(ni, nj) && g[ni][nj] == word.charAt(1)) {
							dfs(ni, nj, 2, k, false);
						}
					}
				}
			}
		}

		System.out.println(cnt);
	}

	static void dfs(int r, int c, int indx, int k, boolean turned) {
		if (indx == word.length()) {
			cnt++;
			return;
		}

		int nr = r + dir[k][0];
		int nc = c + dir[k][1];

		if (good(nr, nc) && g[nr][nc] == word.charAt(indx)) {
			dfs(nr, nc, indx + 1, k, turned);
		}

		if (!turned) {
			int nk = (k + 2) % 8;
			nr = r + dir[nk][0];
			nc = c + dir[nk][1];

			if (good(nr, nc) && g[nr][nc] == word.charAt(indx)) {
				dfs(nr, nc, indx + 1, nk, true);
			}

			int newk = (k - 2 + 8) % 8;

			nr = r + dir[newk][0];
			nc = c + dir[newk][1];

			if (good(nr, nc) && g[nr][nc] == word.charAt(indx)) {
				dfs(nr, nc, indx + 1, newk, true);
			}
		}
	}

	static boolean good(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
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

	static char readChar() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}
