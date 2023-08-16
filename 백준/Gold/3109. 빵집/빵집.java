import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] arr;
    static int dx[] = {-1, 0, 1};
    static int result;
    static boolean[][] visited;
    static boolean dfs(int x, int y){
        visited[x][y] = true;
//        System.out.println(x + " " + y);
        if (y == C - 1) {
            result++;
            return true;
        }
        for (int i = 0; i < dx.length; i++) {
            int tx = x + dx[i];
            int ty = y + 1;

            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
            if (visited[tx][ty]) continue;
            if (arr[tx][ty] == 'x') continue;
            if (dfs(tx, ty)) return true;
        }
//        visited[x][y] = false;
        return false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
//            for(boolean[] v1: visited){
//                System.out.println(Arrays.toString(v1));
//            }
//            System.out.println();
        }
        System.out.println(result);
    }
}