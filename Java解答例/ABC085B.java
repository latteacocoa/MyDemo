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
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInMochiNum = wScan.nextInt();
		ArrayList<Integer> wInMochis = new ArrayList<Integer>();
		for (int wIndex = 0; wIndex < wInMochiNum; wIndex++) {
			wInMochis.add(wScan.nextInt());
		}
		wScan.close();

		// 出力情報を生成し出力 **********
		int wMochiDansu = 0;
		Integer wMochiLastTime = Integer.MAX_VALUE;

		// 鏡餅の段数を求める
		// 入力された餅情報を降順に並び替え，前後の値の大小を判定する
		Collections.sort(wInMochis, Comparator.reverseOrder());
		for (Integer wMochi : wInMochis) {
			if (wMochi < wMochiLastTime) {
				wMochiDansu++;
			}
			wMochiLastTime = wMochi;
		}

		System.out.println(wMochiDansu);
	}
}
