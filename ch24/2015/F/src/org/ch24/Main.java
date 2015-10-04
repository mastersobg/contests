package org.ch24;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @author Ivan Gorbachev
 */
public class Main {

    final int EMPTY_ID = -1;
    final static JSONParser parser = new JSONParser();
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    Field field;
    Random rnd = new Random();
    Point prevStepPoint;
    int rounds = 0;
    int totalScore = 0;
    int placeSum;
    int deletations = 0;

    public static void main(String []args) {
        new Main().start();
    }

    static class Field {

        int size = 6;
        int teamId;
        Map<Point, Integer> field = new HashMap<>();

        public Field(int teamId) {
            this.teamId = teamId;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public String toString() {
            return "[x = " + x + ", y = " + y + "]";
        }
    }

    static class Diff {
        int distance;
        int x;
        int y;
        int team;
        boolean added;

        @Override
        public String toString() {
            return x + " " + y + " " + team + " " + added;
        }
    }

    class TeamResult implements Comparable<TeamResult> {
        int team;
        int score;
        int deletations;

        @Override
        public String toString() {
            return "[team = " + team +
                    " score = " + score +
                    " deletations = " + deletations +
                    "]";
        }

        @Override
        public int compareTo(TeamResult o) {
            if (o.score == score) {
                if (team == field.teamId)
                    return -1;
                if (o.team == field.teamId)
                    return 1;
                return team - o.team;
            }
            return o.score - score;
        }
    }

    private void start() {
        while (true) {
            try {
                initNetwork();
                play();
                closeNetwork();
            } catch (Exception e) {
                try {
                    closeNetwork();
                } catch (Exception ignore) {}
                dbg(e.toString());
            }
        }
    }

    private void play() {
        while (true) {
            int myId = parseTeamId(readSocket());
            dbg("my id = " + myId);
            field = new Field(myId);
            while (true) {
                try {
                    makeStep();
                } catch (Exception e) {
                    dbg("Exception during doing step: ", e.toString());
                }
                String s = readSocket();
                List<Diff> diffs;
                try {
                    diffs = parseDiff(s);
                    apply(diffs);
                } catch (Exception e) {
                    List<TeamResult> results = parseTeamResults(s);
                    Collections.sort(results);
                    int idx = 0;
                    for (TeamResult tr : results) {
                        ++idx;
                        if (tr.team == field.teamId) {
                            ++rounds;
                            totalScore += tr.score;
                            placeSum += idx;
                            deletations += tr.deletations;
                            dbg("Our score = ", tr.score + " our place = " + idx, " average score = " + (totalScore / (rounds + 0.0)));
                            dbg("Average place = " + (placeSum / (rounds + 0.0)));
                            dbg("Average deletations = ", (deletations / (rounds + 0.0)));
//                            printMap();
                        }
                    }
                    dbg("result", results);
                    dbg("----------------------------------------------------------------------------------------");
                    break;
                }
            }
        }

    }

    void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                sb.append(format(map[i][j])).append(" ");
            }
            sb.append("\n");
        }
        dbg("map", sb.toString());
    }

    String format(int n) {
        if (n < 0)
            return n + "";
        if (n < 10)
            return "0" + n;
        return n + "";
    }

    int [][]map;

    private void makeStep() {
        map = getMap();
        Point p = new Point(0, 0);
//        if (maxHor(map, p) > 0) {
//            writeSocket(makeCoordsString(p.x - field.size, p.y - field.size));
//            return ;
//        }
        if (algo(map, p) > 0) {
            writeSocket(makeCoordsString(p.x - field.size, p.y - field.size));
            return ;
        }

        dbg("choosing randomly");
        for (int i = 0; i < 100; ++i) {
            int x = rnd.nextInt(field.size * 2 + 1) - field.size;
            int y = rnd.nextInt(field.size * 2 + 1) - field.size;
            p = new Point(x, y);
            if (!field.field.containsKey(p)) {
                writeSocket(makeCoordsString(x, y));
                return;
            }
        }
        dbg("-------------------------------");
    }

    int algo(int [][]map, Point p) {
        int x1 = 0, y1 = 1;
        while (x1 < map.length && y1 < map.length) {
            if (map[x1][y1] != field.teamId) {
                p.x = x1;
                p.y = y1;
                return 1;
            }
            ++x1;
            ++y1;
        }


        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map.length; ++j) {
                if (map[i][j] != EMPTY_ID) {
                    p.x = i;
                    p.y = j;
                }
                if (map[i][j] != EMPTY_ID && map[i][j] != field.teamId) {
                    p.x = i;
                    p.y = j;
                    return 1;
                }
            }
        return 1;
    }

    int doDiagonally(int [][]map, Point p) {
        int x1 = field.size;
        int y1 = field.size;
        int x2 = x1, y2 = y1;
        for (int i = 0; i < 100000; ++i) {
            if ((i & 1) == 1) {
                while (x1 >= -field.size && y1 <= field.size) {
                    if (map[x1][y1]!= field.teamId) {
                        if (prevStepPoint != null && prevStepPoint.x == x1 - field.size && prevStepPoint.y == y1 - field.size) {
                            --x1;
                            ++y1;
                            continue;
                        }
                        p.x = x1;
                        p.y = y1;
                        return 1;
                    }
                    --x1;
                    ++y1;
                }
            } else {
                while (x2 <= field.size && y2 >= -field.size) {
                    if (map[x2][y2] != field.teamId) {
                        if (prevStepPoint != null && prevStepPoint.x == x2 - field.size && prevStepPoint.y == y2 - field.size) {
                            ++x2;
                            --y2;
                            continue;
                        }
                        p.x = x2;
                        p.y = y2;
                        return 1;
                    }
                    ++x2;
                    --y2;
                }
            }
        }
        dbg("dbg", x1, y1, x2, y2);
        return -1;
    }

    int doStepVertically(int [][]map, Point p) {
        int col = field.size + 6;
        StringBuilder sb = new StringBuilder();
        int maxCnt = 0;
        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < map.length; ++i) {
            if (map[i][col] == field.teamId) {
                ++cnt;
            } else {
                maxCnt = Math.max(maxCnt, cnt);
                cnt = 0;
            }
            sb.append(map[i][col]).append("   ");
        }
//        dbg("column = " + sb.toString(), "cnt = " + maxCnt);
//        dbg("currentAnswer = ", maxCnt);
        for (int i = 0; i < map.length; ++i)
            if (map[i][col] != field.teamId) {
                if (prevStepPoint != null && prevStepPoint.x == i - field.size && prevStepPoint.y == col - field.size) {
                    continue;
                }
//                dbg("prev = ", prevStepPoint == null ? "" : prevStepPoint.toString());
                p.x = i;
                p.y = col;
                return 1;
            }
        return -1;
    }

    int doStepHorizontally(int [][]map, Point p) {
        int row = field.size - 6;
        int start = field.size - 6;
        for (int i = start; i >= 0; --i) {
            if (map[row][i] != field.teamId) {
                if (prevStepPoint != null && prevStepPoint.x == i - field.size && prevStepPoint.y == row - field.size)
                    continue;
                p.x = row;
                p.y = i;
                return 1;
            }
        }
        for (int i = start; i < field.size; ++i) {
            if (map[row][i] != field.teamId) {
                if (prevStepPoint != null && prevStepPoint.x == i - field.size && prevStepPoint.y == row - field.size)
                    continue;
                p.x = row;
                p.y = i;
                return 1;
            }
        }
        return -1;
    }

    int maxHor(int [][]map, Point result) {
        int best = -1;
        for (int row = 0; row < map.length; ++row) {
            for (int i = 0; i < map[row].length;) {
                if (map[row][i] == field.teamId) {
                    int j = i;
                    while (j < map[row].length && map[row][j] == field.teamId) {
                        ++j;
                    }
                    if (j - i > best) {
                        if (i > 0) {
                            result.x = row;
                            result.y = i - 1;
                        }
                        if (j < map[row].length) {
                            result.x = row;
                            result.y = j;
                        }
                    }

                    i = j;
                } else
                    ++i;
            }
        }
        return best;
    }

    int [][]getMap() {
        int [][]map = new int[field.size * 2 + 1][field.size * 2 + 1];
        for (int i = 0; i < map.length; ++i)
            Arrays.fill(map[i], EMPTY_ID);
        for (Map.Entry<Point, Integer> entry : field.field.entrySet()) {
            map[entry.getKey().x + field.size][entry.getKey().y + field.size] = entry.getValue();
        }
        return map;
    }

    private void apply(List<Diff> diffs) {
        for (Diff d : diffs) {
            Point p = new Point(d.x, d.y);
            if (d.added) {
                field.field.put(p, d.team);
            } else {
                field.field.remove(p);
            }
            field.size = d.distance;
        }
    }

    String makeCoordsString(int x, int y) {
        prevStepPoint = new Point(x, y);
        return "{ \"x\": " + x + ", \"y\": " + y + "}";
    }

    static int parseTeamId(String s) {
        try {
            JSONObject o = (JSONObject) parser.parse(s);
            return new Integer(o.get("id").toString());
        } catch (ParseException e) {
            throw new RuntimeException(s, e);
        }
    }

    static List<Diff> parseDiff(String s) {
        s = s.replaceAll("\"f\":a", "\"f\":1").replaceAll("\"f\":r", "\"f\":0");
        try {
            JSONObject json = (JSONObject) parser.parse(s);
            int distance = new Integer(json.get("distance").toString());
            JSONArray array = (JSONArray) json.get("moves");
            List<Diff> list = new ArrayList<>(array.size());
            for (Object o : array) {
                JSONObject jo = (JSONObject) o;
                Diff d = new Diff();
                d.x = new Integer(jo.get("x").toString());
                d.y = new Integer(jo.get("y").toString());
                d.team = new Integer(jo.get("t").toString());
                d.added = "a".equals(jo.get("f").toString());
                d.distance = distance;
                list.add(d);
            }
            return list;
        } catch (ParseException e) {
            throw new RuntimeException(s, e);
        }
    }

    List<TeamResult> parseTeamResults(String s) {
        try {
            JSONArray array = (JSONArray) parser.parse(s);
            List<TeamResult> ret = new ArrayList<>(array.size());
            for (Object o : array) {
                JSONObject jo = (JSONObject) o;
                TeamResult tr = new TeamResult();
                tr.team = new Integer(jo.get("t").toString());
                tr.score = new Integer(jo.get("r").toString());
                tr.deletations = new Integer(jo.get("o").toString());
                ret.add(tr);
            }
            return ret;
        } catch (ParseException e) {
            throw new RuntimeException(s, e);
        }
    }

    void writeSocket(String s) {
//        dbg("send = ", s);
        writer.write(s);
        writer.flush();
    }

    String readSocket() {
        try {
            String result = reader.readLine();
//            dbg("read = ", result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeNetwork() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initNetwork() {
        while (true) {
            try {
                socket = new Socket("10.0.107.1", 16600);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                dbg("Connected!");
                break;
            } catch (IOException e) {
                dbg("Couldn't connect", e.toString());
            }
        }
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

    static boolean DEBUG = true;

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
            System.out.print(printLine + " ");
        }
        System.out.println();
        System.out.flush();
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
