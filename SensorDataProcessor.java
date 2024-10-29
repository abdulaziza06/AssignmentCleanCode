// missing import
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SensorDataProcessor {
    
    // Senson data and limits.
    public double[][][] data;
    public double[][] limit;
    
    // constructor
    // The constructor doesnt have void return type and the name of the constructer must match the name of the class
    public SensorDataProcessor(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }
    
    // calculates average of sensor data
    // change the method name from average to calculateAverage
    private double calculateAverage(double[] array) {
        int i = 0;
        // Change the variable name from val to sum
        double sum = 0;
        for (i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }
    
    // calculate data
    //change the name from calculate to processSensorData
    public void processSensorData(double divisor) {
        
        int i, j, k = 0;
        // Change the variable name from data2 to processedData
        double[][][] processedData = new double[data.length][data[0].length][data[0][0].length];
        
        // Write racing stats data into a file
        try {
            // Change the variable name from out to writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("RacingStatsData.txt"));
            for (i = 0; i < data.length; i++) {
                for (j = 0; j < data[0].length; j++) {
                    for (k = 0; k < data[0][0].length; k++) {
                        
                        processedData[i][j][k] = data[i][j][k] / divisor - Math.pow(limit[i][j], 2.0);
                        
                        // Add curly brackets 
                        if (average(processedData[i][j]) > 10 && average(processedData[i][j]) < 50){
                            break;
                        }else if (Math.max(data[i][j][k], processedData[i][j][k]) > data[i][j][k]){
                            break;
                        }else if (Math.pow(Math.abs(data[i][j][k]), 3) <
                                Math.pow(Math.abs(processedData[i][j][k]), 3)
                                && average(data[i][j]) < processedData[i][j][k] && (i + 1)
                                * (j + 1) > 0){
                                
                                processedData[i][j][k] *= 2;
                            }
                        // Delete unnecessary else
                    }
                }
            }
            for (i = 0; i < processedData.length; i++) {
                for (j = 0; j < processedData[0].length; j++) {
                    writer.write(processedData[i][j] + "\t");
                }
            }
            writer.close();

        } catch (Exception e) {
            // add the exception message
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
