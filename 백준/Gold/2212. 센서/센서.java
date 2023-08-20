import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;
    static Integer[] dist;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N];
        dist = new Integer[N - 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 1; i < N; i++) {
            dist[i - 1] = arr[i] - arr[i - 1];
        }
        Arrays.sort(dist, Comparator.reverseOrder());

        int kFirstIdx = 0;
        int kLastIdx = (N>K)? K - 2: N-2;
        int start = 0, end = 0;
        int partition = 0;
        int result = 0;
        for (int i = 1; i < N; i++) {
//            System.out.println(kLastIdx + "  " + kFirstIdx);
            if (kLastIdx < 0) break;
            int distance = arr[i] - arr[i - 1];
            if (dist[kLastIdx] <= distance && distance <= dist[kFirstIdx]){
                result += arr[end] - arr[start];
                if (dist[kLastIdx] == distance) kLastIdx--;
                else if (dist[kFirstIdx] == distance) kFirstIdx++;
                start = end = i;
                partition++;
                if (partition == K - 1)break;
            }
            else{
                end++;
            }

        }
        result += arr[N-1] - arr[start];

        System.out.println(result);
    }
}