import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

public class Solution {

    static String arr[] = new String[4];
    static int rotate[] = new int[4];

    static void counterClockWise(int n)
    {
        String tmp = arr[n].substring(1, 8);
        tmp += arr[n].substring(0, 1);
        arr[n] = tmp;
    }

    static void clockWise(int n)
    {
        String tmp = arr[n].substring(7, 8);
        tmp += arr[n].substring(0, 7);
        arr[n] = tmp;
    }

    static void checkRotateSide(int num, int side)
    {
        Arrays.fill(rotate, 0);
        rotate[num] = side;
        //left
        for (int i = num; i >= 1; i--)
        {
            if (arr[i].charAt(6) == arr[i - 1].charAt(2)) break;
            else
                rotate[i - 1] = -rotate[i];
        }
        //right
        for (int i = num; i <= 2; i++)
        {
            if (arr[i].charAt(2) == arr[i + 1].charAt(6)) break;
            else
                rotate[i + 1] = -rotate[i];
        }

        for (int i = 0; i < 4; i++) {
            if (rotate[i] == 1)
                clockWise(i);
            else if (rotate[i] == -1)
                counterClockWise(i);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < 4; i++) {
                arr[i] = "";
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 8; j++) {
                    arr[i] += st.nextToken();
                }
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int num = Integer.parseInt(st.nextToken());
                int side = Integer.parseInt(st.nextToken());
                checkRotateSide(num-1, side);
            }

            int result = 0;
            for (int i = 3; i >= 0; i--)
            {
                result *= 2;
                if (arr[i].charAt(0) == '1') result++;
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}