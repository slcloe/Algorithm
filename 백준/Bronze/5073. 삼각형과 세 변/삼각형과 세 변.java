import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

//    static int N, W, M, H;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        String E = "Equilateral";
        String S = "Scalene";
        String I = "Isosceles";
        String invalid = "Invalid";

        int[] arr = new int[3];
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());

        while (arr[0] != 0 && arr[1] != 0 && arr[2] != 0) {
            Arrays.sort(arr);
            if (arr[2] < arr[0] + arr[1]) {
                int cnt = 0;
                if (arr[0] == arr[1]) cnt++;
                if (arr[2] == arr[1]) cnt++;

                if (cnt == 2) sb.append(E).append('\n');
                else if (cnt == 1) sb.append(I).append('\n');
                else sb.append(S).append('\n');
            }
            else {
                sb.append(invalid).append('\n');
            }

            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }
}
