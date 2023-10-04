import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int bfs(int x, int y){
        makePool();

        boolean[][] v = new boolean[R][C];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y, 1});
        v[x][y] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tx = dx[i] + cur[0];
                int ty = dy[i] + cur[1];

                if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
                if (v[tx][ty]) continue;
                if (arr[tx][ty] == -1) continue;
                if (arr[tx][ty] == -2) return cur[2];
                if (arr[tx][ty] != 0 && arr[tx][ty] <= cur[2] + 1) continue;
                v[tx][ty] = true;
                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
        }

        return -1;
    }

    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static void makePool(){
        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int tx = dx[i] + cur[0];
                int ty = dy[i] + cur[1];

                if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
                if (arr[tx][ty] != 0) continue;
                arr[tx][ty] = arr[cur[0]][cur[1]] + 1;
                q.offer(new int[] {tx, ty});
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        int x = 0, y = 0;
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = tmp.charAt(j);
                arr[i][j] = 0;
                if (ch == 'S'){
                    x = i; y = j;
                    arr[i][j] = 0;
                }
                else if (ch == '*'){
                    arr[i][j] = 1;
                    q.offer(new int[] {i, j});
                }
                else if (ch == 'X'){
                    arr[i][j] = -1;
                }
                else if (ch == 'D'){
                    arr[i][j] = -2;
                }
            }
        }
        int num = bfs(x, y);

//        for(int[] ar1 : arr)
//            System.out.println(Arrays.toString(ar1));
        if (num == -1)
            System.out.println("KAKTUS");
        else
            System.out.println(num);

    }
}