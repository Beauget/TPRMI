import java.io.Serializable;
import java.rmi.RemoteException;

public class Espece implements Serializable {
    protected String espece;

    public Espece(String newEspece) throws RemoteException{
        this.espece = newEspece;
    }

    public String getEspece() throws RemoteException{
        return this.espece;
    }

    public void setEspece(String newEspece) throws RemoteException {
        this.espece = newEspece;
    }

}
