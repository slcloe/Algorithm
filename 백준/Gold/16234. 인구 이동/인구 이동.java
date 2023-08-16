import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Main {

    static int N, L, R;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayDeque<ArrayDeque<Point>> queue = new ArrayDeque<>();

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int move(){
        int cnt = 0;

        while (true) {
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    ArrayDeque<Point> set1 = new ArrayDeque<>();
                    dfs(i, j, set1);
                    if (set1.size() != 1)
                        queue.offer(set1);
                }
            }
            if (queue.isEmpty()) break;
            calPopulation();
            cnt++;
        }
        return cnt;
    }

    static void calPopulation(){

        while(!queue.isEmpty()){
            ArrayDeque<Point> set = queue.poll();
            int sum = 0;
            for(Point pt : set){
                sum += arr[pt.x][pt.y];
            }
            sum /= set.size();
            for(Point pt : set){
                arr[pt.x][pt.y] = sum;
            }
        }
    }

    static void dfs(int x, int y, ArrayDeque<Point> set){
        visited[x][y] = true;
        set.offer(new Point(x, y));
        for (int i = 0; i < 4; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;

            if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
            if (visited[tx][ty]) continue;
            if (L <= abs(arr[x][y] - arr[tx][ty]) && abs(arr[x][y] - arr[tx][ty]) <= R)
            {
                dfs(tx, ty, set);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());

    }


}