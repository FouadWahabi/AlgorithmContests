
public class AB {
	private String getSol(StringBuilder str, int n, int k, int a, int pos,
			int sc) {
		str.setLength(n);
		if (pos >= n) {
			if(sc == k) {
				return str.toString();
			}
			return "";
		}

		str.setCharAt(pos, 'A');
		String str1 = getSol(str, n, k, a + 1, pos + 1, sc);
		if (!str1.isEmpty()) {
			return str1.toString();
		}

		str.setCharAt(pos, 'B');
		sc += a;
		String str2 = getSol(str, n, k, a, pos + 1, sc);
		if (!str2.isEmpty()) {
			return str2.toString();
		}
		return "";
	}

	public String createString(int n, int k) {
		StringBuilder str = new StringBuilder(n);
		str.setLength(n);
		if((n & 1) == 0) {
			if(k > n*n/4)
				return "";
		}
		else if(k > (n*n - 1)/4)
			return "";
		return getSol(str , n, k, 0, 0, 0);
	}
}
