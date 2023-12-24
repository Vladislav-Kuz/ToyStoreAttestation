package src;

public class SelectToy {
    private static SelectToy exampleToy = null;
    private static int toyId = 0;

    public static SelectToy createToyStore() {
        if (exampleToy == null) {
            exampleToy = new SelectToy();
        }
        return exampleToy;
    }

    public SelectToy() {}

    public Toy createToy(String name) {
        toyId += 1;
        return new Toy(toyId, name);
    }
}