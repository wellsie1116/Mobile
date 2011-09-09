using System.Collections.Generic;
using System.Device.Location;
using System.Windows.Input;
using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Command;
using MapBindingDemo.Maps;
using MapBindingDemo.Serialization;

namespace MapBindingDemo.ViewModel
{
    public class MvvmMap : ViewModelBase
    {
        public MvvmMap()
        {
            _availableMapSources = new List<BaseTileSource> 
            {
                new BingAerial{ Name = "Bing Aerial"},
                new BingRoad {Name = "Bing Road"},
                new Mapnik {Name = "OSM Mapnik"},
                new OsmaRender {Name = "OsmaRender"},
                new Google {Name = "Google Hybrid", MapType = GoogleType.Hybrid},
                new Google {Name = "Google Street", MapType = GoogleType.Street},
            };
        }

        private GeoCoordinate _mapCenter;
        public GeoCoordinate MapCenter
        {
            get { return _mapCenter; }
            set
            {
                if (_mapCenter == value) return;
                _mapCenter = value;
                RaisePropertyChanged("MapCenter");
            }
        }

        private double _zoomLevel;
        public double ZoomLevel
        {
            get
            {
                return _zoomLevel;
            }
            set
            {
                if (value == _zoomLevel) return;
                if (value >= 1)
                {
                    _zoomLevel = value;
                }
                RaisePropertyChanged("ZoomLevel");
            }
        }

        private BaseTileSource _currentMap;
        public  BaseTileSource CurrentMap
        {
            get
            {
                if (_currentMap == null && 
                    _availableMapSources != null && 
                    _availableMapSources.Count > 0)
                {
                    _currentMap = _availableMapSources[0];
                }
                return _currentMap;
            }
            set
            {
                if (value.Equals(CurrentMap)) return;
                {
                    _currentMap = value;
                }
                RaisePropertyChanged("CurrentMap");
            }
        }

        private List<BaseTileSource> _availableMapSources;
        [DoNotSerialize]
        public List<BaseTileSource> AvailableMapSources
        {
            get
            {
                return _availableMapSources;
            }
            set
            {
                _availableMapSources = value;
                RaisePropertyChanged("AvailableMapSources");
            }
        }

        public ICommand NextMap
        {
            get
            {
                return new RelayCommand(() =>
                {
                    var newIdx = AvailableMapSources.IndexOf(CurrentMap) + 1 ;
                    CurrentMap = AvailableMapSources[newIdx > AvailableMapSources.Count - 1? 0 : newIdx];
                });
            }
        }

        public ICommand PreviousMap
        {
            get
            {
                return new RelayCommand(() =>
                {
                    var newIdx = AvailableMapSources.IndexOf(CurrentMap) -1;
                    CurrentMap = AvailableMapSources[newIdx < 0 ? AvailableMapSources.Count - 1 : newIdx];
                });
            }
        }
       
        private static MvvmMap _instance;
        public static MvvmMap Instance
        {
            get { return _instance; }
            set { _instance = value; }
        }

        public static void CreateNew()
        {
            _instance = new MvvmMap();
        }
    }
}
