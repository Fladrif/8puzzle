import java.lang.Math;
import java.util.Comparator;

public class ManhattanHeuristic implements Comparator<Board> {
	public int compare(Board x, Board y) {
		if ((manhattanDist(x) + x.getCost()) < (manhattanDist(y) + y.getCost())) {
			return -1;
		} else if ((manhattanDist(x) + x.getCost()) > (manhattanDist(y) + y.getCost())) {
			return 1;
		} else {
			return 0;
		}
	}

	public int manhattanDist(Board board) {
		int count = 0;
		String[] boardState = new String[9];
		boardState[0] = board.topLeft.getValue();
		boardState[1] = board.topMid.getValue();
		boardState[2] = board.topRight.getValue();
		boardState[3] = board.midLeft.getValue();
		boardState[4] = board.midMid.getValue();
		boardState[5] = board.midRight.getValue();
		boardState[6] = board.botLeft.getValue();
		boardState[7] = board.botMid.getValue();
		boardState[8] = board.botRight.getValue();
    for (int i = 0; i < boardState.length; i++) {
      if (!boardState[i].equals("0")) {
        int num = Integer.parseInt(boardState[i]);
        int vert = Math.abs((num / 3) - (i / 3));
        int horz = Math.abs((num % 3) - (i % 3));
        count += vert;
        count += horz;
      }
    }
		return count;
	}
}
