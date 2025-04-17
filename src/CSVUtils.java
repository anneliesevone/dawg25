import java.io.*;
import java.util.*;

public class CSVUtils {
    public static int[] readCSV(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String value : line.split(",")) {
                    list.add(Integer.parseInt(value.trim()));
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}

