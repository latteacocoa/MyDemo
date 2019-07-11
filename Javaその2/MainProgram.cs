using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace batchSample
{
    class MainProgram
    {
        static readonly string wMyPath = Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
        static Logger myLogger = null;

        static void Main(string[] args)
        {
            // ロガーの生成 ******************************
            myLogger = new Logger(wMyPath);

            
            // 開始ログを出力する ******************************
            myLogger.put(LogType.Info, "処理を開始しました。");

            
            // コマンドライン引数のチェック ******************************
            if (args.Length == 0)
            {
                myLogger.put(LogType.Error, "コマンドライン引数に入力ＣＳＶファイル名が指定されていません。");
                myLogger.put(LogType.Error, "処理を終了(異常)しました。");
                return;
            }


            // コマンドライン引数(入力ファイル名)の値を退避 ******************************
            var wInputFiles = args;


            // 入力ファイル名の存在チェック ******************************
            foreach (var wInputFile in wInputFiles)
            {
                if (!File.Exists(wInputFile))
                {
                    myLogger.put(LogType.Error, $"該当ファイルが存在しません。[{wInputFile}]");
                    myLogger.put(LogType.Error, "処理を終了(異常)しました。");
                    return;
                }
            }


            // 入力ファイル全データに対し，集計処理の実施 ******************************

            // 入力ファイルの全データを「Uriage」クラス形式で取得
            IEnumerable<Uriage> wInputLines = null;
            try
            {
                // 全ファイルの全行を読取
                wInputLines = from wFile in wInputFiles
                              from wLine in File.ReadLines(wFile, Encoding.GetEncoding("shift_jis")).Skip(1)
                              let wSplitLine = wLine.Split(',')     // CSVデータの分割
                              select new Uriage                     // 「Uriage」オブジェクトの生成
                              {
                                  SitenCd = wSplitLine[0],
                                  BusyoCd = wSplitLine[1],
                                  KanjoKamokuCd = wSplitLine[2],
                                  Kingaku = ParseLong(wSplitLine[3])
                              };
            }
            catch (Exception ex)
            {
                myLogger.put(LogType.Error, $"入力ファイルの読み込みに失敗しました。[{ex.ToString()}]");
                myLogger.put(LogType.Error, "処理を終了(異常)しました。");
                return;
            }

            var wCheckResult = wInputLines.FirstOrDefault(x => x.Kingaku == null);
            if (wCheckResult != null)
            {
                myLogger.put(LogType.Error, $"金額の値が不正です。支店コード：[{wCheckResult.SitenCd}] 部署コード：[{wCheckResult.BusyoCd}] 勘定科目コード：[{wCheckResult.KanjoKamokuCd}]");
                myLogger.put(LogType.Error, "処理を終了(異常)しました。");
                return;
            }

            // 取得データを「支店コード」「勘定科目コード」毎の金額を集計する
            var wSumData = wInputLines
                .GroupBy(x => new { x.SitenCd, x.KanjoKamokuCd })       // 「支店コード」「勘定科目コード」でグループ化
                .Select(x => new                                        // 「支店コード」「勘定科目コード」毎の集計金額オブジェクトを生成
                {
                    SitenCd = x.Key.SitenCd,
                    KanjoKamokuCd = x.Key.KanjoKamokuCd,
                    SumKingaku = x.Sum(y => y.Kingaku)
                });


            // 「一時変数.集計済データ」全データをファイル出力 ******************************
            try
            {
                var wOutputFile = Path.Combine(Path.GetDirectoryName(wInputFiles[0]), "Uriage_Out.csv");                  // 出力ファイル名
                using (var wWriter = new StreamWriter(wOutputFile, false, Encoding.GetEncoding("shift_jis")))
                {
                    wWriter.NewLine = "\n";                                                                               // 改行コード指定
                    wWriter.WriteLine("支店コード,勘定科目コード,金額");                                                  // ヘッダ出力
                    wSumData.ToList().ForEach(x => wWriter.WriteLine($"{x.SitenCd},{x.KanjoKamokuCd},{x.SumKingaku}"));   // データ出力
                }
            }
            catch (Exception ex)
            {
                myLogger.put(LogType.Error, $"出力ファイルへの書き込みに失敗しました。[{ex.ToString()}]");
                myLogger.put(LogType.Error, "処理を終了(異常)しました。");
                return;
            }


            // 終了ログを出力する ******************************
            myLogger.put(LogType.Info, "処理を終了(正常)しました。");
        }

        /// <summary>
        /// 数値変換（独自）
        /// </summary>
        /// <param name="pValue"></param>
        /// <returns>longへ変換した値を返却。変換不可の場合，nullを返却。</returns>
        private static long? ParseLong(string pValue)
        {
            long wBuff = 0;
            if (long.TryParse(pValue, out wBuff))
            {
                return wBuff;
            } else
            {
                return null;
            }
        }
    }
    /// <summary>
    /// １行の売上データを示すクラス
    /// </summary>
    class Uriage
    {
        public string SitenCd { get; set; }
        public string BusyoCd { get; set; }
        public string KanjoKamokuCd { get; set; }
        public long? Kingaku { get; set; }
    }

    /// <summary>
    /// ログ出力用
    /// </summary>
    class Logger
    {
        private string _logFile = "";

        public Logger(string pPath)
        {
            this._logFile = Path.Combine(pPath, "Uriage.log");
        }

        public void put(LogType pLogType, string pMessage)
        {
            var wCurrentDateTime = DateTime.Now;
            var wPutData = $"[{wCurrentDateTime.ToString("yyyy/MM/dd")}]" +
                            $"[{wCurrentDateTime.ToString("HH:mm:ss")}]" +
                            $"[{pLogType.GetName()}]" +
                            $"[{pMessage}]";

            File.AppendAllLines(this._logFile, new[] { wPutData }, Encoding.GetEncoding("shift_jis"));
        }
    }

    /// <summary>
    /// ログタイプと名前取得ヘルパ定義
    /// </summary>
    enum LogType { Info, Error }
    static class LogTypeExt
    {
        public static string GetName(this LogType pLogType)
        {
            return new[] { "情　報", "エラー" }[(int)pLogType];
        }
    }
}
