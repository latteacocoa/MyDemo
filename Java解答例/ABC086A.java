/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC086A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得
		var wInValue01 = wSc.nextInt();
		var wInValue02 = wSc.nextInt();
		wSc.close();

		// 出力情報を生成し出力
		var wOutValue01 = "Odd";
		if ((wInValue01 * wInValue02 % 2) == 0) {
			wOutValue01 = "Even";
		}

		System.out.println(wOutValue01);

		return;
	}
}
