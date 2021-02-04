import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DossierDeSuivi extends UnicastRemoteObject implements IDossierDeSuivi {

    protected String dossier;


    public DossierDeSuivi(String newDossier) throws RemoteException {
        this.dossier = newDossier;
    }

    @Override
    public String getSuivi() throws RemoteException {
        return this.dossier;
    }


    @Override
    public void ajoutSuivi(String newDossier) throws RemoteException {
       this.dossier = newDossier;
    }
}
