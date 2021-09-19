import java.util.*;

import java.math.*;

public class ClosestPair {
    public static class Point {
        private int x;
        private int y;
    
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
    }

    static Scanner keyboard = new Scanner(System.in);
    static Random random = new Random();

    public static double calculateDistance(Point A, Point B) {
        double distance = Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2));
        return distance;
    }

    public static void input(Point arr[]) {
        int n = 100000;
        // System.out.println("Nhap diem: x y");
        for (int i = 0; i < arr.length; i++) {
            // System.out.println("diem " + (i+1) +" : ");
            // System.out.print(" x= ");
            int x = random.nextInt(n);
            // int x = keyboard.nextInt();
            // System.out.print(" y= ");
            int y = random.nextInt(n);
            // int y = keyboard.nextInt();
            arr[i] = new Point(x, y);
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static double findMin(Point arr[]) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (calculateDistance(arr[i], arr[j]) < min)
                    min = calculateDistance(arr[i], arr[j]);
            }
        }
        return min;
    }

    public static double min(double x, double y) {
        if (x < y)
            return x;
        else
            return y;
    }

    public static void quickSort(Point arr[], int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int pivot = arr[middle].getX();
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].getX() < pivot) {
                i++;
            }
            while (arr[j].getX() > pivot) {
                j--;
            }
            if (i <= j) {
                Point temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    public static void closestPairDAC(Point arr[]) {
        quickSort(arr, 0, arr.length - 1);
        if (arr.length < 3) {
            System.out.println("2 diem gan nhau nhat la: (" + arr[0].getX() + "," + arr[0].getY() + "va ("
                    + arr[1].getX() + "," + arr[1].getY() + ")");
            System.out.println("Co khoang cach la: " + calculateDistance(arr[0], arr[1]));
        } else {
            Point Lx[] = new Point[arr.length / 2];
            for (int i = 0; i < Lx.length; i++) {
                Lx[i] = arr[i];
            }
            int mid = arr.length / 2;

            if (arr.length % 2 == 0) {
                Point Rx[] = new Point[mid];
                for (int i = mid; i < arr.length; i++) {
                    Rx[i - mid] = arr[i];
                }
                double delta = min(findMin(Lx), findMin(Rx));
                double m = (Lx[arr.length / 2 - 1].getX() + Rx[0].getX()) / 2;

                int count = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].getX() > (m - delta) && arr[i].getX() < (m + delta))
                        count++;
                }
                Point B[] = new Point[count];
                int c = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].getX() > (m - delta) && arr[i].getX() < (m + delta)) {
                        B[c] = arr[i];
                        c++;
                    }
                }
                System.out.println("ClosestPair DAC: " + min(delta, findMin(B)));
            } else {
                Point Rx[] = new Point[mid + 1];
                for (int i = mid; i < arr.length; i++) {
                    Rx[i - mid] = arr[i];
                }
                double delta = min(findMin(Lx), findMin(Rx));
                double m = (Lx[(arr.length / 2) - 1].getX() + Rx[0].getX()) / 2;
                int count = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].getX() > (m - delta) && arr[i].getX() < (m + delta))
                        count++;
                }
                Point B[] = new Point[count];
                int c = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].getX() > (m - delta) && arr[i].getX() < (m + delta)) {
                        B[c] = arr[i];
                        c++;
                    }
                }
                System.out.println("ClosestPair DAC: " + min(delta, findMin(B)));
            }
        }
    }

    public static void closestPairLinear(Point arr[]) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (calculateDistance(arr[i], arr[j]) < min) {
                    min = calculateDistance(arr[i], arr[j]);
                }
            }
        }
        System.out.println("ClosestPair Linear: " + min);
    }

    public static void main(String... args) {
        int n = 10000;
        Point A[] = new Point[n];
        input(A);
        long startTime = System.currentTimeMillis();
        closestPairDAC(A);
        long endTime = System.currentTimeMillis();
        System.out.println("Time = " + (endTime - startTime));
        startTime = System.currentTimeMillis();
        closestPairLinear(A);
        endTime = System.currentTimeMillis();
        System.out.println("Time = " + (endTime - startTime));

        // System.out.println(findMin(A));
        // System.out.println("Ax");
        // for (int i = 0; i < A.length; i++) {
        // System.out.println(A[i].getX() + " " + A[i].getY());
        // }
    }
}