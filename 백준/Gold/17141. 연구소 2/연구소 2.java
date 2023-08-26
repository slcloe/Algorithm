import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[] b;
    static int room = 0;

    static int[] dx = {0, 0, -1 ,1};
    static int[] dy= {1, -1, 0, 0};
    static ArrayList<int[]> virusList = new ArrayList<>();
    static int min = -1;
    static void comb(int cnt, int start){
        if (cnt == M){
            int time = bfs();
            if (min == -1) min = time;
            if (time != -1) min = (min < time) ? min : time;
            return;
        }
        for (int i = start; i < virusList.size(); i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static int bfs(){
        int depth = 0;
        int virus = 0;
        boolean[][] v = new boolean[N][N];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            v[virusList.get(b[i])[0]][virusList.get(b[i])[1]] = true;
            queue.offer(new int[] { virusList.get(b[i])[0],virusList.get(b[i])[1],0});
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            depth = (depth > cur[2]) ? depth : cur[2];
            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                if (v[tx][ty]) continue;
                if (arr[tx][ty] == 1) continue;
                v[tx][ty] = true;
                virus++;
                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
        }

//        for(boolean[] arr1 : v){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println(virus + " " + room + " " + depth);
//        System.out.println();

        if (virus == room) return depth;
//        System.out.println("is return -1");
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        b = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2){
                    virusList.add(new int[] {i, j});
                    arr[i][j] = 0;
                }
                if (arr[i][j] == 0)
                    room++;
            }
        }
        room -= M;
        comb(0, 0);
        System.out.println(min);
    }
}