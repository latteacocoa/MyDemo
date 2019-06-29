/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC083B {

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
		for (int wIntNum = 1; wIntNum <= wInEndIntNum; wIntNum++) {
			int wSumBuffer = getSumDigNum(wIntNum);
			if (wInBetweenStart <= wSumBuffer && wSumBuffer <= wInBetweenEnd) {
				wOutSumNum += wIntNum;
			}
		}

		System.out.println(wOutSumNum);
	}

	/**
	 * 各桁の数値の和を取得
	 * @param pValue
	 * @return
	 */
	private static int getSumDigNum(int pValue) {
		int wSumValue = 0;
		int wBufferNum = pValue;

		while (wBufferNum > 0) {
			wSumValue += wBufferNum % 10;
			wBufferNum /= 10;
		}
		return wSumValue;
	}
}
