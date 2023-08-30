import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static int R, C;
    static int[][] dp;
    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};

    static boolean isRange(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = arr[0][0];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < dx.length; k++) {
                    int tx = i - dx[k];
                    int ty = j - dy[k];
                    if (!isRange(tx, ty)) continue;
                    int tmp = arr[i][j] + dp[tx][ty];
                    dp[i][j] = (dp[i][j] < tmp) ? tmp : dp[i][j];
                }
            }
        }
        System.out.println(dp[R - 1][C - 1]);
    }
}