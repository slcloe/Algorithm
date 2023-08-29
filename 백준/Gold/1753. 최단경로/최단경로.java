import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int V, E;
        int I;
        ArrayList<int[]>[] g;
        int[] dist;
        boolean[] v;


        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(br.readLine()) - 1;

        v = new boolean[V];
        dist = new int[V];
        g = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            g[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            g[from].add(new int[]{to, weight});
        }

        int result = 0;
        dist[I] = 0;
        for (int i = 0; i < V; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < V; j++) {
                if (!v[j] && min > dist[j]){
                    minVertex = j;
                    min = dist[j];
                }
            }
            if (minVertex == -1) {
                sb.append("INF");
                break;
            }
//            System.out.println("minvertex" + minVertex);
            v[minVertex] = true;
            result += min;
            sb.append(min).append("\n");

            if (minVertex == I && result != 0) break;
//            System.out.println(Arrays.toString(dist));
            for(int[] edge : g[minVertex]){
                if (!v[edge[0]] && dist[edge[0]] > min + edge[1]){
                    dist[edge[0]] = min + edge[1];
                }
            }
//            System.out.println(Arrays.toString(dist));
        }

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }
    }
}
/*
4 4
1
1 3 5
3 4 10
1 2 2
2 3 1


0
2
3
5
13

2 1
1
1 2 2

 */