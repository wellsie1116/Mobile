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


namespace trackme
{
    public partial class MainPage : PhoneApplicationPage
    {
        GeoCoordinateWatcher watcher;
        bool trackingOn = false;
        Pushpin myPushpin = new Pushpin();

        // Constructor
        public MainPage()
        {
            InitializeComponent();
            // instantiate watcher, setting its accuracy level and movement threshold.
            watcher = new GeoCoordinateWatcher(GeoPositionAccuracy.High); // using high accuracy;
            watcher.MovementThreshold = 10.0f; // meters of change before "PositionChanged"
            // wire up event handlers
            watcher.StatusChanged += new
            EventHandler<GeoPositionStatusChangedEventArgs>(watcher_StatusChanged);
            watcher.PositionChanged += new
            EventHandler<GeoPositionChangedEventArgs<GeoCoordinate>>(watcher_PositionChanged);
            // start up LocServ in bg; watcher_StatusChanged will be called when complete.
            new Thread(startLocServInBackground).Start();
            statusTextBlock.Text = "Starting Location Service...";
        }
        
        void watcher_StatusChanged(object sender, GeoPositionStatusChangedEventArgs e)
        {
            switch (e.Status)
            {
                case GeoPositionStatus.Disabled:
                    // The Location Service is disabled or unsupported.
                    // Check to see if the user has disabled the Location Service.
                    if (watcher.Permission == GeoPositionPermission.Denied)
                    {
                        // The user has disabled the Location Service on their device.
                        statusTextBlock.Text = "You have disabled Location Service.";
                    }
                    else
                    {
                        statusTextBlock.Text = "Location Service is not functioning on this device.";
                    }
                    break;
                case GeoPositionStatus.Initializing:
                    statusTextBlock.Text = "Location Service is retrieving data...";
                    // The Location Service is initializing.
                    break;
                case GeoPositionStatus.NoData:
                    // The Location Service is working, but it cannot get location data.
                    statusTextBlock.Text = "Location data is not available.";
                    break;
                case GeoPositionStatus.Ready:
                    // The Location Service is working and is receiving location data.
                    statusTextBlock.Text = "Location data is available.";
                    break;
            }
        }
        void startLocServInBackground()
        {
            watcher.TryStart(true, TimeSpan.FromMilliseconds(60000));
        }
        private void trackMe_Click(object sender, RoutedEventArgs e)
        {
            if (trackingOn)
            {
                trackMe.Content = "Track Me On Map";
                trackingOn = false;
                myMap.ZoomLevel = 1.0f;
            }
            else
            {
                trackMe.Content = "Stop Tracking";
                trackingOn = true;
                myMap.ZoomLevel = 16.0f;
            }
        }
        private void startStop_Click(object sender, RoutedEventArgs e)
        {
            if (startStop.Content.ToString() == "Stop LocServ")
            {
                startStop.Content = "Start LocServ";
                statusTextBlock.Text = "Location Services stopped...";
                watcher.Stop();
            }
            else if (startStop.Content.ToString() == "Start LocServ")
            {
                startStop.Content = "Stop LocServ";
                statusTextBlock.Text = "Starting Location Services...";
                new Thread(startLocServInBackground).Start();
            }
        }
        void watcher_PositionChanged(object sender, GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
            //update the TextBlock readouts
            latitudeTextBlock.Text = e.Position.Location.Latitude.ToString("0.0000000000000");
            longitudeTextBlock.Text = e.Position.Location.Longitude.ToString("0.0000000000000");
            speedreadout.Text = e.Position.Location.Speed.ToString("0.0") + " meters per second";
            coursereadout.Text = e.Position.Location.Course.ToString("0.0");
            altitudereadout.Text = e.Position.Location.Altitude.ToString("0.0");
            // update the Map if the user has asked to be tracked.
            if (trackingOn)
            {
                // center the pushpin and map on the current position
                myPushpin.Location = e.Position.Location;
                myMap.Center = e.Position.Location;
                // if this is the first time that myPushpin is being plotted, plot it!
                if (myMap.Children.Contains(myPushpin) == false) { myMap.Children.Add(myPushpin); };
            }
        }
    }
}