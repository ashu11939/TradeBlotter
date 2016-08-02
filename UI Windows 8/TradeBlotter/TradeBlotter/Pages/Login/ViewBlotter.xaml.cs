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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;
using System.Threading;
using System.Net;
using System.IO;
using System.Runtime.Serialization.Json;

namespace TradeBlotter.Pages.Login
{
    /// <summary>
    /// Interaction logic for ViewBlotter.xaml
    /// </summary>
    public partial class ViewBlotter : UserControl
    {

        List<Products> ProductList;
        String userName;
        String entitlement;

        public ViewBlotter()
        {
            InitializeComponent();
            chkAutoRefresh.IsChecked = true;
            Task.Factory.StartNew(() =>
            {
                BeginInvoke();
            });
            DispatcherTimer timer = new DispatcherTimer();
            timer.Tick += new EventHandler(timer_Tick);
            timer.Interval = TimeSpan.FromSeconds(10);
            timer.Start();
            
        }
        
        //Invoke function for timer
        public void BeginInvoke()
        {
            DispatcherOperation op = Dispatcher.BeginInvoke((Action)(() =>
            {
                refresh();
            }));
        }

        void timer_Tick(object sender, EventArgs e)
        {
            if (chkAutoRefresh.IsChecked == true)
            {
                refresh();
            }

        }

        private void refresh()
        {
            List<Products> ProductList;
            //ViewBlotter priceInformation = new ViewBlotter();
            var client = new WebClient();

            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices1?entitlement=" + MyConstants.entitlement);
            Stream contentStream = GenerateStreamFromString(content1);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Products>));

            ProductList = (List<Products>)serializer.ReadObject(contentStream);
            foreach (Products products in ProductList)
            {
                System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
                dtDateTime = dtDateTime.AddMilliseconds(products.timeUpdated).ToLocalTime();
                products.timeUpdate = dtDateTime;
            }

            dataGrid.ItemsSource = ProductList;

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

        //on click SaveNotes
        private void SaveNotes(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/saveNotes1?userName=" + MyConstants.userName + "&notes=" + txtComments.Text);
        }

        //on click Refresh Button
        private void Refresh(object sender, RoutedEventArgs e)
        {
            refresh();
        }

        //On Click Search Button
        private void Search(object sender, RoutedEventArgs e)
        {
            List<Products> ProductList;
            //ViewBlotter priceInformation = new ViewBlotter();
            var client = new WebClient();
            //priceInformation = new ViewBlotter();
            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices1?search=" + txtSearch.Text + "&entitlement=" + MyConstants.entitlement);
            Stream contentStream = GenerateStreamFromString(content1);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Products>));
            ProductList = (List<Products>)serializer.ReadObject(contentStream);
            foreach (Products products in ProductList)
            {
                System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
                dtDateTime = dtDateTime.AddMilliseconds(products.timeUpdated).ToLocalTime();
                products.timeUpdate = dtDateTime;
            }
            //priceInformation.Show();
            dataGrid.ItemsSource = ProductList;

        }

        //On Click GetNotes
        private void GetNotes(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getNotes1?userName=" + MyConstants.userName);
            string note = content.ToString();
            txtComments.Text = note;
        }

        //On Click Sort
        private void Sort(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            string sortString = cmbSort.Text;
            ComboBoxItem cbi = (ComboBoxItem)cmbSort.SelectedItem;
            sortString = cbi.Content.ToString();

            var content2 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getSortedPrices?sort=" + sortString + "&entitlement=" + MyConstants.entitlement + "&value=" + txtQuantity.Text);
            Stream contentStream = GenerateStreamFromString(content2);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Products>));
            ProductList = (List<Products>)serializer.ReadObject(contentStream);
            foreach (Products products in ProductList)
            {
                System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
                dtDateTime = dtDateTime.AddMilliseconds(products.timeUpdated).ToLocalTime();
                products.timeUpdate = dtDateTime;
            }

            dataGrid.ItemsSource = ProductList;
        }

        //On Click Message Button
        private void Message(object sender, RoutedEventArgs e)
        {
            //TODO : Link to message user control
            //NewMessageWindow newMessageWindow = new NewMessageWindow(userName);
            //newMessageWindow.Show();
        }


    }
}
