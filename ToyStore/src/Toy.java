package src;

public class Toy
{
    int id;
    String name;
    public Toy(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Игрушка №" + id + ". " + name;
    }
}