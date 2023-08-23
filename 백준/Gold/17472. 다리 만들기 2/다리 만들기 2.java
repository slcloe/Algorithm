import java.util.*;
import java.io.*;

public class Main {

    static int[][] map;
    static int N, M, islandSize;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static int[] parents;

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight){
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }


    static void addBridge(){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0){
                    searchBridge(i, j);
                }
            }
        }
    }

    static void searchBridge(int x, int y){

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            int distance = 0;
            while (tx >= 0 && tx < N && ty >= 0 && ty < M){
                distance++;
                if (map[tx][ty] == map[x][y]) break;
                if (map[tx][ty] != 0 && map[tx][ty] != map[x][y]){
                    if (distance - 1 > 1)
                        edgeList.add(new Edge(map[x][y] - 2, map[tx][ty] - 2, distance - 1));
                    break;
                }
                tx += dx[i];
                ty += dy[i];
            }

        }

    }

    static int makeIsland(){
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1){
                    dfs(i, j, ++idx);
                }
            }
        }
        return idx - 1;
    }

    static void make(){
        parents = new int[islandSize];
        for (int i = 0; i < islandSize; i++) {
            parents[i] = i;
        }
    }

    static int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    static void dfs(int x, int y, int idx){
        map[x][y] = idx;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
            if (map[tx][ty] != 1) continue;
            map[tx][ty] = idx;
            dfs(tx, ty, idx);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        islandSize = makeIsland();

//        for(int [] arr: map){
//            System.out.println(Arrays.toString(arr));
//        }
        addBridge();
        Collections.sort(edgeList);
//        for(Edge edge: edgeList){
//            System.out.println(edge);
//        }
        make();

        int result = 0;
        int count = 0;
        for (Edge edge : edgeList){
            if (union(edge.from, edge.to)){
                result += edge.weight;
                if (++count == islandSize - 1) break;
            }
        }
        if (count != islandSize - 1)
            System.out.println(-1);
        else
            System.out.println(result);
        
        br.close();
    }
}
/*
8 8
1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 0 0 1 1 0 0 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1


8 8
0 0 0 1 1 1 1 0
0 1 1 1 1 0 1 0
0 1 0 1 1 1 0 0
0 1 0 0 0 1 0 0
0 0 0 1 0 0 1 0
0 0 0 0 0 1 0 0
0 1 1 1 0 0 0 0
0 1 0 0 0 1 0 0

4 8
0 0 0 0 0 0 1 1
1 0 0 1 1 0 1 1
1 1 1 1 0 0 0 0
1 1 0 0 0 1 1 0
 */