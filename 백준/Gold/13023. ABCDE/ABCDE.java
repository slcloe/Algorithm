import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] vertex;
    static boolean[] v;
    static boolean check;

    static void dfs(int a, int depth){
//        System.out.println("A " + a  + "depth " + depth);
        if (depth == 4){
            check = true;
            return ;
        }
        for (int i : vertex[a]) {
            if (v[i]) continue;
            v[i] = true;
            dfs(i, depth + 1);
            v[i] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vertex = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            vertex[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            vertex[a].add(b);
            vertex[b].add(a);
        }

        v = new boolean[N];
        for (int i = 0; i < N; i++) {
            if(!v[i]){
                Arrays.fill(v, false);
                v[i] = true;
                dfs(i, 0);
                v[i] = false;
            }
            if (check) break;
        }

        if (check) System.out.println(1);
        else System.out.println(0);
    }


}