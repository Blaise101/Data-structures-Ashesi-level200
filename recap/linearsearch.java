public class linearsearch {
    public static void main(String[] args) {
        int[] numbers = {2,3,5,45,76,45,4,56,6,45,3,5};
        int element = numbers[0];
        int position = 0;
        for(int j = 0; j < numbers.length; j++){
            if(numbers[j] > element){
                position = j+1;
                element = numbers[j];
            }
        }
        System.out.println("Element: "+ element);
        System.out.println("Position: "+ position);
    }
}