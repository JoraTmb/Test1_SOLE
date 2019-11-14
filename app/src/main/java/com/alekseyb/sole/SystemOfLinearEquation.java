package com.alekseyb.sole;

/**
 * Created by Алексей Бычков on 27.01.2018.
 */

public class SystemOfLinearEquation {
    int n;
    double a [][];
    double b [], x [];

    // Конструктор
    SystemOfLinearEquation (int nn) {
        n = nn;
        a = new double [n][n];
        b = new double [n];
        x = new double [n];
    }

    // Вычеркивание из матрицы matrix [][] строки номер im и столбца номер jm
    private double [][] getMinorMatrix (double [][] matrix, int im, int jm) {
        double [][] minor = new double[matrix.length-1][matrix.length-1];
        int l = 0;
        for (int i = 0; i < minor.length; i++, l++) {
            if (i == im) l++;
            int m = 0;
            for (int j = 0; j < minor.length; j++, m++) {
                if (j == jm) m++;
                minor [i][j] = matrix [l][m];
            }
        }
        return minor;
    }

    // Вычисление определителя матрицы рекурсивным методом разложения по строке
    private double getDet (double [][] matrix) {
        double det = 0;
        int n = matrix.length;
        if (n == 1) return (matrix [0][0]);
        if (n == 2) return (matrix [0][0] * matrix [1][1] - matrix [0][1] * matrix[1][0]);
        for (int i = 0; i < n; i++)
            det += Math.pow(-1, i) * matrix [i][0] * getDet (getMinorMatrix (matrix, i, 0));
        return (det);
    }

    // Матричный метод решения системы линейных уравнений
    boolean executeMatrixMethod () {
        double det = getDet (a);         // детерминант
        double [][] a1 = new double [n][n]; // обратная матрица

        if (Math.abs(det) < 1E-4) return (false);
        for (int i = 0; i < n; i++)     // Вычисляем обратную матрицу и транспонируем её
            for (int j = 0; j < n; j++)
                a1 [j][i] = Math.pow(-1, i+j) * getDet (getMinorMatrix (a,i,j));
        for (int i = 0; i < n; i++)     // Перемножаем обратную матрицу и вектор B
            for (int j = 0; j < n; j++)
                x[i] += a1[i][j] * b[j] / det;
        return (true);
    }
}
