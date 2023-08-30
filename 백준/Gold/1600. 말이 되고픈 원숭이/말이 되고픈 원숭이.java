import java.io.*;
import java.util.*;

public class Main {

    static int W, H, K;
    static int[][][] dp;
    static int[][] arr;
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};

    static int horseX[] = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int horseY[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    static boolean isRange(int x, int y){
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    static int bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {0, 0, 0});
        dp[0][0][0] = 0;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];
                if (!isRange(tx, ty)) continue;
                if (arr[tx][ty] == 1) continue;
                if (dp[cur[2]][cur[0]][cur[1]] + 1 >= dp[cur[2]][tx][ty]) continue;
                dp[cur[2]][tx][ty] = dp[cur[2]][cur[0]][cur[1]] + 1;
                queue.offer(new int[] {tx, ty, cur[2]});
            }

            if (cur[2] == K) continue;

            for (int i = 0; i < 8; i++) {
                int tx = cur[0] + horseX[i];
                int ty = cur[1] + horseY[i];
                if (!isRange(tx, ty)) continue;
                if (arr[tx][ty] == 1) continue;
                if (dp[cur[2]][cur[0]][cur[1]] + 1 >= dp[cur[2] + 1][tx][ty]) continue;
                dp[cur[2] + 1][tx][ty] = dp[cur[2]][cur[0]][cur[1]] + 1;
                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
        }

//        for(int[] arr1 : dp){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= K; i++) {
            min = (min < dp[i][H - 1][W - 1]) ? min : dp[i][H - 1][W - 1];
//            System.out.println(dp[i][H - 1][W - 1]);
        }

        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][W];
        dp = new int[K + 1][H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        System.out.println(bfs());

    }

}