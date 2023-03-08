public class MyCalc {
    public static int rom_to_int(String rom_num) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        int len = rom_num.length();
        if (len == 1) {
            if (rom_num.equals(roman[0])) {
                return 0;
            } else if (rom_num.equals(roman[1])) {
                return 1;
            } else if (rom_num.equals(roman[5])) {
                return 5;
            } else if (rom_num.equals(roman[10])) {
                return 10;
            } else {
                System.out.println("Error: wrong input");
                return -1;
            }
        } else if (len == 2) {
            if (rom_num.equals(roman[2])) {
                return 2;
            } else if (rom_num.equals(roman[4])) {
                return 4;
            } else if (rom_num.equals(roman[6])) {
                return 6;
            } else if (rom_num.equals(roman[9])) {
                return 9;
            } else {
                System.out.println("Error: wrong input");
                return -1;
            }
        } else if (len == 3) {
            if (rom_num.equals(roman[3])) {
                return 3;
            } else if (rom_num.equals(roman[7])) {
                return 7;
            } else if (rom_num.equals(roman[8])) {
                return 8;
            } else {
                System.out.println("Error: wrong input");
                return -1;
            }
        } else {
            System.out.println("Error: wrong input (too long first param");
            return -1; // если число длиннее 3-х символов, то ошибка
        }
    }
    public static String myCalc(String str) {
        char[] arabic = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int a = -1;
        int position_operation = 1;

        AR_ROM ar_rom = AR_ROM.NOT_SET;

        if (str.charAt(0) == arabic[0]) { a = 0; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[1]) {
            a = 1; ar_rom = AR_ROM.AR;
            if (str.charAt(1) == '0') {
                a = 10;
                position_operation = 2;
            }
        } else
        if (str.charAt(0) == arabic[2]) { a = 2; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[3]) { a = 3; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[4]) { a = 4; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[5]) { a = 5; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[6]) { a = 6; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[7]) { a = 7; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[8]) { a = 8; ar_rom = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[9]) { a = 9; ar_rom = AR_ROM.AR; } else {
            // ищем знак (начинаем со второго по счёту символа)
            position_operation = -1;
            int i = 1;
            while (i < str.length() && position_operation == -1) {
                if (str.charAt(i) == '+') { position_operation = i; } else
                if (str.charAt(i) == '-') { position_operation = i; } else
                if (str.charAt(i) == '*') { position_operation = i; } else
                if (str.charAt(i) == '/') { position_operation = i; } else {
                    i = i + 1;
                }
            }
            if (position_operation == - 1) {
                System.out.println("Error: wrong input (no operator)");
                a = -1;
            } // не нашли знак

            // переводим число до знака в int
            String rom_num = str.substring(0, position_operation);
            a = rom_to_int(rom_num);

            if (a != -1) {
                ar_rom = AR_ROM.ROM; // если число было римское хорошее, устанавливаем флаг
            }
        }

        char operator = str.charAt(position_operation);

        if (ar_rom == AR_ROM.NOT_SET) {
            System.out.println("Error: wrong input");
            return ""; // from myCalc
        }

        // теперь работаем с b
        int b = -1;
        int bp = position_operation + 1;

        if (str.charAt(bp) == arabic[0]) { b = 0;} else
        if (str.charAt(bp) == arabic[1]) {
            b = 1;
            if ((str.length() > (bp + 1)) && (str.charAt(bp + 1) == '0')) {
                b = 10;
            }
        } else
        if (str.charAt(bp) == arabic[2]) { b = 2;} else
        if (str.charAt(bp) == arabic[3]) { b = 3;} else
        if (str.charAt(bp) == arabic[4]) { b = 4;} else
        if (str.charAt(bp) == arabic[5]) { b = 5;} else
        if (str.charAt(bp) == arabic[6]) { b = 6;} else
        if (str.charAt(bp) == arabic[7]) { b = 7;} else
        if (str.charAt(bp) == arabic[8]) { b = 8;} else
        if (str.charAt(bp) == arabic[9]) { b = 9;} else {
            if (ar_rom == AR_ROM.AR) {
                System.out.println("Error: wrong input (first arg is arabic)");
                return "";
            } else { // если первое было норм римским
                String rom_num = str.substring(bp);
                b = rom_to_int(rom_num);
            }
        }
        if (b < 0) {
            System.out.println("Error: wrong input (invalid second arg)");
            return "";
        }


        int result = 0;
        if (operator == '+') {
            result = a + b;
        }
        if (operator == '-') {
            result = a - b;
        }
        if (operator == '*') {
            result = a * b;
        }
        if (operator == '/') {
            if (b == 0) {
                System.out.println("Error: divide on zero");
                return "";
            }
            result = a / b;
        }

        if (ar_rom == AR_ROM.AR) {
            return String.valueOf(result);
        } else {
            String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
            return roman[result];
        }
    }
}
