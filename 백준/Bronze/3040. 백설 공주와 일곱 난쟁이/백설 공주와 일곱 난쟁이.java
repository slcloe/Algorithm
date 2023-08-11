import java.util.*;
import java.io.*;

public class Main {


    static int N;
    static int[] arr;
    static int[] b = new int[2];
    static boolean is;
    static StringBuilder sb = new StringBuilder();
    static void comb(int cnt, int start){
        if (is) return ;
        if (cnt == 2){
            int tmp = 0;
            for (int i = 0; i < 9; i++) {
                if (i != b[0] && i != b[1]) tmp += arr[i];
            }
            if (tmp == 100){
                is = true;
                for (int i = 0; i < 9; i++) {
                    if (i != b[0] && i != b[1]) sb.append(arr[i]).append("\n");
                }
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        comb(0, 0);
        System.out.println(sb.toString());
    }
}