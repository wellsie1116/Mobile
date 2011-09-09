using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Controls.Maps;
using System.Device.Location;
using System.Threading;
using Microsoft.Phone.Shell;


//Latitude:39.4820263°
//Longitude:-87.3248677°

namespace RhitMobile {
    public partial class MapPage : PhoneApplicationPage {
        GeoCoordinateWatcher watcher;
        Pushpin user_loc = new Pushpin();
        ApplicationBar appbar;
        double zoom_level = 16;
        GeoCoordinate LOCATION_ROSE = new GeoCoordinate(39.4820263, -87.3248677);

        public MapPage() {
            InitializeComponent();

            map.CredentialsProvider = new ApplicationIdCredentialsProvider(App.Id);
            map.ZoomLevel = zoom_level;

            initApplicationBar();
            initGeoCordinateWatcher();
        }

        void initGeoCordinateWatcher() {
            watcher = new GeoCoordinateWatcher(GeoPositionAccuracy.High);
            watcher.MovementThreshold = 10.0f;

            watcher.StatusChanged += new EventHandler<GeoPositionStatusChangedEventArgs>(watcher_StatusChanged);
            watcher.PositionChanged += new EventHandler<GeoPositionChangedEventArgs<GeoCoordinate>>(watcher_PositionChanged);

            new Thread(bgWatcherUpdate).Start();
        }

        void initApplicationBar() {
            appbar = new ApplicationBar() {
                Opacity = 0.5,
                IsVisible = true,
                IsMenuEnabled = true
            };
            ApplicationBarIconButton button1 = new ApplicationBarIconButton(new Uri("/pin.png", UriKind.Relative)) {
                Text = "Me",
            };
            ApplicationBarIconButton button2 = new ApplicationBarIconButton(new Uri("/pin.png", UriKind.Relative)) {
                Text = "Rose",
            };
            ApplicationBarIconButton button3 = new ApplicationBarIconButton(new Uri("/pin.png", UriKind.Relative)) {
                Text = "Directions",
            };
            button1.Click += new EventHandler(button1_Click);
            button2.Click += new EventHandler(button2_Click);
            button3.Click += new EventHandler(button3_Click);

            appbar.Buttons.Add(button1);
            appbar.Buttons.Add(button2);
            appbar.Buttons.Add(button3);

            ApplicationBarMenuItem menuItem1 = new ApplicationBarMenuItem("Settings");
            menuItem1.Click += new EventHandler(Settings_Click);
            appbar.MenuItems.Add(menuItem1);
            this.ApplicationBar = appbar;
        }

        void Settings_Click(object sender, EventArgs e) {
            NavigationService.Navigate(new Uri("/SettingsPage.xaml", UriKind.Relative));
        }
        void button1_Click(object sender, EventArgs e) {
            new Thread(bgWatcherUpdate).Start();
        }
        void button2_Click(object sender, EventArgs e) {
            watcher.Stop();
            map.Center = LOCATION_ROSE;
        }
        void button3_Click(object sender, EventArgs e) { }

        void bgWatcherUpdate() {
            watcher.TryStart(true, TimeSpan.FromMilliseconds(60000));
        }
        
        void watcher_StatusChanged(object sender, GeoPositionStatusChangedEventArgs e) {
            switch (e.Status) {
                case GeoPositionStatus.Disabled:
                    // The Location Service is disabled or unsupported.
                    // Check to see if the user has disabled the Location Service.
                    if (watcher.Permission == GeoPositionPermission.Denied) {
                        // The user has disabled the Location Service on their device.
                        //statusTextBlock.Text = "You have disabled Location Service.";
                    }
                    else {
                        //statusTextBlock.Text = "Location Service is not functioning on this device.";
                    }
                    break;
                case GeoPositionStatus.Initializing:
                    //statusTextBlock.Text = "Location Service is retrieving data...";
                    // The Location Service is initializing.
                    break;
                case GeoPositionStatus.NoData:
                    // The Location Service is working, but it cannot get location data.
                    //statusTextBlock.Text = "Location data is not available.";
                    break;
                case GeoPositionStatus.Ready:
                    // The Location Service is working and is receiving location data.
                    //statusTextBlock.Text = "Location data is available.";
                    break;
            }
        }
        void watcher_PositionChanged(object sender, GeoPositionChangedEventArgs<GeoCoordinate> e) {
            //update the TextBlock readouts
            //latitudeTextBlock.Text = e.Position.Location.Latitude.ToString("0.0000000000000");
            //longitudeTextBlock.Text = e.Position.Location.Longitude.ToString("0.0000000000000");
            //speedreadout.Text = e.Position.Location.Speed.ToString("0.0") + " meters per second";
            //coursereadout.Text = e.Position.Location.Course.ToString("0.0");
            //altitudereadout.Text = e.Position.Location.Altitude.ToString("0.0");
            // update the Map if the user has asked to be tracked.

            user_loc.Location = e.Position.Location;
            map.Center = e.Position.Location;
            if (map.Children.Contains(user_loc) == false)
                map.Children.Add(user_loc);
        }

        
    }
}