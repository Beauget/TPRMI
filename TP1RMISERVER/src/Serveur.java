import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.security.*;

public class Serveur {

    public Serveur() {}


    public static void main(String args[]) {

        try {

            System.setProperty("java.security.policy","file:\\C:\\Users\\beaug\\Desktop\\M1S2\\ArchiDistrib\\TP1\\TP1RMISERVER\\src\\security_policy");
            System.setSecurityManager (new SecurityManager());


                System.setProperty("java.rmi.server.codebase", "file:\\C:\\Users\\beaug\\Desktop\\M1S2\\ArchiDistrib\\TP1\\TP1RMICLIENT\\out\\production\\TP1RMICLIENT\\");



            DossierDeSuivi doss = new DossierDeSuivi("L'animal se porte bien");
            Espece e = new Espece("Chien");
            AnimalImp obj = new AnimalImp("Max","Dns","Griffon",doss,e);

            CabinetImp cabinet = new CabinetImp();
            //cabinet.add(obj);
            Registry registry = LocateRegistry.createRegistry(1099);
            //Registry registry = LocateRegistry.getRegistry();
            if (registry==null){
                System.err.println("RmiRegistry not found");
            }else{
                registry.rebind("CabinetImp", cabinet);
                System.err.println("Server ready");
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
