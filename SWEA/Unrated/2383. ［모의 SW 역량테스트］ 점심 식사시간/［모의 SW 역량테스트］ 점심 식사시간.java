import java.io.*;
import java.util.*;
public class Solution {

    static int N;
    static ArrayList<int[]> p = new ArrayList<>();
    static int[][] s = new int[2][3];
    static int[] b;
    static int min = Integer.MAX_VALUE;

    static void subs(int cnt){
        if (cnt == p.size()){
            min = Math.min(min, Math.max(calc(0), calc(1)));
            return ;
        }

        b[cnt] = 0;
        subs(cnt + 1);
        b[cnt] = 1;
        subs(cnt + 1);

    }

    static int calc(int idx){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < p.size(); i++) {
            if (b[i] == idx)
                pq.offer(Math.abs(s[idx][0] - p.get(i)[0]) + Math.abs(s[idx][1] - p.get(i)[1])); // 거리 계산
        }

        int[] state= new int[3];
        int j = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if (state[j % 3] == 0) state[j % 3] = cur + 1 + s[idx][2];
            else if (state[j % 3] <= cur) state[j % 3] = cur + 1 + s[idx][2];
            else state[j % 3] += s[idx][2];

            j++;
        }

        return Math.max(state[0], Math.max(state[1], state[2]));
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            p = new ArrayList<>();

            min = Integer.MAX_VALUE;
            int S = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == 0) continue;
                    else if (tmp == 1)        // 사람일때
                        p.add(new int[]{i, j}); // x, y
                    else
                        s[S++] = new int[] {i, j, tmp}; // 계단일때
                }
            }

            b = new int[p.size()];
            subs(0);

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }



}
/*

2
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
 */