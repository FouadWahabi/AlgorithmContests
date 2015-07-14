import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Drummer {

	public static void main(String[] args) {

		 try {
		 System.setOut(new PrintStream("src/Drummer-output.out"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		
		 Scanner in = null;
		 try {
		 in = new Scanner(new File("src/Drummer-small-practice.in"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }

		//Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {

			int n = in.nextInt();
			int[] tab = new int[n];
			for (int j = 0; j < n; j++) {
				tab[j] = in.nextInt();
			}

			double res = solve(tab);

			System.out.println("Case #" + (i + 1) + ": " + res);

		}

	}

	private static double solve(int[] tab) {

		if (tab.length == 2)
			return 0;

		List<Point> vals = new ArrayList<>();
		for (int i = 0; i < tab.length - 1; i++) {
			for (int j = i + 1; j < tab.length; j++) {
				Point p = new Point();
				p.x = Math.abs(tab[j] - tab[i]);
				p.y = j - i;
				vals.add(p);
			}
		}

		double max = Integer.MIN_VALUE;

		for (int i = 0; i < vals.size(); i++) {
			for (int j = i + 1; j < vals.size(); j++) {
				float fact = (vals.get(i).y + vals.get(j).y) * 2;
				float tmp = Math.abs(vals.get(j).x * vals.get(i).y
						- vals.get(j).y * vals.get(i).x);
				if((tmp / fact) > max)
					max = tmp / fact;
			}
		}

		return max;
	}

}
