import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Queue<Integer> queue = new ArrayDeque<>();

    static int queueCal(){
        int tmp = queue.poll();
        int cnt = 0;
        while (!queue.isEmpty()){
            if (cnt % 2 == 1){
                queue.add(tmp);
            }
            tmp = queue.poll();
            cnt = (cnt == 1) ? 0 : 1;
        }
        return tmp;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            queue.add(i + 1);
        }

        System.out.println(queueCal());
    }
}