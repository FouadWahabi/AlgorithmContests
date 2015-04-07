import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CoockieClicker {

	static double getMin(double c, double f, double x) {
		double a1 = 2, b1 = 0;
		return getMinTimeIt(c, f, x);
	}

	private static double getMinTime(double a1, double b1, double c, double f,
			double x) {
		double a2, b2, t, xm;
		a2 = a1 + f;
		t = (c - b1) / a1;
		b2 = -a2 * t;
		xm = ((b2 - b1) / (a1 - a2)) * a1 + b1;
		if (x < xm)
			return (x - b1) / a1;
		else
			return getMinTime(a2, b2, c, f, x);
	}

	private static double getMinTimeIt(double c, double f, double x) {
		double a1 = 2, b1 = 0, a2, b2, t, xm, atmp, btmp;
		do {
			atmp = a1;
			btmp = b1;
			a2 = a1 + f;
			t = (c - b1) / a1;
			b2 = -a2 * t;
			xm = ((b2 - b1) / (a1 - a2)) * a1 + b1;
			a1 = a2;
			b1 = b2;
		} while (x > xm);
		return (x - btmp) / atmp;
	}

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/B-large-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			double c = in.nextDouble();
			double f = in.nextDouble();
			double x = in.nextDouble();
			System.out.println("Case #" + (i + 1) + ": "
					+ (float) getMin(c, f, x));
		}
	}

}
