import java.io.*;
import java.util.*;
public class Main {


    static int[] indegrees;
    static ArrayList<Integer>[] indegree;
    static ArrayList<Integer>[] outdegree;
    static int N, M;
    static boolean[] v;

    static int bfs(ArrayList<Integer>[] degree, int edge){
        int result = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(edge);
        v[edge] = true;

        while (!queue.isEmpty()){
            int cur = queue.poll();
            result++;
            for(int eg : degree[cur]){
                if (v[eg]) continue;
                v[eg] = true;
                queue.offer(eg);
            }
        }
        return result - 1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        v = new boolean[N];
        indegree = new ArrayList[N];
        outdegree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            indegree[i] = new ArrayList<>();
            outdegree[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            indegree[from].add(to);
            outdegree[to].add(from);

        }

        int cnt = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            cnt = 0;
            Arrays.fill(v, false);
            cnt += bfs(indegree, i);
//            System.out.println("i : " + i +" in : " + cnt);
            cnt += bfs(outdegree, i);
//            System.out.println("i : " + i +" out : " + cnt);
            if (cnt == N - 1) result++;
        }
        System.out.println(result);
    }
}

// 진입 + 진출 = N - 1 이면 몇번째인지 판별 가능.