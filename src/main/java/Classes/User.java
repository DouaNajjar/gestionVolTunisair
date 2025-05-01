package Classes;

public class User {
    private String email;
    private String password; // Devrait être hashé en production


    public User(String username, String password) {
        this.email = email;
        this.password = password;

    }

    // Getters et setters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    // Méthode pour vérifier le mot de passe (avec futur hash)
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword); // À remplacer par un hash en production
    }
}
