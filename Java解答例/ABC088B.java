/**
 *
 */
package myAtCoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC088B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInCardNum = wSc.nextInt();
		var wInCards = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInCardNum; wIndex++) {
			wInCards.add(wSc.nextInt());
		}
		wSc.close();

		// 出力情報を生成し出力 **********
		var wSumCardNumOfAlice = 0;
		var wSumCardNumOfBob = 0;
		var wIsAlice = true;

		Collections.sort(wInCards, Comparator.reverseOrder());

		for (Integer wCard : wInCards) {
			if (wIsAlice) {
				wSumCardNumOfAlice += wCard;
				wIsAlice = false;
			} else {
				wSumCardNumOfBob += wCard;
				wIsAlice = true;
			}
		}


		System.out.println(wSumCardNumOfAlice - wSumCardNumOfBob);

		return;
	}
}
