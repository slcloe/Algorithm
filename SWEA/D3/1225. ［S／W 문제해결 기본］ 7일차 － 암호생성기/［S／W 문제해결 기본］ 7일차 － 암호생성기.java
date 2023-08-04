import java.io.*;
import java.util.*;

public class Solution{

    static int N;
    static int[] arr;
    static ArrayDeque<Integer> queue;
    static StringBuilder sb = new StringBuilder();

    static boolean circle(){

        for (int i = 1; i <= 5; i++) {
            int cnt = queue.poll() - i;
            if (cnt > 0)
                queue.offer(cnt);
            else
            {
                queue.offer(0);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            queue = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }
            while(circle()){

            }
//            System.out.println(queue.toString());
            sb.append("#").append(tc);
            for(int a : queue){
                sb.append(" ").append(a);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
