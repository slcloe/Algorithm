import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] arrb;
    static int MaxSize;
    static int[] dp;
    static int[] result;

    static int binarySearch(int idx){
        if (arrb[MaxSize] < arr[idx]) { // 지금껏 본 숫자 중 가장 큰 숫자일 때
            arrb[++MaxSize] = arr[idx];
            return MaxSize + 1;
        }
        if (arrb[0] > arr[idx]){ // 지금껏 본 숫자 중 가장 작은 숫자일 때
            arrb[0] = arr[idx];
            return 1;
        }

        int i = Arrays.binarySearch(arrb, 0, MaxSize+1, arr[idx]);
        if (i >= 0) return i + 1; // 이미 있는 숫자 일 때
        // 새로운 숫자라 arrb의 중간에 넣을 것이다.
        i *= -1;
        arrb[--i] = arr[idx];
        return i + 1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st=  new StringTokenizer(br.readLine());

        arr= new int[N];
        arrb = new int[N];
        dp = new int[N];
        result = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arrb[0] = arr[0];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = binarySearch(i); // dp배열에는 각 idx의 최장길이를 구한다.
        }
        System.out.println(MaxSize + 1);

        int j = MaxSize + 1;
        result = new int[MaxSize + 1];
        StringBuilder sb = new StringBuilder();
        for(int i = N - 1; i >= 0 && j > 0; i--) {
            // dp 값이 중복일 때 가장 최근에 입력된 값을 얻기 위해 역으로 순회하여 값을 저장
            if (j == dp[i]) {
                result[--j] = arr[i];
            }
        }
        for(int i : result) {
            sb.append(i).append(' ');
        }
        System.out.println(sb.toString());

    }
}
/*
10
8 2 4 3 6 11 7 10 14 5


13
3 4 5 6 2 3 1 7 4 3 5 6 7

 */