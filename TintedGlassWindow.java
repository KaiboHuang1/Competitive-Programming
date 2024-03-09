import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TintedGlassWindow {
    static class Line implements Comparable<Line> {
        int x, id;
        int y1, y2;
        int tint, type;

        public Line(int _x, int _y1, int _y2, int _tint, int _id, int _type) {
            x = _x;
            y1 = _y1;
            y2 = _y2;
            id = _id;
            tint = _tint;
            type = _type;
        }

        @Override
        public int compareTo(Line rht) {
            return (x == rht.x) ? Integer.compare(type, rht.type) : Integer.compare(x, rht.x);
        }
    }

    static class Point implements Comparable<Point> {
        int y, id;
        int tint, type;

        public Point(int _y, int _id, int _tint, int _type) {
            y = _y;
            id = _id;
            tint = _tint;
            type = _type;
        }

        @Override
        public int compareTo(Point rht) {
            return (y == rht.y) ? Integer.compare(type, rht.type) : Integer.compare(y, rht.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());

        List<Line> segments = new ArrayList<>();

        for (int i = 0, x1, y1, x2, y2, t, id = 0; i < N; i++) {
            String[] rectInfo = br.readLine().split(" ");
            x1 = Integer.parseInt(rectInfo[0]);
            y1 = Integer.parseInt(rectInfo[1]);
            x2 = Integer.parseInt(rectInfo[2]);
            y2 = Integer.parseInt(rectInfo[3]);
            t = Integer.parseInt(rectInfo[4]);

            segments.add(new Line(x1, y1, y2, t, id, 1));
            segments.add(new Line(x2, y1, y2, t, id, -1));
            id++;
        }

        Collections.sort(segments);

        List<Point> y_axis = new ArrayList<>();
        long ANS = 0;

        for (int i = 0; i < segments.size(); i++) {
            if (i > 0) {
                long delta_x = Math.abs(segments.get(i).x - segments.get(i - 1).x);
                long ctint = 0, delta_y = 0;

                for (int j = 0; j < y_axis.size(); j++) {
                    if (j > 0) {
                        delta_y = y_axis.get(j).y - y_axis.get(j - 1).y;
                        if (ctint >= T) {
                            ANS += delta_x * delta_y;
                        }

                        if (y_axis.get(j).type == 1) {
                            ctint += y_axis.get(j).tint;
                        } else {
                            ctint -= y_axis.get(j).tint;
                        }
                    } else {
                        ctint += y_axis.get(j).tint;
                    }
                }

                if (segments.get(i).type == 1) {
                    y_axis.add(new Point(segments.get(i).y1, segments.get(i).id, segments.get(i).tint, 1));
                    y_axis.add(new Point(segments.get(i).y2, segments.get(i).id, segments.get(i).tint, -1));
                    Collections.sort(y_axis);
                }

                if (segments.get(i).type == -1) {
                    for (int j = 0; j < y_axis.size(); j++) {
                        if (segments.get(i).id == y_axis.get(j).id) {
                            y_axis.remove(j);
                            j--;
                        }
                    }
                }
            } else {
                y_axis.add(new Point(segments.get(i).y1, segments.get(i).id, segments.get(i).tint, 1));
                y_axis.add(new Point(segments.get(i).y2, segments.get(i).id, segments.get(i).tint, -1));
                Collections.sort(y_axis);
            }
        }

        System.out.println(ANS);
    }
}
