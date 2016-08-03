using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Runtime.Serialization.Json;
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

namespace TradeBlotter.Pages.Login
{
    /// <summary>
    /// Interaction logic for Messages.xaml
    /// </summary>
    public partial class Messages : UserControl
    {
        //Instance Variables
        //string message;
        //string sender;
        //string receiver;
        string userName;

        public Messages()
        {
            InitializeComponent();
            List<string> GroupList;
            var client = new WebClient();
            client.Proxy = null;
            var content2 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getUserGroups1?userName=" + MyConstants.userName);
            //System.Windows.Forms.MessageBox.Show("" + content2);
            Stream contentStream = GenerateStreamFromString(content2);

            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<string>));
            GroupList = (List<string>)serializer.ReadObject(contentStream);
            //lstGroups.Items.Add(GroupList);
            foreach (string group in GroupList)
            {
                lstGroups.Items.Add(group);
            }
            /*Task.Factory.StartNew(() =>
            {
                BeginInvoke();
            });
            */
            DispatcherTimer timer = new DispatcherTimer();
            timer.Tick += new EventHandler(timer_Tick);
            timer.Interval = TimeSpan.FromSeconds(1);
            timer.Start();


        }
        
        //Timer tick event handler
        void timer_Tick(object sender, EventArgs e)
        {
            if (chkAutoRefresh.IsChecked == true)
            {
                refresh();
            }

        }

        //On Click Send Messages Button
        private void sendMessage(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            client.Proxy = null;
            var content2 = client.DownloadString("http://localhost:8080/OnlineMessagingServiceWeb/rest/msg/sendMessages?sender=" + MyConstants.userName + "&message=" + txtMessage.Text + "&group=" + lstGroups.SelectedItem.ToString());
            txtMessage.Text = "";
            refresh();
        }
        
        //On refresh 
        void refresh()
        {
            List<Message1> MessageList;
            var client = new WebClient();
            client.Proxy = null;
            var content2 = client.DownloadString("http://localhost:8080/OnlineMessagingServiceWeb/rest/msg/receiveMessage?receiver=" + lstGroups.SelectedItem.ToString());
            //System.Windows.Forms.MessageBox.Show("" + content2);

            //Message message;

            Stream contentStream = GenerateStreamFromString(content2);
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Message1>));
            MessageList = (List<Message1>)serializer.ReadObject(contentStream);

            dataGrid.ItemsSource = MessageList;

        }

        //Generate Stream from string
        public Stream GenerateStreamFromString(string s)
        {
            MemoryStream stream = new MemoryStream();
            StreamWriter writer = new StreamWriter(stream);
            writer.Write(s);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }

        //To show group chat
        private void ShowGroupChat(object sender, RoutedEventArgs e)
        {
            if (lstGroups.SelectedIndex == -1)
            {
                System.Windows.Forms.MessageBox.Show("plz select Group");
                return;
            }
            //System.Windows.Forms.MessageBox.Show("You selected " + lstGroups.SelectedItem.ToString());
            else
            {
                chkAutoRefresh.IsChecked = true;
                refresh();
            }
        }

        //On Click Refresh Button
        private void RefreshMessage(object sender, RoutedEventArgs e)
        {
            refresh();
        }



    }
}
