import java.util.ArrayList; // For storing numbers in a list
import java.util.Scanner; // For reading input file
import java.io.File; // For accessing input file
import java.io.FileWriter; // For writing output file
import java.io.IOException; // For handling write errors
import java.io.FileNotFoundException; // For handling missing file

/**
 * This program reads numbers from input.txt,
 * checks if they are valid,
 * sorts them using Insertion Sort,
 * prints "Sorting",
 * and writes the sorted numbers to output.txt.
 *
 * Author: Angel
 * Version: 1.0
 * Since: 2025-11-07
 */
public final class InsertSort {  // class name

    private InsertSort() { // Prevent creating objects
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sorts an array of numbers using Insertion Sort.
     *
     * @param arr the array to sort
     * @return the sorted array
     */
    public static int[] sorting(final int[] arr) {

        int n = arr.length; // size of array

        for (int i = 1; i < n; i++) { // Start from index 1
            int current = arr[i]; // Current number
            int j = i - 1; // Index of previous element

            // Shift larger elements to the right
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert current element into correct position
            arr[j + 1] = current;
        }

        return arr; // Return sorted array
    }

    /**.
     * Sorts numbers and writes them to output.txt
     *
     * @param numbers array of numbers to sort
     */
    public static void sortingKeeper(final int[] numbers) {
        System.out.println("Sorting"); // Show message
        sorting(numbers); // Sort the numbers

        // Open output file
        try (FileWriter writer = new FileWriter("output.txt")) {
            for (int i = 0; i < numbers.length; i++) { // Loop numbers
                writer.write(numbers[i] + "\n"); // Write one number per line
            }
        } catch (IOException e) { // If writing fails
            System.out.println("Could not write to output.txt"); // Show error
        }
    }

    /**.
     * Main function
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        File file = new File("input.txt"); // Input file

        if (!file.exists()) { // Check if file exists
            System.out.println("No file"); // Show message if missing
            return; // Stop program
        }

        ArrayList<Integer> nums = new ArrayList<>(); // List to store numbers


        try (Scanner reader = new Scanner(file)) { // Open file for reading
            while (reader.hasNextLine()) { // Loop each line
                // Read line and remove spaces
                String line = reader.nextLine().trim();
                if (line.isEmpty()) { // Skip empty lines
                    continue;
                }

                String[] parts = line.split(","); // Split numbers by commas
                for (String part : parts) { // Loop each number
                    String value = part.trim(); // Remove spaces
                    try {
                        int num = Integer.parseInt(value); // Convert to number
                        nums.add(num); // Add to list
                    } catch (NumberFormatException e) { // If not a number
                        System.out.println("This is an error"); // Show error
                        return; // Stop program
                    }
                }
            }
        } catch (FileNotFoundException e) { // If input file missing
            System.out.println("No file"); // Show error
            return; // Stop program
        }

        if (nums.isEmpty()) { // If no numbers found
            System.out.println("No file"); // Show message
            return; // Stop program
        }

        int[] numArray = new int[nums.size()]; // Convert list to array
        for (int i = 0; i < nums.size(); i++) { // Loop list
            numArray[i] = nums.get(i); // Copy numbers
        }

        sortingKeeper(numArray); // Sort numbers and write output
    }
}
