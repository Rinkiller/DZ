import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// В файле содержится строка с исходными данными в такой форме: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
// Требуется на её основе построить и вывести на экран новую строку, в форме SQL запроса:
// SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city = "Moscow";

// Для разбора строки используйте String.split. Сформируйте новую строку, используя StringBuilder. Значения null не включаются в запрос.

class DZ1 {
    public static int getCountLineinfile(String fileName) {
        int count = 1;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            while (fr.ready()) {
                if ((char) fr.read() == '\n')
                    count++;
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count == 0) {
            System.out.println("Файл пустой. Дальнейшая работа невозможна!");
            System.exit(1);
        }
        return count;
    }

    public static String[] getLineOffile(String fileName) {
        String[] strOffile = new String[getCountLineinfile(fileName)];
        int index = 0;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                strOffile[index] = line;
                line = reader.readLine();
                index++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strOffile;
    }

    public static String[][] parse_Line_in_base(String[] lineArr) { // {"name":"Ivanov", "country":"Russia",
                                                                    // "city":"Moscow", "age":"56"}
        String[][] base_of_jslStrings = new String[lineArr.length + 1][5];
        for (int id = 0; id < lineArr.length; id++) {
            String line = lineArr[id].substring(1, lineArr[id].length() - 1); // "name":"Ivanov", "country":"Russia",
                                                                              // "city":"Moscow", "age":"56"
            String[] arr_in_line = line.trim().split(","); // {"name":"Ivanov"},{"country":"Russia"},{"city":"Moscow"},{"age":"56"}
            if (id == 0) {
                for (int ind = 0; ind < arr_in_line.length; ind++) {
                    String[] minArr_of_str = arr_in_line[ind].split(":"); // {"name"},{"Ivanov"}
                    base_of_jslStrings[id][0] = "id"; // "name":"Ivanov", "country":"Russia", "city":"Moscow",
                                                      // "age":"56"
                    String str = minArr_of_str[0].trim();
                    base_of_jslStrings[id][ind + 1] = str.substring(1, minArr_of_str[0].length() - 1); // "id" "name"
                                                                                                       // "country"
                                                                                                       // "country"
                                                                                                       // "age"
                    base_of_jslStrings[id + 1][0] = String.valueOf(id + 1);
                    String str1 = minArr_of_str[1].trim();
                    base_of_jslStrings[id + 1][ind + 1] = str1.substring(1, minArr_of_str[1].length() - 1);
                }

            } else {
                for (int ind = 0; ind < arr_in_line.length; ind++) {
                    String[] minArr_of_str = arr_in_line[ind].split(":"); // {"name"},{"Ivanov"}
                    base_of_jslStrings[id + 1][0] = String.valueOf(id + 1);
                    base_of_jslStrings[id + 1][ind + 1] = minArr_of_str[1].trim().substring(1,
                            minArr_of_str[1].length() - 1); // "1" "Ivanov" "Russia" "Moscow" "56"
                }
            }

        }
        return base_of_jslStrings;
    }

    public static String[] getMeStudent(String[][] base) { // 1 Ivanov Russia Moscow null
        String[] nexSTRarr = new String[base.length - 1];
        StringBuilder Newstr = new StringBuilder();
        for (int index = 1; index < base.length; index++) {
            Newstr.append("SELECT * FROM students WHERE ");
            if (!base[index][1].equals("null")) {
                Newstr.append("name = ");
                Newstr.append(base[index][1]);
            }
            if (!base[index][2].equals("null")) {
                Newstr.append(" AND country = ");
                Newstr.append(base[index][2]);
            }
            if (!base[index][3].equals("null")) {
                Newstr.append(" AND city = ");
                Newstr.append(base[index][3]);
            }
            if (!base[index][4].equals("null")) {
                Newstr.append(" AND age = ");
                Newstr.append(base[index][4]);
            }
            nexSTRarr[index - 1] = Newstr.toString();
            Newstr.delete(0, Newstr.length()); // SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia"
                                               // AND city = "Moscow";
        }
        return nexSTRarr;

    }

    public static void main(String[] args) {
        System.out.println();
        String[] txtOffile = getLineOffile("text1.jsn");
        String[][] newArr = parse_Line_in_base(txtOffile);
        String[] selected = getMeStudent(newArr);
        for (int i = 0; i < selected.length; i++)
            System.out.println(selected[i]);

    }

}
