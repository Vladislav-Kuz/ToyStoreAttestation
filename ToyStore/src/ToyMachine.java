package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ToyMachine {
    private SelectToy toys = SelectToy.createToyStore();
    private Random random = new Random();
    private int numberOfToys = 0;
    private List<Lot> toysList = new ArrayList<Lot>();
    private Queue< Lot > prizes = new LinkedList< Lot >();
    private Lot lot;
    private boolean raffleStarted = false;

    public void addToy() {
        if (!raffleStarted) {
            Menu menu = new Menu();
            Lot lot = new Lot(toys.createToy(menu.input("Введите название: ")));
            setNumberForLot(lot);
            toysList.add(lot);
            numberOfToys += lot.getNumber();
            calcProbablyForLots();
            System.out.println("Всего игрушек: "+numberOfToys);
            System.out.print("Игрушка добавлена!\n");
        } else {
            System.out.print("Розыгрыш еще не закончен, изменения невозможны!\n");
        }
    }

    private void setNumberForLot(Lot lot) {
        int number;
        String input;
        while (true) {
            Menu menu = new Menu();
            input = menu.input("Введите количество игрушек: ");
            while (!isNumber(input)) {
                input = menu.input("Введите количество игрушек: ");
            }
            number = Integer.parseInt(input);
            if (isValidNumber(number)) {
                break;
            }
        }
        lot.setNumber(number);
    }

    private boolean isNumber(String str) {
        if (str.matches("^\\d+$")) {
            return true;
        }
        System.out.print("Нужно ввести число!\n");
        return false;
    }

    private boolean isValidNumber(int number) {
        if (number < 1) {
            System.out.print("Количество игрушек должно быть больше 0!\n");
            return false;
        }
        return true;
    }

    private void calcProbablyForLots() {
        double prob;
        for (Lot lot : toysList) {
            prob = (double) lot.getNumber() / (double) numberOfToys;
            lot.setProbab(prob);
        }
    }

    private Lot getWinedToy() {
        startRaffle();
        if (raffleStarted) {
            if (numberOfToys > 0) {
                double roll = random.nextDouble();
                double summ = toysList.get(0).getProbab() + roll;
                int i = 1;
                while (summ <= 1) {
                    summ += toysList.get(i).getProbab();
                    i += 1;
                }
                i -= 1;
                Lot lot = toysList.get(i);
                lot.decrNumber();
                if (lot.getNumber() == 0) {
                    toysList.remove(i);
                }
                numberOfToys -= 1;
                calcProbablyForLots();
                System.out.println(lot);
                return lot;
            }
            System.out.print("Лототрон пуст!\n");
            raffleStarted = false;
            return null;
        }
        System.out.print("Начните розыгрыш!\n");
        return null;
    }

    private void startRaffle() {
        if (!raffleStarted) {
            raffleStarted = true;
            System.out.print("Розыгрыш начался!\n");
        } else {
            System.out.print("Розыгрыш уже идёт!\n");
        }
    }

    public void takeWinedToy(){
        lot = getWinedToy();
        if (lot != null) {
            prizes.add(lot);
        }
    }

    public void writeWinedToy() {
        if (!prizes.isEmpty()) {
            lot = prizes.poll();
            String file_name = "WinedToys";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true))) {
                writer.write(lot.getItem().toString() + "\n");
                System.out.print("Запись в файл успешна\n");
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла: " + e.getMessage());
            }
        } else {
            System.out.print("Игрушек для выдачи нет!\n");
        }
    }


    @Override
    public String toString() {
        if (numberOfToys > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("В автомате ").append(numberOfToys).append(" игрушек!\n");
            for (Lot lot : toysList) sb.append(lot.toString()).append("\n");
            return sb.toString();
        }
        return "Автомат пуст!\n";
    }
}