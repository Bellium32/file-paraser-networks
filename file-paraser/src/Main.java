import java.io.*;



class Main
{
    public static void main(String args[])
    {
        Ping_Parase("", "", "");
        Median_Parase("", "");
        TraceRoute_Parase2("", "");
    }

    //Finds and outputs the min/avg/max/mdev rtt and the packet loss and outputs them into seperate CSV files
    public static void Ping_Parase(String toRead, String pingWrite, String packetWrite){
    try{
        //Opens the named file

        FileInputStream readIn = new FileInputStream(toRead);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(readIn);
        //Buffer for the input
        BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));


        FileWriter outPing = new FileWriter(pingWrite);
        BufferedWriter pingLogging = new BufferedWriter(outPing);


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
                dataPoints2 = txtLine.substring(txtLine.indexOf("received")+10);
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

    //Finds all of the time values in the log and then outputs them to be processed in excel
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

                    meanLogging.write(writeMean+"\n");

                }
                if (txtLine.toLowerCase().contains(matchLine2)){
                    meanLogging.write(",");
                }


            }
            meanLogging.close();
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
}

    // Gives named adress aswell
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
    // Only gives adress as IP
    public static void TraceRoute_Parase2(String toRead, String traceWrite){
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
                        dataPoints = txtLine.substring(txtLine.indexOf("("), txtLine.indexOf(")") + 1);
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

    //Finds and output the Throughput (transferspeed) of the given file
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
