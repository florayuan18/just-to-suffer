import java.util.LinkedList;
import java.util.Queue;

class QueueMerge {

    private Queue<Integer> mergedQueue;

    // constructor
    public QueueMerge() {
        this.mergedQueue = new LinkedList<>(); // assign private variable
    }

    public Queue<Integer> mergeTwoQueue(Queue<Integer> queue1, Queue<Integer> queue2) {
        Integer myLength = queue1.size(); // setting myLength to queue's size

        Integer element1 = -1;
        Integer element2 = -1;

        for (int i = 0; i < myLength; i++) {
            element1 = queue1.poll();
            element2 = queue2.poll();

            if (element1 != null && element2 != null) { // conditions for if both elements are not null
                if (element1 < element2) { // element in first queue is smaller than the element in the second queue
                    mergedQueue.add(element1); // first element added first in this case
                    mergedQueue.add(element2);
                }
                else { // if the above does not occur
                    mergedQueue.add(element2); // second element added first in this case
                    mergedQueue.add(element1);
                }
            }
            else if (element1 == null && element2 != null) { // if there are no more elements in queue1
                mergedQueue.add(element2); // take from second queue
            }
            else if (element1 != null && element2 == null) { // if there are no more elements in queue2
                mergedQueue.add(element1); // take from first queue
            }
            else {
                continue;
            }
        }
        return mergedQueue;
    }

    public String toString() { // using toString to print object
        String myOutput = "";
        Integer myQueueLength = mergedQueue.size();
        Integer count = 0;

        for (Integer object : mergedQueue) {
            count++;
            if (count < mergedQueue.size()) {
                myOutput += object + " -> "; // printing with the desired arrow format
            }
            else {
                myOutput += object + " -> nil"; // printing with the desired arrow format & nil
            }
        }
        myOutput += "\n";
        return myOutput; // return string that will be printed out later
    }

//    public static void printQueue3(Queue<Integer> myQueue) {
//        String myOutput = "";
//        Integer myQueueLength = myQueue.size();
//        Integer count = 0;
//
//        for (Integer object : myQueue) {
//            count++;
//            if (count < myQueue.size()) {
//                myOutput += object + " -> ";
//            }
//            else {
//                myOutput += object + " nil";
//            }
//        }
//        System.out.print(myOutput);
//    }

    // 1 -> 4 -> 5 -> 8 -> nil
    public static void printQueue(Queue<Integer> myQueue) {
        String myOutput = "";
        Integer myQueueLength = myQueue.size();
        Integer count = 0;

        for (Integer object : myQueue) {
            count++;
            if (count < myQueue.size()) {
                myOutput += object + " -> ";
            }
            else {
                myOutput += object + " -> nil";
            }
        }
        System.out.print(myOutput);
    }

    public static void main(String[] args) {

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        QueueMerge mergedQueue = new QueueMerge();

        // creating queue1
        queue1.add(1);
        queue1.add(4);
        queue1.add(5);
        queue1.add(8);
        QueueMerge.printQueue(queue1);
        System.out.println();

        // creating queue2
        queue2.add(2);
        queue2.add(3);
        queue2.add(6);
        queue2.add(7);
        QueueMerge.printQueue(queue2);
        System.out.println();

        mergedQueue.mergeTwoQueue(queue1, queue2);
        System.out.print(mergedQueue);
    }
}