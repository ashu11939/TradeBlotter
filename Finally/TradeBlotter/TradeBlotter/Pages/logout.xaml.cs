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

namespace TradeBlotter.Pages
{
    /// <summary>
    /// Interaction logic for logout.xaml
    /// </summary>
    public partial class logout : UserControl
    {
        public logout()
        {
            InitializeComponent();
        }
        public void byebye(object sender, RoutedEventArgs e)
        {
            MyConstants.userName = "";
            MyConstants.entitlement = "";
            
            MainWindow loggedInScreen = new MainWindow();
            loggedInScreen.Show();
            var myWindow = Window.GetWindow(this);
            myWindow.Close();
            
            
        }
    }
}
