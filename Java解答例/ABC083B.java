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
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInEndIntNum = wSc.nextInt();
		var wInBetweenStart = wSc.nextInt();
		var wInBetweenEnd = wSc.nextInt();
		wSc.close();

		// 出力情報を生成し出力 **********
		var wOutSumNum = 0;

		for (int wIntNum = 1; wIntNum <= wInEndIntNum; wIntNum++) {
			var wSumBuffer = getSumDigNum(wIntNum);
			if (wInBetweenStart <= wSumBuffer && wSumBuffer <= wInBetweenEnd) {
				wOutSumNum += wIntNum;
			}
		}

		System.out.println(wOutSumNum);

		return;
	}

	private static int getSumDigNum(int pValue) {
		var wSumValue = 0;
		var wBufferNum = pValue;

		while (wBufferNum > 0) {
			wSumValue += wBufferNum % 10;
			wBufferNum /= 10;
		}
		return wSumValue;
	}
}
