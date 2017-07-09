using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello C#" + (args.Length > 0 ? args[0] : ""));


            // マスタ側ソース
            Console.ReadKey(true);

            return;
        }
    }
}
