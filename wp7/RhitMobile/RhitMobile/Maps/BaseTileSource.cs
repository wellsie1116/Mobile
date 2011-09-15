using System;
using Microsoft.Phone.Controls.Maps;

namespace RhitMobile.Maps {
    public class BaseTileSource : TileSource, IEquatable<BaseTileSource> {

        public string Name { get; set; }

        public bool Equals(BaseTileSource other) {
            return other != null && other.Name.Equals(Name);
        }

        public override bool Equals(object obj) {
            return Equals(obj as BaseTileSource);
        }

        public static string tileToQuadKey(int tileX, int tileY, int zoomLevel) {
            string quad_key = "";
            for(int i = zoomLevel; i > 0; i--) {
                int mask = 1 << (i - 1);
                int cell = 0;
                if((tileX & mask) != 0) cell++;
                if((tileY & mask) != 0) cell += 2;
                quad_key += cell;
            }
            return quad_key;
        }
    }
}
