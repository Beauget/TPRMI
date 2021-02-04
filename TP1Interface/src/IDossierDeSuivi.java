import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossierDeSuivi extends Remote {
    public String getSuivi() throws RemoteException;
    public void ajoutSuivi(String newDossier) throws RemoteException;
}
