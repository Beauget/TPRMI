import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;



public class Client implements  IClient {


    public Client() throws RemoteException {
      //  UnicastRemoteObject.exportObject(this, 0);
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

            String nom = "";
            String maitre = "";
            String race = "";
            String espece = "";



            Scanner sc = new Scanner(System.in);
            System.out.println("Nom ?");
            nom = sc.nextLine();
            System.out.println("Maitre ?");
            maitre = sc.nextLine();
            System.out.println("Race ?");
            race = sc.nextLine();
            System.out.println("Espece ?");
            espece = sc.nextLine();


            Espece test = new TestEspece("hayAat");




            stub.createAnimal(nom,maitre,race,test);
            stub.printTout();

            stub.deleteClient(c);

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
        System.out.println("[ALERT]" + n + " animaux on été enregistré !");
    }
}