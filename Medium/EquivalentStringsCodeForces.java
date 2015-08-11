import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintWriter out;

	public static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}

	public static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws IOException {

		String str1 = br.readLine(), str2 = br.readLine();

		System.out.println(equivalent(str1, str2) ? "YES" : "NO");

	}

	static boolean equivalent(String str1, String str2) {

		if (str1.equals(str2))
			return true;

		int a = str1.length();

		if ((a & 1) > 0)
			return false;

		return (equivalent(str1.substring(0, a / 2), str2.substring(0, a / 2)) && equivalent(
				str1.substring(a / 2, a), str2.substring(a / 2, a)))
				|| (equivalent(str1.substring(a / 2, a),
						str2.substring(0, a / 2)) && equivalent(
						str1.substring(0, a / 2), str2.substring(a / 2, a)));

	}

}
