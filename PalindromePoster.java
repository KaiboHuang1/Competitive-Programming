import java.io.*;
import java.util.*;

public class PalindromePoster {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int N = readInt(), M = readInt(), R = readInt(), C = readInt();
		char g[][] = new char[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(g[i], 'a');
		for (int i = C; i < M; i++)
			g[N - 1][i] = (char) (g[0][i] + 1);
		for (int i = R; i < N; i++)
			g[i][M - 1] = (char) (g[i][0] + 1);
		if (R == 0 && C != M)
			g[N - 1][M - 1] = 'c';
		if (R == N && C != 0) {
			if (M % 2 == 0 && C % 2 != 0) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			for (int i = 0; i < M; i++) {
				if (i < C / 2 || i >= M - C / 2)
					g[N - 1][i] = g[0][i];
				else
					g[N - 1][i] = (char) (g[0][i] + 1);
			}
			if (C % 2 != 0)
				g[N - 1][M / 2] = g[0][M / 2];
		}
		if (R != 0 && C == M) {
			if (N % 2 == 0 && R % 2 != 0) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			for (int i = 0; i < N; i++) {
				if (i < R / 2 || i >= N - R / 2)
					g[i][M - 1] = g[i][0];
				else
					g[i][M - 1] = (char) (g[i][0] + 1);
			}
			if (R % 2 != 0)
				g[N / 2][M - 1] = g[N / 2][0];
		}
		int cntR = 0, cntC = 0;
		for (int i = 0; i < N; i++)
			cntR += checkR(g, i);
		for (int i = 0; i < M; i++)
			cntC += checkC(g, i);
		if (cntR != R || cntC != C) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		for (int i = 0; i < N; i++)
			System.out.println(g[i]);
	}

	static int checkR(char g[][], int i) {
		for (int l = 0, r = g[i].length - 1; l <= r; l++, r--)
			if (g[i][l] != g[i][r])
				return 0;
		return 1;
	}

	static int checkC(char g[][], int i) {
		for (int l = 0, r = g.length - 1; l <= r; l++, r--)
			if (g[l][i] != g[r][i])
				return 0;
		return 1;
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
