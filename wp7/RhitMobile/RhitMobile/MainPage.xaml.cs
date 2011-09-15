using System;
using System.Windows;
using Microsoft.Phone.Controls;

namespace RhitMobile {
    public partial class MainPage : PhoneApplicationPage {

        public MainPage() {
            InitializeComponent();
        }

        private void mapButton_Click(object sender, RoutedEventArgs e) {
            NavigationService.Navigate(new Uri("/MapPage.xaml", UriKind.Relative));
        }
    }
}