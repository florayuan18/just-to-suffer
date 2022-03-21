import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class QueueReverse {

    private Stack<Integer> myStack;

    // constructor
    public QueueReverse() {
        this.myStack = new Stack<Integer>(); // allocating memory
    }

    public void reverseQueue(Queue<Integer> queue1) { // taking every element of queue1 out
        for (Integer object : queue1) { // passing element from queue1 to object
//            System.out.println("queue === " + object);
            myStack.push(object); // object to myStack, goes in order of number 1 2 3
        }
    }

    public String toString() { // using toString to print object
        String myOutput = "";

        while(!myStack.isEmpty()) { // if stack is not empty
            myOutput += myStack.pop() + " ";
        }
        myOutput += "\n";
        return myOutput;
    }


    public static void printQueue(Queue<Integer> myQueue) {
        String myOutput = "";
        Integer myQueueLength = myQueue.size();
        Integer count = 0;

        for (Integer object : myQueue) {
            myOutput += object + " ";
        }
        System.out.print(myOutput);
    }

    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>(); // create the queue1

        QueueReverse queueReverseObj = new QueueReverse();

        // creating queue1 with three elements + print original order
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        QueueReverse.printQueue(queue1); // calling printQueue (not object, class instead)

        // using object to call reverseQueue function
        queueReverseObj.reverseQueue(queue1);

        System.out.println();

        // print object based on toString
        System.out.println(queueReverseObj);
    }
}