package batchSample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author
 *
 */
public class MainProgram {

	static final String wMyPath = new File(".").getAbsoluteFile().getParentFile().getPath();
	static Logger myLogger = null;

	public static void main(String[] args) {

		// ロガーの生成 ******************************
		myLogger = new Logger(wMyPath);


		// 開始ログを出力する ******************************
		myLogger.put(logType.Info, "処理を開始しました。");

		// コマンドライン引数のチェック ******************************
		if (args.length == 0) {
			myLogger.put(logType.Error, "コマンドライン引数に入力ＣＳＶファイル名が指定されていません。");
			myLogger.put(logType.Error, "処理を終了(異常)しました。");
			return;
		}


		// コマンドライン引数(入力ファイル名)の値を退避 ******************************
		ArrayList<Path> wInputFiles = new ArrayList<>();
		for (String arg : args) {
			wInputFiles.add(Paths.get(arg));
		}


		// 入力ファイル名の存在チェック ******************************
		for (Path wInputFile : wInputFiles) {
			if (Files.notExists(wInputFile)) {
				myLogger.put(logType.Error, String.format("該当ファイルが存在しません。[%s]", wInputFile.toString()));
				myLogger.put(logType.Error, "処理を終了(異常)しました。");
				return;
			}
		}


		// 入力ファイル全データに対し，集計処理の実施 ******************************

		// 入力ファイルの全データを「Uriage」クラス形式で取得
		ArrayList<Uriage> wUriageDatas = new ArrayList<>();
		for (Path wInputFile : wInputFiles) {
			try (final InputStream iStream = Files.newInputStream(wInputFile);
					final InputStreamReader isReader = new InputStreamReader(iStream, Charset.forName("MS932"));
					final LineNumberReader lineReader = new LineNumberReader(isReader)) {

				// １入力ファイルの全データ読込み
				String wLine = "";
				lineReader.readLine();	// 最初の１行は読み飛ばし
				while ((wLine = lineReader.readLine()) != null) {

					// １行をカンマ区切りで分割し取得（金額については，数値変換時のエラー処理を実施）
					String[] wBuffer = wLine.split(",");
					long wKingaku = 0;
					try {
						wKingaku = Long.parseLong(wBuffer[3]);
					} catch (NumberFormatException e) {
						myLogger.put(logType.Error, String.format("金額の値が不正です。ファイル名：[%s] 行：[%d] データ：[%s]", wInputFile, lineReader.getLineNumber(), wLine));
						myLogger.put(logType.Error, "処理を終了(異常)しました。");
						return;
					}
					wUriageDatas.add(new Uriage(wBuffer[0], wBuffer[1], wBuffer[2], wKingaku));
				}
			} catch (final IOException e) {
			    // 例外処理
				myLogger.put(logType.Error, e.getMessage());
				myLogger.put(logType.Error, "処理を終了(異常)しました。");
				return;
			}
		}

		// 取得データを集計キーとなる項目でソート
		wUriageDatas.sort((Uriage x, Uriage y) -> {
							int wComp1 = x.getSitenCd().compareTo(y.getSitenCd());
							int wComp2 = x.getKanjoKamokuCd().compareTo(y.getKanjoKamokuCd());
							if (wComp1 != 0) {
								return wComp1;
							} else {
								return wComp2;
							}
						});
//				ソートはこれでもＯＫ
//						↓
//		wUriageDatas.stream().sorted(Comparator.comparing(Uriage::getSitenCd).thenComparing(Uriage::getKanjoKamokuCd));

		// 取得データを「支店コード」「勘定科目コード」毎の金額を集計する
		ArrayList<Uriage> wSumUriage = new ArrayList<>();
		String wPreSitenCd = "";				// 前回値集計キー(支店コード)
		String wPreKanjoKamokuCd = "";		// 前回値集計キー(勘定科目コード)
		for (Uriage wUriage : wUriageDatas) {

			// 集計キー毎に「金額」を集計
			if (wPreSitenCd.equals(wUriage.getSitenCd()) &&
				wPreKanjoKamokuCd.equals(wUriage.getKanjoKamokuCd())) {
				// 集計キーが同じ

				// 「一時変数.集計済データ」の最終値に金額を加算
				long wSumKingaku = wSumUriage.get(wSumUriage.size() - 1).getKingaku() + wUriage.getKingaku();
				wSumUriage.get(wSumUriage.size() - 1).setKingaku(wSumKingaku);
			} else {
				// 集計キーがブレイク

				// 新規集計キーの「Uriage」オブジェクトを「一時変数.集計済データ」に退避
				wSumUriage.add(new Uriage(wUriage.getSitenCd(), "", wUriage.getKanjoKamokuCd(), wUriage.getKingaku()));
			}

			// 集計キーを前回値に退避
			wPreSitenCd = wUriage.getSitenCd();
			wPreKanjoKamokuCd = wUriage.getKanjoKamokuCd();
		}

		// 「一時変数.集計済データ」全データをファイル出力 ******************************
		Path wOutputFile = Paths.get(wInputFiles.get(0).getParent().toString(), "Uriage_Out.csv");
		try (final BufferedWriter bWriter = Files.newBufferedWriter(wOutputFile, Charset.forName("MS932"))) {
			bWriter.write("支店コード,勘定科目コード,金額\n");
			for (Uriage wUriage : wSumUriage) {
				String wBuffer = String.format("%s,%s,%d\n",
									wUriage.getSitenCd(), wUriage.getKanjoKamokuCd(), wUriage.getKingaku());
				bWriter.write(wBuffer);
			}
		} catch (IOException e) {
		    // 例外処理
			myLogger.put(logType.Error, e.getMessage());
			myLogger.put(logType.Error, "処理を終了(異常)しました。");
			return;
		}


		// 終了ログを出力する ******************************
		myLogger.put(logType.Info, "処理を終了(正常)しました。");
	}
}

/**
 * @author
 * 売上情報を表すクラス
 */
class Uriage {
	private String sitenCd = "";
	private String busyoCd = "";
	private String kanjoKamokuCd = "";
	private long kingaku = 0;

	public Uriage(String pSitenCd, String pBusyoCd, String pKanjoKamokuCd, long pKingaku) {
		this.sitenCd = pSitenCd;
		this.busyoCd = pBusyoCd;
		this.kanjoKamokuCd = pKanjoKamokuCd;
		this.kingaku = pKingaku;
	}

	String getSitenCd() {
		return sitenCd;
	}
	void setSitenCd(String pSitenCd) {
		sitenCd = pSitenCd;
	}
	String getBusyoCd() {
		return busyoCd;
	}
	void setBusyoCd(String pBusyoCd) {
		busyoCd = pBusyoCd;
	}
	String getKanjoKamokuCd() {
		return kanjoKamokuCd;
	}
	void setKanjoKamokuCd(String pKanjoKamokuCd) {
		kanjoKamokuCd = pKanjoKamokuCd;
	}
	long getKingaku() {
		return kingaku;
	}
	void setKingaku(long pKingaku) {
		kingaku = pKingaku;
	}
}


/**
 * @author
 * ログ出力用クラス
 */
class Logger {

	private Path logFile = null;
	public Logger(String pPath) {
		this.logFile = Paths.get(pPath, "Uriage.log");
	}

	public void put(logType pLogType, String pMessage) {
		Date wSysDate = new Date();
		String wSysYmd = new SimpleDateFormat("yyyy/MM/dd").format(wSysDate);
		String wSysHms = new SimpleDateFormat("HH:mm:ss").format(wSysDate);

		String wPutData = String.format("[%s][%s][%s][%s]",
				wSysYmd,
				wSysHms,
				pLogType.getString(),
				pMessage);

		try (final BufferedWriter bWriter = Files.newBufferedWriter(this.logFile,
																		Charset.forName("MS932"),
																		StandardOpenOption.CREATE,
																		StandardOpenOption.APPEND);
			final PrintWriter pWriter = new PrintWriter(bWriter, true)) {
			    pWriter.println(wPutData);
		} catch (final IOException e) {
		    // 例外処理
			e.printStackTrace();
		}
	}
}

/**
 * @author
 * ログ出力タイプを示す列挙
 */
enum logType {
	Info("情　報"),
	Error("エラー");

	private final String name;

	logType(String pName) { this.name = pName; }

	public String getString() { 	return this.name; }
}
