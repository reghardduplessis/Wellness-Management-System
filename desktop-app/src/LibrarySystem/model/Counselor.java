package LibrarySystem.model;

public class Counselor {
    private int id;
    private String name;
    private String specialization;
    private String availability;

    public Counselor(int id, String name, String specialization, String availability) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public Counselor(String name, String specialization, String availability) {
        this(0, name, specialization, availability);
    }

    // Getters and setters...
}
