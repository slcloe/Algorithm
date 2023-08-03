import java.io.*;
import java.util.*;

public class Solution {
    static int dump;
    static int[] arr;
    static int diff;
    static int front;
    static int rear;

    static void rightIdx(){
        if (front == 99) return ;
        while (arr[front] == arr[front + 1]){
            front++;
            if (front >= 99) break;
        }
    }

    static void leftIdx(){
        if (rear == 0) return ;
        while (arr[rear] == arr[rear - 1]){
            rear--;
            if (rear <= 0) break;
        }
    }

    static void moveDump(){
        rightIdx();
        leftIdx();

        arr[front]++;
        arr[rear]--;

        if( rear+1 <= 99 && arr[rear] < arr[rear + 1]) rear++;
        if( front-1 >= 0 && arr[front] > arr[front - 1]) front--;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc = 1; tc <= 10; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            dump = Integer.parseInt(st.nextToken());
            arr = new int[100];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i=0;i<100;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            //System.out.println("frist " + Arrays.toString(arr));
            front = 0;
            rear = 99;
            for (int i = 0; i <dump; i++) {
                moveDump();
                //System.out.println(Arrays.toString(arr));
                if (arr[99] - arr[0] == 0 || arr[99] - arr[0] == 1) break;
            }
            diff = arr[99] - arr[0];
            sb.append("#").append(tc).append(" ").append(diff).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}