package models;

public abstract class Base {
    private static int count = 0;
    private int id;

    public Base() {
        this.id = ++count;
    }
    
    public Boolean update() {
        return true;
    };

    public Boolean delete() {
        this.id = -1;
        return true;
    };

    public int getId() {
        return id;
    }
}
