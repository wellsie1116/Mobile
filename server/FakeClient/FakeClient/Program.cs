using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Net;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.IO;

namespace FakeClient
{
    class Program
    {
        static void Main(string[] args)
        {
            var client = new TcpClient();
            client.Connect(IPAddress.Parse("127.0.0.1"), 4000);

            var stream = client.GetStream();
            var encoding = new ASCIIEncoding();
            var message = new ClientRequest() { Request = 1000 };

            byte[] request = encoding.GetBytes(message.Serialize());
            stream.Write(request, 0, request.Length);

            byte[] response = new byte[1000];
            int bytesRead = stream.Read(response, 0, 1000);
            Console.WriteLine(encoding.GetString(response, 0, bytesRead));

            client.Close();
        }
    }

    [DataContract]
    public class ClientRequest
    {
        [DataMember(IsRequired = true)]
        public int Request { get; set; }
    }

    public static class JsonUtility
    {
        public static T Deserialize<T>(this string json)
            where T : class
        {
            MemoryStream stream = new MemoryStream(Encoding.Unicode.GetBytes(json));
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(T));
            return (T)serializer.ReadObject(stream);
        }

        public static string Serialize<T>(this T obj)
            where T : class
        {
            MemoryStream stream = new MemoryStream();
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(T));
            serializer.WriteObject(stream, obj);
            stream.Position = 0;
            using (var reader = new StreamReader(stream))
            {
                return reader.ReadToEnd();
            }
        }
    }
}
