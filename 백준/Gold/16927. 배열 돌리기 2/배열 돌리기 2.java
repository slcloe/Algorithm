import java.io.*;
import java.util.*;

public class Main {
    static int N, M , r;
    static int[][] arr;
    static boolean[][] visited;
    static boolean[][] visited1;
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Integer> queue = new ArrayDeque<>();

    static void rotate(){
        int circle = (N > M) ? M / 2 : N / 2;
        for (int i = 0; i < circle; i++) {
            rotateOneCircle(i, i);
        }
    }

    static void rotateOneCircle(int x, int y){
        int dx[] = {0, 1, 0, -1, 0};
        int dy[] = {0, 0, 1, 0, -1};
        queue = new ArrayDeque<>();

        int tx = x - 1;
        int ty = y;
        for(int i = 0; i < 4; i++){
            tx = tx - dx[i] + dx[i + 1];
            ty = ty - dy[i] + dy[i + 1];
            while (isRange(tx, ty) && !visited[tx][ty]){
                visited[tx][ty] = true;
                queue.offer(arr[tx][ty]);
                tx = tx + dx[i + 1];
                ty = ty + dy[i + 1];
            }
        }

//        System.out.print(r + " ");
        int rot = r % queue.size();
//        System.out.print(r + " ");
//        System.out.println(queue.size());

        for (int i = 0; i < rot; i++) {
            int tmp = queue.pollLast();
            queue.offerFirst(tmp);
        }
        tx = x - 1;
        ty = y;
        for(int i = 0; i < 4; i++){
            tx = tx - dx[i] + dx[i + 1];
            ty = ty - dy[i] + dy[i + 1];
            while (isRange(tx, ty) && !visited1[tx][ty]){
                visited1[tx][ty] = true;
                arr[tx][ty] = queue.poll();
                tx = tx + dx[i + 1];
                ty = ty + dy[i + 1];
            }
        }
    }

    static boolean isRange(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < M) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr= new int[N][M];
        visited = new boolean[N][M];
        visited1 = new boolean[N][M];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate();
        for(int[] arr1 : arr){
            for(int arr2: arr1){
                sb.append(arr2).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
5 4 14
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28


 */