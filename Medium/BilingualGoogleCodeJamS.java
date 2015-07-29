import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.xml.internal.ws.util.StringUtils;

public class Bilingual {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream("src/Bilingual-output.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(new File("src/Bilingual-small-practice.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // total cases

		for (int i = 0; i < t; i++) {
			int n = in.nextInt();

			int res = Integer.MAX_VALUE;

			String[][] phrases = new String[n][];

			Map<String, Integer> ids = new HashMap<>();

			int cnt = 0;

			in.nextLine();

			for (int j = 0; j < n; j++) {
				phrases[j] = in.nextLine().split(" ");
				for (int k = 0; k < phrases[j].length; k++) {
					if (!ids.containsKey(phrases[j][k])) {
						ids.put(phrases[j][k], cnt++);
					}
				}
			}

			for (int j = 0; j < (1 << (n - 2)); j++) {

				boolean[] fr = new boolean[cnt];
				boolean[] eng = new boolean[cnt];

				for (int h = 0; h < phrases[0].length; h++) {
					eng[ids.get(phrases[0][h])] = true;
				}

				for (int h = 0; h < phrases[1].length; h++) {
					fr[ids.get(phrases[1][h])] = true;
				}

				int min = 0;

				for (int k = 0; k < n - 2; k++) {
					if ((j & (1 << k)) > 0) {
						for (int h = 0; h < phrases[k + 2].length; h++) {
							eng[ids.get(phrases[k + 2][h])] = true;
						}
					} else {
						for (int h = 0; h < phrases[k + 2].length; h++) {
							fr[ids.get(phrases[k + 2][h])] = true;
						}
					}
				}

				for (int k = 0; k < cnt; k++) {
					if (fr[k] && eng[k])
						min++;
				}

				if (min < res && min != 0)
					res = min;

			}

			System.out.println("Case #" + (i + 1) + ": " + res);

		}

	}
}
