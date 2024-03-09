import java.io.*;
import java.util.StringTokenizer;

public class Template {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int sequence[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = readInt();
        }

        boolean notp = false;
        for (int i = N - 1; i >= 1; i--) {
            if (sequence[i + 1] != 0 && sequence[i] == 0) {
                sequence[i] = sequence[i + 1] - 1;
            }
        }
        for (int i = N; i > 1; i--) {
            if (sequence[i] != 0 && sequence[i - 1] != 0) {
                if (sequence[i] != 1) {
                    if (sequence[i] - sequence[i - 1] != 1) {
                        notp = true;
                    }
                }
            }
        }
        if (notp || (sequence[1] != 1 && sequence[1] != 0)) {
            System.out.println("-1");
            System.exit(0);
        }
        int min = 0;
        int max = 0;
        for (int i = 2; i <= N; i++) {
            if (sequence[i] == 1) {
                min++;
                max++;
            }
            if (sequence[i] == 0) {
                max++;
            }
        }
        System.out.println(min + " " + max);
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
