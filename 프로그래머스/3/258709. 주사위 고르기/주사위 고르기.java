import java.util.*;
class Solution {
    int N;
    boolean[] v;
    List<int[]> diceComb;
    List<Integer> scoreA, scoreB;
    
    void perm(int cnt, int start, int[] arr){
        if (cnt == N / 2){
            diceComb.add(arr.clone());
            return;
        }
        
        for (int i = start; i < N ; i++){
            if (v[i]) continue;
            v[i] = true;
            arr[cnt] = i;
            perm(cnt + 1, i + 1, arr);
            v[i] = false;
        }
    }
    
    void calScore(int[][] dices, int cnt, int[] dice, int sum, List<Integer> comb){
        if (cnt == dice.length){
            comb.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++){
            calScore(dices, cnt + 1, dice, sum + dices[dice[cnt]][i], comb);
        }
    }
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        int[] answer = new int[N / 2];
        
        v = new boolean[N];
        diceComb = new ArrayList<>();
        perm(0, 0, new int[N / 2]);
        
        int max = Integer.MIN_VALUE;
        for (int[] combA : diceComb){
            int[] combB = new int[N / 2];
            boolean[] other = new boolean[N];
            
            int index = 0;
            for (int choice : combA){
                other[choice] = true;
            }
            
            for (int i = 0; i < other.length; i++){
                if (!other[i]){
                    combB[index] = i;
                    index++;
                }
            }
            
            scoreA = new ArrayList<>();
            scoreB = new ArrayList<>();
            
            calScore(dice, 0, combA, 0, scoreA);
            calScore(dice, 0, combB, 0, scoreB);
            
            Collections.sort(scoreA);
            Collections.sort(scoreB);
            
            int winCnt = 0;
            
            for (Integer score : scoreA){
                int left = 0;
                int right = scoreB.size();
                
                while (left + 1 < right){
                    int mid = (left + right) / 2;
                    
                    if (score > scoreB.get(mid)){
                        left = mid;
                    } else right = mid;
                }
                
                winCnt += left;
            }
            
            if (winCnt > max) {
                max = winCnt;
                answer = combA;
            }
            
        }
        
        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        
        return answer;
    }
}