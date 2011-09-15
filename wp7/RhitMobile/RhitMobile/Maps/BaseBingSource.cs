using System;
using System.Text;

namespace RhitMobile.Maps {
    public abstract class BaseBingSource : BaseTileSource {

        public override Uri GetUri(int x, int y, int zoomLevel) {
            if(zoomLevel > 0) {
                string quadKey = tileToQuadKey(x, y, zoomLevel);
                string veLink = string.Format(UriFormat,
                   new object[] { quadKey[quadKey.Length - 1], quadKey });
                return new Uri(veLink);
            }
            return null;
        }
    }
}