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
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInEndIntNum = wSc.nextInt();
		var wInBetweenStart = wSc.nextInt();
		var wInBetweenEnd = wSc.nextInt();
		wSc.close();

		// 出力情報を生成し出力 **********
		var wOutSumNum = 0;

		for (Integer wIntNum = 1; wIntNum <= wInEndIntNum; wIntNum++) {
			var wSumBuffer = 0;

			// 数字の文字コードが連続していることを利用した方法
			for (char wCharNum : wIntNum.toString().toCharArray()) {
				wSumBuffer += wCharNum - '0';
			}
			if (wInBetweenStart <= wSumBuffer && wSumBuffer <= wInBetweenEnd) {
				wOutSumNum += wIntNum;
			}
		}

		System.out.println(wOutSumNum);

		return;
	}

}
