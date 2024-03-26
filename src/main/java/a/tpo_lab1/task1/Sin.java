package a.tpo_lab1.task1;

import static java.lang.Math.*;

public class Sin {

    public static double sinTailor(double x, int n) {

        double res = 0;
        int sign = 1;
        double fact = 1;

        for (int i = 1; i <= n; i += 2) {
            fact *= i;
            res += pow(x, i) * sign / fact;
            fact *= (i + 1);
            sign = -sign;
        }
        return res;
    }



    public static void main(String[] args) {
        double ee = sinTailor(-1, 100);
        System.out.println(ee);
    }
}
