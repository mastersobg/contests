import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "";

	static class DB {

		List<Problem> problems;
		List<Team> teams;
		List<Member> members;
		List<Submission> submissions;
		List<Message> messages;

		DB() {
			problems = Problem.load();
			teams = Team.load();
			members = Member.load();
			submissions = Submission.load();
			messages = Message.load();
		}
	}

	static class Problem {
		int year;
		char letter;
		String author;

		public Problem(String s) {
			StringTokenizer st = new StringTokenizer(s);
			year = Integer.valueOf(st.nextToken());
			letter = st.nextToken().charAt(0);
			author = st.nextToken();
		}

		static List<Problem> load() {
			BufferedReader br;
			List<Problem> ret = new ArrayList<Solution.Problem>();
			try {

				br = new BufferedReader(new FileReader("d-problems.txt"));
				String s;
				while ((s = br.readLine()) != null)
					ret.add(new Problem(s));
				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}
	}

	static class Team {
		String tis;
		String name;
		String country;
		String university;
		String division;

		public Team(String s) {
			StringTokenizer st = new StringTokenizer(s);
			tis = st.nextToken();
			name = st.nextToken();
			country = st.nextToken();
			university = st.nextToken();
			division = st.nextToken();
		}

		static List<Team> load() {
			BufferedReader br;
			List<Team> ret = new ArrayList<Solution.Team>();
			try {

				br = new BufferedReader(new FileReader("d-teams.txt"));
				String s;
				while ((s = br.readLine()) != null)
					ret.add(new Team(s));
				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}
	}

	static class Member {
		String tis;
		String name;
		int age;

		public Member(String s) {
			StringTokenizer st = new StringTokenizer(s);
			tis = st.nextToken();
			name = st.nextToken();
			age = Integer.valueOf(st.nextToken());
		}

		static List<Member> load() {
			BufferedReader br;
			List<Member> ret = new ArrayList<Solution.Member>();
			try {

				br = new BufferedReader(new FileReader("d-members.txt"));
				String s;
				while ((s = br.readLine()) != null)
					ret.add(new Member(s));
				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}
	}

	static class Submission {
		String date;
		String time;
		String tis;
		String problem;
		long size;
		String hash;

		public Submission(String s) {
			StringTokenizer st = new StringTokenizer(s);
			date = st.nextToken();
			time = st.nextToken();
			tis = st.nextToken();
			problem = st.nextToken();
			size = Long.valueOf(st.nextToken());
			hash = st.nextToken();
		}

		static List<Submission> load() {
			BufferedReader br;
			List<Submission> ret = new ArrayList<Solution.Submission>();
			try {

				br = new BufferedReader(new FileReader("d-submissions.txt"));
				String s;
				while ((s = br.readLine()) != null)
					ret.add(new Submission(s));
				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}
	}

	static class Message {
		String tis;
		long time;
		String problem;
		boolean result;

		public Message(String s) {
			StringTokenizer st = new StringTokenizer(s);
			tis = st.nextToken();
			time = Long.valueOf(st.nextToken());
			problem = st.nextToken();
			result = st.nextToken().equals("OK");
		}

		static List<Message> load() {
			BufferedReader br;
			List<Message> ret = new ArrayList<Solution.Message>();
			try {

				br = new BufferedReader(new FileReader("d-messages.txt"));
				String s;
				while ((s = br.readLine()) != null)
					ret.add(new Message(s));
				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}
	}

	static class Easy {

		DB db;
		PrintWriter out;

		public Easy(DB db) {
			this.db = db;
		}

		void solve() {
			try {
				out = new PrintWriter("easy.out");
				f1();
				f2();
				f3();
				f4();
				f5();
				f6();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void f6() {
			final class Pair implements Comparable<Pair> {
				int year;
				char task;
				int authors;

				public Pair(int year, char task, int authors) {
					super();
					this.year = year;
					this.task = task;
					this.authors = authors;
				}

				@Override
				public int hashCode() {
					final int prime = 31;
					int result = 1;
					result = prime * result + task;
					result = prime * result + year;
					return result;
				}

				@Override
				public boolean equals(Object obj) {
					if (this == obj)
						return true;
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Pair other = (Pair) obj;
					if (task != other.task)
						return false;
					if (year != other.year)
						return false;
					return true;
				}

				@Override
				public int compareTo(Pair o) {
					if (year != o.year)
						return year - o.year;
					return task - o.task;
				}

			}
			HashMap<Pair, Integer> map = new HashMap<Pair, Integer>();
			for (Problem p : db.problems) {
				Pair pair = new Pair(p.year, p.letter, 0);
				if (map.containsKey(pair))
					map.put(pair, map.get(pair) + 1);
				else
					map.put(pair, 1);
			}
			List<Pair> list = new ArrayList<Pair>();
			for (Map.Entry<Pair, Integer> e : map.entrySet()) {
				e.getKey().authors = e.getValue();
				list.add(e.getKey());
			}
			Collections.sort(list);
			out.println(list.size());
			for (Pair p : list)
				out.println(p.year + " " + p.task + " " + p.authors);
		}

		private void f5() {
			HashMap<String, String> teams = new HashMap<String, String>();
			for (Team t : db.teams)
				teams.put(t.tis, t.division);
			int open = 0, ocnt = 0;
			int sec = 0, scnt = 0;
			for (Member m : db.members) {
				if (m.age > 0) {
					String type = teams.get(m.tis);
					if (type.equals("open")) {
						open += m.age;
						ocnt++;
					} else {
						sec += m.age;
						scnt++;
					}
				}
			}
			out.println(2);
			out.printf("%s %.1f\n", "open", (double) open / (double) ocnt);
			out.printf("%s %.1f\n", "secondary", (double) sec / (double) scnt);
		}

		private void f4() {
			out.println(1);
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			List<Member> ms = db.members;
			for (Member m : ms) {
				String tis = m.tis;
				if (map.containsKey(tis))
					map.put(tis, map.get(tis) + 1);
				else
					map.put(tis, 1);
			}
			double all = 0;
			for (Map.Entry<String, Integer> e : map.entrySet())
				all += e.getValue();
			out.printf("%.2f\n", all / map.size());
		}

		private void f3() {
			List<Team> ts = db.teams;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (Team t : ts) {
				if (map.containsKey(t.university))
					map.put(t.university, map.get(t.university) + 1);
				else
					map.put(t.university, 1);
			}
			final class Pair implements Comparable<Pair> {
				String s;
				int cnt;

				Pair(String s, int cnt) {
					this.s = s;
					this.cnt = cnt;
				}

				@Override
				public int compareTo(Pair o) {
					if (cnt != o.cnt)
						return o.cnt - cnt;
					return s.compareTo(o.s);
				}
			}
			List<Pair> list = new ArrayList<Pair>();
			for (Map.Entry<String, Integer> e : map.entrySet())
				if (e.getValue() >= 10)
					list.add(new Pair(e.getKey(), e.getValue()));
			Collections.sort(list);
			out.println(list.size());
			for (Pair p : list)
				out.println(p.cnt + " " + p.s);
		}

		private void f2() {
			List<Team> ts = db.teams;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (Team t : ts) {
				if (map.containsKey(t.country))
					map.put(t.country, map.get(t.country) + 1);
				else
					map.put(t.country, 1);
			}
			final class Pair implements Comparable<Pair> {
				String s;
				int cnt;

				Pair(String s, int cnt) {
					this.s = s;
					this.cnt = cnt;
				}

				@Override
				public int compareTo(Pair o) {
					return s.compareTo(o.s);
				}
			}
			List<Pair> list = new ArrayList<Pair>();
			for (Map.Entry<String, Integer> e : map.entrySet())
				list.add(new Pair(e.getKey(), e.getValue()));
			Collections.sort(list);
			out.println(list.size());
			for (Pair p : list)
				out.println(p.s + " " + p.cnt);
		}

		private void f1() {
			int years[] = new int[1000000];
			List<Submission> ss = db.submissions;
			for (Submission s : ss) {
				int year = Integer.valueOf(s.date.split("-")[0]);
				years[year]++;
			}
			out.println(3);
			for (int i = 0; i < years.length; ++i)
				if (years[i] > 0)
					out.println(i + " " + years[i]);
		}
	}

	void solve() throws IOException {
		DB db = new DB();
		Easy easy = new Easy(db);
		easy.solve();
	}

	public Solution() throws IOException {
		solve();
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
