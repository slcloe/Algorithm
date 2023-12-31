import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N + 1];

        arr[N] = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        for (int i = N; i >= 1; i--) {
            if (i % 2 == 0)
                arr[i / 2] = Math.min(arr[i / 2] , arr[i] + 1);
            if (i % 3 == 0)
                arr[i / 3] = Math.min(arr[i / 3] , arr[i] + 1);
            arr[i - 1] = Math.min(arr[i - 1] , arr[i] + 1);
        }
        System.out.println(arr[1]);
    }
}