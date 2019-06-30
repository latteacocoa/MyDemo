/**
 *
 */
package myAtCoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC081B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInValue01 = wScan.nextInt();
		ArrayList<Integer> wInValues = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInValue01; wIndex++) {
			wInValues.add(wScan.nextInt());
		}
		wScan.close();

		// 出力情報を生成し出力 **********
		int wOutValue01 = 0;
		boolean wIsAllEven = false;

		// 入力された数値全てが2で割れる回数を算出
		do {

			// 全ての要素が偶数か判定
			wIsAllEven = true;
			for (int wIndex = 0; wIndex < wInValues.size(); wIndex++) {
				if ((wInValues.get(wIndex) % 2) == 0) {
					wInValues.set(wIndex, wInValues.get(wIndex) / 2);
				} else {
					// 奇数が含まれる場合

					// 偶数判定結果をオフにし、ループを抜ける
					wIsAllEven = false;
					break;
				}
			}

			// 全ての要素判定から操作回数の編集
			if (wIsAllEven) {
				wOutValue01++;
			}
		} while (wIsAllEven);

		System.out.println(wOutValue01);
	}
}
