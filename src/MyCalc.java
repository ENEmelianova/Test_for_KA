import java.io.IOException;

public class MyCalc {
    public static int rom_to_int(String rom_num) throws IOException {
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
                throw new IOException("Error: wrong input1");
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
                throw new IOException("Error: wrong input2");
            }
        } else if (len == 3) {
            if (rom_num.equals(roman[3])) {
                return 3;
            } else if (rom_num.equals(roman[7])) {
                return 7;
            } else {
                throw new IOException("Error: wrong input3");
            }
        } else if (len == 4) {
            if (rom_num.equals(roman[8])) {
                return 8;
            } else {
                throw new IOException("Error: wrong input4");
            }
        } else {
            throw new IOException("Error: wrong input (too long)");
        }
    }
    public static String myCalc(String str) throws IOException {
        if (str.length() < 2) {
            throw new IOException("Error: wrong input");
        }

        char[] arabic = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int first_number = -1;
        int position_operation = 1;

        AR_ROM first_number_type = AR_ROM.NOT_SET;

        if (str.charAt(0) == arabic[0]) { first_number = 0; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[1]) {
            first_number = 1; first_number_type = AR_ROM.AR;
            if (str.charAt(1) == arabic[0]) {
                first_number = 10;
                position_operation = 2;
            }
        } else
        if (str.charAt(0) == arabic[2]) { first_number = 2; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[3]) { first_number = 3; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[4]) { first_number = 4; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[5]) { first_number = 5; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[6]) { first_number = 6; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[7]) { first_number = 7; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[8]) { first_number = 8; first_number_type = AR_ROM.AR; } else
        if (str.charAt(0) == arabic[9]) { first_number = 9; first_number_type = AR_ROM.AR; } else {
            // сюда попадем, если первый символ -- не арабская цифра

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
            if (position_operation == -1) {
                throw new IOException("Error: wrong input (no operation)");
            } // не нашли знак

            // переводим число до знака в int
            String rom_num = str.substring(0, position_operation);
            first_number = rom_to_int(rom_num);
            first_number_type = AR_ROM.ROM; // если число было римское хорошее, устанавливаем флаг
        }

        char operator = str.charAt(position_operation);

        if (first_number_type == AR_ROM.NOT_SET) {
            throw new IOException("Error: wrong input (invalid first number)");
        } // первый символ не подходит

        // теперь работаем с b
        int second_number = -1;
        int second_number_position = position_operation + 1;
        AR_ROM second_number_type = AR_ROM.NOT_SET;

        if (str.charAt(second_number_position) == arabic[0]) { second_number = 0; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[1]) {
            second_number = 1; second_number_type = AR_ROM.AR;
            if ((str.length() > (second_number_position + 1)) && (str.charAt(second_number_position + 1) == '0')) {
                second_number = 10;
            }
        } else
        if (str.charAt(second_number_position) == arabic[2]) { second_number = 2; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[3]) { second_number = 3; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[4]) { second_number = 4; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[5]) { second_number = 5; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[6]) { second_number = 6; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[7]) { second_number = 7; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[8]) { second_number = 8; second_number_type = AR_ROM.AR;} else
        if (str.charAt(second_number_position) == arabic[9]) { second_number = 9; second_number_type = AR_ROM.AR;} else {
            if (first_number_type == AR_ROM.AR) {
                throw new IOException("Error: wrong input (ar-rom mismatch)"); // если первое было арабским, а второе римским
            } else {
                String rom_num = str.substring(second_number_position);
                second_number = rom_to_int(rom_num);
                second_number_type = AR_ROM.ROM;
            }
        }

        if (second_number < 0) {
            throw new IOException("Error: wrong input (invalid second number)");
        }

        if (first_number_type != second_number_type) {
            throw new IOException("Error: wrong input (ar-rom mismatch)");
        }

        int result = 0;
        if (operator == '+') {
            result = first_number + second_number;
        } else if (operator == '-') {
            result = first_number - second_number;
            if (first_number_type == AR_ROM.ROM && result < 0) { // отрицаельная разность для римских
                throw new IOException("Error: wrong input (negative roman result)");
            }
        } else if (operator == '*') {
            result = first_number * second_number;
        } else if (operator == '/') {
            // Если второе число было нулем - поймаем
            result = first_number / second_number;
        } else {
            throw new IOException("Error: wrong input (expected operation)"); // неподходящее первое число
        }

        if (first_number_type == AR_ROM.AR) {
            return String.valueOf(result);
        } else {
            String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                    "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                    "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                    "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
            return roman[result];
        }
    }
}

