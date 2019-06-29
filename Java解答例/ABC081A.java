/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC081A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得
		var wInValue01 = wSc.next();
		wSc.close();

		// 出力情報を生成し出力
		int wOutValue01 = 0;
		for (char wCharValue : wInValue01.toCharArray()) {
			if (wCharValue == '1') {
				wOutValue01++;
			}
		}

		System.out.println(wOutValue01);
		return;
	}
}
