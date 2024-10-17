package ASM.StudentManagement;

public class RandomStudent {
    private long number;

    public RandomStudent(long number) {
        this.number = number;
    }

    // Random double value between 0.0 and 1.0
    public double nextDouble() {
        number = (number * 1103515245 + 12345) & 0x7fffffff;
        return (double) number / ((double) 0x7fffffff);  // Convert to a value between 0.0 and 1.0
    }
}
