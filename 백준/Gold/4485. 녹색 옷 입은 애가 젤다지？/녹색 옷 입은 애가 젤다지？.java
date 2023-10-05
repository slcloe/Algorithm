import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static int[][] dist;
    static int dx[] = { 0, 0, 1, -1};
    static int dy[] = { 1, -1, 0, 0};

    static int DijkstraPQ(int N){

        boolean[][] v = new boolean[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(dist[o1[0]][o1[1]],dist[o2[0]][o2[1]]));

        int result = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = arr[0][0];
        pq.offer(new int[] { 0, 0, arr[0][0]});

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int min = cur[2];
            if (v[x][y]) continue;
            v[x][y] = true;
            result = min;

            if (x == N - 1 && y == N - 1) return result;

            for (int i = 0; i < 4; i++) {
                int tx = dx[i] + x;
                int ty = dy[i] + y;
                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                if (v[tx][ty]) continue;
                if (dist[tx][ty] > min + arr[tx][ty]){
                    dist[tx][ty] = min + arr[tx][ty];
                    pq.offer(new int[] {tx, ty, dist[tx][ty]});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int num = 1;
        int N = Integer.parseInt(br.readLine());
        arr = new int[125][125];
        dist = new int[125][125];
        while (N != 0){

            for (int i = 0; i < N; i++) {
                Arrays.fill(arr[i], 0);
                Arrays.fill(dist[i], 0);
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

//            dp[0][0] = arr[0][0];
            int result = DijkstraPQ(N);

//            System.out.println("Problem "+ num++ + ": " + result);
            sb.append("Problem").append(" ").append(num++).append(": ").append(result).append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }
}