package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fan on 10/24/15.
 */
public class RotateImage {
    public static void rotate(int[][] matrix){
        reverse(matrix);
        transpose(matrix);
    }
    public static void antiRotate(int[][] matrix){
        transpose(matrix);
        reverse(matrix);
    }
    private static void transpose(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
    private static <T> void reverse(T[] arr){
        for(int i=0; i<arr.length/2; i++){
            T tmp = arr[i];
            arr[i]=arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }
    }
    private static void printMatrix(int[][] matrix){
        for(int[] arr:matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        printMatrix(matrix);
        rotate(matrix);
        printMatrix(matrix);
        antiRotate(matrix);
        printMatrix(matrix);
    }
}
