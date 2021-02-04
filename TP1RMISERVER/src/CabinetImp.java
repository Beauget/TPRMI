import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinetImp extends UnicastRemoteObject implements Cabinet  {


    public ArrayList<Animal> animalList = new ArrayList<Animal>();
    public ArrayList<IClient> clientList = new ArrayList<IClient>();


    public CabinetImp() throws RemoteException{
        super();
    }



    @Override
    public int tailleCabinet() throws RemoteException {
        return this.animalList.size();
    }

    @Override
    public void addClient(IClient iClient) throws RemoteException {
        this.clientList.add(iClient);
        System.out.println("[AddCLIENT] bien enregistré");
    }

    @Override
    public void deleteClient(IClient iClient) throws RemoteException {
        this.clientList.remove(iClient);
        System.out.println("[DeleteCLIENT] bien supprimé");
    }

    @Override
    public Animal getPatient(String nom) throws RemoteException {
        if(this.contains(nom)) {
            for(int i = 0; i < this.animalList.size(); i++) {
                if(this.animalList.get(i).getNom().equals(nom)) {
                    return this.animalList.get(i);
                }
            }
        }
        else {
            return new AnimalImp(nom,"","",new DossierDeSuivi("non"),new Espece("non"));
        }
        return null;
    }

    @Override
    public Cabinet add(Animal a) throws RemoteException {
        this.animalList.add(a);
        switch (this.animalList.size()) {
            case 100:
                alertAllClients(100);
                break;
            case 500:
                alertAllClients(500);
                break;
            case 1000:
                alertAllClients(1000);
                break;
            default:
        }

        return this;
    }

    @Override
    public Cabinet suppr(String nom) throws RemoteException{
        for(int i = 0; i < this.animalList.size();i++) {
            if(this.animalList.get(i).getNom().equals(nom)) {
                this.animalList.remove(i);
            }
        }
        switch (this.animalList.size()) {
            case 100:
                alertAllClients(100);
                break;
            case 500:
                alertAllClients(500);
                break;
            case 1000:
                alertAllClients(1000);
                break;
            default:
        }
        return this;
    }

    @Override
    public void printTout() throws RemoteException {
        for(Animal a : animalList) {
           a.affichageAnimal();
        }

    }

    public void createAnimal(String nom, String nommaitre, String race, Espece e) throws RemoteException {
        AnimalImp created = new AnimalImp(nom,nommaitre,race,new DossierDeSuivi("Vide"),e);
        this.add(created);
    }

    @Override
    public void alertAllClients(int i) throws RemoteException {
        for(IClient c : clientList) {
            c.alert(i);
        }
    }

    @Override
    public boolean contains(String nom) throws RemoteException {
        boolean res = false;
        for(Animal a : animalList) {
            if(a.getNom().equals(nom)) {
                res = true;
            }
        }
        return res;
    }
}
