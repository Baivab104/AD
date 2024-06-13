public class LongestCommonSubsequence {

    // Function to find the length of the longest common subsequence of two strings
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store the lengths of LCS of substring
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array from bottom up
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // dp[m][n] contains the length of LCS for s1[0..m-1] and s2[0..n-1]
        return dp[m][n];
    }

    // Function to print the longest common subsequence of two strings
    public static String printLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store the lengths of LCS of substring
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array from bottom up
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the LCS from the dp table
        int index = dp[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[--index] = s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }

    // Main method to test the above functions
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("Length of LCS: " + lcs(s1, s2));
        System.out.println("LCS: " + printLCS(s1, s2));
    }
}
