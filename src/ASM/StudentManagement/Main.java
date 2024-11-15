package ASM.StudentManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student list limit (minimum 10 slots): ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        StudentManager manager = new StudentManager(capacity);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==================================");
            System.out.println("Student Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student");
            System.out.println("6. Show All Students");
            System.out.println("7. Show Student Ranking");
            System.out.println("8. Create 10 Random Students");
            System.out.println("9. Exit");
            System.out.println("==================================");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    double marks;
                    while (true) {
                        System.out.print("Enter student marks (1.0 - 10.0): ");
                        marks = scanner.nextDouble();
                        if (marks >= 1.0 && marks <= 10.0) {
                            break;
                        } else {
                            System.out.println("Invalid marks. Please enter a value between 1.0 and 10.0.");
                        }
                    }
                    scanner.nextLine(); // Consume newline
                    manager.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    System.out.print("Enter student ID you want to change information for: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();

                    while (true) {
                        System.out.print("Enter new marks (1.0 - 10.0): ");
                        marks = scanner.nextDouble();
                        if (marks >= 1.0 && marks <= 10.0) {
                            break;
                        } else {
                            System.out.println("Invalid marks. Please enter a value between 1.0 and 10.0.");
                        }
                    }
                    scanner.nextLine();
                    manager.editStudent(id, name, marks);
                    break;


                case 3:
                    System.out.print("Enter student ID you want to delete: ");
                    id = scanner.nextLine();
                    manager.deleteStudent(id);
                    break;

                case 4:
                    System.out.println("Sort by:");
                    System.out.println("1. Marks");
                    System.out.println("2. Name");
                    System.out.print("Enter your choice: ");
                    int sortType = scanner.nextInt();
                    System.out.println("Order:");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    System.out.print("Enter your choice: ");
                    int order = scanner.nextInt();
                    boolean ascending = (order == 1);
                    manager.sortStudents(sortType, ascending);
                    break;

                case 5:
                    System.out.println("Search by:");
                    System.out.println("1. ID");
                    System.out.println("2. Name");
                    System.out.print("Enter your choice: ");
                    int searchType = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your search criteria: ");
                    String criteria = scanner.nextLine();
                    manager.searchStudent(criteria, searchType);
                    break;

                case 6:
                    manager.printAllStudents();
                    break;

                case 7:
                    manager.printStudentRanking();
                    break;

                case 8:
                    manager.createRandomStudents();
                    break;

                case 9:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
