public class bubblesort {
    public static void main(String[] args) {
        int[] numbers = {2,3,5,45,76,45,4,56,6,45,3,5};
        for(int j = 0; j < numbers.length; j++){
            if(j+1 < numbers.length && numbers[j] > numbers[j+1]){
                int temp = numbers[j];
                numbers[j] = numbers[j+1];
                numbers[j+1] = temp;
                j = 0;
            }else{
                continue;
            }
        }
        System.out.print("Numbers: ");
        for(int i : numbers){
            System.out.print(i+", ");
        }
    }
}