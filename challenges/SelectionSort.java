import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort extends Sorts {
    private final ArrayList<Integer> data = new ArrayList<>();
    private final Duration timeElapsed;

    // constructor
    public SelectionSort(int size) {
        super(size);

        Instant start = Instant.now();  // time capture -- start
        // build an array
        for (int i = 0; i < size; i++) {
            data.add((int)(Math.random() * (size+1)));
        }
        // use Inheritance and Polymorphism to replace data.sort with your own algorithm
        // data.sort(Comparator.naturalOrder());
        // cannot use, using own sort function
        Instant end = Instant.now();    // time capture -- end
        this.timeElapsed = Duration.between(start, end);
    }

    // getter
    public ArrayList<Integer> getData() {
        return data;
    }

    // getter -- object calls getter
    public int getTimeElapsed() {
        return timeElapsed.getNano();
    }

    // sort function --> to sort
    public void sort(ArrayList<Integer> myArray)
    {
        // Assign myArray elements to the array int[] arr
        int[] arr = new int[myArray.size()];
        for (int m = 0; m < myArray.size(); m++)
        {
            arr[m] = myArray.get(m);
        }

        // sorting
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }

        // Assigning int[] arr elements to data
        for (int k = 0; k < arr.length; k++)
        {
            this.data.set(k, arr[k]); // set to first index
        }
    }

    public static void main(String[] args)
    {
        int sum=0, time=0, TIMES=12, SIZE=5000;

        for(int i=0; i< TIMES; i++)
        {
            SelectionSort s = new SelectionSort(SIZE);
            // System.out.println(s.getData()); // moved from below because the other place printed it 10 times lol --> pre sorted data
            s.sort(s.getData());
            System.out.println(s.getData()); // only prints the sorted data
            for(int j = 0; j<s.getData().size(); j++)
            {
                // To see data, uncomment next line
                //System.out.println(s.getData());
                sum += s.getData().get(j);
            }
            System.out.println("Average random: " + sum / ((i+1)*SIZE));
            System.out.println("Nanoseconds: " + s.getTimeElapsed());
            time += s.getTimeElapsed();
        }
        System.out.println("Average random: " + sum / (TIMES*SIZE));
        System.out.println("Total Nanoseconds: " + time );
        System.out.println("Total Seconds: " + time /1000000000.0);
    }

}