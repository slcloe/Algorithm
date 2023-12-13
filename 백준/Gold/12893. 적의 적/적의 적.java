import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int v[];
    static int[][] g;

    static boolean dfs(int n, int depth, int cycleNum){
        v[n] = depth;
//        System.out.println("n  :" +n +  "  depth  :" + depth);
        boolean res = true;

        for (int i = 0; i < N; i++) {
            if (g[n][i] == 1){
//                System.out.println("graph" + i) ;
                if (v[i] == 0){ // 아직 사이클 아님
                    res = dfs(i, depth + 1, cycleNum);
                }
                else{ // 이미 방문한 곳
                    if (v[i] % 2  == depth % 2){ // false
                        return false;
                    }
                }
                if (!res) return res;
            }
        }
        return res;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new int[N][N];
        v = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            g[a - 1][b - 1] = g[b - 1][a - 1] = 1;
        }

        int result = 1;
        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(v));
            if (v[i] == 0) {
//                System.out.println("iii" + i);
                if (!dfs(i, 1, i + 1)){
//                    System.out.println("I" + i);
                    result = 0;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}


//11:13
//1t : 11:35