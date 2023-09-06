import java.io.*;
import java.util.*;

public class Main {

    static int N, E;
    static int v1, v2;
    static ArrayList<int[]>[] g;


    static long dijkstra(int start, int end){
        if (start == end) return 0;
        long[] minEdge = new long[N];
        boolean[] v = new boolean[N];
        long result = 0;

        Arrays.fill(minEdge, Long.MAX_VALUE);
        minEdge[start] = 0;
        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            long min = Long.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!v[j] && min > minEdge[j]){
                    minVertex = j;
                    min = minEdge[j];
                }
            }

            if (minVertex == -1) return -1;
            v[minVertex] = true;
            result += min;
            if (minVertex == end) break;

            for(int[] edge : g[minVertex]){
                if (!v[edge[0]] && minEdge[edge[0]] > min + edge[1]){
                    minEdge[edge[0]] = min + edge[1];
                }
            }
        }
        if (minEdge[end] == Long.MAX_VALUE) return -1;
        return minEdge[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        g = new ArrayList[N];

        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            g[from].add(new int[] {to, weight});
            g[to].add(new int[] {from, weight});
        }

        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        long min = Long.MAX_VALUE;

        long[] d1 = new long[3];
        long[] d2 = new long[3];

        d1[0] = dijkstra(0, v1);
        d1[1] = dijkstra(v1, v2);
        d1[2] = dijkstra(v2, N - 1);

        d2[0] = dijkstra(0, v2);
        d2[1] = dijkstra(v2, v1);
        d2[2] = dijkstra(v1, N - 1);

//        System.out.println(Arrays.toString(d1));
//        System.out.println(Arrays.toString(d2));

        if (!(d1[0] == -1 ||d1[1] == -1 ||d1[2] == -1))
            min = (min < d1[0] + d1[1] + d1[2]) ? min : d1[0] + d1[1] + d1[2];
        if (!(d2[0] == -1 ||d2[1] == -1 ||d2[2] == -1))
            min = (min < d2[0] + d2[1] + d2[2]) ? min : d2[0] + d2[1] + d2[2];

//        System.out.println(min);
        if (min >= Long.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }
//    (1 -> v1 -> v2 -> N)
}