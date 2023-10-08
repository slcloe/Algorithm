import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static char[][] arr;
    static int[] dx = {0, 0, -1 ,1};
    static int[] dy = {1, -1, 0, 0};

    static int calRoute(){
        if (0 == N - 1 && 0 == M - 1) return 1;

        ArrayDeque<int []> q = new ArrayDeque<>();
        boolean v[][][] = new boolean[N][M][11];

        q.offer(new int[]{0, 0, 1, 0});
        v[0][0][0] = true;
        int key;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            key = cur[3];
            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (tx == N - 1 && ty == M - 1) return cur[2] + 1;
                if (arr[tx][ty] == '1') {
                    if ( key >= K || v[tx][ty][key + 1] ) continue;
                        q.offer(new int[] {tx, ty, cur[2] + 1, key + 1});
                        v[tx][ty][key + 1] = true;
                }
                else{
                    if (v[tx][ty][key]) continue;
                    q.offer(new int[] {tx, ty, cur[2] + 1, key});
                    v[tx][ty][key] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }
        System.out.println(calRoute());

    }
}

/*
0 1 2 3
4 5 6 7
8 9 10 11


13 1 10
0
1
1
1
1
1
1
1
1
1
1
0
0

13 1 10
0
1
1
1
1
1
1
1
1
1
1
0
0
 */