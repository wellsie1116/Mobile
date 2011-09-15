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
using System.Windows.Navigation;

namespace RhitMobile {
    public partial class SettingsPage : PhoneApplicationPage {

        public SettingsPage() {
            InitializeComponent();
            List<MapTileSource> source = new List<MapTileSource>();
            source.Add(new MapTileSource() { Name = "Bing Aerial", });
            source.Add(new MapTileSource() { Name = "Bing Road", });
            source.Add(new MapTileSource() { Name = "OSM Mapnik", });
            source.Add(new MapTileSource() { Name = "Osma Render", });
            source.Add(new MapTileSource() { Name = "Google Hybrid", });
            source.Add(new MapTileSource() { Name = "Google Street", });
            this.mapSourcePicker.ItemsSource = source;
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e) {
            string source = ((MapTileSource) mapSourcePicker.SelectedItem).Name;
            this.SaveState("MapSource", source);
            this.SaveState("TileOverlay", toggleSwitch1.IsChecked);
            this.SaveState("PolygonOverlay", toggleSwitch2.IsChecked);
        }

        protected override void OnNavigatedTo(NavigationEventArgs e) {
            string ms_name = this.LoadState<string>("MapSource");
            toggleSwitch1.IsChecked = (bool) this.LoadState<object>("TileOverlay", true);
            toggleSwitch2.IsChecked = (bool) this.LoadState<object>("PolygonOverlay", false);
            if(ms_name != null) {
                foreach(MapTileSource source in mapSourcePicker.Items) {
                    if(source.Name == ms_name)
                        mapSourcePicker.SelectedItem = source;
                }
            }
        }

        public class MapTileSource {
            public string Name {
                get;
                set;
            }
        }
    }
}
