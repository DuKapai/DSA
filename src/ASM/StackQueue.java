package ASM;

public class StackQueue {

    public static class Queue {
        private int[] queue;
        private int front, rear, size, capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            queue = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void enqueue(int value) {
            if (size < capacity) {
                rear = (rear + 1) % capacity;
                queue[rear] = value;
                size++;
            } else {
                System.out.println("Queue Overflow");
            }
        }

        public int dequeue() {
            if (size > 0) {
                int value = queue[front];
                front = (front + 1) % capacity;
                size--;
                return value;
            } else {
                System.out.println("Queue Underflow");
                return -1;
            }
        }

        public int peek() {
            if (size > 0) {
                return queue[front];
            } else {
                System.out.println("Queue is Empty");
                return -1;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    // Stack ADT Implementation
    public static class Stack {
        private int[] stack; // Array to store stack elements
        private int top; // Index of the top element
        private int capacity; // Maximum capacity of the stack

        // Constructor to initialize the stack
        public Stack(int capacity) {
            this.capacity = capacity; // Set the capacity of the stack
            stack = new int[capacity]; // Initialize the array for stack storage
            top = -1; // No elements in the stack, so top is -1
        }

        // Push operation to add an element to the top of the stack
        public void push(int value) {
            if (top < capacity - 1) { // Ensure the stack is not full
                top++; // Increment top to make room for the new element
                stack[top] = value; // Add the new element
            } else {
                System.out.println("Stack Overflow");
            }
        }

        // Pop operation to remove and return the top element
        public int pop() {
            if (top >= 0) { // Ensure the stack is not empty
                int value = stack[top]; // Get the top element
                top--; // Decrease top to remove the element
                return value; // Return the popped value
            } else {
                System.out.println("Stack Underflow");
                return -1; // Return a sentinel value for underflow
            }
        }

        // Peek operation to view the top element without removing it
        public int peek() {
            if (top >= 0) { // Ensure the stack is not empty
                return stack[top]; // Return the top element
            } else {
                System.out.println("Stack is Empty");
                return -1; // Return a sentinel value for an empty stack
            }
        }

        // isEmpty operation to check if the stack is empty
        public boolean isEmpty() {
            return top == -1; // Return true if there are no elements
        }
    }

    // Main method to test both data structures
    public static void main(String[] args) {
        // Testing Queue
        Queue queue = new Queue(5); // Create a queue with a capacity of 5

        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        // Demonstrating FIFO by dequeuing elements
        System.out.println("Queue Dequeue: " + queue.dequeue()); // Should remove 10
        System.out.println("Queue Dequeue: " + queue.dequeue()); // Should remove 20

        // Enqueue more elements to see circular behavior
        queue.enqueue(60);
        queue.enqueue(70);

        // Continue dequeueing to demonstrate the order
        System.out.println("Queue Contents:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        // Testing Stack
        Stack stack = new Stack(5); // Create a stack with capacity of 5

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        // Demonstrating LIFO by popping elements
        System.out.println("Stack Pop: " + stack.pop()); // Should remove 50
        System.out.println("Stack Pop: " + stack.pop()); // Should remove 40

        // Push more elements to see stack behavior
        stack.push(60);
        stack.push(70);

        // Continue popping to demonstrate the order
        System.out.println("Stack Contents:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
