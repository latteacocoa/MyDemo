/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC087B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInCount500Yen = wScan.nextInt();
		int wInCount100Yen = wScan.nextInt();
		int wInCount50Yen = wScan.nextInt();
		int wInGoalPrice = wScan.nextInt();
		wScan.close();

		// 出力情報を生成し出力 **********
		// 各コインの枚数 総当たりで確認する
		int wOutMethodCount = 0;
		for (int wIndex500Yen = 0; wIndex500Yen < wInCount500Yen + 1; wIndex500Yen++) {
			for (int wIndex100Yen = 0; wIndex100Yen < wInCount100Yen + 1; wIndex100Yen++) {
				for (int wIndex50Yen = 0; wIndex50Yen < wInCount50Yen + 1; wIndex50Yen++) {
					if ((500 * wIndex500Yen + 100 * wIndex100Yen + 50 * wIndex50Yen) == wInGoalPrice) {
						wOutMethodCount++;
					}
				}
			}
		}

		System.out.println(wOutMethodCount);
	}
}
