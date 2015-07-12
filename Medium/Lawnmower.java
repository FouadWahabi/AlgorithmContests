import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Lawnmower : code jam , qualification round problem B 2013
 * 
 * @author p0wontnx
 * 
 */

public class Lawnmower {

	public static void main(String[] args) {

		 try {
		 System.setOut(new PrintStream("src/Lawnmower-output.out"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		
		 Scanner in = null;
		 try {
		 in = new Scanner(new File("src/Lawnmower-large-practice.in"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }

		//Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {
			int n, m;
			n = in.nextInt();
			m = in.nextInt();
			int[][] nlawn = new int[n][m];
			int[][] olawn = new int[n][m];

			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					nlawn[j][k] = in.nextInt();
					olawn[j][k] = 100;
				}
			}

			int max;

			for (int j = 0; j < n; j++) {
				max = Integer.MIN_VALUE;
				for (int k = 0; k < m; k++) {
					if (nlawn[j][k] >= max) {
						max = nlawn[j][k];
					}

					if (k == m - 1) {
						for (int h = 0; h < m; h++) {
							if (nlawn[j][h] >= max)
								olawn[j][h] = max;
						}
					}
				}
			}

			for (int j = 0; j < m; j++) {
				max = Integer.MIN_VALUE;
				for (int k = 0; k < n; k++) {
					if (nlawn[k][j] >= max) {
						max = nlawn[k][j];
					}

					if (k == n - 1) {
						for (int h = 0; h < n; h++) {
							if (nlawn[h][j] >= max)
								olawn[h][j] = max;
						}
					}
				}
			}

			// test equality
			boolean equal = true;
			loop: for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (nlawn[j][k] != olawn[j][k]) {
						equal = false;
						break loop;
					}
				}
			}
			if (equal)
				System.out.println("Case #" + (i + 1) + ": " + "YES");
			else
				System.out.println("Case #" + (i + 1) + ": " + "NO");
		}

	}

}
