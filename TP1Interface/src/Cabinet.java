import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cabinet extends Remote {
    public Animal getPatient(String nom) throws RemoteException;
    public int tailleCabinet() throws RemoteException;
    public void addClient(IClient c) throws RemoteException;
    public void deleteClient(IClient c) throws RemoteException;
    public Cabinet add(Animal a) throws RemoteException;
    public Cabinet suppr(String nom) throws RemoteException;
    public void printTout() throws RemoteException;
    public boolean contains(String nom) throws RemoteException;
    public void createAnimal(String nom, String nommaitre, String race, Espece e) throws RemoteException;
    public void alertAllClients(int n) throws RemoteException;


}
