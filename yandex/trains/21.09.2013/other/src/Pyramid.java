import java.io.*;
import java.util.*;
import java.math.*;

public class Pyramid implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;
	
	class P implements Comparable<P> {
		int[] colors;
		
		public P(int[] history) {
			colors = Arrays.copyOf(history, 6);
			
			{
				int[] current = Arrays.copyOf(history, 6);
				
				for(int i = 0; i <= 3; i++) {
					if(arraysCompare(current, colors) < 0) {
						colors = Arrays.copyOf(current, 6);
					}
					turn(current);
				}
			}
			
			{
				int[] current = turnLeft(history);
				
				for(int i = 0; i <= 3; i++) {
					if(arraysCompare(current, colors) < 0) {
						colors = Arrays.copyOf(current, 6);
					}
					turn(current);
				}
			}
			
			{
				int[] current = turnRight(history);
				
				for(int i = 0; i <= 3; i++) {
					if(arraysCompare(current, colors) < 0) {
						colors = Arrays.copyOf(current, 6);
					}
					turn(current);
				}
			}
			
			{
				int[] current = turnBottom(history);
				
				for(int i = 0; i <= 3; i++) {
					if(arraysCompare(current, colors) < 0) {
						colors = Arrays.copyOf(current, 6);
					}
					turn(current);
				}
			}
		}
		
		private int[] turnLeft(int[] arr) {
			return new int[]{arr[1], arr[5], arr[4], arr[2], arr[3], arr[0]};
		}
		
		private int[] turnRight(int[] arr) {
			return new int[]{arr[5], arr[0], arr[3], arr[4], arr[2], arr[1]};
		}
		
		private int[] turnBottom(int[] arr) {
			return new int[]{arr[4], arr[3], arr[2], arr[1], arr[0], arr[5]};
		}

		private void turn(int[] arr) {
			int t = arr[0];
			arr[0] = arr[1];
			arr[1] = arr[2];
			arr[2] = t;
			t = arr[5];
			arr[5] = arr[4];
			arr[4] = arr[3];
			arr[3] = t;
		}

		private int arraysCompare(int[] a, int[] b) {
			for(int i = 0; i < 6; i++) {
				if(a[i] != b[i]) {
					return Integer.compare(a[i], b[i]);
				}
			}
			return 0;
		}

		public int compareTo(P other) {
			for(int i = 0; i < 6; i++) {
				if(colors[i] != other.colors[i]) {
					return Integer.compare(colors[i], other.colors[i]);
				}
			}
			return 0;
		}

		public int getColors() {
			Set<Integer> used = new HashSet<Integer>();
			for(int color : colors)
				used.add(color);
			return used.size();
		}
	}

	private void solve() throws IOException {
		Set<P> pyramids = new TreeSet<P>();
		rec(0, 6, 0, new int[6], pyramids);
		int[] count = new int[7];
		for(P p : pyramids) {
			//out.println(Arrays.toString(p.colors));
			count[p.getColors()]++;
		}
		//out.println(Arrays.toString(count));
		long[][] C = new long[1500][7];
		C[0][0] = 1;
		for(int i = 1; i < 1500; i++) {
			for(int j = 0; j <= 6; j++) {
				long left = (j == 0) ? 0 : C[i - 1][j - 1];
				C[i][j] = C[i - 1][j] + left;
			}
		}
		int n = nextInt();
		for(int i = 0; i < n; i++) {
			int m = nextInt();
			long result = getResult(count, m, C);
			out.println("Case " + (i + 1) + ": " + result);
		}
		
	}

	private long getResult(int[] count, int m, long[][] C) {
		long result = 0;
		for(int i = 1; i <= Math.min(6, m); i++) {
			result += C[m][i] * count[i];
		}
		return result;
	}

	private void rec(int step, int limit, int last, int[] history, Set<P> pyramids) {
		if(step == limit) {
			pyramids.add(new P(history));
		} else {
			for(int i = 0; i < last + 1; i++) {
				history[step] = i;
				rec(step + 1, limit, Math.max(last, i + 1), history, pyramids);
			}
			
		}
	}

	public static void main(String[] args) {
		new Pyramid().run();
	}

	public void run() {
		try {
			final String className = this.getClass().getName().toLowerCase();
			try {
				in = new BufferedReader(new FileReader(className + ".in"));
				out = new PrintWriter(new FileWriter(className + ".out"));
			} catch(FileNotFoundException e) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			}

			rnd = new Random();

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = in.readLine();

			if (line == null)
				return null;

			st = new StringTokenizer(line);
		}

		return st.nextToken();
	}

	private int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	private long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	private double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
