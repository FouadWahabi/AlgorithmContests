import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PasswordProblem {

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
		 "src/PasswordProblem-small-practice.in")));
		out = new PrintWriter("src/output.out");

//		br = new BufferedReader(new InputStreamReader(System.in));

		int t = nextInt();

		for (int i = 0; i < t; i++) {
			double res = Double.MAX_VALUE;

			int a = nextInt(), b = nextInt();

			double[] prob = new double[a];
			for (int j = 0; j < a; j++) {
				prob[j] = nextDouble();
			}

			double[][] exp = new double[2 + a][(1 << a) + 1];
			double[] proba = new double[1 << a];

			// 0 if wrong 1 if correct
			for (int j = 0; j < (1 << a); j++) {
				double pr = 1;
				for (int h = 0; h < a; h++) {
					if ((j & (1 << h)) == 0) {
						pr *= (1 - prob[h]);
					} else {
						pr *= prob[h];
					}
				}
				proba[j] = pr;

				// option 1
				exp[0][j] = Integer.bitCount(j) == a ? (1 + b - a)
						: (2 + 2 * b - a);
				// option 2
				for (int k = 0; k < a; k++) {
					int h = ((1 << (a - (k + 1))) - 1);
					int kj = j & h;
					exp[k + 1][j] = Integer.bitCount(kj) == a - k - 1 ? (2 * (k + 1) + b - a + 1)
									: (2 + 2 * (k + 1) + 2 * b - a);
				}
				// option 3
				exp[a + 1][j] = 2 + b;
			}
			// expected
			for (int j = 0; j < a + 2; j++) {
				exp[j][(1 << a)] = 0;
				for (int k = 0; k < (1 << a); k++) {
					exp[j][(1 << a)] += proba[k] * exp[j][k];
				}

				if (exp[j][(1 << a)] < res) {
					res = exp[j][(1 << a)];
				}
			}

			// output
			System.out.println("Case #" + (i + 1) + ": " + res);
			out.println("Case #" + (i + 1) + ": " + res);

		}

		out.close();

	}
}
