package non_official.pokeum.fragile.model.java;

public class Car {
    private String name;
    private int speed;
    private int gear;
    private int drivetrain;
    private String direction;
    private String color;
    private String fuel;
    private double price;

    public Car(String name, int speed, int gear,
               int drivetrain, String direction, String color,
               String fuel, double price)
    {
        this.name = name;
        this.speed = speed;
        this.gear = gear;
        this.drivetrain = drivetrain;
        this.direction = direction;
        this.color = color;
        this.fuel = fuel;
        this.price = price;
    }
}
