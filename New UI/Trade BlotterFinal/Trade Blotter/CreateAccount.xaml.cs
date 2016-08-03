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

namespace Trade_Blotter
{
    
    public partial class CreateAccount : Window
    {
        public CreateAccount()
        {
            InitializeComponent();
        }
        private void DoSetUp(object sender, RoutedEventArgs e)
        {

            lstEntitlement.Items.Add("Manager");
            lstEntitlement.Items.Add("Spot Trader");
            lstEntitlement.Items.Add("Adhoc Trader");
        }
        private void ShowEntitlement(object sender, RoutedEventArgs e)
        {
            if (lstEntitlement.SelectedIndex == -1)
            {
                MessageBox.Show("plz select Entitlement");
                return;
            }
            MessageBox.Show("You selected " + lstEntitlement.SelectedItem.ToString());
        }

        private void ConfirmNewAccount(object sender, RoutedEventArgs e)
        {
            if(txtFirstName.Text != "")
            {
                if(txtLastName.Text!= "")
                {
                    if(txtUserName.Text!= "")
                    {
                        if (txtNewAccountPassword.Text == txtNewAccountConfirmPassword.Text)
                        {
                            ;
                        }
                        else
                        {
                            MessageBox.Show("Both passwords don't match. Please Confirm the same password");
                            return;
                        }

                        if (lstEntitlement.SelectedIndex == -1)
                        {
                            MessageBox.Show("plz select Entitlement");
                            return;
                        }

                        int index = lstEntitlement.SelectedIndex + 1;
                        var client = new WebClient();
                        var content = client.DownloadString("http://192.168.110.1:8080/OnlineTradeBlotterWebProject/rest/app/register1?firstName=" + txtFirstName.Text + "&lastName=" + txtLastName.Text + "&userName=" + txtUserName.Text + "&key=" + txtNewAccountPassword.Text + "&entitlement=" + index);


                        if (content == "yes")
                        {
                            MessageBox.Show("User is successfully registered!!!!");
                        }
                        else
                        {
                            MessageBox.Show("Error! User is already regisred");
                        }
                    }

                }
                

            }
            else
            {
                MessageBox.Show("Please enter all information");
                return;
            }
        
            
        }
    }
}
