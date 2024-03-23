public class Main {
    public static void main(String[] args) {
        int[] arr = {3,2,1,5,4,6,5};
        System.out.println(diffMaxMin(arr));
    }

    private static int diffMaxMin(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i: arr) {
            min = i < min ? i : min;
            max = i > max ? i : max;
        }
        return max - min;
    }
}
