package Model;

public class Employee {
    
    private int id;
    private String nom;
    private String prenom;
    private double salaire;
    private String email;
    private String phone;
    private Role role;
    private Poste poste;
    private int holidayBalance;

    public Employee(int id, String nom, String prenom, double salaire, String email, String phone, Role role, Poste poste,int holidayBalance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.poste = poste;
        this.holidayBalance=holidayBalance;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public int getHolidayBalance() {
        return holidayBalance;
    }

    public void setHolidayBalance(int holidayBalance) {
        this.holidayBalance = holidayBalance;
    }
}
