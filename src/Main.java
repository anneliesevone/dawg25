import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <algorithm> <csvFile>");
            return;
        }

        String algorithm = args[0];
        String filePath = args[1];

        try {
            int[] arr = CSVUtils.readCSV(filePath);
            Logger.initLogFile();

            long totalTimeUs = 0;
            double totalEnergyJ = 0.0;

            for (int i = 0; i < 400; i++) {
                int[] copy = arr.clone();
                System.out.println("Run #" + i);
                long startTime = System.nanoTime();

                switch (algorithm.toLowerCase()) {
                    case "bubble":
                        SortingAlgorithms.bubbleSort(copy);
                        break;
                    case "counting":
                        SortingAlgorithms.countingSort(copy);
                        break;
                    case "merge":
                        SortingAlgorithms.mergeSort(copy, 0, copy.length - 1);
                        break;
                    case "quick":
                        SortingAlgorithms.quickSort(copy, 0, copy.length - 1);
                        break;
                    default:
                        System.out.println("Unknown algorithm: " + algorithm);
                        return;
                }

                long endTime = System.nanoTime();
                long timeUs = (endTime - startTime) / 1_000; // microseconds
                double simulatedPower = 0.5 + Math.random() * 2.0; // watts
                double energyJ = timeUs / 1_000.0 * simulatedPower;

                Logger.log(algorithm, arr.length, i + 1, timeUs, energyJ);
                totalTimeUs += timeUs;
                totalEnergyJ += energyJ;

            }

            Logger.closeLogFile();

            double averageTimeUs = totalTimeUs / 400.0;
            double averageEnergyJ = totalEnergyJ / 400.0;

            System.out.printf("Average Time (μs): %.2f\n", averageTimeUs);
            System.out.printf("Average Energy (J): %.4f\n", averageEnergyJ);
            System.out.println("Completed 400 runs of " + algorithm + " sort.");

        } catch (IOException e) {
            System.err.println("Failed to read CSV: " + e.getMessage());
        }
    }
}


