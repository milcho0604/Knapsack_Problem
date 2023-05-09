package hw9_1;

import java.util.Scanner;

public class Main_202014036 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt(); // 배낭의 수용 가능한 무게.
        int n = sc.nextInt(); // 물건의 개수.

        int kA[] = new int[n]; // 물건 무게.
        int nA[] = new int [n]; // 물건 가치.

        for(int i = 0; i<n; i++) {
            kA[i] = sc.nextInt(); // 각 물건의 무게를 반복문을 이용해 입력.
            nA[i] = sc.nextInt(); // 각 물건의 가치를 반복문을 아용해 입력.
        }
        // 2차원 dp배열을 생성하고 초기화.
        int [][] dp = new int[n+1][k+1];

        // 생성한 dp배열을 채운다.
        for(int i = 0; i<=n; i++) { // i번째까지의 물건.
        	for(int j = 0; j<=k; j++) { // 베낭의 수용량.
        		if(kA[i-1] > j) { // i번째의 물건이 베낭에 들어가지 않는 경우.
        			dp[i][j] = dp[i-1][j]; // 베낭의 가치를 선정할 수 있는 최대 가치로 유지한다.
        		}
        		else{ // 반대로, i번째 물건이 배낭에 들어갈 수 있는 경우.
        			// 교수님의 설명에서는 3번째를 포함한 경우와 포함하지 않은 경우를 비교하는 것과 같음.
        			dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-kA[i-1]] + nA[i-1]); 	// i번째 물건이 배낭에 들어갈 수 있을 때와 그렇지 않을 때
        																			// 중에서 더 큰 가치를 선택함.
        		}
        	}
        }
    }
}