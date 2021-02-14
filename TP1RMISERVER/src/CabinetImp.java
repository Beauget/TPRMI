import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinetImp extends UnicastRemoteObject implements Cabinet  {


    public ArrayList<Animal> animalList = new ArrayList<Animal>();
    public ArrayList<IClient> clientList = new ArrayList<IClient>();


    public CabinetImp() throws RemoteException{
        super();
    }


    // Taille du cabinet
    @Override
    public int tailleCabinet() throws RemoteException {
        return this.animalList.size();
    }


    // Ajout d'un client
    @Override
    public void addClient(IClient iClient) throws RemoteException {
        this.clientList.add(iClient);
        System.out.println("[AJOUT CLIENT] Nouveau client bien enregistré ! " + iClient.toString());
    }

    // Suppression d'un client
    @Override
    public void deleteClient(IClient iClient) throws RemoteException {
        this.clientList.remove(iClient);
        System.out.println("[SUPPRESSION CLIENT] Client bien supprimé ! " + iClient.toString());
    }

    // Récupérer un animal en particulier (sinon il est créer)
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


    // Ajout d'un animal + vérification du nombre d'animaux pour les ALERT
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


    // Suppression d'un animal + vérification du nombre d'animaux pour les ALERT
    @Override
    public Cabinet suppr(String nom) throws RemoteException{

        while(this.contains(nom)) {
            for(int i = 0; i < this.animalList.size();++i) {
                if (this.animalList.get(i).getNom().equals(nom)) {
                    this.animalList.remove(i);
                }
            }
        }
       /* for(int i = 0; i < this.animalList.size();++i) {
            if(this.animalList.get(i).getNom().equals(nom)) {
                System.out.println("Taille de la liste : " + this.animalList.size());
                System.out.println("J'ai trouver : " + nom + " à l'indice" + i);
                this.animalList.remove(i);
            }
        } */
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


    // Afficher tout les animaux
    @Override
    public void printTout() throws RemoteException {
        for(Animal a : animalList) {
           a.affichageAnimal();
        }

    }
    // Créer un animal
    public void createAnimal(String nom, String nommaitre, String race, Espece e) throws RemoteException {
        AnimalImp created = new AnimalImp(nom,nommaitre,race,new DossierDeSuivi("Vide"),e);
        this.add(created);
    }

    // ALERT tout les clients
    @Override
    public void alertAllClients(int i) throws RemoteException {
        for(IClient c : clientList) {
            c.alert(i);
        }
    }


    // Vérifie si un animal est déjà dans un cabinet
    @Override
    public boolean contains(String nom) throws RemoteException {
        boolean res = false;
        for(Animal a : this.animalList) {
            if(a.getNom().equals(nom)) {
                res = true;
            }
        }
        return res;
    }
    // Affichage du dossier de l'animal (string)
    @Override
    public String getDossier(String nom) throws RemoteException{
        for(int i = 0; i < this.animalList.size();i++) {
            if (this.animalList.get(i).getNom().equals(nom)) {
                return this.animalList.get(i).getDossier();
            }
        }
        return "Cet animal n'existe pas";
    }

}
