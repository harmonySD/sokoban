-----------------------------------------------------------SYSTEME DE SAUVEGARDE-------------------------------------------------------------

Fonctionnement : chaque case sera séparée par une virgule;
Toutes les données seront écrites en  une seule ligne et le système de chargement se chargera de compter les cases pour changer le y. 

taille du board en x longueur y hauteur deux entiers collés séparés par une virgule
après vient un point virgule

Des caractères sont utilisés pour encoder l'information
vient toujours l'un de ceux-là en premier :

Cases codés en un seul caractère :
- les murs : "W," et RIEN d'AUTRE ( la virgule indique qu'on passe directement à la case suivante) 
- le VIDE  (bord de niveau/ impossible à atteindre) : "N," et RIEN D'AUTRE ( la virgule indique qu'on passe directement à la case suivante)

Cases pouvant être codées en plusieurs caractères
- point de réapparition du personnage/ le personnage : P (pourra être suivi de bonus ou point de victoire )
- une case sans contenu (pas de boîte,de mur, ) : E (pourra être suivi de  bonus ou  point de victoire )
- les boîtes de couleurs rouge,vert,bleu : "r","v","b" (pourra être suivi de bonus ou point de victoire  )

vient ensuite SEULEMENT pour les cases codés en plusieurs caractères si présent : 

- les points de victoires de couleurs rouge,vert,bleu : "R","V","B"
- Si un bonus est présent : "+" 

vient ensuite TOUJOURS
","

la fin de fichier est marquée par ";" (qui EST précédé d'une virgule)


cela nous donne  pour un terrain de jeu 5x5 : avec un bonus sur la même case qu'une caisse rouge,
et le joueur sur le point de victoire d'une caisse bleu : 

5,5;W,W,W,W,W,W,ER,E,E,W,W,r+,E,E,W,W,E,E,E,W,W,PB,b,E,W,W,W,W,W,W,; 

une "représentation" en jeu : 

W   W  W  W   W 
W   ER E  E   W 
W   r+ E  E   W 
W   PB E  E   W 
W   W  W  W   W 
