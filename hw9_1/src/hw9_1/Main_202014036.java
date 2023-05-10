package hw9_1;

import java.util.Scanner;

public class Main_202014036 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int k = sc.nextInt(); // 배낭의 수용 가능한 무게.
		int n = sc.nextInt(); // 물건의 개수.

		int kA[] = new int[n]; // 물건 무게.
		int nA[] = new int[n]; // 물건 가치.

		for (int i = 0; i < n; i++) {
			kA[i] = sc.nextInt(); // 각 물건의 무게를 반복문을 이용해 입력.
			nA[i] = sc.nextInt(); // 각 물건의 가치를 반복문을 아용해 입력.
		}
		// 2차원 배열 dp를 생성하고 초기화.
		int[][] dp = new int[n + 1][k + 1];

		// 생성한 배열 dp를 채운다.
		for (int i = 1; i <= n; i++) { // i번째까지의 물건.
			for (int j = 1; j <= k; j++) { // 베낭의 수용량.
				if (kA[i - 1] > j) { // i번째의 물건이 베낭에 들어가지 않는 경우.
					dp[i][j] = dp[i - 1][j]; // 베낭의 가치를 선정할 수 있는 최대 가치로 유지한다.
				} else { // 반대로, i번째 물건이 배낭에 들어갈 수 있는 경우.
							// 교수님의 설명에서는 3번째를 포함한 경우와 포함하지 않은 경우를 비교하는 것과 같음.
					dp[i][j] = Math.max(dp[i - 1][j],
							dp[i - 1][j - kA[i - 1]] + nA[i - 1]); /*
																	 * i번째 물건이 배낭에 들어갈 수 있을 때와 그렇지 않을 때 중에서 더 큰 가치를 선택함.
																	 */
				}
			}
		}
		// 최고의 가치를 충족시킬 물건 선택하기.
		int select[] = new int[n]; // 선택한 물건을 저장할 새로운 배열, 앞서 선언한 물건의 개수가 넣을 수 있는 가장 큰 크기이기 때문에 배열의 크기를 n으로 설정.
		int KG = k; // 현재 물건이 들어간 베낭의 무게
		int R = n; // 역순으로 물건을 선택했는지를 판단. ## 역순으로 찾아야 한다는 것은 구글링을 통해 알게 되었음.

		while (R > 0) { // 물건이 없을 떄까지 반복.
			if (dp[R][KG] != dp[R - 1][KG]) { // R이 선택된 경우 즉, 마지막 물건이 선택된 경우를 말한다.
				select[R - 1] = 1; // 물건 R을 선택.
				KG = KG - kA[R - 1]; // 선택된 R로 인해서 베낭의 무게를 재설정.
			}
			R--; // 다음 물건으로 이동. ex) 물건이 7개면 7번째 물건을 마치고 6번째 물건으로 이동.
		}
		// 최대 가치 출력.
		System.out.println("\n최대 가치 = " + dp[n][k]);
	}
}