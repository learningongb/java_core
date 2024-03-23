public class Main {
    public static void main(String[] args) {
        int[] arr1 = {3,4,0,4,0,3};
        int[] arr2 = {3,5,4,0,0};

        System.out.println(hasTwoNearZero(arr1));
        System.out.println(hasTwoNearZero(arr2));
    }

    private static boolean hasTwoNearZero(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0 && arr[i + 1] == arr[i]) {
                return true;
            }
        }
        return false;
    }
}
