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
using System.Dynamic;
using System.IO;
using System.Net;
using System.Web.Script.Serialization;
using System.Runtime.Serialization.Json;
using System.Runtime.Serialization;



namespace Trade_Blotter
{
    
    public partial class MainWindow : Window
    {
        //MainWindow mainWindow = new MainWindow();
        DataContractJsonSerializer arrayserializer = new DataContractJsonSerializer(typeof(Products[]));
               
        public MainWindow()
        {
            InitializeComponent();         
             
        }

      


        private void ViewPrice(object sender, RoutedEventArgs e)
        {
            
            PriceInformation priceInformation = new PriceInformation();
            var client = new WebClient();
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/signin1?userName="+ txtUserName.Text+"&key="+txtPassword.Text);
            if(content!="0")
            {
                //MessageBox.Show("" + content);
                priceInformation = new PriceInformation(txtUserName.Text,content);
                priceInformation.Show();
                this.Close();
               
            }
            else
            {
                MessageBox.Show("User ID or Password is invalid! Please enter correct information.");
                return;
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

        private void ForgotPassword(object sender, RoutedEventArgs e)
        {
            ForgotPasswordWindow forgotWindow = new ForgotPasswordWindow();
            forgotWindow.Show();
            this.Close();
        }

        
        /*private void sayHello(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Welcome to Trade Blotter");
        }*/

        private void CreateAccount(object sender, RoutedEventArgs e)
        {
            CreateAccount createAccount = new CreateAccount();
            createAccount.Show();
            this.Close();

        }

        
    }
}
