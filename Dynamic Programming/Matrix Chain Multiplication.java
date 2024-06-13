public class MatrixChainMultiplication {

    // Function to find the most efficient way to multiply matrices
    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1; // Number of matrices
        
        // m[i][j] will hold the minimum number of multiplications needed to compute
        // the product of matrices A[i]...A[j] where dimensions of A[i] are p[i-1] x p[i]
        int[][] m = new int[n + 1][n + 1];

        // l is chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    // q = cost / scalar multiplications
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }

        // m[1][n] is the minimum cost to multiply matrices from 1 to n
        return m[1][n];
    }

    // Main method to test the above function
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4}; // Dimensions of matrices A1, A2, and A3 are 1x2, 2x3, and 3x4
        System.out.println("Minimum number of multiplications is " + matrixChainOrder(arr));
    }
}
