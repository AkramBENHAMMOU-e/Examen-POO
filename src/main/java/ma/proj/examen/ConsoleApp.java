    package ma.proj.examen;
    
    import ma.proj.examen.dao.ClientDAO;
    import ma.proj.examen.dao.IngredientDAO;
    import ma.proj.examen.dao.PlatPrincipalDAO;
    import ma.proj.examen.dao.SupplementDAO;
    import ma.proj.examen.model.*;
    
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    import java.util.Scanner;
    
    public class ConsoleApp {
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ClientDAO clientDAO = new ClientDAO();
            PlatPrincipalDAO platDAO = new PlatPrincipalDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();
            SupplementDAO supplementDAO = new SupplementDAO();
    
            while (true) {
                System.out.println("\n=== Gestion du Restaurant ===");
                System.out.println("1. Gérer les clients");
                System.out.println("2. Gérer les plats principaux");
                System.out.println("3. Gérer les ingrédients");
                System.out.println("4. Gérer les suppléments");
                System.out.println("5. Générer un ticket");
                System.out.println("6. Quitter");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                switch (choix) {
                    case 1:
                        gererClients(clientDAO, scanner);
                        break;
    
                    case 2:
                        gererPlats(platDAO, scanner);
                        break;
    
                    case 3:
                        gererIngredients(ingredientDAO, scanner);
                        break;
    
                    case 4:
                        gererSupplements(supplementDAO, scanner);
                        break;
    
    
                    case 5:
                        // Générer un ticket
                        System.out.println("\n=== Génération du Ticket ===");
    
                        // Sélectionner un client
                        System.out.println("Liste des clients disponibles :");
                        List<Client> clients = clientDAO.listerClients();
                        for (Client client : clients) {
                            System.out.println(client.getIdClient() + " - " + client.getNom() + " " + client.getPrenom());
                        }
                        System.out.print("Choisissez l'ID du client : ");
                        int idClient = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Client client = clientDAO.readClient(idClient);
                        if (client == null) {
                            System.out.println("Client non trouvé !");
                            break;
                        }
    
                        // Sélectionner des repas
                        List<Repas> repasList = new ArrayList<>();
                        while (true) {
                            System.out.println("\nListe des plats disponibles :");
                            List<PlatPrincipal> plats = platDAO.listerPlats();
                            for (PlatPrincipal plat : plats) {
                                System.out.println(plat.getIdPlat() + " - " + plat.getNomPlat() + " (" + plat.getPrixBase() + " DH)");
                            }
                            System.out.print("Choisissez l'ID du plat (ou 0 pour terminer) : ");
    
                            try {
                                int idPlat = scanner.nextInt();
                                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                                if (idPlat == 0) {
                                    break; // Terminer la sélection des plats
                                }
    
                                // Vérifier si le plat existe
                                PlatPrincipal plat = platDAO.readPlat(idPlat);
                                if (plat == null) {
                                    System.out.println("Plat non trouvé !");
                                    continue;
                                }
    
                                // Créer un repas
                                Repas repas = new Repas(0, plat);
    
                                // Sélectionner des ingrédients pour ce repas
                                while (true) {
                                    System.out.println("\nListe des ingrédients disponibles :");
                                    List<Ingredient> ingredients = ingredientDAO.listerIngredients();
                                    for (Ingredient ingredient : ingredients) {
                                        System.out.println(ingredient.getIdIngredient() + " - " + ingredient.getNomIngredient() + " (" + ingredient.getPrixUnitaire() + " DH)");
                                    }
                                    System.out.print("Choisissez l'ID de l'ingrédient (ou 0 pour terminer) : ");
    
                                    try {
                                        int idIngredient = scanner.nextInt();
                                        scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                                        if (idIngredient == 0) {
                                            break; // Terminer la sélection des ingrédients
                                        }
    
                                        // Vérifier si l'ingrédient existe
                                        Ingredient ingredient = ingredientDAO.readIngredient(idIngredient);
                                        if (ingredient == null) {
                                            System.out.println("Ingrédient non trouvé !");
                                            continue;
                                        }
    
                                        // Ajouter l'ingrédient au repas
                                        repas.ajouterIngredient(ingredient);
                                    } catch (Exception e) {
                                        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                                        scanner.nextLine(); // Consommer l'entrée invalide
                                    }
                                }
    
                                // Sélectionner des suppléments pour ce repas
                                while (true) {
                                    System.out.println("\nListe des suppléments disponibles :");
                                    List<Supplement> supplements = supplementDAO.listerSupplements();
                                    for (Supplement supplement : supplements) {
                                        System.out.println(supplement.getIdSupplement() + " - " + supplement.getNomSupplement() + " (" + supplement.getPrixSupplement() + " DH)");
                                    }
                                    System.out.print("Choisissez l'ID du supplément (ou 0 pour terminer) : ");
    
                                    try {
                                        int idSupplement = scanner.nextInt();
                                        scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                                        if (idSupplement == 0) {
                                            break; // Terminer la sélection des suppléments
                                        }
    
                                        // Vérifier si le supplément existe
                                        Supplement supplement = supplementDAO.readSupplement(idSupplement);
                                        if (supplement == null) {
                                            System.out.println("Supplément non trouvé !");
                                            continue;
                                        }
    
                                        // Ajouter le supplément au repas
                                        repas.ajouterSupplement(supplement);
                                    } catch (Exception e) {
                                        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                                        scanner.nextLine(); // Consommer l'entrée invalide
                                    }
                                }
    
                                // Ajouter le repas à la liste
                                repasList.add(repas);
                            } catch (Exception e) {
                                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                                scanner.nextLine(); // Consommer l'entrée invalide
                            }
                        }
    
                        // Générer et afficher le ticket
                        if (!repasList.isEmpty()) {
                            genererTicket(client, repasList);
                        } else {
                            System.out.println("Aucun repas sélectionné !");
                        }
                        break; // Retour au menu principal
                    case 6:
                        // Quitter l'application
                        System.out.println("Merci d'avoir utilisé l'application. Au revoir !");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }
        }
    
        // Méthode pour gérer les clients
        private static void gererClients(ClientDAO clientDAO, Scanner scanner) {
            while (true) {
                System.out.println("\n=== Gestion des Clients ===");
                System.out.println("1. Ajouter un client");
                System.out.println("2. Lire un client");
                System.out.println("3. Mettre à jour un client");
                System.out.println("4. Supprimer un client");
                System.out.println("5. Retour au menu principal");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                switch (choix) {
                    case 1:
                        // Ajouter un client
                        System.out.print("Nom du client : ");
                        String nom = scanner.nextLine();
                        System.out.print("Prénom du client : ");
                        String prenom = scanner.nextLine();
                        System.out.print("Email du client : ");
                        String email = scanner.nextLine();
                        System.out.print("Téléphone du client : ");
                        String telephone = scanner.nextLine();
                        Client client = new Client(0, nom, prenom, email, telephone);
                        clientDAO.createClient(client);
                        System.out.println("Client ajouté avec succès !");
                        break;
    
                    case 2:
                        // Lire un client
                        System.out.print("ID du client : ");
                        int idClient = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Client clientLu = clientDAO.readClient(idClient);
                        if (clientLu != null) {
                            System.out.println(clientLu);
                        } else {
                            System.out.println("Client non trouvé !");
                        }
                        break;
    
                    case 3:
                        // Mettre à jour un client
                        System.out.print("ID du client à mettre à jour : ");
                        int idClientUpdate = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Client clientUpdate = clientDAO.readClient(idClientUpdate);
                        if (clientUpdate != null) {
                            System.out.print("Nouveau nom : ");
                            String nouveauNom = scanner.nextLine();
                            System.out.print("Nouveau prénom : ");
                            String nouveauPrenom = scanner.nextLine();
                            System.out.print("Nouvel email : ");
                            String nouvelEmail = scanner.nextLine();
                            System.out.print("Nouveau téléphone : ");
                            String nouveauTelephone = scanner.nextLine();
                            clientUpdate.setNom(nouveauNom);
                            clientUpdate.setPrenom(nouveauPrenom);
                            clientUpdate.setEmail(nouvelEmail);
                            clientUpdate.setTelephone(nouveauTelephone);
                            clientDAO.updateClient(clientUpdate);
                            System.out.println("Client mis à jour avec succès !");
                        } else {
                            System.out.println("Client non trouvé !");
                        }
                        break;
    
                    case 4:
                        // Supprimer un client
                        System.out.print("ID du client à supprimer : ");
                        int idClientDelete = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        clientDAO.deleteClient(idClientDelete);
                        System.out.println("Client supprimé avec succès !");
                        break;
    
                    case 5:
                        // Retour au menu principal
                        return;
    
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }
        }
    
        // Méthode pour gérer les plats principaux
        private static void gererPlats(PlatPrincipalDAO platDAO, Scanner scanner) {
            while (true) {
                System.out.println("\n=== Gestion des Plats Principaux ===");
                System.out.println("1. Ajouter un plat principal");
                System.out.println("2. Lire un plat principal");
                System.out.println("3. Mettre à jour un plat principal");
                System.out.println("4. Supprimer un plat principal");
                System.out.println("5. Retour au menu principal");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                switch (choix) {
                    case 1:
                        // Ajouter un plat principal
                        System.out.print("Nom du plat : ");
                        String nomPlat = scanner.nextLine();
                        System.out.print("Prix de base du plat : ");
                        double prixBase = scanner.nextDouble();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        PlatPrincipal plat = new PlatPrincipal(0, nomPlat, prixBase);
                        platDAO.createPlat(plat);
                        System.out.println("Plat principal ajouté avec succès !");
                        break;
    
                    case 2:
                        // Lire un plat principal
                        System.out.print("ID du plat : ");
                        int idPlat = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        PlatPrincipal platLu = platDAO.readPlat(idPlat);
                        if (platLu != null) {
                            System.out.println(platLu);
                        } else {
                            System.out.println("Plat non trouvé !");
                        }
                        break;
    
                    case 3:
                        // Mettre à jour un plat principal
                        System.out.print("ID du plat à mettre à jour : ");
                        int idPlatUpdate = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        PlatPrincipal platUpdate = platDAO.readPlat(idPlatUpdate);
                        if (platUpdate != null) {
                            System.out.print("Nouveau nom du plat : ");
                            String nouveauNomPlat = scanner.nextLine();
                            System.out.print("Nouveau prix de base : ");
                            double nouveauPrixBase = scanner.nextDouble();
                            scanner.nextLine(); // Pour consommer la nouvelle ligne
                            platUpdate.setNomPlat(nouveauNomPlat);
                            platUpdate.setPrixBase(nouveauPrixBase);
                            platDAO.updatePlat(platUpdate);
                            System.out.println("Plat mis à jour avec succès !");
                        } else {
                            System.out.println("Plat non trouvé !");
                        }
                        break;
    
                    case 4:
                        // Supprimer un plat principal
                        System.out.print("ID du plat à supprimer : ");
                        int idPlatDelete = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        platDAO.deletePlat(idPlatDelete);
                        System.out.println("Plat supprimé avec succès !");
                        break;
    
                    case 5:
                        // Retour au menu principal
                        return;
    
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }
        }
    
        // Méthode pour gérer les ingrédients
        private static void gererIngredients(IngredientDAO ingredientDAO, Scanner scanner) {
            while (true) {
                System.out.println("\n=== Gestion des Ingrédients ===");
                System.out.println("1. Ajouter un ingrédient");
                System.out.println("2. Lire un ingrédient");
                System.out.println("3. Mettre à jour un ingrédient");
                System.out.println("4. Supprimer un ingrédient");
                System.out.println("5. Retour au menu principal");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                switch (choix) {
                    case 1:
                        // Ajouter un ingrédient
                        System.out.print("Nom de l'ingrédient : ");
                        String nomIngredient = scanner.nextLine();
                        System.out.print("Prix unitaire de l'ingrédient : ");
                        double prixUnitaire = scanner.nextDouble();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Ingredient ingredient = new Ingredient(0, nomIngredient, prixUnitaire);
                        ingredientDAO.createIngredient(ingredient);
                        System.out.println("Ingrédient ajouté avec succès !");
                        break;
    
                    case 2:
                        // Lire un ingrédient
                        System.out.print("ID de l'ingrédient : ");
                        int idIngredient = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Ingredient ingredientLu = ingredientDAO.readIngredient(idIngredient);
                        if (ingredientLu != null) {
                            System.out.println(ingredientLu);
                        } else {
                            System.out.println("Ingrédient non trouvé !");
                        }
                        break;
    
                    case 3:
                        // Mettre à jour un ingrédient
                        System.out.print("ID de l'ingrédient à mettre à jour : ");
                        int idIngredientUpdate = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Ingredient ingredientUpdate = ingredientDAO.readIngredient(idIngredientUpdate);
                        if (ingredientUpdate != null) {
                            System.out.print("Nouveau nom de l'ingrédient : ");
                            String nouveauNomIngredient = scanner.nextLine();
                            System.out.print("Nouveau prix unitaire : ");
                            double nouveauPrixUnitaire = scanner.nextDouble();
                            scanner.nextLine(); // Pour consommer la nouvelle ligne
                            ingredientUpdate.setNomIngredient(nouveauNomIngredient);
                            ingredientUpdate.setPrixUnitaire(nouveauPrixUnitaire);
                            ingredientDAO.updateIngredient(ingredientUpdate);
                            System.out.println("Ingrédient mis à jour avec succès !");
                        } else {
                            System.out.println("Ingrédient non trouvé !");
                        }
                        break;
    
                    case 4:
                        // Supprimer un ingrédient
                        System.out.print("ID de l'ingrédient à supprimer : ");
                        int idIngredientDelete = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        ingredientDAO.deleteIngredient(idIngredientDelete);
                        System.out.println("Ingrédient supprimé avec succès !");
                        break;
    
                    case 5:
                        // Retour au menu principal
                        return;
    
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }
        }
    
        // Méthode pour gérer les suppléments
        private static void gererSupplements(SupplementDAO supplementDAO, Scanner scanner) {
            while (true) {
                System.out.println("\n=== Gestion des Suppléments ===");
                System.out.println("1. Ajouter un supplément");
                System.out.println("2. Lire un supplément");
                System.out.println("3. Mettre à jour un supplément");
                System.out.println("4. Supprimer un supplément");
                System.out.println("5. Retour au menu principal");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne
    
                switch (choix) {
                    case 1:
                        // Ajouter un supplément
                        System.out.print("Nom du supplément : ");
                        String nomSupplement = scanner.nextLine();
                        System.out.print("Prix du supplément : ");
                        double prixSupplement = scanner.nextDouble();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Supplement supplement = new Supplement(0, nomSupplement, prixSupplement);
                        supplementDAO.createSupplement(supplement);
                        System.out.println("Supplément ajouté avec succès !");
                        break;
    
                    case 2:
                        // Lire un supplément
                        System.out.print("ID du supplément : ");
                        int idSupplement = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Supplement supplementLu = supplementDAO.readSupplement(idSupplement);
                        if (supplementLu != null) {
                            System.out.println(supplementLu);
                        } else {
                            System.out.println("Supplément non trouvé !");
                        }
                        break;
    
                    case 3:
                        // Mettre à jour un supplément
                        System.out.print("ID du supplément à mettre à jour : ");
                        int idSupplementUpdate = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        Supplement supplementUpdate = supplementDAO.readSupplement(idSupplementUpdate);
                        if (supplementUpdate != null) {
                            System.out.print("Nouveau nom du supplément : ");
                            String nouveauNomSupplement = scanner.nextLine();
                            System.out.print("Nouveau prix : ");
                            double nouveauPrixSupplement = scanner.nextDouble();
                            scanner.nextLine(); // Pour consommer la nouvelle ligne
                            supplementUpdate.setNomSupplement(nouveauNomSupplement);
                            supplementUpdate.setPrixSupplement(nouveauPrixSupplement);
                            supplementDAO.updateSupplement(supplementUpdate);
                            System.out.println("Supplément mis à jour avec succès !");
                        } else {
                            System.out.println("Supplément non trouvé !");
                        }
                        break;
    
                    case 4:
                        // Supprimer un supplément
                        System.out.print("ID du supplément à supprimer : ");
                        int idSupplementDelete = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        supplementDAO.deleteSupplement(idSupplementDelete);
                        System.out.println("Supplément supprimé avec succès !");
                        break;
    
                    case 5:
                        // Retour au menu principal
                        return;
    
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }
        }
    
        public static void genererTicket(Client client, List<Repas> repasList) {
            System.out.println("Bienvenue " + client.getNom() +" "+ client.getPrenom());
            System.out.println("---TICKET---");
            System.out.println("Nom: " + client.getNom() + " " + client.getPrenom());
            System.out.println("Nombre de repas: " + repasList.size());
    
            double total = 0.0;
    
            for (int i = 0; i < repasList.size(); i++) {
                Repas repas = repasList.get(i);
                PlatPrincipal plat = repas.getPlatPrincipal();
                System.out.println("Repas N°:" + (i + 1) + " " + plat.getNomPlat()+ " " + plat.getPrixBase() + " DHS");
    
                // Afficher les ingrédients
                System.out.println("Ingrédient:");
                for (Ingredient ingredient : repas.getListeIngredients()) {
                    System.out.println(ingredient.getNomIngredient() + ": " + ingredient.getPrixUnitaire() + " DHS");
                }
    
                // Afficher les suppléments
                System.out.println("Suppléments:");
                for (Supplement supplement : repas.getListeSupplements()) {
                    System.out.println(supplement.getNomSupplement() + " " + supplement.getPrixSupplement());
                }
    
                // Calculer le total du repas
                total += repas.calculerTotal();
                System.out.println("*********");
            }
    
            // Afficher le total
            System.out.println("---Total: " + total + "---");
        }
        }
    
