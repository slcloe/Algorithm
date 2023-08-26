import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] arr;
    static boolean[][] v;

    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};

    static int[][] direct = {
            {0, 2},
            {1, 2},
            {0, 1, 2}
    };
    static int result;

    static void dfs(int x, int y, int d)
    {
        if (x == N - 1 && y == N - 1){
//            for(boolean[] arr1 : v){
//                System.out.println(Arrays.toString(arr1));
//            }
//            System.out.println();

            result++; return;
        }

        for (int i = 0; i < direct[d].length; i++) {
            int tx = x + dx[direct[d][i]];
            int ty = y + dy[direct[d][i]];
            if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
            if (v[tx][ty]) continue;
            if (arr[tx][ty] == 1) continue;
            if (direct[d][i] == 2){
                if (v[tx - 1][ty] || v[tx][ty - 1]) continue;
                if (arr[tx - 1][ty] == 1 || arr[tx][ty - 1] == 1) continue;
                v[tx - 1][ty] = true;
                v[tx][ty] = true;
                v[tx][ty - 1] = true;
                dfs(tx, ty, direct[d][i]);
                v[tx - 1][ty] = false;
                v[tx][ty - 1] = false;
                v[tx][ty] = false;
            }
            else {
                v[tx][ty] = true;
                dfs(tx, ty, direct[d][i]);
                v[tx][ty] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 1, 0);
        System.out.println(result);
    }
}