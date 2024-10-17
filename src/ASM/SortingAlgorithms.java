package ASM;

public class SortingAlgorithms {

    // Method to sort an array using Bubble Sort
    public static void bubbleSort(int[] grades) {
        int n = grades.length; // Get the length of the array
        // Loop through all elements in the array
        for (int i = 0; i < n - 1; i++) {
            // Loop to compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // Compare and swap if the current element is greater than the next
                if (grades[j] > grades[j + 1]) {
                    // Swap grades[j] and grades[j + 1]
                    swap(grades, j, j + 1);
                }
            }
        }
    }

    // Method to sort an array using Selection Sort
    public static void selectionSort(int[] grades) {
        int n = grades.length; // Get the length of the array
        // Loop through all elements in the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the current element is the smallest
            int minIndex = i;
            // Find the smallest element in the unsorted portion of the array
            for (int j = i + 1; j < n; j++) {
                // Update minIndex if a smaller element is found
                if (grades[j] < grades[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the element at index i
            swap(grades, minIndex, i);
        }
    }

    // Method to swap elements in the array
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    // Method to display the contents of the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " "); // Print each element
        }
        System.out.println(); // Move to the next line after printing
    }

    // Main method to test the sorting algorithms
    public static void main(String[] args) {
        // Example array of grades
        int[] grades = {57, 83, 29, 76, 91, 48, 62};

        System.out.println("Original array:");
        printArray(grades); // Print the original array

        // Test Bubble Sort
        bubbleSort(grades); // Call the bubble sort method
        System.out.println("Sorted array using Bubble Sort:");
        printArray(grades); // Print the sorted array

        // Reset the array to test Selection Sort
        int[] gradesForSelectionSort = {57, 83, 29, 76, 91, 48, 62};

        // Test Selection Sort
        selectionSort(gradesForSelectionSort); // Call the selection sort method
        System.out.println("Sorted array using Selection Sort:");
        printArray(gradesForSelectionSort); // Print the sorted array
    }
}