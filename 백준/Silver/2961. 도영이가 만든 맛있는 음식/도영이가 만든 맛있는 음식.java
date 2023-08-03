import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.abs;

public class Main {
    static int N;
    static int[][] tastes;
    static boolean[] v;
    static int taste;


    static void subSet(int cnt){
        if (cnt == N){
            int sour = 1;
            int bitter = 0;
            for (int i = 0; i < N; i++) {
                if (v[i]){
                    sour *= tastes[i][0];
                    bitter += tastes[i][1];
                }
            }
            if (bitter == 0) return ;
            if (taste < 0 || taste > abs(sour - bitter)){
                taste = abs(sour - bitter);
            }
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

        tastes = new int[N][2];
        v = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            tastes[i][0] = Integer.parseInt(st.nextToken());
            tastes[i][1] = Integer.parseInt(st.nextToken());
        }
        taste = -1;
        subSet(0);
        System.out.println(taste);
    }
}