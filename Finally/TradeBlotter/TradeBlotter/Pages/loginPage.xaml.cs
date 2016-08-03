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
using TradeBlotter.Pages.Login;
//using System.Windows.Forms;
namespace TradeBlotter.Pages
{
    /// <summary>
    /// Interaction logic for loginPage.xaml
    /// </summary>
    /// 
    


    public partial class loginPage : UserControl
    {

        //public string PlaceholderText { get; set; }
        DataContractJsonSerializer arrayserializer = new DataContractJsonSerializer(typeof(Products[]));

        public loginPage()
        {
            InitializeComponent();
        }

        //On Click Login Button
        private void ViewTradeBlotter(object sender, RoutedEventArgs e)
        {
            //ViewBlotter viewBlotter = new ViewBlotter();
            var client = new WebClient();
            client.Proxy = null;
            var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/signin1?userName=" + txtUserName.Text + "&key=" + txtPassword.Text);
            if (content != "0")
            {
                //MessageBox.Show("" + content);

                // Loginpage --> LoggedInScreen ----> ViewBlotter , Messages
                MyConstants.userName = txtUserName.Text;
                MyConstants.entitlement = content;
                LoggedInScreen loggedInScreen = new LoggedInScreen();
                //ViewBlotter viewBlotter = new ViewBlotter(txtUserName.Text, content);
                //Messages message = new Messages(txtUserName.Text);
                //createAccount.Visibility = Visibility.Visible;
                loggedInScreen.Show();
                //this.Visibility = Visibility.Collapsed;
                //createAccount.Close();
                var myWindow = Window.GetWindow(this);
                myWindow.Close();


                //viewBlotter.Visibility = Visibility.Visible;
                //this.Visibility = Visibility.Collapsed;

            }
            else
            {
                System.Windows.Forms.MessageBox.Show("User ID or Password is invalid! Please enter correct information.");
                return;
            }
        }

        private void ForgotPassword(object sender, RoutedEventArgs e)
        {
            ForgotPassword forgotWindow = new ForgotPassword();
            //forgotWindow.Visibility = Visibility.Visible;
            ////forgotWindow.Show();
            ////this.Close();
            //this.Visibility = Visibility.Collapsed;
            forgotWindow.Show();

        }

        private void CreateNewAccount(object sender, RoutedEventArgs e)
        {
            CreateAccount createAccount = new CreateAccount();
            //createAccount.Visibility = Visibility.Visible;
            createAccount.Show();
            //this.Visibility = Visibility.Collapsed;
            //createAccount.Close();
            //var myWindow = Window.GetWindow(this);
            //myWindow.Close();
        }
    }

    
}
