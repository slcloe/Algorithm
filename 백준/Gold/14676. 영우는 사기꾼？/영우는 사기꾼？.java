import java.io.*;
import java.util.*;
public class Main {

    static int N, M, K;
    static ArrayList<Integer>[] build;
    static Set<Integer>[] checkBuild;
    static int[] curbuildings;
    static int[] indegrees;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        build = new ArrayList[N];
        checkBuild = new Set[N];
        curbuildings = new int[N];
        indegrees = new int[N];

        for (int i = 0; i < N; i++) {
            build[i] = new ArrayList<>();
            checkBuild[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            build[from].add(to);
            indegrees[to]++;
        }


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int mode = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken()) - 1;

            if (mode == 1){
                if (indegrees[building] == checkBuild[building].size()){
                    curbuildings[building]++;
                    for(int b : build[building]){
                        checkBuild[b].add(building);
                    }
                }else{
                    System.out.println("Lier!");
                    return;
                }
            }else{
                if (curbuildings[building] == 0)
                {
                    System.out.println("Lier!");
                    return;
                }
                else {
                    curbuildings[building]--;
                    if (curbuildings[building] == 0){
                        for(int b : build[building]){
                            checkBuild[b].remove(building);
                        }
                    }
                }
            }
        }

        System.out.println("King-God-Emperor");
    }
}
/*

4 4 3
1 2
1 3
2 4
3 4
1 1
1 3
1 4



3 2 4
1 2
2 3
1 1
1 2
2 1
1 2


4 4 6
1 2
1 3
2 4
3 4
1 1
1 2
1 3
1 3
2 2
1 4

4 4 4
1 2
1 3
2 4
3 4
1 1
1 2
1 2
1 4


7 6 6
1 3
2 3
3 4
4 5
4 6
4 7
1 1
1 2
1 3
2 1
2 1
1 4
 */