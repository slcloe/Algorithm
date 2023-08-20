import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {

    static char[][] arr;
    static int[][] dp;
    static int N, M;

    static class Maze{
        int depth;
        int x, y;

        Maze(int x, int y, int depth){
            this.x = x;
            this.y=  y;
            this.depth = depth;
        }
    }

    static void bfs(){
        ArrayDeque<Maze> queue = new ArrayDeque<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        queue.offer(new Maze(0, 0, 1));
        while (!queue.isEmpty()){
//            System.out.println("dd");
            Maze maze = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tx = maze.x + dx[i];
                int ty = maze.y + dy[i];
                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (arr[tx][ty] == '0') continue;
                if (dp[tx][ty] != 0 && dp[tx][ty] <= maze.depth + 1) continue;
                dp[tx][ty] = maze.depth + 1;
                queue.offer(new Maze(tx, ty, maze.depth + 1));
            }



        }


    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }

        arr[0][0] = 1;
        bfs();
        System.out.println(dp[N-1][M-1]);



    }
}