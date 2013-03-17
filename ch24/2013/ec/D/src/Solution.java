import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.text.html.MinimalHTMLWriter;

import org.w3c.dom.ls.LSInput;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	boolean[][] loadMap(File f) throws IOException {
		BufferedImage image = ImageIO.read(f);
		int h = image.getHeight();
		int w = image.getWidth();
		boolean[][] map = new boolean[h][w];
		for (int j = 0; j < h; ++j) {
			for (int i = 0; i < w; ++i) {
				int color = image.getRGB(i, j);
				map[j][i] = color != -1;
			}
		}
		return map;
	}

	static class Coord {
		int minI, maxI, minJ, maxJ;

		void clear() {
			minI = minJ = Integer.MAX_VALUE;
			maxI = maxJ = Integer.MIN_VALUE;
		}

		void update(int x, int y) {
			minI = min(minI, x);
			maxI = max(maxI, x);
			minJ = min(minJ, y);
			maxJ = max(maxJ, y);
		}
	}

	Coord coord = new Coord();

	void dfs(int x, int y, boolean[][] map, boolean[][] was) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[0].length)
			return;
		if (was[x][y])
			return;
		coord.update(x, y);
		was[x][y] = true;
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				int nx = x + i;
				int ny = y + j;
				if (!(nx == x && ny == y) && map[nx][ny]) {
					dfs(nx, ny, map, was);
				}
			}
		}
	}

	boolean[][][] digits;

	boolean[][] copy(Coord coord, boolean[][] map) {
		int n = coord.maxI - coord.minI + 1;
		int m = coord.maxJ - coord.minJ + 1;
		boolean[][] ret = new boolean[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				ret[i][j] = map[i + coord.minI][j + coord.minJ];
			}
		return ret;
	}

	void loadSample() throws IOException {
		boolean[][] map = loadMap(new File("reference_digits.png"));
		int n = map.length;
		int m = map[0].length;
		boolean[][] was = new boolean[n][m];
		digits = new boolean[8][][];
		int d = 0;
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				if (map[i][j] && !was[i][j]) {
					coord.clear();
					dfs(i, j, map, was);
					digits[d] = copy(coord, map);
					// System.out.println(d + " " + digits[d].length + " "
					// + digits[d][0].length);
					// print(digits[d]);
					++d;
				}
			}
		}
	}

	ArrayList<Integer> getLayers(boolean[][] map) {
		int n = map.length;
		int m = map[0].length;
		int[] ret = new int[map[0].length];
		for (int j = 0; j < m; ++j) {
			int prev = -1;
			int cnt = 0;
			for (int i = 0; i < n; ++i) {
				if (map[i][j]) {
					if (prev == -1) {
						prev = i;
					}
				} else {
					if (prev != -1) {
						++cnt;
						prev = -1;
					}
				}
			}
			if (prev != -1)
				++cnt;
			ret[j] = cnt;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < ret.length; ++i) {
			if (ret[i] != ret[i - 1])
				list.add(ret[i - 1]);
		}
		list.add(ret[m - 1]);
		return list;
	}

	int compare(boolean[][] map1, boolean[][] map2) {
		ArrayList<Integer> l1 = getLayers(map1), l2 = getLayers(map2);
		if(l1.size() != l2.size())
			return Integer.MAX_VALUE;
		for (int i = 0; i < l1.size(); ++i) {
			if (l1.get(i) != l2.get(i))
				return Integer.MAX_VALUE;
		}
		return 0;
		// if (map1.length != map2.length || map1[0].length != map2[0].length)
		// return Integer.MAX_VALUE;
		// int ret = 0;
		// int n = min(map1.length, map2.length);
		// int m = min(map1[0].length, map2[0].length);
		// for (int i = 0; i < n; ++i) {
		// for (int j = 0; j < m; ++j) {
		// if (map1[i][j] != map2[i][j])
		// ++ret;
		// }
		// }
		// return ret;
	}

	void print(boolean[][] map, PrintStream out) {
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j)
				out.print(map[i][j] ? 1 : 0);
			out.println();
		}
		out.println();
		out.flush();
	}

	static class Ret {
		int x, y, digit;

	}

	boolean checkLine(boolean[] line) {
		for (int i = 0; i < line.length; ++i)
			if (line[i])
				return true;
		return false;
	}

	ArrayList<int[]> getBlocks(boolean[][] map) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		int prev = -1;
		for (int i = 0; i < map.length; ++i) {
			boolean v = checkLine(map[i]);
			if (v) {
				if (prev == -1) {
					prev = i;
				}
			} else {
				if (prev != -1) {
					int[] arr = new int[2];
					arr[0] = prev;
					arr[1] = i;
					list.add(arr);
					prev = -1;
				}
			}
		}
		return list;
	}

	void solve() throws IOException {
		loadSample();
		boolean[][] map = loadMap(new File("3.png"));
		int n = map.length;
		int m = map[0].length;
		ArrayList<int[]> blocks = getBlocks(map);
		boolean[][] was = new boolean[n][m];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int b = 0; b < blocks.size(); ++b) {
			list.clear();
			for (int j = 0; j < m; ++j) {
				for (int i = blocks.get(b)[0]; i <= blocks.get(b)[1]; ++i) {
					if (map[i][j] && !was[i][j]) {
						coord.clear();
						dfs(i, j, map, was);
						boolean[][] copy = copy(coord, map);
						// print(copy);
						int digit = getDigit(copy);
						list.add(digit);
					}
				}
			}
			if (!check(list)) {
				System.err.println(b + " failed");
			}
			for (int i = 0; i < list.size(); ++i) {
				out.print(list.get(i));
			}
			out.println();
		}
	}

	boolean check(ArrayList<Integer> list) {
		int a = 0;
		for (int i = 0; i < list.size() - 1; ++i) {
			a = (a + list.get(i)) % 8;
		}
		return a == list.get(list.size() - 1);
	}

	Scanner sc = new Scanner(System.in);
	
	int getDigit(boolean[][] map) {
		print(map, System.out);
		System.out.print("number = ");
		return sc.nextInt();
//		int ret = Integer.MAX_VALUE;
//		int digit = -1;
//		for (int i = 0; i < 8; ++i) {
//			print(map);
//			print(digits[i]);
//			int a = compare(map, digits[i]);
//			if (a < ret) {
//				ret = a;
//				digit = i;
//			}
//		}
//		return digit;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		// in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("D3.out");
		solve();
		in.close();
		out.close();
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
		new Solution();
	}
}
