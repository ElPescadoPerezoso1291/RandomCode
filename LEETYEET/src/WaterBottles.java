public class WaterBottles {
    public static int numWaterBottles(int numBottles, int numExchange) {
        int sum = 0;
        int numFull = numBottles;
        int numEmpty = 0;
        while(numFull != 0) {
            sum += numFull;
            numEmpty = numFull + numEmpty;
            numFull = numEmpty / numExchange;
            numEmpty -= numFull * numExchange;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(numWaterBottles(2, 3));
    }
}
