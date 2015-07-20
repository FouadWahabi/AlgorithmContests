import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class NoisyNeighbors {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/NoisyNeighbors-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/NoisyNeighbors-large-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases
		for (int i = 0; i < t; i++) {
			int res1 = 0, res2 = 0;
			
			int r = in.nextInt(), c = in.nextInt(), n = in.nextInt();
			
			int n1 = n;
			
			{
				final Point[][] board = new Point[r][c];

				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						board[j][k] = new Point();
						if (k % 2 == 0) {
							board[j][k].x = j % 2 == 0 ? 1 : 0;
							if (board[j][k].x == 1)
								n--;
						} else {
							board[j][k].x = j % 2 == 0 ? 0 : 1;
							if (board[j][k].x == 1)
								n--;
						}

						board[j][k].y = 0;
					}
				}

				recalLimits(board, r, c);

				// place the rest of neighbors
				while (n > 0) {
					int tmpj = 0, tmpk = 0;
					int min = Integer.MAX_VALUE;

					boolean incj = c == 1 ? true : false;
					boolean inck = c == 1 ? false : true;
					int xj = 1;
					int xk = 1;
					int tj = 1;
					int tk = 0;
					int ij = 0, ik = 0;

					for (int h = 0; h < r * c; h++) {

						// System.out.println(ij + "  " + ik);

						if (board[ij][ik].y < min && board[ij][ik].x == 0) {
							min = board[ij][ik].y;
							tmpj = ij;
							tmpk = ik;
						}

						if (incj && r != 1) {
							ij += xj;
						}
						if (inck && c != 1) {
							ik += xk;
						}

						if (!incj
								&& ((ik == c - 1 - tk / 2 && xk > 0) || (ik == 0 + tk / 2 && xk < 0))) {
							tk++;
							inck = false;
							incj = true;
							xk = -xk;
						}

						else if (!inck
								&& ((ij == r - 1 - tj / 2 && xj > 0) || (ij == 0 + tj / 2 && xj < 0))) {
							tj++;
							inck = true;
							incj = false;

							xj = -xj;
						}

					}

					board[tmpj][tmpk].x = 1;

					if (tmpk + 1 < c) {
						board[tmpj][tmpk + 1].y++;
					}

					if (tmpk - 1 >= 0) {
						board[tmpj][tmpk - 1].y++;
					}

					if (tmpj - 1 >= 0) {
						board[tmpj - 1][tmpk].y++;
					}

					if (tmpj + 1 < r) {
						board[tmpj + 1][tmpk].y++;
					}

					res1 += board[tmpj][tmpk].y;
					n--;
				}

				List<Point> pos = new ArrayList<>();

				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {

						if (board[j][k].x == 0) {
							pos.add(new Point(j, k));
						}
					}
				}

				// optimise
				boolean done = false;
				while (!pos.isEmpty() && !done) {
					int jm = -1, km = -1;
					int max = Integer.MIN_VALUE;

					boolean incj = c == 1 ? true : false;
					boolean inck = c == 1 ? false : true;
					int xj = 1;
					int xk = 1;
					int tj = 1;
					int tk = 0;
					int ij = 0, ik = 0;

					for (int h = 0; h < r * c; h++) {

						// System.out.println(ij + "  " + ik);

						if (board[ij][ik].y >= max && board[ij][ik].x == 1) {
							max = board[ij][ik].y;
							jm = ij;
							km = ik;
						}

						if (incj && r != 1) {
							ij += xj;
						}
						if (inck && c != 1) {
							ik += xk;
						}

						if (!incj
								&& ((ik == c - 1 - tk / 2 && xk > 0) || (ik == 0 + tk / 2 && xk < 0))) {
							tk++;
							inck = false;
							incj = true;
							xk = -xk;
						}

						else if (!inck
								&& ((ij == r - 1 - tj / 2 && xj > 0) || (ij == 0 + tj / 2 && xj < 0))) {
							tj++;
							inck = true;
							incj = false;

							xj = -xj;
						}

					}

					if (jm == -1 && km == -1) {
						done = true;
					} else {
						board[jm][km].x = 0;
						recalLimits(board, r, c);
						Collections.sort(pos, new Comparator<Point>() {

							@Override
							public int compare(Point o1, Point o2) {
								return board[o1.x][o1.y].y
										- board[o2.x][o2.y].y;
							}
						});

						if (board[jm][km].y > board[pos.get(0).x][pos.get(0).y].y) {
							res1 -= (board[jm][km].y - board[pos.get(0).x][pos
									.get(0).y].y);
							Point p = new Point(jm, km);
							board[pos.get(0).x][pos.get(0).y].x = 1;
							pos.remove(0);
							pos.add(p);
							Collections.sort(pos, new Comparator<Point>() {

								@Override
								public int compare(Point o1, Point o2) {
									return board[o1.x][o1.y].y
											- board[o2.x][o2.y].y;
								}
							});
							recalLimits(board, r, c);
						} else {
							board[jm][km].x = 1;
							done = true;
						}

					}
				}

			}

			n = n1;
			
			{
				final Point[][] board = new Point[r][c];

				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						board[j][k] = new Point();
						if (k % 2 == 1) {
							board[j][k].x = j % 2 == 0 ? 1 : 0;
							if (board[j][k].x == 1)
								n--;
						} else {
							board[j][k].x = j % 2 == 0 ? 0 : 1;
							if (board[j][k].x == 1)
								n--;
						}

						board[j][k].y = 0;
					}
				}

				recalLimits(board, r, c);

				// place the rest of neighbors
				while (n > 0) {
					int tmpj = 0, tmpk = 0;
					int min = Integer.MAX_VALUE;

					boolean incj = c == 1 ? true : false;
					boolean inck = c == 1 ? false : true;
					int xj = 1;
					int xk = 1;
					int tj = 1;
					int tk = 0;
					int ij = 0, ik = 0;

					for (int h = 0; h < r * c; h++) {

						// System.out.println(ij + "  " + ik);

						if (board[ij][ik].y < min && board[ij][ik].x == 0) {
							min = board[ij][ik].y;
							tmpj = ij;
							tmpk = ik;
						}

						if (incj && r != 1) {
							ij += xj;
						}
						if (inck && c != 1) {
							ik += xk;
						}

						if (!incj
								&& ((ik == c - 1 - tk / 2 && xk > 0) || (ik == 0 + tk / 2 && xk < 0))) {
							tk++;
							inck = false;
							incj = true;
							xk = -xk;
						}

						else if (!inck
								&& ((ij == r - 1 - tj / 2 && xj > 0) || (ij == 0 + tj / 2 && xj < 0))) {
							tj++;
							inck = true;
							incj = false;

							xj = -xj;
						}

					}

					board[tmpj][tmpk].x = 1;

					if (tmpk + 1 < c) {
						board[tmpj][tmpk + 1].y++;
					}

					if (tmpk - 1 >= 0) {
						board[tmpj][tmpk - 1].y++;
					}

					if (tmpj - 1 >= 0) {
						board[tmpj - 1][tmpk].y++;
					}

					if (tmpj + 1 < r) {
						board[tmpj + 1][tmpk].y++;
					}

					res2 += board[tmpj][tmpk].y;
					n--;
				}

				List<Point> pos = new ArrayList<>();

				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {

						if (board[j][k].x == 0) {
							pos.add(new Point(j, k));
						}
					}
				}

				// optimise
				boolean done = false;
				while (!pos.isEmpty() && !done) {
					int jm = -1, km = -1;
					int max = Integer.MIN_VALUE;

					boolean incj = c == 1 ? true : false;
					boolean inck = c == 1 ? false : true;
					int xj = 1;
					int xk = 1;
					int tj = 1;
					int tk = 0;
					int ij = 0, ik = 0;

					for (int h = 0; h < r * c; h++) {

						// System.out.println(ij + "  " + ik);

						if (board[ij][ik].y >= max && board[ij][ik].x == 1) {
							max = board[ij][ik].y;
							jm = ij;
							km = ik;
						}

						if (incj && r != 1) {
							ij += xj;
						}
						if (inck && c != 1) {
							ik += xk;
						}

						if (!incj
								&& ((ik == c - 1 - tk / 2 && xk > 0) || (ik == 0 + tk / 2 && xk < 0))) {
							tk++;
							inck = false;
							incj = true;
							xk = -xk;
						}

						else if (!inck
								&& ((ij == r - 1 - tj / 2 && xj > 0) || (ij == 0 + tj / 2 && xj < 0))) {
							tj++;
							inck = true;
							incj = false;

							xj = -xj;
						}

					}

					if (jm == -1 && km == -1) {
						done = true;
					} else {
						board[jm][km].x = 0;
						recalLimits(board, r, c);
						Collections.sort(pos, new Comparator<Point>() {

							@Override
							public int compare(Point o1, Point o2) {
								return board[o1.x][o1.y].y
										- board[o2.x][o2.y].y;
							}
						});

						if (board[jm][km].y > board[pos.get(0).x][pos.get(0).y].y) {
							res2 -= (board[jm][km].y - board[pos.get(0).x][pos
									.get(0).y].y);
							Point p = new Point(jm, km);
							board[pos.get(0).x][pos.get(0).y].x = 1;
							pos.remove(0);
							pos.add(p);
							Collections.sort(pos, new Comparator<Point>() {

								@Override
								public int compare(Point o1, Point o2) {
									return board[o1.x][o1.y].y
											- board[o2.x][o2.y].y;
								}
							});
							recalLimits(board, r, c);
						} else {
							board[jm][km].x = 1;
							done = true;
						}

					}
				}
			}

			System.out
					.println("Case #" + (i + 1) + ": " + Math.min(res1, res2));

		}

	}

	static void recalLimits(Point[][] board, int r, int c) {
		for (int j = 0; j < r; j++) {
			for (int k = 0; k < c; k++) {

				int tmp = 0;
				if (k + 1 < c && board[j][k + 1].x == 1) {
					tmp++;
				}

				if (k - 1 >= 0 && board[j][k - 1].x == 1) {
					tmp++;
				}

				if (j - 1 >= 0 && board[j - 1][k].x == 1) {
					tmp++;
				}

				if (j + 1 < r && board[j + 1][k].x == 1) {
					tmp++;
				}

				board[j][k].y = tmp;
			}
		}
	}
}
