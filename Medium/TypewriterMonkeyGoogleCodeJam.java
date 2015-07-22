import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class TypewriterMonkey {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/TypewriterMonkey-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/TypewriterMonkey-small-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {

			double res = 0;

			int k = in.nextInt(), l = in.nextInt(), s = in.nextInt();

			in.nextLine();

			String keyboard = in.nextLine();

			String word = in.nextLine();

			boolean impo = false;
			for (int j = 0; j < word.length(); j++) {
				if (keyboard.indexOf(word.charAt(j)) == -1) {
					impo = true;
					break;
				}
			}

			if (impo) {
				res = 0;
			} else {

				int p = 0;
				int h = 0;

				while (p < l - 1) {
					if (word.substring(l - p - 1, l).equals(
							word.substring(0, p + 1))) {
						p++;
						if (p > h)
							h = p;
					} else {
						p++;
					}
				}

				int max = (s - h) / (l - h);

				double prob = 0;
				double tot = Math.pow(k, s);
				for (int j = 0; j < tot; j++) {
					String str = "";
					int tmp = 1;
					for (int m = 0; m < s; m++) {
						str = keyboard.charAt((j / tmp) % k) + str;
						tmp *= k;
					}
					prob += (double) typedWord(str, word) / tot;
				}

				res = max - prob;

				res = (double) Math.round(res * 10000000) / 10000000;

				// res = (double) Math
				// .round((max - max * prob(word, keyboard)) * 10000000) /
				// 10000000;

			}

			System.out.println("Case #" + (i + 1) + ": " + res);

		}

	}

	private static double typedWord(String str, String word) {
		int j = 0;
		for (int i = 0; i <= str.length() - word.length(); i++) {
			if (str.substring(i, word.length() + i).equals(word))
				j++;
		}
		return j;
	}

}
