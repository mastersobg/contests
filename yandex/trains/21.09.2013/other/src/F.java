import java.io.*;
import java.util.*;
import java.math.*;

public class F implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		while(solveOne()) ;
	}

	private boolean solveOne() throws IOException {
		int n = nextInt();
		if(n == 0) return false;
		String[] languages = new String[n];
		for(int i = 0; i < n; i++) languages[i] = nextToken().toLowerCase();
		int result = 0;
		for(char c = 'a'; c <= 'z'; c++) {
			List<String> curLanguages = new ArrayList<String>();
			for(String language : languages) {
				if(language.charAt(0) == c)
					curLanguages.add(language.substring(1));
			}
			result += solveOne(curLanguages);
		}
		out.println(result);
		return true;
	}
	
	class Edge {
		int to, capacity, flow;
		Edge rev;

		public Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
		
		
	}

	private int solveOne(List<String> curLanguages) {
		int vertexes = curLanguages.size() + 26 + 2;
		int from = vertexes - 2, to = vertexes - 1;
		List<Edge>[] edges = new ArrayList[vertexes];
		for(int i = 0; i < vertexes; i++)
			edges[i] = new ArrayList<Edge>();
		int currentId = 0;
		for(int i = 0; i < 26; i++) {
			addFlowEdge(edges, curLanguages.size() + i, to, 1);
		}
		for(String language : curLanguages) {
			Set<Character> already = new HashSet<Character>();
			for(char c : language.toCharArray()) {
				if(!already.contains(c)) {
					//out.println(currentId + " " + (curLanguages.size() + c - 'a'));
					already.add(c);
					addFlowEdge(edges, currentId, curLanguages.size() + c - 'a', 1);
				}
			}
			addFlowEdge(edges, from, currentId, 1);
			++currentId;
		}
		return maxFlow(vertexes, from, to, edges);
	}

	private int maxFlow(int vertexes, int from, int to, List<Edge>[] edges) {
		int result = 0;
		int[] cameFrom = new int[vertexes];
		Edge[] edgeUsed = new Edge[vertexes];
		boolean[] used = new boolean[vertexes];
		Queue<Integer> q = new ArrayDeque<Integer>();
		while(true) {
			Arrays.fill(used, false);
			q.add(from);
			used[from] = true;
			while(q.size() > 0) {
				int u = q.poll();
				for(Edge e : edges[u]) {
					if(!used[e.to] && e.capacity > e.flow) {
						used[e.to] = true;
						q.add(e.to);
						cameFrom[e.to] = u;
						edgeUsed[e.to] = e;
					}
				}
			}
			if(!used[to])
				break;
			++result;
			int ptr = to;
			while(ptr != from) {
				Edge e = edgeUsed[ptr];
				e.flow++;
				e.rev.flow--;
				ptr = cameFrom[ptr];
			}
		}
		return result;
	}

	private void addFlowEdge(List<Edge>[] edges, int from, int to, int capacity) {
		Edge a = new Edge(to, 1);
		Edge b = new Edge(from, 0);
		a.rev = b;
		b.rev = a;
		edges[from].add(a);
		edges[to].add(b);
	}

	public static void main(String[] args) {
		new F().run();
	}

	public void run() {
		try {
			in = new BufferedReader(new FileReader("language.in"));
			out = new PrintWriter(new FileWriter("language.out"));
			//in = new BufferedReader(new InputStreamReader(System.in));
			//out = new PrintWriter(System.out);

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
