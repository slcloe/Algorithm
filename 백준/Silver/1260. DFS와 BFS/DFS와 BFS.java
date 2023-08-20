import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V;
    static boolean[] v;
    static int arr[][];

    static StringBuilder sb = new StringBuilder();


    static void dfs(int num){
        sb.append(num + 1).append(" ");
        v[num] = true;
        for (int i = 0; i < N; i++) {
            if (arr[i][num] == 1 && v[i] == false)
            {

                dfs(i);
            }
        }
    }

    static void bfs(){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        v = new boolean[N];
        v[V - 1] = true;
        sb.append(V).append(" ");
        queue.offer(V - 1);
        while (!queue.isEmpty()){
            int num = queue.poll();
            for (int i = 0; i < N; i++) {
                if (arr[i][num] == 1 && v[i] == false)
                {
                    v[i] = true;
                    sb.append(i + 1).append(" ");
                    queue.offer(i);
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        v = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) -1 ;
            int to = Integer.parseInt(st.nextToken()) -1;
            arr[from][to] = arr[to][from] = 1;
        }

        dfs(V - 1);
        sb.append("\n");
//        System.out.println("end");
        bfs();
        System.out.println(sb.toString());
    }
}