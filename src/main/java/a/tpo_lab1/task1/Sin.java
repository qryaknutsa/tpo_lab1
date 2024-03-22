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

    public static double calc(double x, int n) {
        double PI2 = Math.PI * 2;

        if (x >= 0) {
            while (x > PI2) {
                x -= PI2;
            }
        } else if (x < 0) {
            while (x < PI2) {
                x += PI2;
            }
        }

        double result = 0;  // Хранится результат

        double xx = x * x;  // x^2
        double pow = x;     // Для Вычисления числителя
        double fact = 1;    // Для вычисления факториала знаменателя

        int sign = 1;       // Отвечает за знак (чередование '+' -> '-' -> '+' -> ...)

        for (int i = 1; i < n; i += 2) {
            fact /= i;
            result += sign * pow * fact;    // (-1)^(n-1) * x^(2n-1) / (2n-1)!
            sign = -sign;
            fact /= (i + 1);
            pow *= xx;
        }

        // Дополнительно округляем, чтобы легче было проверять
        return result;
    }

    public static void main(String[] args) {
        double ee = sinTailor(-1, 100);
        System.out.println(ee);
        ee = calc(-1, 100);
        System.out.println(ee);
    }
}
