# SAE S3 - FindTheBreach
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)</br>
Projet universitaire visant à la création d'un serious game sur les réseaux, évolutif.</br>
Nous avons choisi un scénario afin de rendre l'application plus attrayante :</br></br>
Vous êtes un étudiant en informatique et vous venez de trouver une signature laissée par un groupe de hacker.
Votre mission est de les retrouver afin de les dénoncer à la police.

## Demandes ✍️

- Jeu qui sert de support pour les cours
- Site web pour administrer le contenu du jeu -> Site accessible sur https://findthebreach.ddns.net et code sur le Github :
- Utilisation des sockets

## Ce que nous avons réalisé ⚙️

Application en Java/JavaFx comprenant :
- Une partie apprentissage afin de maîtriser les outils nécéssaire à la recherche
- Une partie jeu où nous sommes confrontés à la recherche et la mise en application des outils vus
- Une authentification
- Un terminal distant utilisant les sockets

Notre application est innovante car elle permet d'utiliser un environnement linux sur n'importe quel OS. 
Le terminal fait que nous sommes dans la même situation que sur un pc Linux traditionnel et il nous permet d'utiliser les mêmes commandes.

## Démarrage 🚀

Application fonctionnelle sur : 

![Mac OS](https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=macos&logoColor=F0F0F0)![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)

Pour lancer l'application via un IDE ouvrez simplement le fichier Main.java, et exécuter cette ligne :
```java
public static void main(String[] args) throws SQLException {
```
## Utilisation 🎮

- Accueil

Sur cette page, vous pouvez avoir un aperçu de notre application. Tous les boutons redirigent vers la page connection.

![Image page d'accueil de l'application](readmePictures/home.jpg "Page d'accueil")

- Connection

Vous devez vous connecter avec le compte créé précédemment sur le site https://findthebreach.ddns.net</br>
Le compte avec lequel vous vous connectez se verra attribué le score de votre partie de jeu.

![Image page connection](readmePictures/login.jpg "Page de connection")

- Page d'accueil utilisateur connecté

Vous pouvez maintenant choisir votre mode de jeu. Vous pouvez accéder à la partie apprentissage ou la partie jeu. 
Nous vous conseillons de commencer par la partie apprentissage afin de vous entraîner.

![Image page d'accueil connecté](readmePictures/home_logged.jpg "Page d'acceuil utilisateur connecté")

- Menu d'apprentissage

Vous avez cliqué sur apprentissage a l'étape précédente, maintenant il faut choisir le niveau à effectuer. 
Vous pouvez naviguer librement entre les niveaux et reprendre là où vous vous êtes arrétés.

![Image menu apprentissage](readmePictures/practice_menu.jpg "Page menu apprentissage")

- Apprentissage

Le niveau choisi se lance, vous avez la question sur la gauche.
Vous pouvez utiliser le terminal à droite pour chercher les réponses et les insérer.
Les boutons solution et indice sont disponible si vous êtes bloqués.
Vous pouvez cliquer sur l'icone "play" une fois que vous êtes prêts pour lancer la partie enquête.

![Image page apprentissage](readmePictures/practice.jpg  "Page apprentissage")

- Jeu

Vous faites maintenant votre recherche de l'utilisateur qui a laissé une trace.
Les boutons indice et solution apparaissent qu'au bout de 5 minutes et 10 minutes respectivement.
Votre score est calculé en fonction du temps écoulé et des bonus utilisés.

![Image page jeu](readmePictures/play.jpg  "Page jeu")

- Tableau des scores

Quand votre partie est finie, vous accédez au tableau des scores avec votre score et le classement des 5 meilleurs joueurs.
Vous pouvez redémarrer une partie, retourner vous entraîner ou quitter l'application en la fermant.

![Image page tableau des scores](readmePictures/leaderBoard.jpg  "Page tableau des scores")

### Mesures d'amélioration

Pour améliorer notre projet, nous aurions pu chercher à perfectionner l'aspect sécurisation vis à vis du Terminal distant.
La mise en place d'un mode multijoueur aurait été aussi une perspective d'évolution.

## Membres du projet 🧑‍💻

Ceccarelli Luca</br>
Egenscheviller Frédéric</br>
Ramdani Djibril</br>
Saadi Nils</br>
Vial Amaury
