import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Abeceda {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Pair<U, V> {
        private final U first;
        private final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }

    public static void main(String[] args) throws IOException {
        int MAXN = 100;
        int MAXLEN = 10;

        int N = readInt();
        String[] word = new String[MAXN];

        for (int i = 0; i < N; ++i)
            word[i] = next();

        int M = 0;
        boolean[] seen = new boolean[26];
        boolean[][] greater = new boolean[26][26];
        List<Pair<Integer, Character>> ret = new ArrayList<>();

        for (int i = 0; i < N; ++i)
            for (char c : word[i].toCharArray())
                if (!seen[c - 'a']) {
                    M++;
                    seen[c - 'a'] = true;
                }

        for (int i = 0; i < N - 1; ++i) {
            char[] a = word[i].toCharArray();
            char[] b = word[i + 1].toCharArray();
            int j = 0;

            while (j < a.length && j < b.length && a[j] == b[j])
                j++;

            if (j < a.length && j < b.length)
                greater[b[j] - 'a'][a[j] - 'a'] = true;

            if (j < a.length && !(j < b.length)) {
                System.out.println("!");
                return;
            }
        }

        for (int k = 0; k < 26; ++k)
            for (int i = 0; i < 26; ++i)
                for (int j = 0; j < 26; ++j)
                    greater[i][j] |= greater[i][k] && greater[k][j];

        for (int i = 0; i < 26; ++i)
            if (greater[i][i]) {
                System.out.println("!");
                return;
            }

        for (int i = 0; i < 26; ++i) {
            if (!seen[i]) continue;
            int out = 0;
            int in = 0;

            for (int j = 0; j < 26; ++j) {
                out += greater[i][j] ? 1 : 0;
                in += greater[j][i] ? 1 : 0;
            }

            if (out + in != M - 1) {
                System.out.println("?");
                return;
            }

            ret.add(new Pair<>(out, (char) ('a' + i)));
        }

        ret.sort(Comparator.comparingInt(Pair::getFirst));

        for (Pair<Integer, Character> pair : ret)
            System.out.print(pair.getSecond());

        System.out.println();
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
