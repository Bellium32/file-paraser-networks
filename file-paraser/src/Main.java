import java.io.*;
import java.util.Arrays;


class Main
{
    public static void main(String args[])
    {
        try{
            //Opens the named file
            FileInputStream readIn = new FileInputStream("camPi1.log");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(readIn);
            //Buffer for the input
            BufferedReader bufferLine = new BufferedReader(new InputStreamReader(in));

            FileWriter outWriter = new FileWriter("newLog.txt");
            //CSVWriter printWriter = new PrintWriter(outWriter);
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
                        System.out.println(data[x] + ",");

                    }
                }
                if (txtLine.toLowerCase().contains(matchLine2)){
                    dataPoints2 = txtLine.substring(39);


                        System.out.println(dataPoints2 + ",");

                }

            }
            //printWriter.close();
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }


}
