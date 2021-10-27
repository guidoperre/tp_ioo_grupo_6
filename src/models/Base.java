package models;

public abstract class Base {
    private int id;
    
    public Boolean update() {
        return true;
    };

    public Boolean delete() {
        this.id = -1;
        return true;
    };
}
