public class bubblesort {
    public static void main(String[] args) {
        int[] numbers = {64, 24, 12, 22, 11};
        int swaps = 0;
        // Optimized bubble sort: stop early if no swaps in a pass
        boolean swapped;
        for (int pass = 0; pass < numbers.length - 1; pass++) {
            swapped = false;
            // After each pass the largest element among unsorted moves to its final position
            for (int i = 0; i < numbers.length - 1 - pass; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    swaps++;
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // array is already sorted
        }
        System.out.print("Numbers: ");
        for(int i : numbers){
          System.out.print(i+", ");
        }
        System.out.print("\nSwaps: "+swaps);

    }
}