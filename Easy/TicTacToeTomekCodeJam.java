import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TicTacToeTomek : code jam , qualification round problem A 2013
 * 
 * @author p0wontnx
 * 
 */

public class TicTacToeTomek {

	public static void main(String[] args) {
		 try {
		 System.setOut(new PrintStream("src/TicTacToeTomek-output.out"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		
		 Scanner in = null;
		 try {
		 in = new Scanner(new File("src/TicTacToeTomek-large-practice.in"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }

		//Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases
		int[] board = new int[16];
		for (int i = 0; i < t; i++) {

			in.nextLine();

			for (int j = 0; j < 4; j++) {
				String str = in.nextLine();
				for (int k = 0; k < 4; k++) {
					board[4 * j + k] = str.charAt(k) == 'X' ? 1
							: str.charAt(k) == 'T' ? 100
									: str.charAt(k) == '.' ? 0 : -1;
				}
			}

			char won = 0;
			boolean row = false;
			boolean col = false;
			boolean diag = false;
			int s3 = 0;
			int s4 = 0;
			for (int j = 0; j < 4; j++) {
				int s1 = 0;
				int s2 = 0;
				for (int k = 0; k < 4; k++) {
					s1 += board[j * 4 + k];
					s2 += board[k * 4 + j];
				}
				// test row
				if (s1 == 103 || s1 == 4) {
					row = true;
					won = 'X';
				} else if (s1 == -4 || s1 == 97) {
					row = true;
					won = 'O';
				}

				// test col
				if (s2 == 103 || s2 == 4) {
					col = true;
					won = 'X';
				} else if (s2 == -4 || s2 == 97) {
					col = true;
					won = 'O';
				}

				s3 += board[j * 5];
				s4 += board[3 * (j + 1)];
				if (s3 == 103 || s3 == 4) {
					diag = true;
					won = 'X';
				} else if (s3 == -4 || s3 == 97) {
					diag = true;
					won = 'O';
				}

				if (s4 == 103 || s4 == 4) {
					diag = true;
					won = 'X';
				} else if (s4 == -4 || s4 == 97) {
					diag = true;
					won = 'O';
				}

				// test row
				if (row || col || diag) {
					break;
				}
			}
			
			
			// show result
			if (row || col || diag)
				System.out.println("Case #" + (i + 1) + ": " + won + " won");
			else {
				boolean draw = true;
				for (int j = 0; j < 16 && draw; j++) {
					if (board[j] == 0)
						draw = false;
				}

				if (draw) {
					System.out.println("Case #" + (i + 1) + ": " + "Draw");
				} else {
					System.out.println("Case #" + (i + 1) + ": " + "Game has not completed");
				}

			}
		}

	}

}
