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
public class ABC086C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInValueN = wScan.nextInt();
		ArrayList<int[]> wInPositions = new ArrayList<int[]>();
		for (int wIndex = 0; wIndex < wInValueN; wIndex++) {
			wInPositions.add(new int[] {wScan.nextInt(), wScan.nextInt(), wScan.nextInt()});
		}
		wScan.close();

		// 出力情報を生成し出力 **********
		// 以下の考えでロジックを組む
		// ①距離に対し使用時間が少ない は，到達不可
		// ②使用時間と距離の偶奇が一致しない は，到達不可
		// 「距離」はX座標とY座標の前回との差の絶対値を足したものとし考える

		int[] wPrePosition = {0, 0, 0};
		String wOutResult = "YES";

		for (int[] wCurPosition : wInPositions) {

			int wDiffT = wCurPosition[0] - wPrePosition[0];	// 前回との時刻の差
			int wDiffXY = Math.abs(wCurPosition[1] - wPrePosition[1]) +
							Math.abs(wCurPosition[2] - wPrePosition[2]); // 前回からの距離

			// 使用可能時間(前回との時刻の差)と距離(座標 XとYの前回との絶対値の差)との比較
			if (wDiffT < wDiffXY) {
				// 使用時間が距離に対し少ない場合

				wOutResult = "NO";
			}

			// 使用時間と距離の偶奇を比較
			if ((wDiffT % 2) != (wDiffXY % 2)) {
				// 使用時間と距離の偶奇が不一致

				wOutResult = "NO";
			}

			// 前回値を更新
			System.arraycopy(wCurPosition, 0, wPrePosition, 0, wCurPosition.length);
		}
		System.out.println(wOutResult);
	}
}
