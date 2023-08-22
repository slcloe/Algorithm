import java.util.*;
import java.io.*;
public class Main {


    static int N, M;
    static List<Integer>[] g;
    static int[] indegree;
    static StringBuilder sb = new StringBuilder();
    static void topologicalSort(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N + 1 ; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        while(!q.isEmpty()){
            int i = q.poll();
            sb.append(i).append(" ");
            for(int j : g[i]){
                if (--indegree[j] == 0) q.offer(j);
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            g[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            indegree[b]++;
        }
        topologicalSort();
        System.out.println(sb.toString());
        br.close();
    }
}