package src;

import java.util.Scanner;

public class Menu{
    private ToyMachine lototron = new ToyMachine();

    public void menu() {
        String menu = """
                выберите действие:
                
                1 - Добавить игрушку в автомат
                2 - Показ фонда игрушек
                3 - Разыграть игрушку и поместить в очередь выдачи
                4 - Внести игрушку в файл выигрышей
                5 - Вывести список команд
                0 - Завершить программу
                """;

        System.out.print("Розыгрыш игрушек!\n");
        System.out.print(menu);
        boolean run = true;
        String command;
        while (run) {
            command = input("Введите команду: ");
            switch (command) {
                case "1" -> lototron.addToy();
                case "2" -> System.out.print(lototron);
                case "3" -> lototron.takeWinedToy();
                case "4" -> lototron.writeWinedToy();
                case "5" -> System.out.print(menu);
                case "0" -> run = false;
                default -> System.out.print("Нет такой команды.\n");
            }
        }
    }

    public String input(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

