import java.io.*;
import java.util.Arrays;


class Main
{
    public static void main(String args[])
    {
        try{
            //Opens the named file
            //Name the file you want to read from here
            FileInputStream readIn = new FileInputStream("camPi1.log");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(readIn);
            //Buffer for the input
            BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

            //Name the file you want to write pings to
            FileWriter outPing = new FileWriter("newLog.txt");
            BufferedWriter pingLogging = new BufferedWriter(outPing);

            //Name the file you want to write packet loss to
            FileWriter outLoss = new FileWriter("newLog2.txt");
            BufferedWriter lossLogging = new BufferedWriter(outLoss);
            String txtLine;
            //Read File Line By Line
            String matchLine="rtt";
            String matchLine2="packet loss,";
            String dataPoints;
            String dataPoints2;
            while ((txtLine = bufferLine.readLine()) != null)   {
                // Print the content on the console
                if (txtLine.toLowerCase().contains(matchLine)){
                    dataPoints = txtLine.substring(23, 49);
                    String[] data = dataPoints.split("/");
                    for(int x = 0; x < data.length; x++ ) {
                        pingLogging.write(data[x] + ",");

                    }
                }
                if (txtLine.toLowerCase().contains(matchLine2)){
                    dataPoints2 = txtLine.substring(39);


                    lossLogging.write(dataPoints2 + ",");

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


}
