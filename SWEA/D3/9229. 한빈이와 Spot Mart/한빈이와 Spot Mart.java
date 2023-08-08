import java.io.*;
import java.util.*;

public class Solution {

    static int T;
    static int N, M;
    static Integer[] arr;
    static StringBuilder sb = new StringBuilder();
    static int result;
    static int index;
    static void comb(int cnt, int sum, int start){
        if (cnt == 2){
            if (sum <= M)
                result = (result < sum) ? sum : result;
            return;
        }
        for (int i = start; i < index; i++) {
            if (sum + arr[i] <= M)
            {
                comb(cnt + 1, sum + arr[i], i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/input_d3_9229.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new Integer[N];
            st = new StringTokenizer(br.readLine(), " ");
            index = 0;
            for (int i = 0; i < N; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp < M) arr[index++] = tmp;
            }
            Arrays.sort(arr,0, index, Collections.reverseOrder());
            result = 0;
            comb(0, 0, 0);
            result  = (result == 0) ? -1 : result;
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
/*
4
3 40
20 20 20
6 10
1 2 5 8 9 11
4 100
80 80 60 60
4 1
10 5 10 16

 */