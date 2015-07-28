import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class KiddiePool {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/KiddiePool-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/KiddiePool-small-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {

			int n = in.nextInt();
			double v = in.nextDouble(), x = in.nextDouble();

			Source[] sources = new Source[n];

			for (int j = 0; j < n; j++) {

				Source source = new Source();
				source.v = in.nextDouble();
				source.t = in.nextDouble();

				sources[j] = source;
			}

			// determine if its impossible
			boolean impo = true;
			for (int j = 0; j < n; j++) {
				if (sources[j].t >= x) {
					impo = false;
					break;
				}
			}
			if (!impo) {
				impo = true;
				for (int j = 0; j < n; j++) {
					if (sources[j].t <= x) {
						impo = false;
						break;
					}
				}
			}

			if (impo) {
				System.out.println("Case #" + (i + 1) + ": " + "IMPOSSIBLE");
			} else {
				double res = 0, t11 = 0, t21 = 0, t12 = 0, t22 = 0, tp = 0, rp = 0;

				if (n == 1) {
					res = v / sources[0].v;
				} else {

					if (sources[0].t != sources[1].t) {
						rp = sources[0].v + sources[1].v;
						tp = ((sources[0].v * sources[0].t) + (sources[1].v * sources[1].t))
								/ rp;

						// s2 first
						t22 = (v * (x - sources[1].t))
								/ ((tp - sources[1].t) * rp);
						t12 = (v - t22 * rp) / sources[1].v;

						// s1 first
						t21 = (v * (x - sources[0].t))
								/ ((tp - sources[0].t) * rp);
						t11 = (v - t21 * rp) / sources[0].v;

						if (t11 < 0 || t21 < 0) {
							res = t12 + t22;
						} else {
							res = t11 + t21;
						}
					} else {
						res = v / (sources[0].v + sources[1].v);
					}

				}

				System.out.println("Case #" + (i + 1) + ": " + res);

			}

		}

	}

	static class Source {
		double v, t;
	}

}
