package fr.univ_amu.iut.database;

public class Utilisateur {
    private String ID;
    private String motDePasse;

    /**
     * Obtenir l'ID de l'utilisateur
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Modifier l'ID de l'utilisateur
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Obtenir le mot de passe de l'utilisateur
     * @return motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Modifier le mot de passe de l'utilisateur
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Retourne le tuple
     * @return String
     */
    @Override
    public String toString() {
        return "Utilisateur{" +
                "ID='" + ID + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
