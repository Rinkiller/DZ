import java.util.Random;

//сортинг пузырьком


public class DZ2 {
    private static int[] fillarray(int size) {
    int[] array = new int[size];
    Random rand = new Random();
    for(int i = 0; i<size;i++)
        array[i] = rand.nextInt(10);
    return array;
    } 
    private static int[] sort_intarray(int[] array){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array.length - 1 - i; j++)
                if(array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;

    }
    private static void print_intarray(int[] array){
        for(int i =0; i < array.length; i++)
            System.out.print(array[i] + "  ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] array = fillarray(15); 
        print_intarray(array);
        print_intarray(sort_intarray(array));
    }
   
    
}
