# **Задачи № 1 Поворот матрицы по часовой стрелке на 90°/180°/270°/360°**

## **Цель**:

1. Научиться поворачивать матрицу с равными сторонами. Этот алгоритм мог бы быть использован в графических редакторах вроде ```Photoshop``` для поворота изображений. Дано: двумерная матрица 8 на 8 из случайных чисел от 0 до 255 (спектр цветов GrayScale);
2. Напишите алгоритм "поворота" такой матрицы на 90 градусов по часовой стрелке;
3. Вывод должен быть вынесен в отдельный метод, с помощью которого будут выведены и исходная, и перевёрнутая матрицы;
4. Добавьте в логику программы возможность вводить угол поворота (кратный 90) с клавиатуры.

### *Пример*:

``` Пример 1
Для первичной инициализации программы введите размер матрицы:
8
Эта программа способна поворачивать матрицу на заданное количество градусов°!
Возможные команды программы:
0 или выход: чтобы выйти из программы.
1: чтобы вывести на экран оригинальную матрицу.
2: чтобы  повернуть матрицу на заданное количество градусов°.
>>>>>>>
1
Ваша оригинальная матрица размером 8x8:
 207 242 154 182  78  15  62 197
 151 249 226 243 165  45 250 144
 155  78   6 131   9 192 108 250
 135  63  14 253 146 149  58 103
  20  93  98  20 248 120  44 108
  48 145 187 112  27 243 242  71
 223 136 115 138 149 129  88 206
 252  21 153 215 123 116  71 253

2
Введите 90°/180°/270°/360°, на которые необходимо повернуть матрицу
270
Ваша текущая матрица размером 8x8:
 207 242 154 182  78  15  62 197
 151 249 226 243 165  45 250 144
 155  78   6 131   9 192 108 250
 135  63  14 253 146 149  58 103
  20  93  98  20 248 120  44 108
  48 145 187 112  27 243 242  71
 223 136 115 138 149 129  88 206
 252  21 153 215 123 116  71 253
*********************************************
Ваша матрица повернутая на 270°
 197 144 250 103 108  71 206 253
  62 250 108  58  44 242  88  71
  15  45 192 149 120 243 129 116
  78 165   9 146 248  27 149 123
 182 243 131 253  20 112 138 215
 154 226   6  14  98 187 115 153
 242 249  78  63  93 145 136  21
 207 151 155 135  20  48 223 252
```

### **Моя реализация**:

1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - отвечающий за запуск программы, путем инициирования метода *start()* (с инициированием внутри себя
  вспомогательного метода *printMenu()*);

#### Класс **Program**:
``` java
   public class Program {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            System.out.println(Utils.ANSI_BLUE + "Для первичной инициализации программы введите размер матрицы:"
                    + Utils.ANSI_RESET);
            String input = scanner.nextLine();
            var matrix = new Matrix(Integer.parseInt(input));
            while (true) {
                try {
                    printMenu();
                    input = scanner.nextLine();
                    if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                        scanner.close();
                        break;
                    } else {
                        var operationNumber = Integer.parseInt(input);
                        switch (operationNumber) {
                            case 1:
                                System.out.printf("%sВаша оригинальная матрица размером %sx%s:%s%n",
                                        Utils.ANSI_BLUE, matrix.getSize(), matrix.getSize(), Utils.ANSI_RESET);
                                matrix.printOriginalMatrix(matrix);
                                break;
                            case 2:
                                System.out.println(Utils.ANSI_BLUE + "Введите 90°/180°/270°/360°, на которые " +
                                        "необходимо повернуть матрицу" + Utils.ANSI_RESET);
                                input = scanner.nextLine();
                                matrix.printRotatedMatrix(Integer.parseInt(input), matrix);
                                break;
                            default:
                                System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" +
                                        Utils.ANSI_RESET);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
                }
            }
        } catch (NumberFormatException e) {
            System.out.printf("%sТакой размер матрицы недопустим!%s%n", Utils.ANSI_RED, Utils.ANSI_RESET);
            start();
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа способна поворачивать матрицу на заданное " +
                "количество градусов°!" + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: чтобы выйти из программы.");
        System.out.println("1: чтобы вывести на экран оригинальную матрицу.");
        System.out.println("2: чтобы  повернуть матрицу на заданное количество градусов°.");
        System.out.print(">>>>>>>");
    }
}
```

* **Matrix** - описывающий работу матрицы. Имеет следующие важные ```void``` методы: *printOriginalMatrix()*, который выводит в консоль сгенерированную матрицу (с использованием ```Random```) заданного размера и *printRotatedMatrix()*, который выводит в консоль сгенерированную матрицу заданного размера и ее же перевернутую на заданное количество градусов. 

#### Класс **Matrix**:
``` java   
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:

``` java
public class Main {
    public static void main(String[] args) {
        Program program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта программа способна поворачивать матрицу на заданное количество градусов°!
Возможные команды программы:
0 или выход: чтобы выйти из программы.
1: чтобы вывести на экран оригинальную матрицу.
2: чтобы  повернуть матрицу на заданное количество градусов°.
>>>>>>>
```