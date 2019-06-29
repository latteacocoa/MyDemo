/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC049C_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		String wInStringS = wScan.next();
		wScan.close();

		// 出力情報を生成し出力 **********
		String wStringT = wInStringS.replace("eraser", "").replace("erase", "").
										replace("dreamer", "").replace("dream", "");

		System.out.println(wStringT.equals("") ? "YES" : "NO");
	}
}
