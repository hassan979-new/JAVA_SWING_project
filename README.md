# Gestion de Bibliothèque — Documentation Académique
### 1. Objectif du projet
Mettre en place une application Java Swing permettant la gestion complète d’une bibliothèque:

- Centraliser les informations sur les livres, les membres et les emprunts.

- Offrir une interface graphique intuitive pour les opérations CRUD.

- Assurer la sécurité via un système d’authentification.

- Fournir des statistiques visuelles sur l’activité de la bibliothèque.

### 2. Fonctionnalités du projet
- Authentification des utilisateurs avec mot de passe haché (MD5).

- Gestion des livres : ajout, modification, suppression, filtrage par auteur/genre.

- Gestion des membres : ajout, modification, suppression avec contrôle métier (pas de suppression si emprunts en cours).

- Gestion des emprunts : enregistrement, retour, détection des retards, historique par membre.

- Statistiques : affichage des emprunts par mois via JFreeChart.

- Sélection de dates avec JCalendar.

### 3. MCD (Modèle Conceptuel de Données)
le schéma conceptuel des principales entités et relations :
```
+-------------------+             +-------------------+             +-------------------+
|      Membre       |             |      Emprunt      |             |       Livre       |
+-------------------+             +-------------------+             +-------------------+
| idMembre (PK)     |<---------+  | idEmprunt (PK)    |  +-------->| idLivre (PK)      |
| nom               |          |  | dateEmprunt       |             | titre             |
| email             |          |  | dateRetour        |             | auteur            |
| dateInscription   |          |  | idMembre (FK)     |             | genre             |
+-------------------+          |  | idLivre (FK)      |             | anneePublication  |
                               |  +-------------------+             +-------------------+
                               |
Relation : Un membre peut avoir plusieurs emprunts
Relation : Un livre peut être emprunté plusieurs fois

```
- Membre(id, nom, email, dateInscription)
- Livre(id, titre, auteur, genre, anneePublication)
- Emprunt(id, dateEmprunt, dateRetour, idMembre, idLivre)
- Login(id, nom, passwordHash)
- Membre : peut effectuer plusieurs emprunts.

- Livre : peut être emprunté plusieurs fois, mais pas supprimé s’il est en cours d’emprunt.

- Emprunt : relie un membre et un livre avec des dates.

- Login : gère l’accès sécurisé à l’application.

### 4. Architecture du projet
- Organisation en couches
UI (app) : interfaces Swing (mainApp, Livre_Frame, Membre_Frame, Emprunt_Frame, Graph_frame, Login, LogingMain).

- Service (service) : logique métier (LivreService, MembreService, EmpruntService).

- DAO (dao) : accès aux données (LivreDao, MembreDao, EmpruntDao, LoginDao, GraphDao, IDao).

- Entities (entities) : modèles métier (Livre, Membre, Emprunt, Login).

- Utilitaires (util) : ConnexionSingleton (connexion DB), Hash (sécurité).

- Lib (lib) : JCalendar et JFreeChart.
### Exécution du programme (version installée)
Installation

- Ouvrir le dossier setup/.

- Lancer le fichier setup_bibliotheque.exe (créé avec Inno Setup).

- Suivre les étapes de l’assistant d’installation pour installer l’application sur ton PC.

Base de données

- Démarrer XAMPP et activer Apache et MySQL.

- Ouvrir phpMyAdmin via http://localhost/phpmyadmin.

- Créer une base de données nommée bibliotheque.

- Importer le fichier SQL situé dans le dossier :

```
/base_des_donnee/bibliotheque.sql 
```

- Vérifier que la classe ConnexionSingleton pointe bien vers :
```
Serveur : localhost

Base : bibliotheque

Utilisateur : root

Mot de passe : (vide par défaut sous XAMPP)
```
Lancement

- Après installation, un raccourci vers l’application est créé sur le bureau ou dans le menu démarrer.

- Double-cliquer pour lancer l’application.

- La fenêtre de connexion (LogingMain) s’ouvre.

- Se connecter avec admin et mot de pass admin123.

### Vidéo de démonstration

- https://github.com/user-attachments/assets/aea0e26e-5671-4819-a91d-816ea75985d8


##  Author

-  Agouram Hassan
-  Projet Java Swing : Gestion de Bibliothèque
-  Instructor	Mr.LACHGAR
-  30	novembre 2025
