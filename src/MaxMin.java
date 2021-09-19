import java.util.Random;

public class MaxMin {

    static Random  random = new Random();
    public static void input(int arr[]){
        for(int i = 0; i<arr.length; i++){
            arr[i] = random.nextInt();
        }
        //quickSort(arr,0,arr.length-1);
    }
    public static int min(int A, int B){
        if(A < B){
            return A;
        }
        else{
            return B;
        }
    }
    public static int max(int A, int B){
        if(A > B){
            return A;
        }
        else{
            return B;
        }
    }
    public static int[] minmaxDAC(int arr[], int left, int right){
        if(right - left <= 1){
            return new int [] {min(arr[left],arr[right]),max(arr[left],arr[right])};
        }
        else{
            int result[] = minmaxDAC(arr, left, (left+right)/2);
            int min1 = result[0];
            int max1 = result[1];
            int result1[] = minmaxDAC(arr, (left+right)/2+1, right);
            int min2 = result1[0];
            int max2 = result1[1];
            return new int[] {min(min1, min2),max(max1, max2)};
        }
    }
    public static void minmaxLinear(int[] A) {
        int min = A[0];
		int max = A[0];
        for(int i=0; i<A.length; i++){
            if(A[i]<min){
                min=A[i];
            }	
        }
        for(int i=0; i<A.length; i++){
            if(A[i]>max){
                max=A[i];
            }	
        }
    }
    public static void quickSort(int arr[], int low, int high){
        if (arr == null || arr.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    public static void main(String[] args) {
        int n = 80000000;
        int A[] = new int[n];
        input(A);
        long startTime = System.currentTimeMillis();
        int result[] = minmaxDAC(A, 0 , A.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println("Time MinMax DAC: " + (endTime-startTime));
        startTime = System.currentTimeMillis();
        minmaxLinear(A);
        endTime = System.currentTimeMillis();
        System.out.println("Time MinMax linear: " + (endTime-startTime));
    }
        
}
