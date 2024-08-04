import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] arrb;
    static int MaxSize;
    static int[] result;

    static void makeResult(int idx) {
        int j = MaxSize;
        result = new int[MaxSize + 1];
        result[j] = arr[idx];

//        System.out.println("MaxSize :" + (MaxSize + 1));

        for(int i = idx - 1; i >= 0; i--) {
            int pos = Arrays.binarySearch(result, arr[i]);
            if (pos < 0) {
                pos *= -1;
                System.out.println(pos);
                result[--pos] = arr[i];
            }
        }
    }
    static void binarySearch(int idx){
        if (arrb[MaxSize] < arr[idx]) {
            arrb[++MaxSize] = arr[idx];
//            makeResult(idx);
            return;
        }
        if (arrb[0] > arr[idx]){
            arrb[0] = arr[idx];
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
                return;
            }
        }
        int i = Arrays.binarySearch(arrb, 0, MaxSize+1, arr[idx]);
        i *= -1;

        arrb[--i] = arr[idx];
//        System.out.println(i + " " + front);
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
//            System.out.println(Arrays.toString(arrb) + " " + MaxSize);
        }
        System.out.println(MaxSize + 1);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < MaxSize + 1; i++) {
//            sb.append(result[i]).append(' ');
////            System.out.print(arrb[i] + " ");
//        }
//        System.out.println(sb.toString());
    }
}
/*
10
8 2 4 3 6 11 7 10 14 5


13
3 4 5 6 2 3 1 7 4 3 5 6 7

 */