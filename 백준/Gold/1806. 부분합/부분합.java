import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제 해설]

*/

public class Main {

    static int N, S;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() ," " );
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sum = new int[N + 2];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int i = 0, j = 0;

        while (i <= N && sum[i] < S)
            i++;

//        System.out.println(Arrays.toString(sum));
//        System.out.println(i);

        int result = Integer.MAX_VALUE;
        while (i <= N && j <= N && i > j) {
//            System.out.println(i + " " + j);
             if (sum[i] - sum[j] >= S){
//                 System.out.println(i + " " + j);
                 result = Math.min(result, i - j);
                 j++;
             }
             else {
                 i++;
             }
        }

        if (result == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(result);

    }

}


