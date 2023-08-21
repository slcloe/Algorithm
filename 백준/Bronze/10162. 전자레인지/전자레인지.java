import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		// 입력할 결과를 strindbuilder 에 저장해서 한번에 출력할 예정
		StringBuilder sb = new StringBuilder();
		
		
		N = Integer.parseInt(br.readLine()); // T 초 입력 
		sb.append(N / 300).append(" "); // 5분 == 300초  5분으로 추가할 수 있는 최대 수를 추가해둔다. 
		N %= 300;// 이후 나머지를 N 에 저장
		
		sb.append(N / 60).append(" "); // 1분 == 60초  1분으로 추가할 수 있는 최대 수를 추가해둔다. 
		N %= 60;// 이후 나머지를 N 에 저장
		
		sb.append(N / 10).append(" "); // 10초   10초으로 추가할 수 있는 최대 수를 추가해둔다. 
		N %= 10;// 이후 나머지를 N 에 저장
		
		if (N == 0) // 타이머로 정확히 입력받은 시간을 맞출 수 있는 경우
			System.out.println(sb.toString());
		else // 입력받은 시간을 맞출 수 없는 경우
			System.out.println(-1);
		br.close();
	}

}