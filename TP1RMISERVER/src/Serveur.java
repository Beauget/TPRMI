import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.security.*;

public class Serveur {

    public Serveur() {}


    public static void main(String args[]) {

        try {
            // Gestion de la politique de sécurité
            System.setProperty("java.security.policy","file:\\C:\\Users\\beaug\\Desktop\\M1S2\\ArchiDistrib\\TP1\\TP1RMISERVER\\src\\security_policy");
            System.setSecurityManager (new SecurityManager());

            // Création du codebase pour la classe TestEspece côté client (à décommenter pour faire apparaitre l'erreur voulu)
            System.setProperty("java.rmi.server.codebase", "file:\\C:\\Users\\beaug\\Desktop\\M1S2\\ArchiDistrib\\TP1\\TP1RMICLIENT\\out\\production\\TP1RMICLIENT\\");


            // Création du cabinet
            CabinetImp cabinet = new CabinetImp();


            // Ajout de 99 animaux pour tester les ALERT
            for(int i = 0; i < 99; i++) {
                DossierDeSuivi doss = new DossierDeSuivi("L'animal se porte bien");
                Espece e = new Espece("Chien");
                AnimalImp obj = new AnimalImp("Max", "Dns", "Griffon", doss, e);
                cabinet.add(obj);
            }


            Registry registry = LocateRegistry.createRegistry(1099);
            //Registry registry = LocateRegistry.getRegistry();
            if (registry==null){
                System.err.println("RmiRegistry not found");
            }else{
                registry.rebind("CabinetImp", cabinet);
                System.err.println("[Le serveur est prêt et fonctionnel]");
                System.out.println("Le serveur ajoute 99 animaux de base au cabinet (pour pouvoir tester les ALERT)");
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
