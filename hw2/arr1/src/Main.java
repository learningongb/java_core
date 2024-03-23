public class Main {

    public static void main(String[] args) {

        int[] arr = {2, 1, 2, 3, 4};
        System.out.println(countEvents(arr));
    }

    private static int countEvents(int[] arr) {
        int count = 0;
        for (int i: arr) {
            if (i % 2 == 0)
                count++;
        }
        return count;
    }
}
