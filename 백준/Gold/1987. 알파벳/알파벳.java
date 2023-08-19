import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int max = 0;

    static void dfs(int x, int y, int depth, int visitedAlpha){
//        System.out.println(x + " " + y + " pos");
        max = (max > depth) ? max : depth;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
            if (visited[tx][ty]) continue;
            int alpha = 1 << (board[tx][ty] - 'A');
            if ((visitedAlpha & alpha) != 0) continue;
//            System.out.println("alpha : " + alpha);
            visited[tx][ty] = true;
            dfs(tx, ty, depth + 1, visitedAlpha | alpha);
            visited[tx][ty] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }
        visited[0][0] = true;
        dfs(0, 0, 1, 1 << (board[0][0] - 'A'));
        System.out.println(max);

    }
}