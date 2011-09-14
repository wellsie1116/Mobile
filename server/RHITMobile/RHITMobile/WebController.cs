using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Net;

namespace RHITMobile
{
    public static class WebController
    {
        public static IEnumerable<ulong> HandleClients(ThreadManager TM)
        {
            var currentThread = TM.CurrentThread;
            var listener = new TcpListener(IPAddress.Parse("127.0.0.1"), 4000);
            listener.Start();
            while (true)
            {
                yield return TM.WaitForTcpClient(currentThread, listener);
                var client = TM.GetResult<TcpClient>();
                TM.Enqueue(HandleRequests(TM, client));
            }
        }

        private static IEnumerable<ulong> HandleRequests(ThreadManager TM, TcpClient client)
        {
            var currentThread = TM.CurrentThread;
            using (var stream = client.GetStream())
            {
                var bytes = new byte[256];
                var builder = new StringBuilder();
                int bytesRead;
                var encoder = new ASCIIEncoding();
                while (true)
                {
                    yield return TM.WaitForStream(currentThread, stream, bytes, 256);
                    try
                    {
                        bytesRead = TM.GetResult<int>();
                    }
                    catch
                    {
                        break;
                    }
                    if (bytesRead == 0)
                        break;

                    string requestStr = encoder.GetString(bytes, 0, bytesRead);
                    ClientRequest request = null;
                    bool deserializeException = false;
                    try
                    {
                        request = requestStr.Deserialize<ClientRequest>();
                    }
                    catch (Exception ex)
                    {
                        var a = ex;
                        deserializeException = true;
                    }
                    JsonObject response;
                    if (!deserializeException)
                    {
                        if (requestMethods.ContainsKey(request.Request))
                        {
                            yield return TM.Await(currentThread, requestMethods[request.Request](TM, request));
                            try
                            {
                                response = TM.GetResult<JsonObject>();
                            }
                            catch (Exception ex)
                            {
                                response = new ErrorResponse("An exception occurred: {0}, Stack: {1}", ex.Message, ex.StackTrace);
                            }
                        }
                        else
                        {
                            response = new ErrorResponse("Unknown request type: \"{0}\"", request.Request);
                        }
                    }
                    else
                    {
                        response = new ErrorResponse("Could not parse JSON object");
                    }
                    byte[] responseBytes = encoder.GetBytes(response.Serialize());
                    stream.Write(responseBytes, 0, responseBytes.Length);
                }
            }

            client.Close();
        }

        private static Dictionary<int, Func<ThreadManager, ClientRequest, IEnumerable<ulong>>> requestMethods = new Dictionary<int, Func<ThreadManager, ClientRequest, IEnumerable<ulong>>>
        {
            { 1000, HandleLocation },
        };

        private static IEnumerable<ulong> HandleLocation(ThreadManager TM, ClientRequest request)
        {
            var currentThread = TM.CurrentThread;
            yield return TM.Return(currentThread,
                new PointsOfInterestResponse(
                    new PointOfInterest(1, 2.3, "Java City", "Coffee"),
                    new PointOfInterest(4.5, 6, "Logan's", "More Coffee"),
                    new PointOfInterest(7.8, 9, "Noble Roman's", "Pizza")));
        }
    }
}
