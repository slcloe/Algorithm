import java.io.*;
import java.util.*;

public class Solution {

    static int T;
    static int n;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    static boolean isRange(int x, int y){
        if (0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }

    static void dfs(int day, int x, int y){
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
            if (!isRange(tx ,ty)) continue;
            if (visited[tx][ty]) continue;
            if (map[tx][ty] <= day) continue;
            dfs(day, tx, ty);
        }
    }

    static int search(int min, int max){
        int cheese = 1;
//        System.out.println(min + "-" + max);
        for (int i = min - 1; i <= max; i++) {
            int tmp = 0;
            visited = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visited[j][k] && map[j][k] > i)
                    {
                        visited[j][k] = true;
                        dfs(i, j, k);
                        tmp++;
                    }
                }
            }
            //System.out.println("tmp  : " + tmp + " " + i);
            cheese = (tmp > cheese) ? tmp : cheese;
        }
        return cheese;
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());



        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            int max = 1, min = 100;
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = (map[i][j] > max) ? map[i][j] : max;
                    min = (map[i][j] > min) ? min : map[i][j];
                }
            }
            sb.append("#").append(tc).append(" ").append(search(min, max)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
/*
1
3
3 4 5
3 4 5
3 4 5
 */