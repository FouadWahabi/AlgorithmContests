import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Brattleship {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/Brattleship-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/Brattleship-large-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {

			int r = in.nextInt(), c = in.nextInt(), w = in.nextInt();

			int res = 0;

			if (c % w == 0) {
				res = w + (c - w) / w + (r - 1) * (int) (c / w);
			} else {
				res = (int) (c / w) + w + (r - 1) * (int) (c / w);
			}

			System.out.println("Case #" + (i + 1) + ": " + res);

		}

	}

}
