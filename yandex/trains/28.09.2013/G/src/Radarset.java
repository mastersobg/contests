import java.io.*;
import java.util.*;
import java.math.*;
import org.omg.CORBA.MARSHAL;

public class Radarset implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final double eps = 1e-9;

	class Segment {
		double start, end;

		public Segment(double start, double end) {
			if (start > end + eps) {
				throw new AssertionError();
			}

			this.start = start;
			this.end = end;
		}

		public boolean intersect(Segment other) {
      double left = Math.max(start, other.start);
      double end = Math.min(this.end, other.end);
      return left < end && !(Math.abs(left - end) < eps);
/*			if (start - eps < other.start && other.start < end + eps) {
				return true;
			}
			if (start - eps < other.end && other.end < end + eps) {
				return true;
			}
			if (other.start - eps < start && start < other.end + eps) {
				return true;
			}
			if (other.start - eps < end && end < other.end + eps) {
				return true;
			}
			if (other.start - eps < start && end < other.end + eps) {
				return true;
			}
			if (start - eps < other.start && other.end < end + eps) {
				return true;
			}

			return false;
      */
		}

		public String toString() {
			return start + " " + end;
		}

	}

	private void solve() throws IOException {
		double alpha = nextDouble(), phi = nextDouble() + Math.PI * 0.5;
		int result = 0, n = nextInt();

		// out.println(alpha + " " + phi);

		List<Segment> baseSegments = getSegments(phi - alpha * 0.5 - eps, phi
				+ alpha * 0.5 + eps);

		double len = 0;
		for (Segment s : baseSegments) {
			len += s.end - s.start;
			// out.println(s.end + " " + s.start);
		}
		// out.println(alpha + " " + len + " " + Math.abs(len - alpha));
		// out.flush();
		if (Math.abs(len - alpha) > 3 * eps)
			throw new AssertionError();

		// out.println((phi - alpha) + " " + (phi + alpha));
		//
		// for (Segment s : baseSegments) {
		// out.println(s);
		// }

		for (int i = 0; i < n; i++) {
			double x0 = nextDouble(), y0 = nextDouble(), x1 = nextDouble(), y1 = nextDouble();
			boolean ok = false;

			if (x0 - eps < 0 && 0 < x1 + eps && y0 - eps < 0 && 0 < y1 + eps)
				ok = true;

			if (x1 > 0 && y1 > 0) {
				double start = getAngle(Math.max(y0, 0), x1);
				double end = getAngle(y1, Math.max(x0, 0));
				ok |= checkIntersection(baseSegments, new Segment(start, end));
				// out.println(start + " " + end);
			}
			if (x0 < 0 && y1 > 0) {
				double start = getAngle(y1, Math.min(0, x1));
				double end = getAngle(Math.max(0, y0), x0);
				ok |= checkIntersection(baseSegments, new Segment(start, end));
			}
			if (x0 < 0 && y0 < 0) {
				double start = getAngle(Math.min(y1, 0), x0);
				double end = getAngle(y0, Math.max(x1, 0));
				ok |= checkIntersection(baseSegments, new Segment(start, end));
			}
			if (x1 > 0 && y0 < 0) {
				double start = getAngle(y0, Math.max(0, x0));
				double end = getAngle(Math.min(0, y1), x1);
				if (end < eps)
					end = 2 * Math.PI;
				ok |= checkIntersection(baseSegments, new Segment(start, end));
			}

			if (ok) {
				++result;
			} else {
				// out.println(x0 + " " + y0 + " " + x1 + " " + y1);
			}
		}

		out.println(result);
	}

	private boolean checkIntersection(List<Segment> baseSegments,
			Segment segment) {
		boolean result = false;
		for (Segment baseSegment : baseSegments) {
			result |= baseSegment.intersect(segment);
		}
		return result;
	}

	private double getAngle(double y, double x) {
		// out.println("Get angle" + " " + y + " " + x);
		// out.flush();
		double angle = Math.atan2(y, x);
		if (angle < 0)
			angle += 2 * Math.PI;
		return angle;
	}

	private List<Segment> getSegments(double start, double end) {
		if (end > 2 * Math.PI) {
			start -= 2 * Math.PI;
			end -= 2 * Math.PI;
		}

		// out.println("Wtf: " + start + " " + end);

		List<Segment> segments = new ArrayList<Radarset.Segment>();

		if (start < 0) {
			if (end < 0) {
				throw new AssertionError();
			}
			segments.add(new Segment(start + 2 * Math.PI, 2 * Math.PI));
			segments.add(new Segment(0, end));
		} else if (start < end + eps) {
			segments.add(new Segment(start, end));
		} else {
			segments.add(new Segment(start, 2 * Math.PI));
			segments.add(new Segment(0, end));
		}

		return segments;
	}

	public static void main(String[] args) {
		// final boolean oldChecker = false;
		//
		// if (oldChecker)
		// new Thread(null, new Template(), "yarrr", 1 << 24).start();
		// else
		new Radarset().run();
	}

	public void run() {
		try {
			final String className = this.getClass().getName().toLowerCase();

			try {
				in = new BufferedReader(new FileReader(className + ".in"));
				out = new PrintWriter(new FileWriter(className + ".out"));
				// in = new BufferedReader(new FileReader("input.txt"));
				// out = new PrintWriter(new FileWriter("output.txt"));
			} catch (FileNotFoundException e) {
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

