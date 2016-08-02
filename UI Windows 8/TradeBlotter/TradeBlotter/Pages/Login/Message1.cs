using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace TradeBlotter.Pages.Login
{
    [DataContract]
    class Message1
    {
        [DataMember]
        public string message { get; set; }
        [DataMember]
        public string sender { get; set; }
        
    }
}
