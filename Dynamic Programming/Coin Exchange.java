import java.util.Arrays;

public class CoinExchange {

    // Function to find the minimum number of coins needed to make the given amount
    public static int minCoins(int[] coins, int amount) {
        // Initialize dp array with a value greater than the possible maximum number of coins
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins are needed to make amount 0
        dp[0] = 0;

        // Iterate through all amounts from 1 to amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still greater than amount, return -1 as it is not possible to make the amount
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Main method to test the above function
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        int result = minCoins(coins, amount);
        if (result != -1) {
            System.out.println("Minimum number of coins required: " + result);
        } else {
            System.out.println("It is not possible to make the amount with the given coins.");
        }
    }
}
