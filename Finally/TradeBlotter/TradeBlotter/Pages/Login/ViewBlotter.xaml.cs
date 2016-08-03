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
using System.Windows.Forms;

namespace TradeBlotter.Pages.Login
{
    /// <summary>
    /// Interaction logic for ViewBlotter.xaml
    /// </summary>
    public partial class ViewBlotter : System.Windows.Controls.UserControl
    {

        List<Products> ProductList;
        String userName;
        String entitlement;

        public ViewBlotter()
        {
            InitializeComponent();
            chkAutoRefresh.IsChecked = false;
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
            client.Proxy = null;

            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices?entitlement=" + MyConstants.entitlement);
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
            client.Proxy = null;
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/saveNotes2?userName=" + MyConstants.userName + "&notes=" + txtComments.Text);
            txtComments.Text = "";
        }

        //on Click clearbutton
        private void clearNotes(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            client.Proxy = null;
            txtComments.Text = "";
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
            client.Proxy = null;
            //priceInformation = new ViewBlotter();
            var content1 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getPrices?search=" + txtSearch.Text + "&entitlement=" + MyConstants.entitlement);
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
            client.Proxy = null;
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getNotes1?userName=" + MyConstants.userName);
            string note = content.ToString();
            txtComments.Text = note;
        }

        //On Click Sort
        private void Sort(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            client.Proxy = null;
            string qty = "10";
            if (txtQuantity.Text == "") { qty = "15"; }
            else qty = txtQuantity.Text;


            string sortString = cmbSort.Text;

            if (sortString == "") sortString = "price";
            else {
                    ComboBoxItem cbi = (ComboBoxItem)cmbSort.SelectedItem;
                    sortString = cbi.Content.ToString();
             }

            var content2 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getSortedPrices?sort=" + sortString + "&entitlement=" + MyConstants.entitlement + "&value=" + qty);
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

        private void txtSearch_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void dataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void Export(object sender, RoutedEventArgs e)
        {
            dataGrid.SelectAllCells();
            dataGrid.ClipboardCopyMode = DataGridClipboardCopyMode.IncludeHeader;
            ApplicationCommands.Copy.Execute(null, dataGrid);
            String resultat = (string)System.Windows.Clipboard.GetData(System.Windows.DataFormats.CommaSeparatedValue);
            String result = (string)System.Windows.Clipboard.GetData(System.Windows.DataFormats.Text);
            dataGrid.UnselectAllCells();
            //System.Windows.MessageBox.Show("Enter Export path");

            System.Windows.Forms.FolderBrowserDialog fbd = new FolderBrowserDialog();
            //fbd.ShowNewFolderButton = true;
            fbd.SelectedPath = "";
            fbd.ShowDialog();
            fbd.Description = "Select the directory to save the records.";
            //System.IO.StreamWriter file1 = new System.IO.StreamWriter(@"C:\Trade Blotter\Records.xls");
            /*
            file1.WriteLine(result.Replace(',', ' '));
            file1.Close();
            */
            if(string.Equals(fbd.SelectedPath,""))
            {
                // Prompt that there was no entry.
                // Do nothing.
            }
            else
            {
                string sample = fbd.SelectedPath;
                System.IO.StreamWriter file1 = new System.IO.StreamWriter(@"" + sample + "/Records.xls");
                file1.WriteLine(result.Replace(',', ','));
                //file1.WriteLine();
                file1.Close();
                System.Windows.MessageBox.Show(" Exporting DataGrid data to Excel file created.xls");

            }


        }



    }
}
