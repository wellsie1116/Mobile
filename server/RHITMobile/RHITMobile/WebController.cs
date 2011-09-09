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
            var listener = new TcpListener(IPAddress.Any, 3000);
            while (true)
            {
                yield return TM.WaitForTcpClient(currentThread, listener);
                var client = TM.GetResult<TcpClient>();
                TM.Enqueue(HandleRequests(TM, client));
            }
        }

        public static IEnumerable<ulong> HandleRequests(ThreadManager TM, TcpClient client)
        {
            var currentThread = TM.CurrentThread;
            using (var stream = client.GetStream())
            {
                var message = new byte[256];
                int bytesRead;
                while (true)
                {
                    yield return TM.WaitForStream(currentThread, stream, message, 256);
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

                    
                }
            }

            client.Close();
        }
    }
}
