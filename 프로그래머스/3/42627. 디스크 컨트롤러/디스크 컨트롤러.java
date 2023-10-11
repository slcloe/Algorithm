import java.util.*;
import java.io.*;

class Solution {
    
    
    
    public int solution(int[][] jobs) {
        int answer = 0;

        int[][] result = new int[jobs.length][2]; // start - end;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int idx = 0;
        int curState = 0;
        boolean v[] = new boolean[jobs.length];

        while (idx < jobs.length){

            // 현재 curState 와 비교해서 실행시킬수 있는 프로세스 넣기
            for (int i = 0; i < jobs.length; i++) {
                if (v[i]) continue;
                if (curState >= jobs[i][0]) {
                    pq.offer(jobs[i]);
                    v[i] = true;
                }
            }

            // 현재 진행할 수 있는 프로세스가 있다면 -> pq 에서 하나 빼서 계산
            if (!pq.isEmpty()){
                int cur[] = pq.poll();

                result[idx][0] = cur[0];
                result[idx][1] = curState + cur[1];
                curState = result[idx][1];
                idx++;

            }
            // 현재 진행할 수 있는 프로세스가 없다면 -> 그냥 curState ++ 하고 돌리기
            else{
                curState++;
            }
        }

        for (int i = 0; i < jobs.length; i++) {
            answer += result[i][1] - result[i][0];
        }
        answer /= jobs.length;
        return answer;
    }
}