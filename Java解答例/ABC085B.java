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
public class ABC085B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInMochiNum = wSc.nextInt();
		var wInMochis = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInMochiNum; wIndex++) {
			wInMochis.add(wSc.nextInt());
		}
		wSc.close();

		// 出力情報を生成し出力 **********
		var wMochiDansu = 0;
		Integer wMochiLastTime = Integer.MAX_VALUE;

		Collections.sort(wInMochis, Comparator.reverseOrder());

		for (Integer wMochi : wInMochis) {
			if (wMochi < wMochiLastTime) {
				wMochiDansu++;
			}
			wMochiLastTime = wMochi;
		}

		System.out.println(wMochiDansu);

		return;
	}
}
