import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImp extends UnicastRemoteObject implements Animal {

    protected String nom;
    protected String nomMaitre;
    protected String race;
    protected DossierDeSuivi d;
    protected Espece e;


    public AnimalImp(String newNom, String newNomMaitre, String newRace,DossierDeSuivi newD, Espece newE) throws RemoteException {
        this.nom = newNom;
        this.nomMaitre = newNomMaitre;
        this.race = newRace;
        this.d = newD;
        this.e = newE;
    }

    @Override
    public void affichageAnimal() throws RemoteException {
        System.out.println("Nom : " + this.nom + " NomMaitre : " + this.nomMaitre + " Race : " + this.race);
        System.out.println(this.getDossierSuivi().getSuivi());
        System.out.println("Espece : " + this.getEspeceAnimal().getEspece());

    }

    public String getNom() throws RemoteException{
        return this.nom;
    }

    @Override
    public IDossierDeSuivi getDossier() throws RemoteException {
        return null;
    }

    public DossierDeSuivi getDossierSuivi() throws RemoteException {
        return this.d;
    }

    public Espece getEspeceAnimal() throws RemoteException {
        return this.e;
    }
}
