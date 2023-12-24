package src;

public class Lot {
    private Toy item;
    private int id;
    private int number;
    private double probab;

    public Lot(Toy item) {
    this.item = item;
    this.id = item.getId();
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void decrNumber() {
        number -= 1;
    }

    public double getProbab() {
        return probab;
    }

    public void setProbab(double probab) {
        this.probab = probab;
    }

    public Toy getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString() + " " + number + " шт" + " " + probab;
    }
}
