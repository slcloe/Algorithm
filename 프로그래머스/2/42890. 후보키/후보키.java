import java.util.*;

class Solution {
    String[][] r;
    int N, M;
    int answer = 0;
    int b[];
    void comb(int n, int start) {
        // System.out.println(n + " " + Arrays.toString(b));
        // 확인
        if (isminimun(n)) {
            answer++;
            // System.out.println("** " + n + " " + Arrays.toString(b));
            return;
        }
        
        if (start == M)
            return;
        for(int i = start; i < M; i++) {
            b[n] = i;
            comb(n + 1, i + 1);
        }
        
    }
    
    boolean isminimun(int n) {
        if (!isKey(n, -1)) return false;
        
        for(int i = 0; i < n; i++) {
            if (isKey(n, i)) return false;
        }
        return true;
        
    }
    
    boolean isKey(int n, int j) {
        HashSet<String> set = new HashSet<>();
        
        for(String [] tuple : r) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                if (j == i) continue;
                sb.append(tuple[b[i]]).append(' ');
            }
            set.add(sb.toString());
            
        }
        
        if (set.size() == N)
            return true;
        else return false;
    }
    
    
    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        b = new int[M];
        r = relation;
        
        comb(0, 0);
        return answer;
    }
}