import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String OUTPUT_CSV = "results.csv";
    private static BufferedWriter writer = null;

    public static void initLogFile() {
        try {
            writer = new BufferedWriter(new FileWriter(OUTPUT_CSV, false));
            writer.write("Algorithm,InputSize,Run,Time(μs),Energy(J)\n");
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }

    public static void log(String algorithm, int inputSize, int run, long timeMs, double energyJ) {
        try {
            if (writer != null) {
                writer.write(String.format("%s,%d,%d,%d,%.4f\n",
                        algorithm, inputSize, run, timeMs, energyJ));
            } else {
                System.err.println("Logger not initialized! Call initLogFile() first.");
            }
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }

    public static void closeLogFile() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            System.err.println("Failed to close log file: " + e.getMessage());
        }
    }
}

