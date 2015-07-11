import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deceitful War : code jam , qualification round problem D 2014
 * 
 * @author p0wontnx
 * 
 */
public class DeceitfulWar {

	public static void main(String[] args) {

		 try {
		 System.setOut(new PrintStream("src/output.out"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		
		 Scanner in = null;
		 try {
		 in = new Scanner(new File("src/D-large-practice.in"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }

		//Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			int z = 0;
			int y = 0;

			int index = n - 1;

			float[] mass1 = new float[n];
			float[] mass2 = new float[n];

			for (int j = 0; j < n; j++) {
				mass1[j] = in.nextFloat();
			}

			Arrays.sort(mass1);

			for (int j = 0; j < n; j++) {
				mass2[j] = in.nextFloat();
			}

			Arrays.sort(mass2);

			// war
			for (int j = n - 1; j >= 0; j--) {

				if (mass1[j] > mass2[index]) {
					z++;
				} else {
					index--;
				}

			}

			index = 0;
			// deceitful war
			for (int j = 0; j < n; j++) {
				// if naomi can catch a point
				if(mass1[j] > mass2[index]) {
					y ++;
					index ++;
				}
				
				
			}
			
			
			

			System.out.println("Case #" + (i + 1) + ": " + y + " " + z);
		}
	}

}
