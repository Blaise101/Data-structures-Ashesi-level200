class Main {
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
        
        char char1 = 'a';
        char char2 = 'z';
        
        System.out.println("");
        System.out.println((int) char1);
        System.out.println("====================");
        System.out.println((int) char2);
        
    }
}