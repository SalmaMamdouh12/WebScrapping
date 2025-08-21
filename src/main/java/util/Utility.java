package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.io.Writer;

public class Utility {

    static Random random;
    private static Writer writer;
    private static double totalPrice = 0.0;

    // === Filename generated from today's date ===
    public static String getTodayFileName() {
        String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "src/test/resources/" + dateString + ".csv";
    }

    // === Initialize CSV file (overwrite or append) ===
    public static void initCSV(String filePath, boolean append) throws IOException {
        writer = new FileWriter(filePath, append);
        if (!append) {
            // Write timestamp at top of new file
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writeCSVLine(new String[]{"Exported on:", timestamp});
            writeEmptyLine();
        }
    }

    // === Append mode with CSVPrinter (optional for advanced usage) ===
    public static void initCSVForAppend(String filePath) throws IOException {
        writer = new FileWriter(filePath, true);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
    }

    public static void writeEmptyLine() throws IOException {
        writer.write("\n");
    }

    public static void writeCSVHeader(String[] headers) throws IOException {
        writeCSVLine(headers);
    }

    public static void writeCSVRow(List<String> rowData) throws IOException {
        writeCSVLine(rowData.toArray(new String[0]));
    }

    private static void writeCSVLine(String[] values) throws IOException {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            String value = values[i] != null ? values[i] : "";

            // Escape quotes
            value = value.replace("\"", "\"\"");

            // Quote if it contains commas or quotes
            if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
                value = "\"" + value + "\"";
            }

            line.append(value);
            if (i < values.length - 1) {
                line.append(",");
            }

            // Sum prices — assuming price is always at index 2 (adjust if needed)
            if (i == 2 && !value.isEmpty()) {
                try {
                    String numericValue = value.replace("\"", "").replace("$", "").trim();
                    totalPrice += Double.parseDouble(numericValue);
                } catch (NumberFormatException ignored) {
                    // Not a valid number — ignore
                }
            }
        }

        writer.write(line.toString());
        writer.write("\n");
    }

    // === Call this at the end to add total price row ===
    public static void writeTotalPriceRow() throws IOException {
        writeEmptyLine();
        writeCSVLine(new String[]{"Total Price", "", String.format("%.2f", totalPrice)});
    }

    public static void closeCSV() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    // === Optional: Reset totalPrice (if needed between test runs) ===
    public static void resetTotalPrice() {
        totalPrice = 0.0;
    }

    public static String generateRandomName() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        random = new Random();

        // Generate random length between 20 and 30
        int length = 20 + random.nextInt(11); // 11 because nextInt is exclusive of the upper bound

        StringBuilder name = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            name.append(characters.charAt(random.nextInt(characters.length())));
        }

        return name.toString();
    }

    public static String generateRandomGmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        random = new Random();

        int length = 8 + random.nextInt(8); // length between 8 and 15

        StringBuilder username = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            username.append(characters.charAt(random.nextInt(characters.length())));
        }

        return username.toString() + "@gmail.com";
    }


    public static String generaterandomDigits(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++)
            sb.append((int) (Math.random() * 10));
        return sb.toString();
    }

    public static String generateRandomZipCode() {
        random = new Random();
        int zip = 10000 + random.nextInt(90000); // Generates number between 10000 and 99999
        return String.valueOf(zip);
    }

    public static String generatecomplexPassword(int length) {
        String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%&*!";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(all.charAt((int) (Math.random() * all.length())));
        return sb.toString();
    }


    //method for Network
    public static void openBrowserNetworkTab() throws AWTException {
        // Create Robot instance
        Robot robot = new Robot();
        // Add a delay for setup (optional)
        robot.delay(2000); // Wait for the browser window to be active
        // Step 1: Press Ctrl+Shift+I to open Developer Tools
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_I);
        // Add a delay for Developer Tools to open
        robot.delay(1000);
        // release press buttons
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_I);
        // Step 2: Navigate to the Network tab
        // Use Right Arrow key multiple times to move to the Network tab
        for (int i = 0; i < 3; i++) {
            // Press and hold Ctrl
            robot.keyPress(KeyEvent.VK_CONTROL);
            // Press and release
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
            // Release Ctrl
            robot.keyRelease(KeyEvent.VK_CONTROL);
            // Add a small delay between presses
            robot.delay(200);
        }
    }

}
