import java.util.*;
import java.io.*;

public class Main {

    static class Meeting implements Comparable<Meeting>{
        int start, end;

        Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    "\n";
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) return Integer.compare(this.start, o.end);
            return Integer.compare(this.end, o.end);
        }
    }

    static int N;
    static Meeting[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        int end = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i].start >= end){
                cnt++;
                end = arr[i].end;
            }
        }
        System.out.println(cnt);
    }

}