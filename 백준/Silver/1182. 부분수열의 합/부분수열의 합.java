import java.io.*;
import java.util.*;
public class Main {
    static int N,S;
    static int[] arr;
    static boolean[] v;
    static int result = 0;

    static void subSet(int cnt){
        if (cnt == N){
            int tmp = 0;
            int flag = 0;
            for (int i = 0; i < N; i++) {
                if (v[i]){
                    tmp += arr[i];
                    flag = 1;
                }
            }
            if (flag != 0 && tmp == S) result++;
            return ;
        }
        v[cnt] = true;
        subSet(cnt + 1);
        v[cnt] = false;
        subSet(cnt + 1);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        subSet(0);
        System.out.println(result);


    }
}