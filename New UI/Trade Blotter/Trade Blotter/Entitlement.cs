using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Trade_Blotter
{
    class Entitlement
    {
        public string entitlementName { get; private set; }
        public int entitlementNumber { get; private set; }

        public Entitlement(string entitlementName,int entitlementNumber)
        {
            this.entitlementName = entitlementName;
            this.entitlementNumber = entitlementNumber;
        }


        public override string ToString()
        {
            return entitlementName +"with entitlementNumber="+  entitlementNumber;
        }
    }
}
