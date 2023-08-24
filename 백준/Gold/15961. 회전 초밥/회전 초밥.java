import java.util.*;
import java.io.*;
public class Main {

    static int N, d, k, c;
    static ArrayDeque<Integer> queue = new ArrayDeque<>();
    static int[] arr;
    static int[] plateNum;
    static int[] sushiNum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[N + k];
        plateNum = new int[N + 1 + k];
        sushiNum = new int[d + 1];

        int max = 0;

        arr[0] = Integer.parseInt(br.readLine());
        plateNum[0] = 1;
        sushiNum[arr[0]]++;
        for (int i = 1; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            int prevPlate = plateNum[i - 1];
            if (i >= k){
                if(--sushiNum[arr[i - k]] == 0) prevPlate--;
            }
            if (++sushiNum[arr[i]] == 1) prevPlate++;
            plateNum[i] = prevPlate;
            max = (max < plateNum[i]) ? plateNum[i] : max;
//            System.out.println("i : " + i + "sushi " + sushiNum[c]);
            if (sushiNum[c] == 0)
                max = (max < plateNum[i] + 1) ? plateNum[i] + 1 : max;
        }
        for (int i = 0; i < k - 1; i++) {
            int cur = N + i;
            arr[cur] = arr[i];
            int prevPlate = plateNum[cur - 1];
            if(--sushiNum[arr[cur - k]] == 0) prevPlate--;
            if (++sushiNum[arr[cur]] == 1) prevPlate++;
            plateNum[cur] = prevPlate;
            max = (max < plateNum[cur]) ? plateNum[cur] : max;
            if (sushiNum[c] == 0)
                max = (max < plateNum[cur] + 1) ? plateNum[cur] + 1 : max;
        }

        System.out.println(max);

    }
}