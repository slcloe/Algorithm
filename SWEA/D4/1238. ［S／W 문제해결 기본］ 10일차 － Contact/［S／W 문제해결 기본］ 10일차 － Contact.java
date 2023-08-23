import java.util.*;
import java.io.*;

public class Solution {



    static int N, VIdx;
    static int[][] vertex;
    static Set<Integer> set = new HashSet<>();
    static boolean[] v;



    static int bfs(int n){
        int max = n;
        int depth = 0;
        ArrayDeque<int []> queue = new ArrayDeque<>();
        queue.offer(new int[] {n , 0});
        v[n] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (depth < cur[1]){
                depth = cur[1];
                max = cur[0];
            }
            if (depth == cur[1]){
                max = (max < cur[0]) ? cur[0] : max;
            }
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                int next = it.next();

                if (vertex[cur[0]][next] == 1 && !v[next]) {
                    v[next] = true;
                    queue.offer(new int[] {next, cur[1] + 1});
                }
            }
        }
        return max + 1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10 ; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            VIdx = Integer.parseInt(st.nextToken());
            vertex = new int[100][100];
            v = new boolean[100];
            set = new HashSet<>();

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                set.add(from); set.add(to);
                vertex[from][to] = 1;
            }
            sb.append("#").append(tc).append(" ").append(bfs(VIdx - 1)).append("\n");
        }

        System.out.println(sb.toString());

    }

}