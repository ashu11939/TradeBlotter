using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Trade_Blotter
{
    public class Item
    {
        public string currencyPair { get; set; }
        double bidPrice { get; set; }
        double offerPrice { get; set; }
        DateTime timeUpdated { get; set; }
        double quantity { get;  set;}
    }
}
