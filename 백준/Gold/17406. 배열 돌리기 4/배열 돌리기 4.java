import java.io.*;
import java.util.*;
public class Main {

    static int N, M, R;
    static int[][] arr;
    static int[][] arr1;
    static int[] b;
    static int[][] rot;
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Integer> queue;

    static int finalResult = -1;
    static boolean[] v;

    static void copy(){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr1[i].clone();
        }
    }

    static void comb(int cnt){
        if (cnt == R){
//            System.out.println("b : " + Arrays.toString(b));
            copy();
            for (int i = 0; i < R; i++) {
                rotate(rot[b[i]]);
            }
//            for(int[] arr1 : arr){
//                for(int arr2: arr1){
//                    sb.append(arr2).append(" ");
//                }
//                sb.append("\n");
//            }
//            sb.append("\n");
            int tmp = calArr();
            if (finalResult == -1) finalResult = tmp;
            finalResult = (finalResult < tmp) ? finalResult : tmp;
            return;
        }

        for (int i = 0; i < R; i++) {
            if (v[i]) continue;
            v[i] = true;
            b[cnt] = i;
            comb(cnt + 1);
            v[i] = false;
        }
    }

    static void rotate(int[] a){
        int x, y;
        x = a[0] - a[2];
        y = a[1] - a[2];
        for (int i = 0; i < a[2]; i++) {
            rotateOneCircle(x + i, y + i, a[2] - i);
        }
    }

    static void rotateOneCircle(int x, int y, int r){
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        queue = new ArrayDeque<>();

        int tx = x;
        int ty = y;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < r * 2; j++) {
                tx += dx[i];
                ty += dy[i];
                queue.offer(arr[tx][ty]);
            }
        }

        int tmp = queue.pollLast();
        queue.offerFirst(tmp);

        tx = x;
        ty = y;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < r * 2; j++) {
                tx += dx[i];
                ty += dy[i];
                arr[tx][ty] = queue.poll();
            }
        }
    }

    static int calArr(){
        int result = 0;

        for (int i = 0; i < M; i++) {
            result += arr[0][i];
        }
        for (int i = 1; i < N; i++) {
            int tmp = 0;
            for (int j = 0; j < M; j++) {
                tmp += arr[i][j];
                if (tmp > result) break;
            }
            result = (result < tmp) ? result : tmp;
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(),  " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arr1[i][j] = arr[i][j];
            }
        }

        rot = new int[R][3];
        b = new int[R];
        v = new boolean[R];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            rot[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rot[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rot[i][2] = Integer.parseInt(st.nextToken());
        }

        comb(0);

        System.out.println(finalResult);
//        System.out.println(sb.toString());
    }
}