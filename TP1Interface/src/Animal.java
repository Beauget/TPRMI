import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {
    public void affichageAnimal() throws RemoteException;

    public String getNom() throws RemoteException;

    public IDossierDeSuivi getDossier() throws RemoteException;
}
