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
public class ABC081B_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 標準入力取得用オブジェクト
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得
		int wInValue01 = wScan.nextInt();
		ArrayList<Integer> wInValues = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInValue01; wIndex++) {
			wInValues.add(wScan.nextInt());
		}
		wScan.close();

		// 出力情報を生成し出力
		int wOutValue01 = 0;

		// 入力された数値全てが2で割れる回数を算出
		while (wInValues.stream().allMatch(x -> x % 2 == 0)) {
			wOutValue01++;		// 操作回数を編集

			// 全ての要素を２で除算
			ArrayList<Integer> wBufferValues = new ArrayList<Integer>();
			wInValues.forEach(x -> wBufferValues.add( x / 2));
			wInValues = wBufferValues;
		}

		System.out.println(wOutValue01);

	}
}
