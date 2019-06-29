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
public class ABC085B_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInMochiNum = wScan.nextInt();
		ArrayList<Integer> wInMochis = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInMochiNum; wIndex++) {
			wInMochis.add(wScan.nextInt());
		}
		wScan.close();

		// 出力情報を生成し出力 **********
		// 重複する数値を除き，その数が段数となる
		long wMochiDansu = wInMochis.stream().distinct().count();

		System.out.println(wMochiDansu);
	}
}
