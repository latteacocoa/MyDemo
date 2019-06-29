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
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInCardNum = wScan.nextInt();
		ArrayList<Integer> wInCards = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInCardNum; wIndex++) {
			wInCards.add(wScan.nextInt());
		}
		wScan.close();

		// 出力情報を生成し出力 **********
		int wSumCardNumOfAlice = 0;
		int wSumCardNumOfBob = 0;
		boolean wIsAlice = true;

		// カードを降順に並び替え，Aliceから先に交互にカードを取得したときの総和を求める
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
	}
}
