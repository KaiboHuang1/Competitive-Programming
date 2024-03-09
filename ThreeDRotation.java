import java.util.*;
import java.io.*;

public class ThreeDRotation {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		
		 int T = readInt(); 
		 
		 for (int t = 0; t < T; t++) {
	            String[] input = readLine().split(" ");
	            double x = Double.parseDouble(input[0]);
	            double y = Double.parseDouble(input[1]);
	            double z = Double.parseDouble(input[2]);
	            double rx = Double.parseDouble(input[3]);
	            double ry = Double.parseDouble(input[4]);
	            double rz = Double.parseDouble(input[5]);
	            double theta = Double.parseDouble(input[6]);

	            double[] result = rotatePoint(x, y, z, rx, ry, rz, theta);
	            System.out.println(result[0] + " " + result[1] + " " + result[2]);
	        }

	}
	 public static double[] rotatePoint(double x, double y, double z, double rx, double ry, double rz, double theta) {
	        double cosTheta = Math.cos(theta);
	        double sinTheta = Math.sin(theta);
	        double[] result = new double[3];

	        double ux = rx;
	        double uy = ry;
	        double uz = rz;

	        // Normalize axis vector
	        double length = Math.sqrt(ux * ux + uy * uy + uz * uz);
	        if (length != 0) {
	            ux /= length;
	            uy /= length;
	            uz /= length;
	        }

	        // Calculate rotation matrix elements
	        double cosT = 1 - cosTheta;
	        double sinT = sinTheta;

	        double[][] rotationMatrix = {
	            {cosTheta + ux * ux * cosT, ux * uy * cosT - uz * sinT, ux * uz * cosT + uy * sinT},
	            {uy * ux * cosT + uz * sinT, cosTheta + uy * uy * cosT, uy * uz * cosT - ux * sinT},
	            {uz * ux * cosT - uy * sinT, uz * uy * cosT + ux * sinT, cosTheta + uz * uz * cosT}
	        };

	        // Apply rotation matrix to the point
	        result[0] = rotationMatrix[0][0] * x + rotationMatrix[0][1] * y + rotationMatrix[0][2] * z;
	        result[1] = rotationMatrix[1][0] * x + rotationMatrix[1][1] * y + rotationMatrix[1][2] * z;
	        result[2] = rotationMatrix[2][0] * x + rotationMatrix[2][1] * y + rotationMatrix[2][2] * z;

	        return result;
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
