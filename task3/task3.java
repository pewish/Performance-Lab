import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <values_file> <tests_file> <report_file>");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            String valuesContent = new String(Files.readAllBytes(Paths.get(valuesFilePath)));
            JSONObject valuesJson = new JSONObject(valuesContent);
            JSONArray valuesArray = valuesJson.getJSONArray("values");

            String testsContent = new String(Files.readAllBytes(Paths.get(testsFilePath)));
            JSONObject testsJson = new JSONObject(testsContent);
            JSONArray testsArray = testsJson.getJSONArray("tests");

            updateTestValues(testsArray, valuesArray);

            writeReportFile(testsJson, reportFilePath);

            System.out.println("Report file created successfully!");
        } catch (IOException | JSONException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void updateTestValues(JSONArray testsArray, JSONArray valuesArray) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject test = testsArray.getJSONObject(i);
            int testId = test.getInt("id");

            for (int j = 0; j < valuesArray.length(); j++) {
                JSONObject value = valuesArray.getJSONObject(j);
                int valueId = value.getInt("id");
                if (testId == valueId) {
                    test.put("value", value.getString("value"));
                    break;
                }
            }

            if (test.has("values")) {
                JSONArray nestedTests = test.getJSONArray("values");
                updateTestValues(nestedTests, valuesArray);
            }
        }
    }

    private static void writeReportFile(JSONObject testsJson, String reportFilePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(reportFilePath)) {
            fileWriter.write(testsJson.toString(4));
        }
    }
}
