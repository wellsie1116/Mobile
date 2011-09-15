using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.IO.IsolatedStorage;
using Microsoft.Phone.Controls;

namespace RhitMobile {
    public static class StateManagment {
        private static IsolatedStorageSettings userSettings = IsolatedStorageSettings.ApplicationSettings;

        public static void SaveState(this PhoneApplicationPage page, string key, object value) {
            if(userSettings.Contains(key)) userSettings[key] = value;
            else userSettings.Add(key, value);

            if(page.State.ContainsKey(key)) page.State[key] = value;
            else page.State.Add(key, value);
        }

        public static T LoadState<T>(this PhoneApplicationPage page, string key) where T : class {
            if(userSettings.Contains(key)) return (T) userSettings[key];
            if(page.State.ContainsKey(key)) return (T) page.State[key];
            return default(T);
        }

        public static T LoadState<T>(this PhoneApplicationPage page, string key, T defaultValue) where T : class {
            if(userSettings.Contains(key)) return (T) userSettings[key];
            if(page.State.ContainsKey(key)) return (T) page.State[key];
            return defaultValue;
        }
    }
}
