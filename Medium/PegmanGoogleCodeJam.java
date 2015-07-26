import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pegman {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/Pegman-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/Pegman-large-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		loop: for (int i = 0; i < t; i++) {

			int res = 0;

			int r = in.nextInt(), c = in.nextInt();

			char[][] grid = new char[r][c];

			in.nextLine();

			for (int j = 0; j < r; j++) {
				String str = in.nextLine();
				for (int k = 0; k < c; k++) {
					grid[j][k] = str.charAt(k);
				}
			}

			String str = "<>^v";

			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (go(grid, r, c, j, k) != 0) {
						boolean ok = false;

						switch (grid[j][k]) {
						case 'v':
							str = "^<>v";
							break;
						case '^':
							str = "v<>^";
							break;
						case '<':
							str = ">v^<";
							break;
						case '>':
							str = "<v^>";
							break;
						}

						for (int l = 0; l < 4; l++) {
							char tmpo = grid[j][k];
							grid[j][k] = str.charAt(l);
							if (go(grid, r, c, j, k) != 1) {
								ok = true;
								break;
							} else {
								grid[j][k] = tmpo;
							}
						}

						if (ok) {
							res++;
						} else {
							System.out.println("Case #" + (i + 1) + ": "
									+ "IMPOSSIBLE");
							continue loop;
						}

					}
				}
			}

			System.out.println("Case #" + (i + 1) + ": " + res);

		}
	}

	static int go(char[][] grid, int r, int c, int pr, int pc) {

		int blast = pr * c + pc;
		int last = pr * c + pc;

		int tmp = last;

		if (grid[pr][pc] == '.')
			return 0;

		List<Integer> indexes = new ArrayList<>();
		while (true) {
			if (pr >= r || pr < 0 || pc >= c || pc < 0) {

				if (last != tmp)
					return 0;

				if (last == blast)
					return 1;
				else
					return 2;
			}

			if (indexes.contains(pr * c + pc)) {
				return 0;
			}

			indexes.add(pr * c + pc);

			switch (grid[pr][pc]) {
			case 'v':
				blast = last;
				last = pr * c + pc;
				pr++;
				break;
			case '<':
				blast = last;
				last = pr * c + pc;
				pc--;
				break;
			case '>':
				blast = last;
				last = pr * c + pc;
				pc++;
				break;
			case '^':
				blast = last;
				last = pr * c + pc;
				pr--;
				break;

			case '.':
				switch (grid[last / c][last % c]) {
				case 'v':
					pr++;
					break;
				case '<':
					pc--;
					break;
				case '>':
					pc++;
					break;
				case '^':
					pr--;
					break;
				}
			}
		}
	}
}
