import java.io.*;
import java.util.*;
import java.math.*;

public class Money implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		int tests = nextInt();
		for(int test = 0; test < tests; test++)
			solveOne();
	}
	
	class Line implements Comparable<Line> {
		long a, b;

		public Line(long a, long b) {
			this.a = a;
			this.b = b;
		}
		
		public long eval(long x) {
			return a * x + b;
		}
		
		public int compareTo(Line other) {
			if(a != other.a)
				return Long.compare(other.a, a);
			return Long.compare(b, other.b);
		}
		
		public double intersect(Line other) {
			return ((double) (other.b - b)) / (a - other.a);
		}
	}
	
	class LinesContainer {
		List<Line> builded, cache, tmp;
		
		public LinesContainer() {
			builded = new ArrayList<Line>();
			cache = new ArrayList<Line>();
		}
		
		void add(Line line) {
			cache.add(line);
			if(cache.size() >= 256) {
				rebuild();
			}
		}
		
		private void rebuild() {
			builded.addAll(cache);
			cache.clear();
			Collections.sort(builded);
			List<Line> newBuilded = new ArrayList<Line>();
			for(Line line : builded) {
				// remove same
				while(newBuilded.size() > 0 && newBuilded.get(newBuilded.size() - 1).a == line.a) {
					newBuilded.remove(newBuilded.size() - 1);
				}
				
				while(newBuilded.size() >= 2 && newBuilded.get(newBuilded.size() - 2).intersect(newBuilded.get(newBuilded.size() - 1)) < newBuilded.get(newBuilded.size() - 2).intersect(line)) {
					newBuilded.remove(newBuilded.size() - 1);
				}
				
				newBuilded.add(line);
			}
			builded = newBuilded;
		}

		long get(long x) {
			long result = getBuilded(x);
			for(Line line : cache) {
				result = Math.max(result, line.eval(x));
			}
			return result;
		}

		private long getBuilded(long x) {
			long result = Long.MIN_VALUE;
			for(Line line : builded) {
				result = Math.max(result, line.eval(x));
			}
			return result;
		}
	}

	private void solveOne() throws IOException {
		int n = nextInt();
		long t = nextLong();
		long[] arr = new long[n];
		for(int i = 0; i < n; i++)
			arr[i] = nextLong();
		long[] d = new long[n];
		long before = 0;
		LinesContainer lines = new LinesContainer();
		lines.add(new Line(0, 0));
		for(int i = 0; i < n; i++) {
			before += arr[i];
			d[i] = lines.get(i + 1) + before * (i + 1) - t;
			lines.add(new Line(-before, d[i]));
		}
	
		out.println(d[n - 1]);
	}

	public static void main(String[] args) {
		new Money().run();
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

			rnd = new Random(42);

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
