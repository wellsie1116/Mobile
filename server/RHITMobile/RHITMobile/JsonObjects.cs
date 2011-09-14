using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.IO;

namespace RHITMobile
{
    [DataContract]
    public abstract class JsonObject { }

    [DataContract]
    public class ClientRequest : JsonObject
    {
        [DataMember(IsRequired = true)]
        public int Request { get; set; }
    }

    [DataContract]
    public class ErrorResponse : JsonObject
    {
        public ErrorResponse(string error, params object[] objects)
        {
            Error = String.Format(error, objects);
        }

        [DataMember(IsRequired = true)]
        public string Error { get; set; }
    }

    [DataContract]
    public class PointsOfInterestResponse : JsonObject
    {
        public PointsOfInterestResponse(params PointOfInterest[] pointsOfInterest)
        {
            PointsOfInterest = new List<PointOfInterest>(pointsOfInterest);
        }

        [DataMember(IsRequired = true)]
        public List<PointOfInterest> PointsOfInterest { get; set; }
    }

    [DataContract]
    public class PointOfInterest : JsonObject
    {
        public PointOfInterest(double latitude, double longitude, string name, string description)
        {
            Latitude = latitude;
            Longitude = longitude;
            Name = name;
            Description = description;
        }

        [DataMember(IsRequired = true)]
        public double Latitude { get; set; }
        [DataMember(IsRequired = true)]
        public double Longitude { get; set; }
        [DataMember(IsRequired = true)]
        public string Name { get; set; }
        [DataMember(IsRequired = true)]
        public string Description { get; set; }
    }

    public static class JsonUtility
    {
        public static T Deserialize<T>(this string json)
            where T : JsonObject
        {
            MemoryStream stream = new MemoryStream(Encoding.Unicode.GetBytes(json));
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(T));
            return (T)serializer.ReadObject(stream);
        }

        public static string Serialize<T>(this T obj)
            where T : JsonObject
        {
            MemoryStream stream = new MemoryStream();
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(obj.GetType());
            serializer.WriteObject(stream, obj);
            stream.Position = 0;
            using (var reader = new StreamReader(stream))
            {
                return reader.ReadToEnd();
            }
        }
    }
}
