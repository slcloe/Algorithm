import java.util.*;

class Solution {
    
    static class Work{
        private String name;
        private int start;
        private int playtime;
        
        public Work(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        
        PriorityQueue<Work> pq = new PriorityQueue<>(
        (o1, o2) -> (o1.start - o2.start)
        );
        
        for(int i = 0;i < plans.length; i++){
            String name = plans[i][0];
            
            String[] times = plans[i][1].split(":");
            int h = Integer.parseInt(times[0]);
            int m = Integer.parseInt(times[1]);
            
            int playtime = Integer.parseInt(plans[i][2]);
            
            pq.add(new Work(name, ((h*60)+m), playtime));
        }
        
        ArrayDeque<Work> curState = new ArrayDeque<>();
        
        while(!pq.isEmpty()){
            Work cur = pq.poll();
            Work next;
            if (!pq.isEmpty())
                next = pq.peek();
            else{
                answer[idx++] = cur.name;
                while(!curState.isEmpty()){
                    Work work = curState.pollFirst();
                    answer[idx++] = work.name;
                }
                continue; // break; 해도 될듯?
            }
                
            int startTime = cur.start;
            if (cur.start + cur.playtime < next.start){
                answer[idx++] = cur.name;
                startTime += cur.playtime;
                
                while(!curState.isEmpty()){
                    Work work = curState.pollFirst();
                    if (startTime + work.playtime <= next.start){
                        startTime += work.playtime;
                        answer[idx++] = work.name;
                    }
                    else{
                        int remainTime = work.playtime - (next.start - startTime);
                        curState.offerFirst(new Work(work.name, 0, remainTime));
                        break;
                    }
                }
            }
            else if (cur.start + cur.playtime == next.start){
                answer[idx++] = cur.name;
            }
            else{
                cur.playtime -= (next.start - cur.start);
                curState.offerFirst(new Work(cur.name, 0, cur.playtime));
            }
            
        }
        
        
        
        
        return answer;
    }
}