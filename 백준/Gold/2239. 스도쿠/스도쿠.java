import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr = new int[9][9];
    static boolean end;


    static void dfs(int depth){
        if (depth == 81){
            end = true;
            return;
        }
        int x = depth / 9;
        int y = depth % 9;

        if (arr[x][y] != 0)
            dfs(depth + 1);
        else{
            for (int i = 1; i <= 9 ; i++) {
                if (!isAvaliable(x, y, i)) continue;
                arr[x][y] = i;
                dfs(depth + 1);
                if (end) return;
                arr[x][y] = 0;
            }
        }




    }

    static boolean isAvaliable(int x, int y, int n){
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;

        for (int i = 0; i < 9; i++) {
            if(arr[x][i] == n || arr[i][y] == n) return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[startX + i][startY + j] == n) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = input.charAt(j) - '0';
            }
        }


        dfs(0);


        for(int[] arr1 : arr){
            for(int arr2 : arr1)
                sb.append(arr2);
            sb.append("\n");
        }

        System.out.println(sb.toString());



    }
}