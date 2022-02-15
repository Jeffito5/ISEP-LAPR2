package app.ui.console.utils;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class MathUtils {

    /**
     * Method that returns the t-student critical value for a given alpha
     * and degrees of freedom [Table 7 of MATCP]
     *
     * @param alphaTD alpha.
     * @param degreesOfFreedom degrees of freedom.
     *
     * @return t-Student value.
     */
    public static double tStudentTable (double alphaTD, int degreesOfFreedom)
    {
        // Table number 7 from MATCP - t-Student
        TDistribution td = new TDistribution(degreesOfFreedom);
        if (alphaTD > 0.5)
        {
            return td.inverseCumulativeProbability(alphaTD / 2);
        }
        else
        {
            return td.inverseCumulativeProbability(1 - alphaTD / 2);
        }
    }

    /**
     * Method that returns the fisher-Snedecor value for a given alpha
     * and degrees of freedom [Table 8 of MATCP]
     *
     * @param alphaFD alpha.
     * @param numDegreesOfFreedom numerator degrees of freedom.
     * @param denDegreesOfFreedom denominator degrees of freedom.
     *
     * @return fisher-Snedecor value.
     */
    public static double fisherSnedecorTable (double alphaFD, double numDegreesOfFreedom,
                                       double denDegreesOfFreedom)
    {
        FDistribution fd = new FDistribution(numDegreesOfFreedom, denDegreesOfFreedom);
        return fd.inverseCumulativeProbability(1 - alphaFD);
    }

    /**
     * Method that returns the multiplication of two matrices.
     *
     * @param matrix1 Matrix 1
     * @param matrix2 Matrix 2
     *
     * @return resulting multiplied matrix.
     */
    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2)
    {
        int l1 = matrix1.length; // Matrix 1 Lines
        int c1 = matrix1[0].length; // Matrix 1 Columns
        int c2 = matrix2[0].length; // Matrix 2 Columns

        double sum;
        double[][] matrixResult = new double[l1][c2];
        for (int w = 0; w < l1; w++)
        {
            for (int c = 0; c < c2; c++)
            {
                sum = 0;
                for (int i = 0; i < c1; i++)
                {
                    sum += matrix1[w][i] * matrix2[i][c];
                }
                matrixResult[w][c] = sum;
            }
        }
        return matrixResult;
    }

    /**
     * Method that returns the multiplication of a matrix to a vector.
     *
     * @param matrix Matrix
     * @param vector Vector
     *
     * @return resulting multiplied vector.
     */
    public static double[] multiplyMatrixToArray(double[][] matrix, double[] vector)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;

        if (columns != vector.length)
        {
            throw new IllegalArgumentException("Matrix columns do not match vector rows");
        }

        double[] result = new double[rows];

        for (int row = 0; row < rows; row++)
        {
            double sum = 0;
            for (int column = 0; column < columns; column++)
            {
                sum += matrix[row][column]
                        * vector[column];
            }
            result[row] = sum;
        }
        return result;
    }

    /**
     * Method that returns the transposed of a given matrix.
     *
     * @param matrix Matrix
     *
     * @return resulting transposed matrix.
     */
    public static double[][] transposeMatrix(double[][] matrix)
    {
        double[][] result = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    /**
     * Method that returns the transposed of a given vector.
     *
     * @param array vector
     *
     * @return resulting transposed vector.
     */
    public static double[][] transposeArray(double[] array)
    {
        double[][] result = new double[1][array.length];
        for (int i = 0; i < array.length; i++)
        {
            result[0][i] = array[i];
        }
        return result;
    }

    public static void printMatrix (double[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }

    }

    public static void printArray (double[] array)
    {
        for (double d : array) {
            System.out.println(d);
        }
    }

    /**
     * Method that constructs the Anova table for the report.
     *
     * @param sr Unexplained variation.
     * @param msr Quadratic regression mean.
     * @param f0 Observed statistical value.
     * @param se Explained variation.
     * @param n Number of observations.
     * @param mse Quadratic error mean.
     * @param st Total variation.
     * @param alpha test alpha.
     * @param fisherSnedecor fisher-Snedecor value.
     *
     * @return String containing the Anova table.
     */
    public static String anovaTableBuilder (double sr, double msr, double f0,
                                            double se, int n, double mse, double st,
                                            double alpha, double fisherSnedecor)
    {
        StringBuilder t = new StringBuilder();
        t.append(String.format("%-20s%-10s%-6s%-12s%-10s%n", "Variation Source", "SoS", "DOF", "Quad Mean", "F0")).
                append("-----------------------------------------------------\n").
                append(String.format("%-20s%-10.2f%-6d%-12.2f%-10.2f%n", "Regression", sr, 1, msr, f0)).
                append(String.format("%-20s%-10.2f%-6d%-12.2f%n", "Error", se, n - 2, mse)).
                append(String.format("%-20s%-10.2f%-6d%n%n", "Total", st, n - 1)).
                append(String.format("Fisher-Snedecor value (%.2f %%): %.4f%n%n", alpha, fisherSnedecor));

        return t.toString();
    }

    /**
     * Method that constructs the Multiple Linear Regression significance
     * test for the report.
     *
     * @param sqr Residual variance.
     * @param k Quadratic regression mean.
     * @param mqr Quadratic regression mean.
     * @param f0 Observed statistical value.
     * @param sqe Error variance.
     * @param n Number of observations.
     * @param mqe Quadratic error mean.
     * @param sqt Total variance.
     * @param alpha test alpha.
     * @param fisherSnedecor fisher-Snedecor value.
     *
     * @return String containing the Significance model table.
     */
    public static String mlrSignificanceTableBuilder(double sqr, int k, double mqr,
                                                     double f0, double sqe, int n, double mqe,
                                                     double sqt, double alpha, double fisherSnedecor)
    {
        StringBuilder t = new StringBuilder();
        t.append(String.format("%-20s%-10s%-6s%-12s%-10s%n", "Variation Source", "SoS", "DOF", "Quad Mean", "F0")).
                append("-----------------------------------------------------\n").
                append(String.format("%-20s%-10.2f%-6d%-12.2f%-10.2f%n", "Regression", sqr, k, mqr, f0)).
                append(String.format("%-20s%-10.2f%-6d%-12.2f%n", "Error", sqe, n - (k + 1), mqe)).
                append(String.format("%-20s%-10.2f%-6d%n%n", "Total", sqt, n - 1)).
                append(String.format("Fisher-Snedecor value (%.2f %%): %.4f%n%n", alpha, fisherSnedecor));
        return t.toString();
    }

    /**
     * Method that returns the necessary matrix for the multiple linear regression model.
     *
     * @param x1 vector 1.
     * @param x2 vector 2.
     *
     * @return resulting X variable matrix for the MLR model.
     */
    public static double[][] getXMatrix (double[] x1, double[] x2)
    {
        if (x1.length != x2.length)
        {
            throw new IllegalArgumentException("Array lengths are not equal");
        }

        double[][] result = new double[x1.length][3];

        for (int i = 0; i < x1.length; i++)
        {
            result [i][0] = 1;
            result [i][1] = x1[i];
            result [i][2] = x2[i];
        }

        return result;
    }

    /**
     * Method that merges two vectors horizontally.
     *
     * @param x1 Vector 1.
     * @param x2 Vector 2.
     *
     * @return resulting merged matrix.
     */
    public static double[][] mergeArrays (double[] x1, double[] x2)
    {
        if (x1.length != x2.length)
        {
            throw new IllegalArgumentException("Array lengths are not equal");
        }

        double[][] result = new double[x1.length][2];

        for (int i = 0; i < x1.length; i++)
        {
            result [i][0] = x1[i];
            result [i][1] = x2[i];
        }

        return result;
    }
}
