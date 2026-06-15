public class NumberPatterns {
    public static void main(String[] args) {
        int n = 5;

        // ── Pattern 1: Right-angle triangle of numbers ──
        /*  1
            1 2
            1 2 3
            1 2 3 4
            1 2 3 4 5  */
        System.out.println("Pattern 1: Number Triangle");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        // ── Pattern 2: Multiplication table triangle ──
        /*  1
            2 4
            3 6  9
            4 8 12 16
            5 10 15 20 25  */
        System.out.println("\nPattern 2: Multiplication Triangle");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }

        // ── Pattern 3: Inverted star triangle ──
        /*  * * * * *
            * * * *
            * * *
            * *
            *         */
        System.out.println("\nPattern 3: Inverted Star Triangle");
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // ── Pattern 4: Floyd's Triangle ──
        /*   1
             2  3
             4  5  6
             7  8  9 10  */
        System.out.println("\nPattern 4: Floyd's Triangle");
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%4d", num++);
            }
            System.out.println();
        }

        // ── Pattern 5: Diamond Pattern ──
        System.out.println("\nPattern 5: Diamond");
        for (int i = 1; i <= n; i++) {
            for (int k = n; k > i; k--) System.out.print(" ");
            for (int j = 1; j <= (2*i-1); j++) System.out.print("*");
            System.out.println();
        }
        for (int i = n-1; i >= 1; i--) {
            for (int k = n; k > i; k--) System.out.print(" ");
            for (int j = 1; j <= (2*i-1); j++) System.out.print("*");
            System.out.println();
        }
    }
}