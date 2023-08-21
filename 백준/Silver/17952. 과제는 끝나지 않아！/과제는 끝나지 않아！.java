import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static int A, T;
	static char bit; // 업무 유무
	static ArrayDeque<Work> stack = new ArrayDeque<>(); // 업무를 저장할 스택
	static int result; // 최종결과 저장
	
	static class Work{ // 업무의 정보를 저장할 클래스
		
		int A, T;
		
		Work(int A, int T){
			this.A = A;
			this.T = T;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		N = Integer.parseInt(br.readLine()); // N 분 입력
		Work cur = null; // Work 초기화
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			bit = st.nextToken().charAt(0); // 업무의 유무를 체크
			if (bit == '0') { // 업무가 주어지지 않았을 때
				if (cur != null) { // 현재 진행중인 업무가 있었을 때
					cur.T--; // 업무시간 1분 줄이기
					if (cur.T == 0) { // 업무를 처리했을때
						result +=  cur.A; // 업무 점수 추가
						cur = stack.pollFirst();// 기존에 하던 업무 가져오기
					}
				}
			}
			else // 업무가 주어졌을 때
			{
				A = Integer.parseInt(st.nextToken()); // 점수 입력
				T = Integer.parseInt(st.nextToken()); // 시간 입력
				if (cur != null) { // 현재 진행중인 업무가 있었을 때
					stack.offerFirst(cur); // 스택에 저장
				}
				cur = new Work(A, T - 1); // 새로 입력받은 업무를 저장.
				if (cur.T == 0) { // 1분만에 업무를 처리했을때
					result += cur.A; // 업무 점수 추가
					cur = stack.pollFirst(); // 기존에 하던 업무 가져오기
				}
			}
		}
		
		System.out.println(result); // 결과 출력
		br.close();
	}

}