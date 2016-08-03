using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Dynamic;
using System.IO;
using System.Net;
using System.Web.Script.Serialization;
using System.Runtime.Serialization.Json;
using System.Runtime.Serialization;
using System.Windows.Forms;


namespace Trade_Blotter
{   [DataContract]
    class Message1
    {   [DataMember]
        public string message { get; set; }
        [DataMember]
        public string sender { get; set; }
        
    }
}
