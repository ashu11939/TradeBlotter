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
    class Groups
    {
        [DataMember]
        public string  name { get; set; }
        [DataMember]
        public DateTime date { get; set; }
        [DataMember]
        public string message { get; set; }
        

        public Groups(string name, DateTime date, string message)
        {
            this.name = name;
            this.date = date;
            this.message = message;

        }
        
    }
}
