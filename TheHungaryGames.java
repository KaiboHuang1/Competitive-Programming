import java.io.*;
import java.util.*;

public class TheHungaryGames {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        ArrayList<Graph>[] adj = new ArrayList[n + 1];
        int[][] dist = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            dist[i][0] = Integer.MAX_VALUE;
            dist[i][1] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            int a = readInt();
            int b = readInt();
            int dis = readInt();
            adj[a].add(new Graph(b, dis));
        }

        dist[1][0] = 0;
        PriorityQueue<Graph> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Graph(1, 0));

        while (!pq.isEmpty()) {
            Graph gr = pq.poll();
            if (gr.weight > dist[gr.dest][1]) {// more than second best option
                continue;
            }
            for (Graph nxt : adj[gr.dest]) {

                if (dist[nxt.dest][0] > gr.weight + nxt.weight) {//  if  new path is better than the old second best path
                    int temp = dist[nxt.dest][0];
                    dist[nxt.dest][0] = gr.weight + nxt.weight;
                    dist[nxt.dest][1] = temp;
                    pq.add(new Graph(nxt.dest, dist[nxt.dest][0]));
                } else if (dist[nxt.dest][1] > gr.weight + nxt.weight && gr.weight + nxt.weight != dist[nxt.dest][0]) {
                    dist[nxt.dest][1] = gr.weight + nxt.weight;
                    pq.add(new Graph(nxt.dest, dist[nxt.dest][1]));
                }
            }
        }

        if (dist[n][1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist[n][1]);
        }
    }

    static class Graph {
        int dest, weight;

        Graph(int d, int w) {
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
