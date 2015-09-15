import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PasswordProblemL {

	static BufferedReader br = null;
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

	public static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) throws IOException {

		 br = new BufferedReader(new FileReader(new File(
		 "src/PasswordProblem-large-practice.in")));
		out = new PrintWriter("src/output.out");

//		br = new BufferedReader(new InputStreamReader(System.in));

		int t = nextInt();

		for (int i = 0; i < t; i++) {
			double res = Double.MAX_VALUE;

			int a = nextInt(), b = nextInt();

			double proba = 1;

			double[] prob = new double[a];
			for (int j = 0; j < a; j++) {
				prob[j] = nextDouble();
				proba *= prob[j];
			}

			// option 1
			double opt1 = proba * (b - a + 1) + (1 - proba) * (2 * b - a + 2);

			// option 2
			double opt2 = Double.MAX_VALUE;
			for (int j = 1; j <= a; j++) {
				double probak = 1;
				for (int k = 0; k < a - j; k++) {
					probak *= prob[k];
				}

				double opt = (1 - probak) * (2 * j + 2 * b - a + 2) + (probak)
						* (2 * j + b - a + 1);

				if (opt < opt2)
					opt2 = opt;
			}

			// option 3
			double opt3 = 2 + b;

			res = Math.min(opt1, Math.min(opt2, opt3));

			// output
			System.out.println("Case #" + (i + 1) + ": " + res);
			out.println("Case #" + (i + 1) + ": " + res);

		}

		out.close();

	}
}
