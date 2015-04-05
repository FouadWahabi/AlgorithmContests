public class ABoardGame {

	public String whoWins(String[] board) {
		int i = board.length / 2 - 1;
		while (i >= 0) {
			int sc = 0;
			int length = board.length - 2 * i;
			for (int j = 0; j < length; j++) {
				if (board[i].charAt(j + i) == 'A')
					sc += 1;
				if (board[i].charAt(j + i) == 'B')
					sc -= 1;
				if (j != 0 && j != length - 1) {
					if (board[i + j].charAt(i) == 'A')
						sc += 1;
					if (board[i + j].charAt(i) == 'B')
						sc -= 1;
				}

				if (board[board.length - 1 - i].charAt(length - 1 - j + i) == 'A')
					sc += 1;
				if (board[board.length - 1 - i].charAt(length - 1 - j + i) == 'B')
					sc -= 1;
				if (j != 0 && j != length - 1) {
					if (board[length - 1 - j + i].charAt(board.length - 1 - i) == 'A')
						sc += 1;
					if (board[length - 1 - j + i].charAt(board.length - 1 - i) == 'B')
						sc -= 1;
				}
			}
			if (sc < 0)
				return "Bob";
			else if (sc > 0)
				return "Alice";
			i--;
		}

		return "Draw";
	}

}
