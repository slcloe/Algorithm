import java.io.*;
import java.util.*;
public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());
        int n, k;
        String nums;


        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            nums = br.readLine();

            TreeSet<String> ts = new TreeSet<>();

            for (int i = 0; i < n / 4; i++) {
                // rotate
                String tmp = nums.substring(nums.length() - 1, nums.length());
                tmp += nums.substring(0, nums.length() - 1);
                nums = tmp;

                for (int j = 0; j < 4; j++) {
                    ts.add(nums.substring(j * (n / 4), (j + 1) * (n / 4)));
                }

            }
            Object[] results = ts.toArray();
            int result = hexToDecimal(results[results.length - k].toString());
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int hexToDecimal(String tmp){
        char[] hex = tmp.toCharArray();
        int result = 0;
        for (int i = 0; i < hex.length; i++) {
            result *= 16;
            result += hexTonum(hex[i]);
        }
        return result;
    }

    static int hexTonum(char ch){
        if ('0' <= ch && ch <= '9') return ch - '0';
        else return 10 + (ch - 'A');
    }
}
/*
1
12 10
1B3B3B81F75E
 */