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
		// 標準入力取得用オブジェクト
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得
		var wInValue01 = wSc.nextInt();
		var wInValues = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInValue01; wIndex++) {
			wInValues.add(wSc.nextInt());
		}
		wSc.close();

		// 出力情報を生成し出力
		var wOutValue01 = 0;

		// 解答案１
		boolean wIsAllEven = false;
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


//		// 解答案２
//		while (wInValues.stream().allMatch(x -> x % 2 == 0)) {
//			wOutValue01++;		// 操作回数を編集
//
//			// 全ての要素を２で除算
//			var wBufferValues = new ArrayList<Integer>();
//			wInValues.forEach(x -> wBufferValues.add( x / 2));
//			wInValues = wBufferValues;
//		}


		System.out.println(wOutValue01);

		return;
	}
}
