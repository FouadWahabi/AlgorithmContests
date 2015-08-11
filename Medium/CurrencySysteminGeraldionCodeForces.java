import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {

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

		int n = nextInt();
		int[] t = new int[n];

		for (int i = 0; i < n; i++) {

			t[i] = nextInt();

		}

		Arrays.sort(t);

		long tmp = t[n - 1];

		long h = -1;

		if (t[0] != 1) {
			for (long i = 1; i < tmp; i++) {
				int index = n - 1;

				long val = i;
				while (index >= 0 && val != 0) {
					if (val / t[index] == 0)
						index--;
					else {
						val = val % t[index];
					}
				}

				if (val != 0) {
					h = val;
					break;
				}
			}
		}

		System.out.println(h);

	}

	private static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

}
