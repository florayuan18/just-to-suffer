import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort extends Sorts {
    private final ArrayList<Integer> data = new ArrayList<>();
    private final Duration timeElapsed;

    // constructor
    public MergeSort(int size) {
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

    // setter
    public void setData(int myArray[])
    {
        for (int k = 0; k < myArray.length; k++)
        {
            this.data.set(k, myArray[k]); // set to first index
        }
    }

    // getter -- object calls getter
    public int getTimeElapsed() {
        return timeElapsed.getNano();
    }

    // Function to merge the subarrays of a[]
    void merge(int a[], int beg, int mid, int end)
    {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        // temporary Arrays
        int LeftArray[] = new int[n1];
        int RightArray[] = new int[n2];

        // copy data to temp arrays
        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];

        i = 0; // initial index of first sub-array
        j = 0; // initial index of second sub-array
        k = beg;  // initial index of merged sub-array

        while (i < n1 && j < n2)
        {
            if(LeftArray[i] <= RightArray[j])
            {
                a[k] = LeftArray[i];
                i++;
            }
            else
            {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i<n1)
        {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j<n2)
        {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    void mergeSort(int a[], int beg, int end)
    {
        if (beg < end)
        {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merge(a, beg, mid, end);
        }
    }



    // sort function --> to sort
    public void sort(ArrayList<Integer> myArray)
    {
        //1. Assign myArray elements to the array int[] arr
        int[] arr = new int[myArray.size()];
        for (int m = 0; m < myArray.size(); m++)
        {
            arr[m] = myArray.get(m);
        }

        //2. Merge sorting



        //3. Assigning int[] arr elements to data
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
            MergeSort s = new MergeSort(SIZE);
            // System.out.println(s.getData()); // moved from below because the other place printed it 10 times lol --> pre sorted data

            //1. Assign this.data elements to the array int[] arr
            int[] arr = new int[s.getData().size()];
            for (int m = 0; m < s.getData().size(); m++)
            {
                arr[m] = s.getData().get(m);
            }

            // 2. Merge sort
            int n = arr.length;
            s.mergeSort(arr, 0, n - 1);

            // 3, array to this.data
            //3. Assigning int[] arr elements to data
            s.setData(arr);

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