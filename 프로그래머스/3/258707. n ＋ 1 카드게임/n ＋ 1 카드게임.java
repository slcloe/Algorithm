import java.util.*;

class Solution {
    Set<Integer> card, pick;
    int coins, sum;
    
    boolean pick_0() {
        for(int n : card) {
            if (card.contains(sum - n)) {
                card.remove(n);
                card.remove(sum - n);
                // System.out.println(n);
                return true;
            }
        }
        return false;
    }
    
    boolean pick_1() {
        if (coins == 0)
            return false;
        
        for(int n : card) {
            if (pick.contains(sum - n)) {
                pick.remove(sum - n);
                card.remove(n);
                coins--;
                // System.out.println(n);
                return true;
            }
        }
        return false;
    }
    
    boolean pick_2() {
        if (coins <= 1)
            return false;
        
        for (int n : pick) {
            if (pick.contains(sum - n)) {
                pick.remove(sum - n);
                pick.remove(n);
                coins -= 2;
                // System.out.println(n);
                return true;
            }
        }
        
        return false;
    }
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int N = cards.length;
        coins = coin;
        
        card = new HashSet<>();
        pick = new HashSet<>();
        
        int idx = N / 3;
        for (int i = 0; i < idx; i++) {
            card.add(cards[i]);
        }
        
        sum = N + 1;
        int i;
        for (i = idx; i < N; i += 2) {
            answer++;
            pick.add(cards[i]);
            pick.add(cards[i + 1]);
        
            if (pick_0()) continue;
            if (pick_1()) continue;
            if (pick_2()) continue;
            
            break;
        }
        if (i == N) answer++;
        
        return answer;
    }
}