import java.awt.Point;
import java.util.Scanner;
import java.util.Stack;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Player {

	public static void main(String args[]) {

		int[] runes = new int[30];

		Scanner in = new Scanner(System.in);
		String magicPhrase = in.nextLine();
		StringBuilder str = new StringBuilder();

		int order = 0;
		int dist = 0;
		int pos = 0;

		int repi = 0;
		int c = 0;

		int i = 0;

		while (i < magicPhrase.length()) {

			boolean loop = false;

			long tot1 = 0;
			long tot2 = 0;

			if (magicPhrase.charAt(i) == magicPhrase.charAt(c)) {

				repi++;

			} else {

				if (repi > 1) {
					// loop detected

					int t1 = runes[mod(pos + 1, 30)];
					int t2 = runes[mod(pos - 1, 30)];

					int hash1 = 27 - t1;
					int hash2 = 27 - t2;

					long[] q1 = minLcm(hash1, repi);

					long[] q2 = minLcm(hash2, repi);

					tot1 = (q1[3] + 6);
					tot2 = (q2[3] + 6);

					long q = tot1 < tot2 ? tot1 : tot2;

					if (q > 0 && q < repi) {

						loop = true;

						StringBuilder he = new StringBuilder();

						he.append(str.toString().substring(
								0,
								str.toString().length() - repi
										+ (int) (tot1 > tot2 ? q2[0] : q1[0])));

						he.append((tot1 > tot2 ? "<" : ">"));

						for (int j = 0; j < (tot1 > tot2 ? q2[1] : q1[1]); j++) {
							he.append("+");
						}

						he.append(tot1 > tot2 ? "[>.<" : "[<.>");

						for (int j = 0; j < (tot1 > tot2 ? q2[2] : q1[2]); j++) {
							he.append("+");
						}

						he.append("]");

						str = he;
					}

					repi = 0;
				}
				c = i;
			}

			if (!loop) {

				if (i == magicPhrase.length() - 1 && repi > 1) {

					if (magicPhrase.charAt(i) == magicPhrase.charAt(c))
						str.append(".");

					int t1 = runes[mod(pos + 1, 30)];
					int t2 = runes[mod(pos - 1, 30)];

					int hash1 = 27 - t1;
					int hash2 = 27 - t2;

					long[] q1 = minLcm(hash1, repi);

					long[] q2 = minLcm(hash2, repi);

					tot1 = (q1[3] + 6);
					tot2 = (q2[3] + 6);

					long q = tot1 < tot2 ? tot1 : tot2;

					if (q > 0 && q < repi) {

						loop = true;

						StringBuilder he = new StringBuilder();

						he.append(str.toString().substring(
								0,
								str.toString().length() - repi
										+ (int) (tot1 > tot2 ? q2[0] : q1[0])));

						he.append((tot1 > tot2 ? "<" : ">"));

						for (int j = 0; j < (tot1 > tot2 ? q2[1] : q1[1]); j++) {
							he.append("+");
						}

						he.append(tot1 > tot2 ? "[>.<" : "[<.>");

						for (int j = 0; j < (tot1 > tot2 ? q2[2] : q1[2]); j++) {
							he.append("+");
						}

						he.append("]");

						str = he;
					}

					repi = 0;

					runes[pos % 30] = order(magicPhrase.charAt(i));

					i++;

				} else {

					order = Integer.MAX_VALUE;

					int m = 0;

					for (int j = 0; j <= (i < 30 ? i : 29); j++) {

						int tmp = mod(order(magicPhrase.charAt(i))
								- runes[j % 30], 27) > mod(runes[j % 30]
								- order(magicPhrase.charAt(i)), 27) ? mod(
								runes[j % 30] - order(magicPhrase.charAt(i)),
								27) : mod(order(magicPhrase.charAt(i))
								- runes[j % 30], 27);

						if (tmp < order) {
							m = j;
							order = tmp;
						}

					}

					dist = m - pos;

					for (int j = 0; j < Math.abs(dist); j++) {
						str.append(dist >= 0 ? ">" : "<");
					}

					for (int j = 0; j < Math.abs(order); j++) {
						str.append(mod(order(magicPhrase.charAt(i))
								- runes[m % 30], 27) > mod(runes[m % 30]
								- order(magicPhrase.charAt(i)), 27) ? "-" : "+");
					}

					str.append(".");

					pos = m;
					runes[pos % 30] = order(magicPhrase.charAt(i));

					// increment loop
					i++;

				}

			} else {

				pos = (tot1 < tot2 ? mod(pos - 1, 30) : mod(pos + 1, 30));
				runes[pos] = 0;

			}

		}

		System.out.println(str.toString());
	}

	public static Stack<Point> loopDetector(String str) {

		Stack<Point> rep = new Stack<>();

		int t = 0;
		int tmp = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {
				t++;
			} else {
				if (t > 1) {
					rep.push(new Point(tmp + 1, t));
					t = 0;
				}
				tmp = i;
			}
		}

		if (t > 0) {
			rep.push(new Point(tmp + 1, t));
			t = 0;
		}

		return rep;
	}

	public static long[] minLcm(int hash, int rep) {

		// execution prealable + initialisation + const(acolade,
		// decalage, exec) + incrementation

		int cnst = 6;

		long tmp[] = new long[4];

		long m = Long.MAX_VALUE;

		tmp[3] = m;

		for (int i = rep; i >= 1; i--) {

			for (int j = 0; j <= 26; j++) {
				if (mod(27 - (hash + j), 27) != 0) {
					if (((lcm(mod(27 - (hash + j), 27), i)) / i + j + (rep - i) + cnst) < m
							&& lcm(mod(27 - (hash + j), 27),
									lcm(mod(27 - (hash + j), 27), i) / i) >= lcm(
									mod(27 - (hash + j), 27), i)) {
						m = ((lcm(mod(27 - (hash + j), 27), i)) / i + j
								+ (rep - i) + cnst);
						// exec
						tmp[0] = rep - i;
						// init
						tmp[1] = j;
						// incre(
						tmp[2] = (lcm(mod(27 - (hash + j), 27), i)) / i;
						// m
						tmp[3] = m;
					}
				}
			}

		}

		return tmp;
	}

	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	private static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	public static int mod(int a, int b) {
		return (((a % b) + b) % b);
	}

	public static int order(char c) {
		if (c != ' ')
			return (int) c - (int) 'A' + 1;
		else
			return 0;
	}
}
