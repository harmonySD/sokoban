# **UTILISATION DU PROGRAMME**
1. Premier pas :
    
    Il faudra se positionner dans le dossier sokoban ou se trouve le makefile. Puis a l'aide de la commande make compiler le programme.
    Cela vous donnera un lien, Voici comment l'utiliser avec une ligne de comande :
        - java -jar leliendonneparlemake
        
2. Jouer :
    Indiquer votre nom dans le terminal et le jeu se lancera. Vous pourrez ainsi commencer une partie, créér votre propre niveau, ou choisir un niveau en particulier.

3. Score : 
     Le score est en étoiles, plus il y a de coup moins vous aurez d'étoiles !

4. Prenez du plaisir !
    

# **SYSTEME DE SAUVEGARDE**

1. Fonctionnement : 

    Afin de sauvegarder un niveau, celui-ci est traduit en fichier TXT basic suivant des régles d'écritures stricts permettant une traduction vers un Board adéquat lors du chargement de niveau.
    
    Le correspond au format comme suit (donné à titre d'exemple). Ceci correspond à un terrain de jeu 5x5, avec un bonus sur la même case qu'une caisse rouge et le joueur sur le point de victoire d'une caisse bleu :
    
        5,5;W,W,W,W,W,W,ER,E,E,W,W,r+,E,E,W,W,E,E,E,W,W,PB,b,E,W,W,W,W,W,W,;
    
    Voici les régles en question :
    
        - Le fichier TXT est composé d'une seule ligne traduisant chaque case et données liées au niveau.
        - Chaque case est séparée par une virgule.
        - Les deux premiers nombres correspondent à X et Y, les dimensions de la grille de jeu.
        - La fin du fichier est marqué par un point virgule ';'.
    
    Les cases sont codées en un ou plusieurs caractères selon leur nature. 
    
    _**En un caractère**_ :
    
        - "W" traduit un mur.
        - "N" traduit une case vide.
        - "P" traduit la case d'apparition du personnage
    
    _**En plusieurs caractère**_ :
    
        - "R", "G", "B" traduit les points de victoires ou objetifs selon leur couleur.
        - "r", "g", "b" traduit les cartons / boxs à bouger selon leur couleur.



# **EDITEUR DE NIVEAU**


L'utilisateur a la possibilité de réaliser de lui-même des niveaux sokobans (sauvegardable). Ceci, grâce à l'éditeur de niveau.

1. Fonctionnement général :

    Celui-ci se compose d'une JFRAME divisé en deux panels, Left et Right. 
    Left possède la grille de jeu ainsi que divers champs et boutons en dessous tel que "Quitter", un champ Nombre de coup et un champ Nom de level. 
    
    Egalement un panneau permettant d;'expliquer à l'utilisateur certains points de la validation de niveau.
        - "Quitter" permet de revenir sur le menu lors du click.
        - Le champ Nombre de coup permet de rentrer le nombre optimale de déplacement afin de terminer le niveau en création à 3 étoiles.
        - Le champ Nom de level génère au hasard un nom de la forme "LevelXXX" ou XXX est compris entre 100 et 999. L'utilisateur peut modifier le nom à sa convenance.
        - Le panel de vérification change de contenu en fonction de l'avancée de création du niveau lorsque l'utilisateur clique sur "Done".
    
    Right possède un ensemble de boutons de façon à remplir la grille de jeu au panel Left.
        - Les boutons représentants un objet du jeu (Personnage, objectif, carton, mur et vide) sont fait de tel façon qu'un clique sur lui 
        puis un clique un point de la grille remplisse le clique destinataire de celui expéditeur.
        A noter que case vide et mur, après un clique sur eux, sont posable autant de fois que voulu sur la grille de jeu.
        - Le bouton Gomme permet, après clique sur lui, d'effacer l'objet se trouvant sur le clique destinataire en grille de jeu.
        - Le bouton Poubelle permet de "clear" toute la grille en la remettant à son état initial. Cela appel la méthode clear().
        - Le bouton "Done" lance la vérification de l'état du niveau. Le panel de vérification en LEFT traduira le résultat de cett vérification.
    
2. Initialisation :

    La création d'un CREATEVUE lance la fonction init responsable du visuel de l'éditeur de partie. Celui-ci construit la JFRAME de tel façon à créer les différents boutons, au bon design, et mis à l'écoute par le MouseListener.
    Le JFRAME est composé de deux panels LEFT et RIGHT, eux composé comme suit :
    
        - LEFT : Est divisé en deux tel qu'un setLayout(2, 1). La partie du dessus est la grille de jeu (Board) et celle du dessous est un pannel en setLayout(1, 4).
        Dessous est composé de 4 élèments aux designs divers. 2 bouttons et 2 champs de textes prérempli et modifiable.
        - RIGHT : est divisé en 6 niveaux, 2 colonnes tel que setLayout(6, 2). Le tout de boutons représentant chaque élèment applicable ou chaque action necessaire au User.
    
    L'état de base correspond à un niveau vide d'objet, entouré d'une paroie de mur de chaque coté.

3. Action utilisateur :

    Afin de garder en mémoire l'action demandé par l'utilisateur, un int selected a été crée. Sa valeur change selon un clique sur un bouton donné et prends la valeur de l'action vooulu.
    Lors du clique sur une case de la grille de jeu, on questionne la valeur de selected et applique l'action correspondant.
    De même, les valeurs des champs Nombre de coup et Nom de level sont également enregistré et gardée en mémoire selon les actions et modifications écrites du User.
    
        - A noter que selected est mis à zéro après que l'action fut executée sauf pour le placement de Mur, case vide et l'effacement par la Gomme.
        Ceci afin de faciliter le gameplay et ne pas forcer l'utilisateur à recliquer à chaque fois concernant ces actions parfois très répétitives.
        
    Egalement, le style est modifié. Lorsqu'un clique est fait sur un des boutons, celui-ci s'entoure d'une bordure jaune afin de se montrer visuellement "selectionné". 
    La fonction noBorder() permet de rafraichir les bordures dans le reste des cases afin qu'un seul bouton ne soit selectionné en terme de design.
    
        - Chaque action appel la méthode update() afin que le visuel de notre JFRAME soit mis à jour.

4. Vérification :

    Lors que le User souhaite finalisé son niveau, il clique sur le bouton "DONE" du pannel RIGHT. Par cette action, une validation est mise en oeuvre par la méthode isItFinish() afin de vérifier l'état du level créeé.
    Il y a de multiples conditions à cette validation :
    
        - Si il y a un personnage placé sur la grille.
        - Si il y a un unique personnage.
        - Si il y a un ou plusieurs cartons.
        - Si il y a un ou plusieurs objectifs.
        - Si il y a autant de cartons d'une couleur que d'objectifs de cette même couleur.
        - Si le nombre de coup renseigné est cohérent (un int compris entre 1 et 100).
        - Si le nom de level rensigné n'est pas un string vide.
        
    En fonction de ces conditions, un message explicatif sera affiché dans le panneau de vérification de LEFT. Entouré de bordure rouge lorsque la vérification n'a pas abouti à un résultat concluant, il detail au User les raisons de son refus.
    De même, celui-ci revient à son état de base avec le message "En construction" et le noBorder() appliqué sur lui lorsque le User continue de modifier / finir son niveau.
