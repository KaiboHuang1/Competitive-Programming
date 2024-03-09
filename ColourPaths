import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Main {
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        private byte read() {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        private void fillBuffer() {
            try {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bytesRead == -1) buffer[0] = -1;
        }
    }

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static final int MM = 2 * 100000 + 2;
    static int N, M, A, B, C, D, minPathAB = 0x3f3f3f3f;
    static Pair[] edges = new Pair[MM];
    static boolean[] vis = new boolean[MM];
    static int minDR = 0x3f3f3f3f;
    static StringBuilder output = new StringBuilder();

    static void dfs(int u, int dest, int avoid, int dep) {
        if (dep >= minDR) return;
        vis[u] = true;
        for (int v : adj[u]) {
            if (v == dest) {
                minDR = Math.min(minDR, dep + 1);
            } else if (v != avoid && !vis[v]) {
                dfs(v, dest, avoid, dep + 1);
            }
        }
    }

    static java.util.ArrayList<Integer>[] adj = new java.util.ArrayList[MM];

    public static void main(String[] args) {
        FastReader reader = new FastReader();

        N = reader.nextInt();
        M = reader.nextInt();

        for (int i = 0; i < MM; i++) {
            adj[i] = new java.util.ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            adj[b].add(a);
            adj[a].add(b);
            edges[i] = new Pair(a, b);
        }

        A = reader.nextInt();
        B = reader.nextInt();
        C = reader.nextInt();
        D = reader.nextInt();

        for (int i = 0; i < M; ++i) {
            if ((edges[i].first == C && edges[i].second == D) || (edges[i].first == D && edges[i].second == C)) {
                output.append("-1\n");
                System.out.print(output);
                return;
            }
        }

        dfs(A, B, C, 0);
        minPathAB = minDR;

        java.util.Arrays.fill(vis, false);
        minDR = 0x3f3f3f3f;
        dfs(A, B, D, 0);
        if (minDR < minPathAB) {
            output.append("2\n");
            for (int i = 0; i < M; ++i) {
                if (edges[i].first == D || edges[i].second == D) {
                    output.append("2\n");
                } else {
                    output.append("1\n");
                }
            }
        } else {
            if (minPathAB >= 0x3f3f3f3f) {
                output.append("-1\n");
                System.out.print(output);
                return;
            }
            output.append("2\n");
            for (int i = 0; i < M; ++i) {
                if (edges[i].first == C || edges[i].second == C) {
                    output.append("2\n");
                } else {
                    output.append("1\n");
                }
            }
        }
        System.out.print(output);
    }
}
