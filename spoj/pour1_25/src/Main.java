import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        for (int t = ni(); t > 0; --t) {
            int a = ni();
            int b = ni();
            int c = ni();
            PriorityQueue<Vertex> pq = new PriorityQueue<Vertex> ();
            HashSet<Vertex> was = new HashSet<Vertex> ();
            HashMap<Vertex, Vertex> mapping = new HashMap<Vertex,Vertex> ();
            Vertex start = new Vertex(0, 0);
            start.d = 0;
            int ret = Integer.MAX_VALUE;
            for (pq.add(start); !pq.isEmpty(); ) {
                Vertex v = pq.poll();
                if (was.contains(v))
                    continue;
                was.add(v);

                for (int []edge : new int[][] {{a, v.b}, {v.a, b}, {0, v.b}, {v.a, 0}, {v.a - Math.min(b - v.b, v.a), v.b + Math.min(b - v.b, v.a)}, 
                {v.a + Math.min(a - v.a, v.b), v.b - Math.min(a - v.a, v.b)}}) {
                    Vertex o = getVertex(mapping, edge[0], edge[1]);
                    if (o.d > v.d + 1) {
                        o.d = v.d + 1;
                        pq.add(o);
                    }
                    if (o.a == c || o.b == c)
                        ret = min(ret, o.d);
                }
            }
            out.println(ret == Integer.MAX_VALUE ? -1 : ret);
        }
	}

    Vertex getVertex(HashMap<Vertex, Vertex> mapping, int a, int b) {
        Vertex v = new Vertex(a, b);
        Vertex o = mapping.get(v);
        if (o != null) {
            return o;
        }
        mapping.put(v, v);
        return v;
    }


    static class Vertex implements Comparable<Vertex> {
        int a, b, d;

        Vertex(int a, int b) {
            this.a = a;
            this.b = b;
            this.d = Integer.MAX_VALUE;
        }

        @Override
        public int hashCode() {
            return a * 31 + b;
        }

        @Override
        public boolean equals(Object o) {
            Vertex v = (Vertex) o;
            return v.a == a && v.b == b;
        }

        @Override
        public int compareTo(Vertex o) {
            return d - o.d;
        }
    }
	
	public Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
        out.flush();
        // runtime error if trying to close on spoj
		// in.close();
		// out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
			DEBUG = true;
		}
		new Main();
	}

	class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }
        long time() {
            return System.currentTimeMillis() - time;
        }
        void print() {
            print("Time spent = ");
        } 

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[]) 
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }
}
