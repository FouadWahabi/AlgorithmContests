import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {

		// try {
		// System.setOut(new PrintStream("src/output.out"));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		//
		// Scanner in = null;
		// try {
		// in = new Scanner(new File("src/A-large (2).in"));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

		int[] coords = new int[n];

		for (int i = 0; i < coords.length; i++) {
			coords[i] = in.nextInt();
		}

		for (int i = 0; i < coords.length; i++) {
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			if (i - 1 >= 0)
				min = Math.abs(coords[i] - coords[i - 1]);
			if (i + 1 < n && Math.abs(coords[i] - coords[i + 1]) < min)
				min = Math.abs(coords[i] - coords[i + 1]);

			max = Math.abs(coords[i] - coords[n - 1]);

			if (Math.abs(coords[i] - coords[0]) > max)
				max = Math.abs(coords[i] - coords[0]);
			System.out.println(min + " " + max);
		}

	}
}
