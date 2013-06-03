/*
 * 서로 싫어하지 않는 선수로 2팀을 구성한다.
 * 
 * 1. 전체 선수 인원 입력
 * 2. 싫어하는 선수가 있는 선수는 싫어하는 선수수와 선수 번호를 차례대로 입력한다.
 * 3. 선수는 최대 100명까지 가능하다.
 * 
 * (입력)
 * 5           설명 : 총 선수수
 * 1 1 2       설명 : 1번선수는 1명을 싫어하고 싫어하는 선수는 2번이다.
 * 2 1 3       설명 : 2번선수는 1명을 싫어하고 싫어하는 선수는 3번이다.
 * 3 0 0       설명 : 3번선수는 싫어하는 선수가 없다.
 * 4 2 2 3     설명 : 4번선수는 2명을 싫어하고 싫어하는 선수는 2,3번이다.
 * 5 1 4       설명 : 5번선수는 1명을 싫어하고 싫어하는 선수는 4번이다.
 * 
 * 위와 같이 입력(설명은 단지 입력에 대한 설명이고 숫자만을 입력한다)하면 결과는
 * 
 * A팀 : 1,5
 * B팀 : 2
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

class TeamMake {

	String arr[] = new String[100];
	int player = 0; /* 총 선수수 */
	
	int aT[]; /* A팀 */
	int bT[]; /* B팀 */
	int info[][]; /* 선수별 입력정보 */
	
	/*
	 * 입력
	 */
	protected void stdin() {
		
		String composition = "";
		int cnt = 0; /* 엔터기준 입력 횟수 */
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			while (true) {
				if (player > 0 && cnt > player) {
					break;
				}

				if (cnt < 1) {
					System.out.print("총 선수수를 입력하세요==> ");
					composition = br.readLine();
					player = Integer.parseInt(composition);
				} else {
					System.out.print(" " + cnt + "번 선수가 싫어하는 선수는==> ");
					composition = br.readLine();
					arr[cnt - 1] = composition;
				}

				cnt++;
			}
		} catch (Exception e) {
			System.err.println("예외상황 발생!");
			System.err.println("프로그램을 다시 실행해 주세요.");
			System.exit(0);
		}
		
		initTeam(player);
	}
	
	protected void fileIn(String filename) throws FileNotFoundException {
		int cnt = 0;
		Scanner s = new Scanner(new File(filename));
		player = Integer.parseInt(s.nextLine());
		while (s.hasNext()) {
			arr[cnt] = s.nextLine();
			cnt++;
		}
		initTeam(player);
	}
	
	protected String[] getArray() {
		return arr;
	}
	
	private void initTeam(int numberOfPlayers) {
		aT = new int[numberOfPlayers];
		bT = new int[numberOfPlayers];
		info = new int[numberOfPlayers][];
	}
	
	protected void buildDislikeMatrix() {
		/*
		 * 계산
		 */
		int tmp = 0;

		for (int i = 0; i < player; i++) {
			info[i] = new int[player];
			aT[i] = i + 1;

			for (int k = 0; k < player; k++) {
				if (arr[i].length() > tmp + 2) {
					info[i][k] = Integer.parseInt(arr[i].substring(tmp + 2,
							tmp + 3));
				}
				tmp += 2;
			}
			tmp = 0;
		}
	}
	
	protected int[][] getDislikeMatrix() {
		return info;
	}
	
	protected int[] getATeam() {
		return aT;
	}
	
	protected int[] getBTeam() {
		return bT;
	}
	
	protected void printResult() {
		/*
		 * 결과 출력
		 */
		System.out.print("A팀 ==> ");

		for (int k = 0; k < aT.length; k++) {
			if (aT[k] > 0) {
				System.out.print(" " + aT[k] + " ");
			}
		}

		System.out.print(" B팀 ==> ");

		for (int k = 0; k < bT.length; k++) {
			if (bT[k] > 0) {
				System.out.println(" " + bT[k] + " ");
			}
		}
	}
	
	protected void buildATeam() {
		int number = 1; /* 선수번호 */

		/*
		 * A팀구성
		 */
		for (int i = 0; i < player; i++) { /* 전체 선수수 반복 */
			if (aT[i] == number) {
				for (int j = 0; j < info[i][0]; j++) { /* 싫어하는 선수수 만큼 반복 */
					for (int k = 0; k < aT.length; k++) {
						if (aT[k] == info[i][j + 1]) {
							aT[k] = 0;
							bT[k] = info[i][j + 1];
						}
					}
				}
			}

			number++;
		}

	}
	
	public void buildBTeam() {
		int number = 1; /* 선수번호 초기화 */

		/*
		 * B팀구성
		 */
		for (int i = 0; i < player; i++) { /* 전체 선수수 반복 */
			if (bT[i] == number) {
				for (int j = 0; j < info[i][0]; j++) { /* 싫어하는 선수수 만큼 반복 */
					for (int k = 0; k < bT.length; k++) {
						if (bT[k] == info[i][j + 1]) {
							bT[k] = 0;
						}
					}
				}
			}

			number++;
		}
	}
	
	
	public void run() throws FileNotFoundException {
		fileIn("./input.txt");
		buildDislikeMatrix();
		buildATeam();
		buildBTeam();
		printResult();
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		TeamMake clazz = new TeamMake();
		clazz.run();
	}
}