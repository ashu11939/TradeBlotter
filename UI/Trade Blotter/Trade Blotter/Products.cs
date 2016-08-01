using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization.Json;
using System.Runtime.Serialization;

namespace Trade_Blotter
{
    
    [DataContract]
    class Products
    {
        string result;
        [DataMember]
        //public int tradeID { get; set; }
        //[DataMember]
        public char side { get; set; }
        [DataMember]
        public long timeUpdated { get; set; }
        [DataMember]
        public string tradeStatus { get; set; }
        [DataMember]
        public string product { get; set; }
        [DataMember]
        public int qty { get; set; }
        [DataMember]
        public double price { get; set; }
        [DataMember]
        public string firm { get; set; }
        [DataMember]
        public DateTime? timeUpdate  { get; set; }

    public Products(char side, long timeUpdated, string tradeStatus, string product, int qty,double price, string firm)
        {
            //this.TradeID = TradeID;
            this.side = side;
            this.timeUpdated = timeUpdated;
            this.tradeStatus = tradeStatus;
            this.product = product;
            this.qty = qty;
            this.price = price;
            this.firm = firm;

        }
        //public override string ToString()
        //{
        //    //return result=   firm +  price + product + qty + side + timeUpdated+ tradeStatus;
        //}
    }
}
