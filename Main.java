package encryptdecrypt;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> map = argsToMap(args);
        printLine(map);
    }

    public static HashMap<String, String> argsToMap(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        if (!map.containsKey("-mode")) {
            map.put("-mode", "enc");
        }
        if (!map.containsKey("-key")) {
            map.put("-key", "0");
        }
        if (!map.containsKey("-data") && !map.containsKey("-in")) {
            map.put("-data", "");
        }
        return map;
    }

    public static void printLine(Map<String, String> map) throws IOException {

        if (map.get("-mode").equals("enc")) {
            if (map.containsKey("-data") && map.containsKey("-in")) {
                if (map.containsKey("-out")) {
                    // берем из строки и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(encryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(encryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }
                } else {
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(encryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(encryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    }
                }
            } else if (map.containsKey("-data") && !map.containsKey("-in")) {
                if (map.containsKey("-out")) {
                    // берем из строки и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(encryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(encryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }
                } else {
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(encryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(encryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    }
                }
            } else {
                if (map.containsKey("-out")) {
                    // берем из файла и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(encryptionUni(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(encryptionShift(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }
                } else {
                    //берем из файла и выводим в стандартный вывод
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(encryptionUni(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(encryptionShift(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))));
                    }
                }
            }
        } else {
            if (map.containsKey("-data") && map.containsKey("-in")) {
                if (map.containsKey("-out")) {
                    // берем из строки и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(decryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(decryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }

                } else {
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(decryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(decryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    }
                }
            } else if (map.containsKey("-data") && !map.containsKey("-in")) {
                if (map.containsKey("-out")) {
                    // берем из строки и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(decryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(decryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }
                } else {
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(decryptionUni(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(decryptionShift(map.get("-data"), Integer.parseInt(map.get("-key"))));
                    }
                }
            } else {
                if (map.containsKey("-out")) {
                    // берем из файла и выводим в файл
                    if (map.get("-alg").equals("unicode")) {
                        fileWrite(decryptionUni(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    } else {
                        fileWrite(decryptionShift(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))), map.get("-out"));
                    }
                } else {
                    //берем из файла и выводим в стандартный вывод
                    if (map.get("-alg").equals("unicode")) {
                        System.out.println(decryptionUni(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))));
                    } else {
                        System.out.println(decryptionShift(fileRead(map.get("-in")), Integer.parseInt(map.get("-key"))));
                    }
                }
            }
        }
    }

    public static String encryptionUni(String string, int key) {
        String encryptedString = "";
        for (int i = 0; i < string.length(); i++) {
            encryptedString = encryptedString + (char) (string.charAt(i) + key);
        }
        return encryptedString;
    }

    public static String decryptionUni(String string, int key) {
        String decryptedString = "";
        for (int i = 0; i < string.length(); i++) {
            decryptedString = decryptedString + (char) (string.charAt(i) - key);
        }
        return decryptedString;
    }

    public static String encryptionShift(String string, int key) {
        String encryptedString = "";
        for (int i = 0; i < string.length(); i++) {
            if (key > 26) {
                key = key % 26;
            }
            var i1 = string.charAt(i) + key;
            if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
                char ch;// = (char) i1;
                if (i1 > 90) {
                    ch = (char) (i1 - 26);
                    encryptedString = encryptedString + ch;
                } else {
                    encryptedString += (char) (string.charAt(i) + key);
                }
            } else if (string.charAt(i) <= 122 && string.charAt(i) >= 97) {
                char ch = (char) i1;
                if (i1 > 122) {
                    ch = (char) (ch - 26);
                    encryptedString = encryptedString + ch;
                } else {
                    encryptedString += (char) (string.charAt(i) + key);
                }
            } else {
                encryptedString += string.charAt(i);
            }
        }
        return encryptedString;
    }

    public static String decryptionShift(String string, int key) {
        String decryptedString = "";
        for (int i = 0; i < string.length(); i++) {
            if (key > 26) {
                key = key % 26;
            }
            var i1 = string.charAt(i) - key;
            if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
                char ch;// = (char) i1;
                if (i1 < 65) {
                    ch = (char) (i1 + 26);
                    decryptedString = decryptedString + ch;
                } else {
                    decryptedString += (char) (string.charAt(i) - key);
                }
            } else if (string.charAt(i) <= 122 && string.charAt(i) >= 97) {
                char ch = (char) i1;
                if (i1 < 97) {
                    ch = (char) (ch + 26);
                    decryptedString = decryptedString + ch;
                } else {
                    decryptedString += (char) (string.charAt(i) - key);
                }
            } else {
                decryptedString += string.charAt(i);
            }
        }
        return decryptedString;
    }

    public static String fileRead(String fileName) throws IOException {
        File file = new File(fileName);
        String str = "";
        try (Scanner scanner = new Scanner(file)) {
            str = scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
        }
        return str;
    }

    public static void fileWrite(String data, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();
    }
}