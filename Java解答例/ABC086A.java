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
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInValue01 = wScan.nextInt();
		int wInValue02 = wScan.nextInt();
		wScan.close();

		// 出力情報を生成し出力 **********
		String wOutValue01 = "Odd";
		if ((wInValue01 * wInValue02 % 2) == 0) {
			wOutValue01 = "Even";
		}

		System.out.println(wOutValue01);
	}
}
