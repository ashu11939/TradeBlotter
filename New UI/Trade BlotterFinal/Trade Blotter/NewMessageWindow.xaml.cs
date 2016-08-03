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
using System.Windows.Threading;

namespace Trade_Blotter
{
    /// <summary>
    /// Interaction logic for NewMessageWindow.xaml
    /// </summary>
    public partial class NewMessageWindow : Window
    {
        List<Message> MessageList;
        string message;
        string sender;
        string receiver;
        string userName;
        public NewMessageWindow()
        {
            InitializeComponent();
        }

        public NewMessageWindow(string userName)
        {
            InitializeComponent();
            this.userName = userName;
            List<string> GroupList;
            var client = new WebClient();
            var content2 = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/getUserGroups1?userName=" + userName);
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
        /*public void BeginInvoke()
        {

            DispatcherOperation op = Dispatcher.BeginInvoke((Action)(() =>
            {
                refresh();

            }));

        }*/
        void timer_Tick(object sender, EventArgs e)
        {
            if (chkAutoRefresh.IsChecked == true)
            {
                refresh();
            }

        }

        public NewMessageWindow(string sender,string receiver, string message)
        {
            this.sender = sender;
            this.receiver = receiver;
            this.message = message;
        }

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


        public Stream GenerateStreamFromString(string s)
        {
            MemoryStream stream = new MemoryStream();
            StreamWriter writer = new StreamWriter(stream);
            writer.Write(s);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }

        private void sendMessage(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var content2 = client.DownloadString("http://10.87.198.165:8080/OnlineMessagingServiceWeb/rest/msg/sendMessages?sender=" +userName+"&message="+txtMessage.Text);
            refresh();
        }

       
        private void RefreshMessage(object sender, RoutedEventArgs e)
        {
            refresh();
        }

        void refresh()
        {
            List<Message1> MessageList;
            var client = new WebClient();
            var content2 = client.DownloadString("http://10.87.206.175:8080/OnlineMessagingServiceWeb/rest/msg/receiveMessage?receiver="+lstGroups.SelectedItem.ToString());
            //System.Windows.Forms.MessageBox.Show("" + content2);

            //Message message;

            Stream contentStream = GenerateStreamFromString(content2);
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(List<Message1>));
            MessageList = (List<Message1>)serializer.ReadObject(contentStream);

            dataGrid.ItemsSource = MessageList;

        }

    }
    }
