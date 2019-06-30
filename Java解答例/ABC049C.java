/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC049C {

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
		String wStringT = "";
		// チェックキーワードを優先順に格納したコレクション(最終値 null はbreak条件)
		String[] wCheckValues = {"eraser", "erase", "dreamer", "dream", null};

		// 文字列Sの後方からキーワード終わりかを判定する
		// キーワードがnull(＝該当無し)の場合，即座にチェックを終了
		boolean wIsCheackBreak = false;
		while (wInStringS.length() > wStringT.length() && !wIsCheackBreak) {

			for (String wCheckValue : wCheckValues) {

				if (wCheckValue == null) {
					wIsCheackBreak = true;
					break;
				}

				if (wInStringS.endsWith(wCheckValue + wStringT)) {
					wStringT = wCheckValue + wStringT;
					break;
				}
			}
		}

		System.out.println(wInStringS.equals(wStringT) ? "YES" : "NO");
	}
}
