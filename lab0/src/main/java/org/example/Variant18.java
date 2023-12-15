package org.example;

public class Variant18 {

    static int integerTask(int n) { return (n / 1000) % 10;}

    static public boolean booleanTask(int a, int b, int c) {
        return a == b || b == c || a == c;
    }

    static public int if_task(int a, int b, int c) {
        if (a != b && a != c) return 1;
        if (b != a) return 2;
        return 3;
    }

    static public String caseTask(int n) {
        String result = "";

        switch (n / 100) {
            case 9 -> result += "дев'ятсот ";
            case 8 -> result += "вісімсот ";
            case 7 -> result += "сіімсот ";
            case 6 -> result += "шістсот ";
            case 5 -> result += "п'ятьсот ";
            case 4 -> result += "чотириста ";
            case 3 -> result += "триста ";
            case 2 -> result += "двісті ";
            case 1 -> result += "сто ";
        }

        int a = (n / 10) % 10;
        if (a > 1) {
            switch (a) {
                case 9 -> result += "дев'яносто ";
                case 8 -> result += "вісімдесят ";
                case 7 -> result += "сімдесят ";
                case 6 -> result += "шітдесят ";
                case 5 -> result += "п'ятьдест ";
                case 4 -> result += "сорок ";
                case 3 -> result += "тридцять ";
                case 2 -> result += "двадцять ";
            }

            switch (a % 10) {
                case 9 -> result += "дев'ять";
                case 8 -> result += "вісім";
                case 7 -> result += "сім";
                case 6 -> result += "шість";
                case 5 -> result += "п'ять";
                case 4 -> result += "чотири";
                case 3 -> result += "три";
                case 2 -> result += "два";
                case 1 -> result += "один";
            }
        }

        else
            switch (n % 10) {
                case 9 -> result += "дев'ятнадцять";
                case 8 -> result += "вісімнадцять";
                case 7 -> result += "сімнадцять";
                case 6 -> result += "шістнадцять";
                case 5 -> result += "п'ятнадцять";
                case 4 -> result += "чотирнадцять";
                case 3 -> result += "тринадцять";
                case 2 -> result += "дванадцять";
                case 1 -> result += "одинадцять";
            }

        return result;
    }


    static public double forTask(double a, int n) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += (Math.pow(-1, i)) * Math.pow(a, i);
        }

        return result;
    }

    static public int[] whileTask(int n) {
        int sum = 0, count = 0;
        while (n > 0) {
            sum += n % 10;
            count ++;
            n = n / 10;
        }

        return new int[] {sum, count};
    }

    static public int minmaxTask(int n, int[] array)
    {
        int firstIndex = 0, lastIndex = 0;
        for (int i = 1; i < n; i++) {
            if (array[firstIndex] == array[i]) lastIndex = i;
            if (array[i] > array[firstIndex]) {
                firstIndex = i;
                lastIndex = i;
            }
        }

        if (firstIndex == lastIndex) return 0;
        return lastIndex - firstIndex - 1;
    }

    static public int arrayTask(int[] array) {
        for (int i = 0; i < 9; i++) {
            if (array[i] < array[9]) return array[i];
        }

        return 0;
    }

    static public int[] matrixTask(int[][] matrix, int k) {
        int sum = 0;
        int prod = 1;

        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][k];
            prod *= matrix[i][k];
        }

        return new int[] {sum, prod};
    }
}
