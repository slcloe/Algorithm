import java.util.*;
import java.io.*;

public class Main {

    static int[] character = new int[4];
    static int S, P;
    static String str;
    static int[] check = new int[4];


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        str = br.readLine();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            character[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < S - P + 1; i++) {
            if (i == 0){
                for (int j = 0; j < P; j++) {
                    if (str.charAt(i + j) == 'A') check[0]++;
                    else if (str.charAt(i + j) == 'C') check[1]++;
                    else if (str.charAt(i + j) == 'G') check[2]++;
                    else if (str.charAt(i + j) == 'T') check[3]++;
                }
            }
            else {
                if (str.charAt(i - 1) == 'A') check[0]--;
                else if (str.charAt(i - 1) == 'C') check[1]--;
                else if (str.charAt(i - 1) == 'G') check[2]--;
                else if (str.charAt(i - 1) == 'T') check[3]--;

                int j = i + P - 1;

                if (str.charAt(j) == 'A') check[0]++;
                else if (str.charAt(j) == 'C') check[1]++;
                else if (str.charAt(j) == 'G') check[2]++;
                else if (str.charAt(j) == 'T') check[3]++;
            }

            int k = 0;
            for (; k < 4; k++) {
                if (check[k] < character[k]) break;
            }

            if (k == 4) result++;
        }
        System.out.println(result);

    }
}