import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] arrb;
    static int curSize;
    static int MaxSize;
    static void binarySearch(int idx){
        if (arrb[MaxSize] < arr[idx]){
            arrb[++MaxSize] = arr[idx];
            curSize = MaxSize;
            return;
        }
        if (arrb[0] > arr[idx]){
            arrb[0] = arr[idx];
            curSize = 0;
            return;
        }
        int front = 0;
        int rear = MaxSize + 1;
        while(front <= rear){
            int middle = (front + rear) / 2;
            if (arrb[middle] < arr[idx]){
                front = middle + 1;
            }
            else if(arrb[middle] > arr[idx]){
                rear = middle - 1;
            }
            else{
                curSize = middle;
                return;
            }
        }
//        System.out.println(front + " " + rear);
        arrb[front] = arr[idx];
        curSize = front;
//        if (arrb[front] == arr[idx])
//            curSize = front;
//        else{
//
//        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st=  new StringTokenizer(br.readLine());
        arr= new int[N];
        arrb = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arrb[0] = arr[0];
        for (int i = 1; i < N; i++) {
            binarySearch(i);
//            System.out.println(Arrays.toString(arrb));
        }
        System.out.println(MaxSize + 1);

    }
}
/*
10
8 2 4 3 6 11 7 10 14 5
 */