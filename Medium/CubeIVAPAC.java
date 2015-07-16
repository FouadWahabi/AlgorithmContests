import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Cube {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/Cube-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/Cube-large-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {

			int s = in.nextInt();
			int[][] muze = new int[s][s];

			for (int j = 0; j < s; j++) {
				for (int k = 0; k < s; k++) {
					muze[j][k] = in.nextInt();
				}
			}

			int max = Integer.MIN_VALUE;
			int r = Integer.MAX_VALUE;

			for (int j = 0; j < s; j++) {
				for (int k = 0; k < s; k++) {
					int d = 1;
					boolean done = false;
					int current = j * s + k;
					while (!done) {
						int tmpk = current % s;
						int tmpj = current / s;
						// move right
						if ((tmpk) < s - 1
								&& muze[tmpj][tmpk + 1] == muze[tmpj][tmpk] + 1) {
							current = tmpj * s + tmpk + 1;
							d++;
							continue;
						}

						// move left
						if ((tmpk) > 0
								&& muze[tmpj][tmpk - 1] == muze[tmpj][tmpk] + 1) {
							current = tmpj * s + tmpk - 1;
							d++;
							continue;
						}

						// move up
						if ((tmpj) > 0
								&& muze[tmpj - 1][tmpk] == muze[tmpj][tmpk] + 1) {
							current = (tmpj - 1) * s + tmpk;
							d++;
							continue;
						}

						// move down
						if ((tmpj) < s - 1
								&& muze[tmpj + 1][tmpk] == muze[tmpj][tmpk] + 1) {
							current = (tmpj + 1) * s + tmpk;
							d++;
							continue;
						}

						// not able to move
						done = true;
					}

					if (d > max) {
						r = muze[j][k];
						max = d;
					} else if (d == max && muze[j][k] < r) {
						r = muze[j][k];
					}
				}
			}

			System.out.println("Case #" + (i + 1) + ": " + r + " " + max);

		}

	}
}
