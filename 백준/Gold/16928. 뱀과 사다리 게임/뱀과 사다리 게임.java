import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 풀이]

exp 이 짝수일때:
    n * n 이므로 n 을 재귀를 통해 구해서 n * n 을 구한다.
exp 이 홀수일때:
    n * n * 1 이므로 n을 재귀를 통해 구해서 n * n 계산 후 1 을 더 곱하여 구한다.

 */

public class Main {

    static int N, M;
    static HashMap<Integer, Integer> ladder, snack;
    static boolean v[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[101];

        ladder = new HashMap<>();
        snack = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snack.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

//        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
//            if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
//            else return -Integer.compare(o1[1], o2[1]);
//        }); // depth position
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return -Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        }); // depth position

        pq.offer(new int[] {0, 1});
        v[1] = true;

        int result = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
//            System.out.println(Arrays.toString(cur));

            int next = 0;
            for(int i = 1; i <= 6; i++) {
                next = cur[1] + i;
//                System.out.println(next  + " pre next");

                if (next == 100) {
                    result = cur[0] + 1;
                    break;
                }
                if (v[next]) continue;

                if (ladder.containsKey(next)) {
//                    System.out.println(next + " " + ladder.get(next) + " "+ cur[0]);
                    next = ladder.get(next);
//                    System.out.println("next : " + next);
                }
                if (snack.containsKey(next)) {
                    next = snack.get(next);
                }

                if (v[next]) continue;
                v[next] = true;
                v[cur[1] + i] = true;
                pq.offer(new int[] {cur[0] + 1, next});
            }


            if (next == 100) break;
        }
        System.out.println(result);
    }
}