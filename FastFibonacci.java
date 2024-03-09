import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Fibonacci {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final BigInteger MOD = new BigInteger("1000000007");

    public static void main(String[] args) throws IOException {
        BigInteger n = readBigInteger();
        System.out.println(fib(n));

    }

    static BigInteger fib(BigInteger n) {
        BigInteger F[][] = new BigInteger[][] { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;
        power(F, n.subtract(BigInteger.ONE));

        return F[0][0].mod(MOD);
    }

    static void multiply(BigInteger F[][], BigInteger M[][]) {
        BigInteger x = F[0][0].multiply(M[0][0]).add(F[0][1].multiply(M[1][0])).mod(MOD);
        BigInteger y = F[0][0].multiply(M[0][1]).add(F[0][1].multiply(M[1][1])).mod(MOD);
        BigInteger z = F[1][0].multiply(M[0][0]).add(F[1][1].multiply(M[1][0])).mod(MOD);
        BigInteger w = F[1][0].multiply(M[0][1]).add(F[1][1].multiply(M[1][1])).mod(MOD);

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    static void power(BigInteger F[][], BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return;
        BigInteger M[][] = new BigInteger[][] { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };

        power(F, n.divide(BigInteger.valueOf(2)));
        multiply(F, F);

        if (!n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
            multiply(F, M);
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }

    static BigInteger readBigInteger() throws IOException {
        return new BigInteger(next());
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
