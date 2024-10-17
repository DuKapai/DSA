package ASM.StudentManagement;

public class StudentManager {
    private Student[] students;
    private int size;
    private long globalSeed = 123456789; // Initial seed value

    // Constructor with capacity check
    public StudentManager(int capacity) {
        if (capacity < 10) {
            System.out.println("Error: Capacity must be at least 10. Exiting program.");
            System.exit(1); // Exits the program if capacity is less than 10
        }
        students = new Student[capacity];
        size = 0;
    }

    // Custom logic to add a student (ensures no duplicate IDs)
    public void addStudent(Student student) {
        if (size == students.length) {
            System.out.println("Student list is full.");
            return;
        }

        // Check for duplicate ID
        for (int i = 0; i < size; i++) {
            if (students[i].getId().equals(student.getId())) {
                System.out.println("Error: Student ID already exists.");
                return;
            }
        }

        students[size++] = student;
        System.out.println("Student added successfully.");
    }

    // Custom logic to edit student information (checks if student exists by ID)
    public void editStudent(String id, String newName, double newMarks) {
        if (size == 0) {
            System.out.println("Error: No students available to edit.");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (students[i].getId().equals(id)) {
                students[i].setName(newName);
                students[i].setMarks(newMarks);
                System.out.println("Student information updated.");
                printAllStudents();
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    // Custom logic to delete a student by ID
    public void deleteStudent(String id) {
        if (size == 0) {
            System.out.println("Error: No students available to delete.");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (students[i].getId().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--size] = null;
                System.out.println("Student deleted successfully.");
                printAllStudents();
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    // Sorting by marks (ascending or descending)
    private void sortByMarks(boolean ascending) {
        // Bubble Sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if ((ascending && students[j].getMarks() > students[j + 1].getMarks()) ||
                        (!ascending && students[j].getMarks() < students[j + 1].getMarks())) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        // Selection Sort
        /*for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if ((ascending && students[j].getMarks() < students[index].getMarks()) ||
                        (!ascending && students[j].getMarks() > students[index].getMarks())) {
                    index = j;
                }
            }
            Student temp = students[index];
            students[index] = students[i];
            students[i] = temp;
        }*/
        System.out.println(ascending ? "Students sorted by marks (ascending)." : "Students sorted by marks (descending).");
        printAllStudents();
    }

    private int customMin(int a, int b) {
        return (a < b) ? a : b;
    }

    private int customCompareTo(String s1, String s2) {
        int length = customMin(s1.length(), s2.length()); // Use customMin instead of Math.min

        for (int i = 0; i < length; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }

        // If all characters are the same, compare by length
        return s1.length() - s2.length();
    }

    // Sorting by name (ascending or descending)
    private void sortByName(boolean ascending) {
        // Bubble Sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                int comparisonResult = customCompareTo(students[j].getName(), students[j + 1].getName());
                if ((ascending && comparisonResult > 0) ||
                        (!ascending && comparisonResult < 0)) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        // Selection Sort
        /*for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                int comparisonResult = customCompareTo(students[j].getName(), students[index].getName());
                if ((ascending && comparisonResult < 0) ||
                        (!ascending && comparisonResult > 0)) {
                    index = j;
                }
            }
            Student temp = students[index];
            students[index] = students[i];
            students[i] = temp;
        }*/
        System.out.println(ascending ? "Students sorted by name (ascending)." : "Students sorted by name (descending).");
        printAllStudents();
    }

    // Sorting method based on user input (either by name or marks and in chosen order)
    public void sortStudents(int sortType, boolean ascending) {
        switch (sortType) {
            case 1: // Sort by marks
                sortByMarks(ascending);
                break;
            case 2: // Sort by name
                sortByName(ascending);
                break;
            default:
                System.out.println("Invalid sort type.");
        }
    }

    // Use to search by ID
    private boolean customEquals(String str1, String str2) {
        // String comparison
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Use to search by name
    private boolean customEqualsIgnoreCase(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
        }
        return true;
    }

    public void searchStudent(String criteria, int searchType) {
        if (size == 0) {
            System.out.println("Error: No students available to search.");
            return;
        }

        boolean found = false;
        switch (searchType) {
            case 1: // Search by ID
                for (int i = 0; i < size; i++) {
                    if (customEquals(students[i].getId(), criteria)) {
                        System.out.println("Student found: " + students[i]);
                        found = true;
                        break;
                    }
                }
                break;

            case 2: // Search by name
                for (int i = 0; i < size; i++) {
                    if (customEqualsIgnoreCase(students[i].getName(), criteria)) {
                        System.out.println("Student found: " + students[i]);
                        found = true;
                    }
                }
                break;

            default:
                System.out.println("Invalid search type.");
                return;
        }

        if (!found) {
            System.out.println("Error: Student not found.");
        }
    }

    public void printAllStudents() {
        if (size == 0) {
            System.out.println("No students to display.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(students[i]);
            }
        }
    }

    // Print ranking from high to low
    public void printStudentRanking() {
        sortByMarks(false); // Sorts students by marks in descending order
        System.out.println("Student Ranking (sorted by marks):");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + students[i].getName() + " - Marks: " + students[i].getMarks() + ", Rank: " + students[i].getRank());
        }
    }

    // Create 10 random students using custom random logic
    public void createRandomStudents() {
        if (size + 10 > students.length) {
            System.out.println("Not enough capacity to add 10 random students.");
            return;
        }

        RandomStudent random = new RandomStudent(globalSeed);
        String[] randomNames = {"Dung", "Minh", "Nhung", "Cuong", "Toan", "Thang", "Khanh", "Dat", "Phuc", "Kien"};

        for (int i = 0; i < 10; i++) {
            String id = "" + (size + 1);
            String name = randomNames[i];

            // Generate random marks between 0.0 and 10.0
            double marks = random.nextDouble() * 10.0;

            addStudent(new Student(id, name, marks));
        }
        System.out.println("10 random students created successfully.");
    }
}
