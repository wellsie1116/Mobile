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
using Microsoft.Phone.Controls;

namespace RhitMobile {
    /// <summary>
    /// State Manager
    /// </summary>
    public static class StateManager {
        /// <summary>
        /// Saves a key-value pair into the state object
        /// </summary>
        /// <param name="phoneApplicationPage">The phone application page.</param>
        /// <param name="key">The key.</param>
        /// <param name="value">The value.</param>
        public static void SaveState(this PhoneApplicationPage phoneApplicationPage, string key, object value) {
            if (phoneApplicationPage.State.ContainsKey(key)) {
                phoneApplicationPage.State.Remove(key);
            }

            phoneApplicationPage.State.Add(key, value);
        }

        /// <summary>
        /// Loads value from the state object, according to the key.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="phoneApplicationPage">The phone application page.</param>
        /// <param name="key">The key.</param>
        /// <returns>The loaded value</returns>
        public static T LoadState<T>(this PhoneApplicationPage phoneApplicationPage, string key)
            where T : class {
            if (phoneApplicationPage.State.ContainsKey(key)) {
                return (T) phoneApplicationPage.State[key];
            }

            return default(T);
        }
    }
}
