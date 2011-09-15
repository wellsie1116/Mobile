using System;
using System.Device.Location;
using System.Threading;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Controls.Maps;
using Microsoft.Phone.Shell;
using RhitMobile.Maps;
using RhitMobile.ViewModel;

namespace RhitMobile {

    public partial class MapPage : PhoneApplicationPage {
        GeoCoordinateWatcher watcher;
        Pushpin user_loc = new Pushpin();
        ApplicationBar appbar;

        GeoCoordinate LOCATION_ROSE = new GeoCoordinate(39.4820263, -87.3248677);
        GeoCoordinate LOCATION_HATFIELD = new GeoCoordinate(39.481968, -87.322276);

        MapTileLayer cutomTilesLayer = new MapTileLayer();
        MapLayer polygonLayer = new MapLayer();
        MapLayer textLayer = new MapLayer();

        public MapPage() {
            InitializeComponent();

            Map.CredentialsProvider = new ApplicationIdCredentialsProvider(App.Id);
            Map.MapZoom += new EventHandler<MapZoomEventArgs>(Map_MapZoom);

            cutomTilesLayer.TileSources.Add(new RoseTileOverlay());

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

        void Map_MapZoom(object sender, MapZoomEventArgs e) {
            this.textBlock1.Text = "Zoom: " + Map.ZoomLevel.ToString();
        }

        void Settings_Click(object sender, EventArgs e) {
            NavigationService.Navigate(new Uri("/SettingsPage.xaml", UriKind.Relative));
        }
        void button1_Click(object sender, EventArgs e) {
            new Thread(bgWatcherUpdate).Start();
        }
        void button2_Click(object sender, EventArgs e) {
            watcher.Stop();
            Map.Center = LOCATION_ROSE;
            Map.ZoomLevel = 16;
        }
        void button3_Click(object sender, EventArgs e) {
            //TODO: Remove this
            addTextLayer();
        }

        private void addTextLayer() {
            watcher.Stop();
            Map.Center = LOCATION_HATFIELD;
            if(Map.ZoomLevel < 16)
                Map.ZoomLevel = 16;

            textLayer.Children.Clear();
            Pushpin hatfield = new Pushpin() {
                Location = LOCATION_HATFIELD,
                Template = (ControlTemplate) Resources["myct"],
                Content = "Hatfield Hall",
                Background = new SolidColorBrush(Colors.Gray) { Opacity = 0.3 },
                PositionOrigin = PositionOrigin.Center,

            };

            textLayer.Children.Add(hatfield);
            Map.Children.Add(textLayer);
        }

        void bgWatcherUpdate() {
            watcher.TryStart(true, TimeSpan.FromMilliseconds(60000));
        }

        void watcher_StatusChanged(object sender, GeoPositionStatusChangedEventArgs e) {
            switch(e.Status) {
                case GeoPositionStatus.Disabled:
                    // The Location Service is disabled or unsupported.
                    // Check to see if the user has disabled the Location Service.
                    if(watcher.Permission == GeoPositionPermission.Denied) {
                        // The user has disabled the Location Service on their device.
                        //statusTextBlock.Text = "You have disabled Location Service.";
                    } else {
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
            Map.Center = e.Position.Location;
            if(Map.Children.Contains(user_loc) == false)
                Map.Children.Add(user_loc);
        }

        protected override void OnNavigatedTo(NavigationEventArgs e) {
            if(MvvmMap.Instance == null)
                MvvmMap.CreateNew();
            MvvmMap.Instance.setTileSource(this.LoadState<string>("MapSource", ""));

            if(!Map.Children.Contains(cutomTilesLayer)) {
                    Map.Children.Add(cutomTilesLayer);
            }
            //if(!Map.Children.Contains(textLayer)) {
            //    Map.Children.Add(textLayer);
            //}

            if(polygonLayer.Children.Count == 0) {
                if((bool) this.LoadState<object>("PolygonOverlay", false)) {
                    polygonLayer.Children.Add(getPolygon());
                    Map.Children.Add(polygonLayer);
                    
                }
            }
        }

        private MapPolygon getPolygon() {
            MapPolygon polygon = new MapPolygon();
            polygon.Fill = new SolidColorBrush(Colors.Gray) { Opacity = 0.3 };
            polygon.Stroke = new SolidColorBrush(Colors.White) { Opacity = 0.7 };
            polygon.StrokeThickness = 5;
            polygon.Locations = new LocationCollection() { 
                new GeoCoordinate(39.48222435102057, -87.3227208852768), 
                new GeoCoordinate(39.481996630009654, -87.32262969017029), 
                new GeoCoordinate(39.48186413744205, -87.3222005367279), 
                new GeoCoordinate(39.48224919326755, -87.32184112071991),
                new GeoCoordinate(39.48252245739884, -87.3222005367279),
            };
            return polygon;
        }
    }
}