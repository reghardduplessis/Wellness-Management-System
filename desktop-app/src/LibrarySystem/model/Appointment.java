package LibrarySystem.model;

public class Appointment {
    private int id;
    private String student;
    private String counselor;
    private String date;
    private String time;
    private String status;

    public Appointment(int id, String student, String counselor, String date, String time, String status) {
        this.id = id;
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Appointment(String student, String counselor, String date, String time, String status) {
        this(0, student, counselor, date, time, status);
    }

    // Getters and setters omitted for brevity (include all)
    // ...
}
