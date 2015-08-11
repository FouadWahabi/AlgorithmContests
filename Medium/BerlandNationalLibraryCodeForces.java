import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class B {

	public static void main(String[] args) {

		// try {
		// System.setOut(new PrintStream("src/output.out"));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		//
		// Scanner in = null;
		// try {
		// in = new Scanner(new File("src/A-large (2).in"));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();

		List<Integer> room = new ArrayList<Integer>();

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			String str = in.nextLine();
			int p = Integer.parseInt(str.substring(2));
			if (str.charAt(0) == '+') {
				room.add(p);
				if (room.size() > max)
					max = room.size();
			} else {
				if (room.contains(p)) {
					room.remove(new Integer(p));
				} else {
					if(max == Integer.MIN_VALUE)
						max = 0;
					max++;
				}
			}
		}

		System.out.println(max);
	}
}
