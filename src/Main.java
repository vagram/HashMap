import java.util.*;

public class Main {
    public static String regexName = "[А-Я]{1}[а-я]+\s?";
    public static String regexPhone = "[7][\\d+]{10}\s?";
    public static String regexLIST = "list";
    public static String regexEXIT = "exit";
    public static String regexYES = "да";
    public static String input;
    public static boolean isContinue = true;
    public static HashMap<String, TreeSet<String>> phoneBook = new HashMap<>();
    public static String name;
    public static String phoneNum;
    public static boolean temp = true;

    public static void main(String[] args) {
        while (isContinue) {
            System.out.println("Введите Имя или номер телефона либо команду:");
            input = new Scanner(System.in).nextLine();
            if (input.matches(regexName)) {
                name = input;
                temp = phoneBook.containsKey(name);
                if (!temp) {
                    System.out.println("Введите номер телефона:");
                    input = new Scanner(System.in).nextLine();
                    if (input.matches(regexPhone)) {
                        phoneNum = input;
                        phoneBook.put(name, new TreeSet<>());
                        phoneBook.get(name).add(phoneNum);
                        System.out.println("Контакт сохранён!");
                        printName();
                    }
                } else if (phoneBook.containsKey(name)) {
                    System.out.println("Показать тел. " + name + " введите: да! для ввода нового номера просто ВВОД!");
                    input = new Scanner(System.in).nextLine();
                    if (input.matches(regexYES)){
                        printName();
                    } else System.out.println("Введите номер тел. для " + name + ":");
                    input = new Scanner(System.in).nextLine();
                    phoneNum = input;
                    if (input.matches(regexPhone)) {
                        phoneBook.get(name).add(phoneNum);
                        System.out.println("Телефон для " + name + " сохранён!");
                        printName();
                    } else System.out.println("Формат ввода телефона неверный!");
                } else {
                    printName();
                }

            } else if (input.matches(regexPhone)) {
                phoneNum = input;
                temp = phoneBook.containsValue(phoneNum);
                if (!temp) {
                    System.out.println("Введите имя абонента:");
                    input = new Scanner(System.in).nextLine();
                    name = input;
                    temp = phoneBook.containsKey(name);
                    if (input.matches(regexName)) {
                        if (!temp) {
                            phoneBook.put(name, new TreeSet<>());
                            phoneBook.get(name).add(phoneNum);
                            System.out.println("Контакт сохранён!");
                            printName();
                        } else if (phoneBook.containsKey(name)) {
                            phoneBook.get(name).add(phoneNum);
                            System.out.println("Контакт сохранён!");
                            printName();
                        }
                    } else System.out.println("Формат ввода неверен!");
                }
            } else if (input.matches(regexLIST)){
                if (phoneBook.isEmpty()){
                    System.out.println("Сохранённых контактов нет!");
                }else for (Map.Entry<String, TreeSet<String>> entry : phoneBook.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else if (input.matches(regexEXIT)) {
                isContinue = false;
            } else System.out.println("Формат ввода неверен!");
        }
    }
    public static void printName(){
        for (Map.Entry<String, TreeSet<String>> entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }
}




