import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] arr;
    static StringBuilder sb = new StringBuilder();
    static void compress(int n, int x, int y){

        int checkNum = checkValidation(n , x, y);
        if (checkNum != -1){
            sb.append((char) ('0' + checkNum));
            return;
        }
        if (n == 2){
            sb.append('(');
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(arr[x + i][y + j]);
                }
            }
            sb.append(')');
            return;
        }
        if (n == 1){
            sb.append(arr[x][y]);
            return;
        }

        sb.append('(');

        checkNum = checkValidation(n / 2, x, y);
        if (checkNum == -1)
            compress(n / 2, x, y);
        else
            sb.append((char) ('0' + checkNum));

        checkNum = checkValidation(n / 2, x, y + n / 2);
        if (checkNum == -1)
            compress(n / 2, x, y + n / 2);
        else
            sb.append((char) ('0' + checkNum));

        checkNum = checkValidation(n / 2, x + n / 2, y);
        if (checkNum == -1)
            compress(n / 2, x + n /2, y);
        else
            sb.append((char) ('0' + checkNum));

        checkNum = checkValidation(n / 2, x + n /2, y + n /2);
        if (checkNum == -1)
            compress(n / 2, x + n / 2, y + n / 2);
        else
            sb.append((char) ('0' + checkNum));

        sb.append(')');
    }

    static int checkValidation(int n, int x, int y){
//        System.out.println(n + " " + x + " " + y);
        char startCh = arr[x][y];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (startCh != arr[x + i][y + j]) return -1;
            }
        }
        return startCh - '0';
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        compress(N, 0, 0);

        System.out.println(sb.toString());
    }
}
/*

8
11110100
11110010
00011100
00011100
11110000
11110000
11110011
11110011

 */