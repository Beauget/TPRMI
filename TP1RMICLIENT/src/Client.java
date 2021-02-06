import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;



public class Client extends UnicastRemoteObject implements  IClient {


    public Client() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        IClient c = new Client();
        String host = (args.length < 1) ? null : args[0];
        try {
            System.setProperty("java.security.policy","file:\\C:\\Users\\beaug\\Desktop\\M1S2\\ArchiDistrib\\TP1\\TP1RMICLIENT\\src\\security_policy");
            System.setSecurityManager (new SecurityManager());


            Registry registry = LocateRegistry.getRegistry(host);
            Cabinet stub = (Cabinet) registry.lookup("CabinetImp");
            stub.addClient(c);

            int cmd = 1;
            Scanner cmdLine = new Scanner(System.in);

            while (cmd != 0) {
                System.out.println("Voici quelques commandes pour tester l'application : ");
                System.out.println("0 : Quittez l'application  ; 1 : Afficher tout les animaux du cabinet (côté serveur) ; 2 : Créer un animal (et déclencher une ALERT) ; 3 : Supprimer un animal via son nom");
                cmd = cmdLine.nextInt();
            switch (cmd) {
                case 0 :
                    stub.deleteClient(c);
                    System.out.println("L'application s'arrête, bonne journée ! ");
                    System.exit(0);
                break;
                case 1 :
                    System.out.println("Tout les animaux s'affiche actuellement côté serveur ! ");
                    stub.printTout();
                break;
                case 2 :
                    String nom = "";
                    String maitre = "";
                    String race = "";
                    String espece = "";
                    System.out.println("Vous allez pouvoir créer votre propre animal : ");
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Nom ?");
                    nom = sc.nextLine();
                    System.out.println("Maitre ?");
                    maitre = sc.nextLine();
                    System.out.println("Race ?");
                    race = sc.nextLine();
                    System.out.println("Espece ?");
                    espece = sc.nextLine();

                    // Ligne qui déclenche l'erreur si le Codebase n'est pas présent
                    Espece test = new TestEspece(espece);

                    stub.createAnimal(nom,maitre,race,test);
                break;
                case 3 :
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Donnez le nom de l'animal que vous voulez supprimer : (taille cabinet actuel :  " + stub.tailleCabinet()+ ")");
                    String nomAnimal;
                    nomAnimal = sc2.nextLine();
                    stub.suppr(nomAnimal);
                    System.out.println("L'animal a bien été supprimé ! (taille cabinet maintenant : " + stub.tailleCabinet() + ")");
                break;
                default:
                    System.out.println("Commande inconnue rappel des commandes : ");
                    System.out.println("0 : Quittez l'application  ; 1 : Afficher tout les animaux du cabinet (côté serveur) ; 2 : Créer un animal (et déclencher une ALERT) ; 3 : Supprimer un animal via son nom");

                }
            }



        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    @Override
    public void alert(int n) throws RemoteException {
        System.out.println("[ALERT] " + n + " animaux on été enregistré !");
    }
}