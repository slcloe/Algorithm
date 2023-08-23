import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] map;



    static void dfs(int x, int y){
        map[x][y] = 3;

        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
            if (map[tx][ty] == 1) continue;
            if (map[tx][ty] == 0) dfs(tx,ty);
        }

    }

    static int institution(){
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2)
                    dfs(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) result++;
            }
        }
        return result;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1 || arr[i][j] == 2) continue;
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        if (i == k && j == l) continue;
                        if (arr[k][l] == 1 || arr[k][l] == 2) continue;
                        for (int m = 0; m < N; m++) {
                            for (int n = 0; n < M; n++) {
                                if (arr[m][n] == 1 || arr[m][n] == 2) continue;
                                if (m == k && n == l) continue;
                                if (i == m && j == n) continue;
                                map = new int[N][];
                                for (int o = 0; o < N; o++) {
                                    map[o] = arr[o].clone();
                                }
                                map[i][j] = 1;
                                map[k][l] = 1;
                                map[m][n] = 1;
                                int tmp =institution();

                                max = (max < tmp) ? tmp : max;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}