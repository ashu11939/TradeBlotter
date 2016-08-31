using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Dynamic;
using System.IO;
using System.Net;
using System.Web.Script.Serialization;
using System.Runtime.Serialization.Json;
using System.Runtime.Serialization;
using System.Windows.Forms;


namespace Trade_Blotter
{
    //DataContractJsonSerializer arrayserializer = new DataContractJsonSerializer(typeof(Products[]));
    
    //DataGrid dataGrid = new DataGrid();
    /// <summary>
    /// Interaction logic for Price_Information.xaml
    /// </summary>
    public partial class PriceInformation : Window
    {
        List<Products> ProductList;
        String userName;
        String entitlement;
        public PriceInformation()
        {
            InitializeComponent();
   
        }

        public PriceInformation(String userName,String entitlement)
        {
            InitializeComponent();
            this.userName = userName;
            this.entitlement = entitlement;
            //MessageBox.Show("username: " );
        }

        private void Refresh(object sender, RoutedEventArgs e)
        {
            List<Products> ProductList;
            PriceInformation priceInformation = new PriceInformation();
            var client = new WebClient();
            //var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices");

            priceInformation = new PriceInformation();
            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices1?entitlement="+entitlement);
            Stream contentStream = GenerateStreamFromString(content1);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Products>));
            ProductList = (List<Products>)serializer.ReadObject(contentStream);
            //priceInformation.Show();
           // System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
           // dtDateTime = dtDateTime.AddMilliseconds(ProductList.)
            foreach(Products products in ProductList)
            {
                System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
                dtDateTime = dtDateTime.AddMilliseconds(products.timeUpdated).ToLocalTime();
                products.timeUpdate = dtDateTime;
            }
            
            dataGrid.ItemsSource = ProductList;
           // this.dataGrid.Columns[1].Visibile = false;
            //dataGrid.Columns[5].CellStyle= "MM/DD/YYYY HH:MM:SS";
            
        }

        public Stream GenerateStreamFromString(string s)
        {
            MemoryStream stream = new MemoryStream();
            StreamWriter writer = new StreamWriter(stream);
            writer.Write(s);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }

        

        private void GetNotes(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getNotes1?userName="+userName);
            string note = content.ToString();
            txtComments.Text= note;
        }

        private void SaveNotes(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/saveNotes1?userName=" + userName+"&notes="+txtComments.Text);

        }

        private void Search(object sender, RoutedEventArgs e)
        {
            List<Products> ProductList;
            PriceInformation priceInformation = new PriceInformation();
            var client = new WebClient();
            priceInformation = new PriceInformation();
            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices1?search="+txtSearch.Text+"&entitlement="+entitlement);
            Stream contentStream = GenerateStreamFromString(content1);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Products>));
            ProductList = (List<Products>)serializer.ReadObject(contentStream);
            
            //priceInformation.Show();
            dataGrid.ItemsSource = ProductList;
           
        }
    }
}
