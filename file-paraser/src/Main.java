import java.io.*;



class Main
{
    public static void main(String args[])
    {
        //Ping_Parase("camPi1.log", "newLog.txt", "newLog2.txt");
        //Ping_Parase("ethzPi1.log", "ethzPiLog.txt", "ethzPacketLog.txt");
        //Median_Parase("camPi1.log", "newLog3.txt");
        //TraceRoute_Parase("camTrace1.log", "newLog4.txt");
        TPut_Parase("bigFileDown1.log", "newLog5.txt");
    }

    public static void Ping_Parase(String toRead, String pingWrite, String packetWrite){
    try{
        //Opens the named file
        //Name the file you want to read from here
        FileInputStream readIn = new FileInputStream(toRead);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(readIn);
        //Buffer for the input
        BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

        //Name the file you want to write pings to
        FileWriter outPing = new FileWriter(pingWrite);
        BufferedWriter pingLogging = new BufferedWriter(outPing);

        //Name the file you want to write packet loss to
        FileWriter outLoss = new FileWriter(packetWrite);
        BufferedWriter lossLogging = new BufferedWriter(outLoss);
        String txtLine;
        //Read File Line By Line
        String matchLine="rtt";
        String matchLine2="packet loss,";
        String dataPoints;
        String dataPoints2;
        String dataLoss;
        while ((txtLine = bufferLine.readLine()) != null)   {
            // Print the content on the console
            if (txtLine.toLowerCase().contains(matchLine)){
                dataPoints = txtLine.substring(23, 49);
                String[] data = dataPoints.split("/");
                for(int x = 0; x < data.length; x++ ) {
                    pingLogging.write(data[x] + ",");

                }
                pingLogging.write("\n");
            }
            if (txtLine.toLowerCase().contains(matchLine2)){
                dataPoints2 = txtLine.substring(39);
                dataLoss = dataPoints2.substring(0, dataPoints2.indexOf(" "));

                lossLogging.write(dataLoss + ",\n");

            }

        }
        pingLogging.close();
        lossLogging.close();
        //Close the input stream
        in.close();
    }catch (Exception e){//Catch exception if any
        System.err.println("Error: " + e.getMessage());
    }
}

    public static void Median_Parase(String toRead, String meanWrite){
        try{
            //Opens the named file
            FileInputStream readIn = new FileInputStream(toRead);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(readIn);
            //Buffer for the input
            BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

            FileWriter outMean = new FileWriter(meanWrite);
            BufferedWriter meanLogging = new BufferedWriter(outMean);

            String txtLine;
            //Checks to see if line matches data being looked for
            String matchLine= "time=";
            String matchLine2= "packets transmitted";
            String dataPoints;
            String writeMean;
            //Read File Line By Line
            while ((txtLine = bufferLine.readLine()) != null)   {
                // Print the content on the console
                if (txtLine.toLowerCase().contains(matchLine)){
                    dataPoints = txtLine.substring(txtLine.indexOf(matchLine));

                    writeMean = dataPoints.substring(5,9);

                    meanLogging.write(writeMean+",");

                }
                if (txtLine.toLowerCase().contains(matchLine2)){
                    meanLogging.write("\n");
                }


            }
            meanLogging.close();
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
}

    public static void TraceRoute_Parase(String toRead, String traceWrite){
        try{
            //Opens the named file
            FileInputStream readIn = new FileInputStream(toRead);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(readIn);
            //Buffer for the input
            BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

            FileWriter outTrace = new FileWriter(traceWrite);
            BufferedWriter traceLogging = new BufferedWriter(outTrace);

            String txtLine;
            //Checks to see if line matches data being looked for
            String matchLine= "traceroute to";

            String dataPoints;

            //Read File Line By Line
            while ((txtLine = bufferLine.readLine()) != null)   {
                // Print the content on the console
                if (txtLine.toLowerCase().contains(matchLine) == false){
                    if (txtLine.toLowerCase().contains("* * *")) {
                        traceLogging.write(txtLine + ", ");
                    }
                    else{
                        dataPoints = txtLine.substring(0, txtLine.indexOf(")") + 1);
                        traceLogging.write(dataPoints + ", ");
                    }
                }
                if (txtLine.toLowerCase().contains(matchLine)){
                    traceLogging.write("\n");
                }


            }
            traceLogging.close();
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void TPut_Parase(String toRead, String tPutWrite){
        try{
            //Opens the named file
            FileInputStream readIn = new FileInputStream(toRead);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(readIn);
            //Buffer for the input
            BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

            FileWriter writeTPut = new FileWriter(tPutWrite);
            BufferedWriter tPutLogging = new BufferedWriter(writeTPut);

            String txtLine;
            //Checks to see if line matches data being looked for
            String matchLine= "written to stdout";

            String dataPoints;
            //Read File Line By Line
            while ((txtLine = bufferLine.readLine()) != null)   {
                // Print the content on the console
                if (txtLine.toLowerCase().contains(matchLine)){
                    //dataPoints = txtLine.substring(txtLine.indexOf("(") ,txtLine.indexOf(")") + 1);
                    dataPoints = txtLine.substring(txtLine.indexOf("(") + 1,txtLine.indexOf("(") + 5);
                    tPutLogging.write(dataPoints+",\n");

                }


            }
            tPutLogging.close();
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

}
