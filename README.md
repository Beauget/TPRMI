# How to use

# TP1Interface

Ce dossier contient les éléments commun au client et au serveur (notemment les interfaces) les projets serveur et client y sont liés (Via leur module).

# TP1RMISERVER

Ce dossier contient tout les éléments du serveur, le serveur en lui même, l'implémentation des interfaces Animal, ICabinet et IDossierDeSuivi il crée un Cabinet et effectue les commandes du client (afficher tout les animaux d'un cabinet, gestion des ALERT, création des nouveaux animaux ou leur suppression). De base le serveur crée un Cabinet et ajoute 99 animaux à l'intérieur pour permettre de déclencher une ALERT à 100 animaux facilement.


# TP1RMICLIENT

Ce dossier contient tout les éléments du client, implémentation de la classe IClient et ajout d'une classe TestEspece pour déclencher l'erreur en cas de suppression du Codebase.
Le client possède un système de commande pour permettre de tester plus facilement les différentes fonctionnalités demandés dans ce TP. 
