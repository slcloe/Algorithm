import java.util.*;
import java.io.*;

public class Main {


    static int N, K;

    static class Hide{
        int pos, depth;
        Hide(int pos, int depth){
            this.pos = pos;
            this.depth = depth;
        }
    }

    static int bfs(){
        ArrayDeque<Hide> queue= new ArrayDeque<>();
        TreeSet<Integer> visited = new TreeSet<>();

        visited.add(N);
        queue.offer(new Hide(N, 0));
        if (N == K) return 0;
        while (!queue.isEmpty()){
            Hide hide = queue.poll();
            if (hide.pos < 0 || hide.pos > 100000) continue;
//            System.out.println(" hide" + hide.pos + " " + hide.depth);
            if (hide.pos + 1 == K || hide.pos * 2 == K || hide.pos - 1 == K) return hide.depth + 1;
            else{
                if (!visited.contains(hide.pos * 2)) {
                    visited.add(hide.pos * 2);
                    queue.offer(new Hide(hide.pos * 2, hide.depth + 1));
                }if (!visited.contains(hide.pos + 1)) {
                    visited.add(hide.pos + 1);
                    queue.offer(new Hide(hide.pos + 1, hide.depth + 1));
                }if (!visited.contains(hide.pos - 1)) {
                    visited.add(hide.pos - 1);
                    queue.offer(new Hide(hide.pos - 1, hide.depth + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(        bfs());
    }
}