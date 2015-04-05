public class TurretDefense  {
    public int firstMiss (int[] xs, int[] ys, int[] times) {
		int currentPos = -1;
		for (int i = 0; i < xs.length; i++) {
			if (currentPos == -1) {
				if ((Math.abs(xs[i]) + Math.abs(ys[i])) > times[i]) {
					return i;
				}
			} else {
				if ((Math.abs(xs[currentPos] - xs[i]) + Math.abs(ys[currentPos]
						- ys[i])) > (times[i] - times[currentPos])) {

					return i;
				}
			}
			currentPos = i;
		}
		return -1;
	}
    
    }
