package com.gmail.at.kotamadeo.matrix;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Random;

public class Matrix {
    private int size;
    private int[][] colors;

    public Matrix(int size) {
        this.size = size;
        colors = new int[size][size];
        for (var i = 0; i < colors.length; i++) {
            for (var j = 0; j < colors.length; j++) {
                var random = new Random();
                colors[i][j] = random.nextInt(256); // GrayScale 8 bit;
            }
        }
    }

    public void printOriginalMatrix(Matrix matrix) {
        for (var i = 0; i < matrix.size; i++) {
            for (var j = 0; j < matrix.size; j++) {
                System.out.printf("%4d", matrix.colors[i][j]);
            }
            System.out.println();
        }
    }

    public void printRotatedMatrix(int degree, Matrix matrix) {
        System.out.printf("%sВаша текущая матрица размером %sx%s:%s%n", Utils.ANSI_BLUE, size, size, Utils.ANSI_RESET);
        printOriginalMatrix(matrix);
        Utils.printDelim();
        if (degree == 90 || degree == 180 || degree == 270 || degree == 360) {
            System.out.printf("%sВаша матрица повернутая на %s°%s%n", Utils.ANSI_GREEN, degree, Utils.ANSI_RESET);
            var stepForRotate = switch (degree) {
                case 90 -> 1;
                case 180 -> 2;
                case 270 -> 3;
                case 360 -> 4;
                default -> 0;
            };
            for (var degreeStep = 0; degreeStep < stepForRotate; degreeStep++) {
                var rotatedColors = new int[size][size];
                for (var i = 0; i < colors.length; i++) {
                    for (var j = 0; j < colors.length; j++) {
                        rotatedColors[j][colors.length - i - 1] = colors[i][j];
                    }
                }
                colors = rotatedColors;
            }
            printOriginalMatrix(matrix);
        } else {
            System.out.printf("%sНельзя повернуть матрицу на %s°!%s%n", Utils.ANSI_RED, degree, Utils.ANSI_RESET);
        }
    }

    public int getSize() {
        return size;
    }
}
