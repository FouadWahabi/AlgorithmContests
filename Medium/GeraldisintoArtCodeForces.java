import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintWriter out;

	public static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}

	public static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws IOException {

		int a1 = nextInt(), b1 = nextInt(), a2 = nextInt(), b2 = nextInt(), a3 = nextInt(), b3 = nextInt();

		if (a3 + a2 <= a1 && b2 <= b1 && b3 <= b1) {
			System.out.println("YES");
		} else if (a3 + b2 <= a1 && a2 <= b1 && b3 <= b1) {
			System.out.println("YES");
		} else if (b3 + b2 <= a1 && a2 <= b1 && a3 <= b1) {
			System.out.println("YES");
		} else if (b3 + a2 <= a1 && b2 <= b1 && a3 <= b1) {
			System.out.println("YES");
		} else if (a3 + a2 <= b1 && b2 <= a1 && b3 <= a1) {
			System.out.println("YES");
		} else if (a3 + b2 <= b1 && a2 <= a1 && b3 <= a1) {
			System.out.println("YES");
		} else if (b3 + b2 <= b1 && a2 <= a1 && a3 <= a1) {
			System.out.println("YES");
		} else if (b3 + a2 <= b1 && b2 <= a1 && a3 <= a1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}
}
