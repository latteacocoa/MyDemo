/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC083B_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInEndIntNum = wScan.nextInt();		// 検査対象値
		var wInBetweenStart = wScan.nextInt();	// 検査範囲（自）
		var wInBetweenEnd = wScan.nextInt();		// 検査範囲（至）
		wScan.close();

		// 出力情報を生成し出力 **********
		int wOutSumNum = 0;

		// 検査対象値が検査範囲であるものの総和を求める
		for (Integer wIntNum = 1; wIntNum <= wInEndIntNum; wIntNum++) {
			int wSumBuffer = 0;

			// 数字の文字コードが連続していることを利用した方法
			for (char wCharNum : wIntNum.toString().toCharArray()) {
				wSumBuffer += wCharNum - '0';
			}
			if (wInBetweenStart <= wSumBuffer && wSumBuffer <= wInBetweenEnd) {
				wOutSumNum += wIntNum;
			}
		}

		System.out.println(wOutSumNum);
	}

}
