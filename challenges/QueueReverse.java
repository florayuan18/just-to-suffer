import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class QueueReverse {

    private Stack<Integer> myStack;

    // constructor
    public QueueReverse() {
        this.myStack = new Stack<Integer>();
    }

    public void reverseQueue(Queue<Integer> queue1) {
        for (Integer object : queue1) {
//            System.out.println("queue === " + object);
            myStack.push(object);
        }
    }

    public String toString() {
        String myOutput = "";

        while(!myStack.isEmpty()) {
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
        Queue<Integer> queue1 = new LinkedList<>();

        QueueReverse queueReverseObj = new QueueReverse();

        // creating queue1 + print
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        QueueReverse.printQueue(queue1);

        // using object to call reverseQueue function
        queueReverseObj.reverseQueue(queue1);

        System.out.println();

        // print object based on toString
        System.out.println(queueReverseObj);
    }
}