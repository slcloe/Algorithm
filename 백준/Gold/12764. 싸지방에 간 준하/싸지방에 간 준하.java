import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<int[]> pq;
    static PriorityQueue<int[]> curSeats;
    static PriorityQueue<Integer> remainSeats;
    static int[] seats;
    static int size = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        seats = new int[N];
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        curSeats = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        });
        // endTime, seat

        remainSeats = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pq.offer(new int[] {a, b});
        }

        int[] seat = pq.poll();
        curSeats.offer(new int[] { seat[1] ,0});
        seats[0]++;
//
//        while(!pq.isEmpty()) {
//            int[] cur = pq.poll();
//
////            System.out.println(curSeats.peek()[0] + " " + cur[0]);
//            // 자리 없을 경우
//            if (curSeats.peek()[0] > cur[0]) {
//                seats[size]++;
//                curSeats.offer(new int[] {cur[1], size});
//                size++;
//            } else { // 자리 있을 경우
//                int[] removeSeat = curSeats.poll();
//                seats[removeSeat[1]]++;
//                curSeats.offer(new int[] {cur[1] ,removeSeat[1]});
//            }
//
//        }


        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

//            System.out.println(Arrays.toString(seats));

//           // remainSeat 에 가능한 좌석을 넣는다.
            while(!curSeats.isEmpty() && curSeats.peek()[0] <= cur[0]){
                remainSeats.offer(curSeats.poll()[1]);
            }

//            System.out.println(remainSeats.size());

            // 만약 remainSeat 가 empty 라면
            if (remainSeats.isEmpty()) {
                seats[size]++;
                curSeats.offer(new int[] {cur[1], size});
                size++;
            } else {
                int removeSeat = remainSeats.poll();
                seats[removeSeat]++;
                curSeats.offer(new int[] {cur[1] ,removeSeat});
//                remainSeats.remove(removeSeat);
            }

            // 앉을 수 있다면?

        }

        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(seats[i]).append(" ");
        }

        System.out.print(sb.toString());
    }
}