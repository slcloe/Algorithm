import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        arr[0] = 1; arr[1] = 2;
        for (int i = 2; i < N ; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
            arr[i] %= 10007;
        }
        System.out.println(arr[N - 1]);

    }


    /*
    1 -> 1
    2 -> 2
    3 -> 3
    4 -> f(3) + f(2)


     */

}