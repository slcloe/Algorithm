import java.io.*;
import java.util.*;

public class Main {


    static int[] arr = new int[365];
    static int N;
    static int max;
    static int min;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (i == 0)
            {
                min = n;
                max = m;
            } else {
                min = (min > n) ? n : min;
                max = (max > n) ? max : n;
            }

            for (int j=n;j<=m;j++){
                arr[j - 1]++;
            }
        }
//        for(int a: arr)
//            System.out.print(a + " ");
//        System.out.println();
        //System.out.println(min + " " + max);
        int result = 0;

        for (int i= min - 1 ; i < max ; i++){
            int row = 0, col = 0;
            if (arr[i] != 0) {
                row = arr[i];
                col = 0;
                while (arr[i] != 0){
                    col++;
                    row = (row > arr[i]) ? row : arr[i];
                    i++;
                    if (i == 365)
                        break;
                }
            }

            result += col * row;
            //System.out.println(result);
        }
        System.out.println(result);
    }
}
