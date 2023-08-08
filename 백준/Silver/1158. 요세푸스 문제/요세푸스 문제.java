import java.util.*;
import java.io.*;

public class Main {

    static ArrayDeque<Integer> list = new ArrayDeque<>();
    static int N, K;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
             list.offer(i);
        }
        sb.append("<");
        while (list.size() != 1){
            for (int i = 0; i < K - 1; i++) {
                int tmp = list.poll();
                list.offer(tmp);
            }
            sb.append(list.poll()).append(",").append(" ");
        }
        sb.append(list.poll()).append(">");
        System.out.println(sb.toString());
    }
}