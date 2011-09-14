using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RHITMobile
{
    public static class Program
    {
        public static void Main()
        {
            var TM = new ThreadManager();
            TM.Enqueue(WebController.HandleClients(TM));
            TM.Start(1);
        }
    }
}
