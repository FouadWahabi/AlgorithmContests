import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MagicTrick {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/A-small-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = in.nextInt();
		in.nextLine();

		for (int i = 0; i < n; i++) {
			int card = -1;
			int firstRowI = in.nextInt();
			in.nextLine();
			int firstRow[] = new int[4];
			int secondRow[] = new int[4];

			for (int j = 0; j <= 4; j++) {
				if (j == firstRowI - 1) {
					for (int j1 = 0; j1 < 4; j1++) {
						firstRow[j1] = in.nextInt();
					}
				} else
					in.nextLine();
			}
			int secondRowI = in.nextInt();
			in.nextLine();
			for (int j = 0; j <= 4; j++) {
				if (j == secondRowI - 1) {
					for (int k = 0; k < 4; k++) {
						secondRow[k] = in.nextInt();
						for (int j2 = 0; j2 < 4; j2++) {
							if (firstRow[j2] == secondRow[k]) {
								if (card == -1)
									card = firstRow[j2];
								else
									card = 0;
							}
						}
					}
				} else
					in.nextLine();
			}
			System.out.println("Case #"
					+ (i + 1)
					+ ": "
					+ (card > 0 ? card + "" : card != -1 ? "Bad magician!"
							: "Volunteer cheated!"));
		}

	}

}
