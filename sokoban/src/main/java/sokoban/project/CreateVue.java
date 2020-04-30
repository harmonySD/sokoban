package sokoban.project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.BorderFactory;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JApplet;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
todo :
- Interface graphique : La vue du jeu à gauche, le panel des objets à droite, le boutton finish
    - Grille du jeu vide avec mur autour 10x10 (mur compris)
    - Une grille 2 * 4 ( Perso - Mur / Carton V - Objectif V / R - R / B - B / Erase - Clear / None - Done )
    - En bas de grille droite, bouton finish
    - Un champ 'nombre de coup' et un bouton quitter en dessous de grille jeu
- Click & point objet à mettre dans chaque case (Effet visuel, objet pointé encadré)
    - Click sur un objet, int selected prends sa valeur (0 par défaut)
    - Si selected > 0, objet en question encadré
    - Si click sur grille jeu et selected != 0, placer objet (et selected = 0 sauf pour mur)
    - Click sur objet selection le deselecte
    - Pas plusieurs personnes sur la grille
    - Si selectionner erase, selected = -1
    - Si click sur grille avec objet et selected = -1, retirer l'objet
    - Si clear, remise à zero de la grille
    - Que le champ nombre de coup soit opérationnel
    - Bouton quitter marche
- Verifier les contraintes (Personnage et but / carton équivalent)
    - Avoir un unique personnage
    - Avoir autant d'objectifs d'une couleur que de cartons de cette meme couleur
    - Avoir au moins un objectif et un carton
    - Qu'un chiffre 'nombre de coup' soit renseigné
- Traduire en format TXT à sauvegarder
    - ??
 */



public class CreateVue extends JFrame implements MouseListener{
    private MenuVue fenMenu;
    private int selected; // 0
    private BoardVue board;
    private String nom;
    private String path;
    private boolean charSet;
    // Les différents boutons
    private JButton character; // 1
    private JButton wall; // 2
    private JButton redBox; // 3
    private JButton redGoal; // 4
    private JButton blueBox; // 5
    private JButton blueGoal; // 6
    private JButton greenBox; // 7
    private JButton greenGoal; // 8
    private JButton erase; // -1
    private JButton clear; // 9
    private JButton finish; // 10
    private JButton noneCase; // 11
    private JButton text;
    private JButton quit;
    private JTextField nbCoup;

    public CreateVue(String nom) {
        this.nom = nom;
        this.selected = 0;
        this.charSet = false;
        this.path=System.getProperty("user.dir")+"/Textures/";
        init();
    }
    public void init() {
        this.setTitle("Sokoban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ========= Grille gauche et droite de la fenetre

        // === Coté gauche, partie jeu
        JPanel left = new JPanel(); left.setPreferredSize(new Dimension(1000, 1000));
        // La grille de jeu
        BoardVue board = new BoardVue(new Board());
        board.setPreferredSize(new Dimension(1000, 850));
        this.board = board;
        left.add(board);
        // Le dessous de grille composé d'un champ nombre de coup
        // D'un bouton quitter et d'un suivi (niveau conforme, etc)
        JPanel dessous = new JPanel(); dessous.setPreferredSize(new Dimension(1000, 100));
        dessous.setLayout(new GridLayout(1, 3));
        // Le JButton suivi
        JButton text = new JButton("En construction...");
        text.setPreferredSize(new Dimension(300, 100));
        text.setBackground(Color.WHITE); this.text = text;
        dessous.add(text);
        // Le champ nombre de coups
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(300, 100));
        JTextField nbCoup = new JTextField("15");
        nbCoup.setPreferredSize(new Dimension(100, 100));
        // Avec une jolie Font !
        Font font = new Font("Courier", Font.BOLD, 25);
        nbCoup.setFont(font); nbCoup.setHorizontalAlignment(JTextField.CENTER);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        nbCoup.setForeground(Color.BLUE);
        container.add(nbCoup);
        dessous.add(container);
        this.nbCoup = nbCoup;
        // Un bouton quitter
        JButton quit = new JButton("Quitter"); quit.addMouseListener(this);
        quit.setBackground(Color.RED); quit.setForeground(Color.WHITE); quit.setFont(font);
        quit.setPreferredSize(new Dimension(300, 100)); this.quit = quit; dessous.add(quit);
        // Ecouter les events clicks sur les cases de board sauf Mur contour
        for(int i = 0; i < this.board.getBoard().getHeight(); i++) {
            for(int j = 0; j < this.board.getBoard().getLength(); j++) {
                this.board.getCase(i, j).addMouseListener(this);
                this.board.getCase(i, j).requestFocus();
            }
        }
        left.add(dessous);

        // === Coté droit, partie boutons
        JPanel right = new JPanel(); right.setPreferredSize(new Dimension(400, 1000));
        // Six lignes sur deux colonnes
        right.setLayout(new GridLayout(6, 2));

        // Créer l'ensemble des boutons et avec du style !
        BufferedImage characterImg = null;
        BufferedImage wallImg = null;
        BufferedImage redBoxImg = null;
        BufferedImage redGoalImg = null;
        BufferedImage blueBoxImg = null;
        BufferedImage blueGoalImg = null;
        BufferedImage greenBoxImg = null;
        BufferedImage greenGoalImg = null;
        BufferedImage gumImg = null;
        BufferedImage trashImg = null;
        BufferedImage doneImg = null;
        BufferedImage noneImg = null;

        try {
            characterImg = ImageIO.read(new File(path+"Character.bmp"));
            wallImg = ImageIO.read(new File(path+"Wall.bmp"));
            redBoxImg = ImageIO.read(new File(path+"Box_R.bmp"));
            redGoalImg = ImageIO.read(new File(path+"Objective_R.bmp"));
            blueBoxImg = ImageIO.read(new File(path+"Box_B.bmp"));
            blueGoalImg = ImageIO.read(new File(path+"Objective_B.bmp"));
            greenBoxImg = ImageIO.read(new File(path+"Box_G.bmp"));
            greenGoalImg = ImageIO.read(new File(path+"Objective_G.bmp"));
            gumImg = ImageIO.read(new File(path+"Gomme.jpg"));
            trashImg = ImageIO.read(new File(path+"Trash.png"));
            doneImg = ImageIO.read(new File(path+"Done.png"));
            noneImg = ImageIO.read(new File(path+"Vide.png"));
        } catch (IOException ioex) {
            System.err.println("load error: " + ioex.getMessage());
        }

        // Créer les icons avec les bonnes images
        ImageIcon characterIcon = new ImageIcon(characterImg);
        ImageIcon wallIcon = new ImageIcon(wallImg);
        ImageIcon redBoxIcon = new ImageIcon(redBoxImg);
        ImageIcon redGoalIcon = new ImageIcon(redGoalImg);
        ImageIcon blueBoxIcon = new ImageIcon(blueBoxImg);
        ImageIcon blueGoalIcon = new ImageIcon(blueGoalImg);
        ImageIcon greenBoxIcon = new ImageIcon(greenBoxImg);
        ImageIcon greenGoalIcon = new ImageIcon(greenGoalImg);
        ImageIcon gumIcon = new ImageIcon(gumImg);
        ImageIcon trashIcon = new ImageIcon(trashImg);
        ImageIcon noneIcon = new ImageIcon(noneImg);
        ImageIcon doneIcon = new ImageIcon(doneImg);

        // Créer les boutons avec les bonnes icons
        JButton character = new JButton(characterIcon);
        JButton wall = new JButton(wallIcon);
        JButton redBox = new JButton(redBoxIcon);
        JButton redGoal = new JButton(redGoalIcon);
        JButton blueBox = new JButton(blueBoxIcon);
        JButton blueGoal = new JButton(blueGoalIcon);
        JButton greenBox = new JButton(greenBoxIcon);
        JButton greenGoal = new JButton(greenGoalIcon);
        JButton erase = new JButton(gumIcon);
        JButton clear = new JButton(trashIcon);
        JButton noneCase = new JButton(noneIcon);
        JButton finish = new JButton(doneIcon);

        // Changer la couleur des boutons
        character.setBackground(Color.WHITE); wall.setBackground(Color.WHITE);
        redBox.setBackground(Color.WHITE); redGoal.setBackground(Color.WHITE);
        blueBox.setBackground(Color.WHITE); blueGoal.setBackground(Color.WHITE);
        greenBox.setBackground(Color.WHITE); greenGoal.setBackground(Color.WHITE);
        erase.setBackground(Color.WHITE); clear.setBackground(Color.WHITE);
        noneCase.setBackground(Color.WHITE); finish.setBackground(Color.WHITE);

        // Ecouter les events
        character.addMouseListener(this); wall.addMouseListener(this); redBox.addMouseListener(this);
        redGoal.addMouseListener(this); blueBox.addMouseListener(this); blueGoal.addMouseListener(this);
        greenBox.addMouseListener(this); greenGoal.addMouseListener(this); erase.addMouseListener(this);
        noneCase.addMouseListener(this); clear.addMouseListener(this); finish.addMouseListener(this);
        noneCase.requestFocus();
        // Ajouter les boutons au panel droit
        right.add(character); right.add(wall); right.add(redBox); right.add(redGoal); right.add(blueBox);
        right.add(blueGoal); right.add(greenBox); right.add(greenGoal); right.add(erase); right.add(clear);
        right.add(noneCase); right.add(finish);

        // Affecter aux bonnes variables de CreateVue
        this.wall = wall; this.character = character; this.redBox = redBox;
        this.redGoal = redGoal; this.blueBox = blueBox; this.blueGoal = blueGoal;
        this.greenBox = greenBox; this.greenGoal = greenGoal; this.erase = erase;
        this.clear = clear; this.finish = finish; this.noneCase = noneCase;

        noBorder();

        // ========= La fenetre entière
        JPanel panel = new JPanel( new BorderLayout() );
        panel.add( left, BorderLayout.WEST );
        panel.add( right, BorderLayout.EAST );
        this.update();
        this.add(panel);
        this.setVisible(true);
        this.setSize(1400,1000);
    }

    // Metre à jour la grille de jeu
    public void update() {
        for(int i=0; i < this.board.getBoard().getHeight(); i++) {
            for(int j=0; j < this.board.getBoard().getLength(); j++) {
                this.board.getCase(i, j).update();
                this.board.getCase(i, j).repaint();
            }
        }
        this.repaint();
    }

    // A l'écoute des evenements clicks
    public void mouseClicked(MouseEvent arg0){
        System.out.println("Clicked !");
        // Si click sur bouton personnage
        if(arg0.getSource() == character){
            selected = 1;
            noBorder();
            character.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton quitter
        else if(arg0.getSource()== quit){
            this.dispose();
            fenMenu = new MenuVue(this.nom);
        }
        // Si click sur bouton mur
        else if(arg0.getSource()== wall){
            selected = 2;
            noBorder();
            wall.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton box rouge
        else if(arg0.getSource()== redBox){
            selected = 3;
            noBorder();
            redBox.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton objectif rouge
        else if(arg0.getSource()== redGoal){
            selected = 4;
            noBorder();
            redGoal.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton box bleu
        else if(arg0.getSource()== blueBox){
            selected = 5;
            noBorder();
            blueBox.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton objectif bleu
        else if(arg0.getSource()== blueGoal){
            selected = 6;
            noBorder();
            blueGoal.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton box verte
        else if(arg0.getSource()== greenBox){
            selected = 7;
            noBorder();
            greenBox.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton objectif vert
        else if(arg0.getSource()== greenGoal){
            selected = 8;
            noBorder();
            greenGoal.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton gomme
        else if(arg0.getSource()== erase){
            selected = -1;
            noBorder();
            erase.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur bouton poubelle
        else if(arg0.getSource()== clear){
            selected = 9;
            noBorder();
            clear.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.clear();
            this.update();
            selected = 0;
        }
        // Si click sur bouton terminé
        else if(arg0.getSource()== finish){
            selected = 10;
            noBorder();
            finish.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            // Si on veut valider le level
            if(!this.isItFinish()) {
                text.setBorder(BorderFactory.createLineBorder(Color.red, 5));
            } else {
                text.setText("Niveau conforme");
                text.setBorder(BorderFactory.createLineBorder(Color.green, 5));
                // Remetre les cases none dans la bonne forme
                for(int i=0; i < this.board.getBoard().getHeight(); i++) {
                    for(int j=0; j < this.board.getBoard().getLength(); j++) {
                       if(this.board.getBoard().getCase(i, j).getColor() == "none") {
                           Case rien = new Case("n", null, false);
                           this.board.getBoard().setCase(i, j, rien);
                           this.board.getCase(i, j).setCase(rien);
                       }
                    }
                }
                // Créer une game à partir de celle produite par le User
                Game newGame = new Game(new Player(this.nom), this.board.getBoard());
            }
            this.update();
            selected = 0;
        }
        // Si click sur bouton case vide
        else if(arg0.getSource()== noneCase){
            selected = 11;
            noBorder();
            noneCase.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            this.update();
        }
        // Si click sur une case de la grille jeu
        // On cherche quelle case a été selectionnée
        for(int i = 0; i < this.board.getBoard().getHeight(); i++) {
            for(int j = 0; j < this.board.getBoard().getLength(); j++) {
                // Si la case est trouvée et que selected contient une action
                if(arg0.getSource() == this.board.getCase(i, j) && selected != 0) {
                    System.out.println(i + " - " + j);
                    switch(selected) {
                        case 1: // Placer le personnage
                            if(!charSet) { // Si le personnage n'est pas déjà sur la grille
                                Case target = new Case("n", new Empty(), false);
                                target.setChar(true);
                                this.board.getBoard().setCase(i, j, target);
                                this.board.getCase(i, j).setCase(target);
                                charSet = true;
                            }
                            selected = 0;
                            noBorder();
                            break;
                        case 2: // Placer un mur
                            System.out.println("Le mur !");
                            Case mur = new Case(new Wall());
                            this.board.getBoard().setCase(i, j, mur);
                            this.board.getCase(i, j).setCase(mur);
                            // On ne remet pas selected à 0 pour pouvoir mettre autant de murs qu'on souhaite
                            break;
                        case 3: // Placer une box rouge
                            Case boite1 = new Case("n", new Box("red"), false);
                            this.board.getBoard().setCase(i, j, boite1);
                            this.board.getCase(i, j).setCase(boite1);
                            selected = 0;
                            noBorder();
                            break;
                        case 4: // Placer un objectif rouge
                            Case objectif1 = new Case("red", new Empty(), false);
                            this.board.getBoard().setCase(i, j, objectif1);
                            this.board.getCase(i, j).setCase(objectif1);
                            selected = 0;
                            break;
                        case 5: // Placer une box bleu
                            System.out.println("Box bleu !!");
                            Case boite2 = new Case("n", new Box("blue"), false);
                            this.board.getBoard().setCase(i, j, boite2);
                            this.board.getCase(i, j).setCase(boite2);
                            selected = 0;
                            noBorder();
                            break;
                        case 6: // Placer un objectif bleu
                            Case objectif2 = new Case("blue", new Empty(), false);
                            this.board.getBoard().setCase(i, j, objectif2);
                            this.board.getCase(i, j).setCase(objectif2);
                            selected = 0;
                            noBorder();
                            break;
                        case 7: // Placer une box verte
                            Case boite3 = new Case("n", new Box("green"), false);
                            this.board.getBoard().setCase(i, j, boite3);
                            this.board.getCase(i, j).setCase(boite3);
                            selected = 0;
                            noBorder();
                            break;
                        case 8: // Placer un objectif vert
                            Case objectif3 = new Case("green", new Empty(), false);
                            this.board.getBoard().setCase(i, j, objectif3);
                            this.board.getCase(i, j).setCase(objectif3);
                            selected = 0;
                            noBorder();
                            break;
                        case 11: // Placer une case vide, à faire autant que souhaité
                            Case rien = new Case("none", new Empty(), false);
                            this.board.getBoard().setCase(i, j, rien);
                            this.board.getCase(i, j).setCase(rien);
                            break;
                        case -1: // Surpprimer le contenu de la case, à faire autant que souhaité
                            // Si la case contenait le personnage, mettre à jour charSet
                            if(this.board.getBoard().getCase(i, j).getChar()) charSet = false;
                            Case vide = new Case(new Empty());
                            this.board.getBoard().setCase(i, j, vide);
                            this.board.getCase(i, j).setCase(vide);
                            break;
                        default: // Cas par défaut, au cas ou
                            System.out.println("Dechet");
                    }
                }
            }
        }
        this.update();
    }

    // Pour remettre à zéro le style des bordures
    public void noBorder() {
        character.setBorder(BorderFactory.createLineBorder(Color.black, 1)); wall.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        redBox.setBorder(BorderFactory.createLineBorder(Color.black, 1)); redGoal.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        blueBox.setBorder(BorderFactory.createLineBorder(Color.black, 1)); blueGoal.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        greenBox.setBorder(BorderFactory.createLineBorder(Color.black, 1)); greenGoal.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        erase.setBorder(BorderFactory.createLineBorder(Color.black, 1)); clear.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        finish.setBorder(BorderFactory.createLineBorder(Color.black, 1)); text.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        noneCase.setBorder(BorderFactory.createLineBorder(Color.black, 1)); text.setText("En construction...");
    }

    // Pour remettre à zéro le level à designer
    public void clear() {
        for(int i = 0; i < this.board.getBoard().getHeight(); i++) {
            for (int j = 0; j < this.board.getBoard().getLength(); j++) {
                if(i == 0 || i == this.board.getBoard().getHeight() - 1 || j == 0 || j == this.board.getBoard().getLength() - 1) {
                    Case mur = new Case(new Wall());
                    this.board.getBoard().setCase(i, j, mur);
                    this.board.getCase(i, j).setCase(mur);
                } else {
                    Case vide = new Case("n", new Empty(), false);
                    this.board.getBoard().setCase(i, j, vide);
                    this.board.getCase(i, j).setCase(vide);
                }
            }
        }
        this.update();
        charSet = false;
    }

    // Renvoie boolean, indiquant si le level est conforme
    public boolean isItFinish() {
        int chara = 0; int nombre = 0;
        int redB = 0; int redG = 0;
        int blueB = 0; int blueG = 0;
        int greenB = 0; int greenG = 0;
        // On parcourt les cases
        for(int i = 0; i < this.board.getBoard().getHeight(); i++) {
            for (int j = 0; j < this.board.getBoard().getLength(); j++) {
                Case target = this.board.getBoard().getCase(i, j);
                // Si la case contient un personnage, incrémenté chara
                if(target.getChar()) { chara++; }
                // Si c'est une case contenant Empty
                else if(target.getContent() instanceof Empty) {
                    // C'est donc un objectif, incrémenté le bon
                    if(target.getColor() != null && target.getColor().equals("red")) { redG++; }
                    else if(target.getColor() != null && target.getColor().equals("green")) { greenG++; }
                    else if(target.getColor() != null && target.getColor().equals("blue")) { blueG++; }
                    // Si c'est une box
                } else if(target.getContent() instanceof Box) {
                    // Incrémenté le bon
                    if(target.getContent().getColor().equals("red")) { redB++; }
                    else if(target.getContent().getColor().equals("green")) { greenB++; }
                    else if(target.getContent().getColor().equals("blue")) { blueB++; }
                }
            }
        }
        // Récupérer le nombre de coup renseigné sans erreur
        try {
            nombre = Integer.parseInt(nbCoup.getText());
        } catch(NumberFormatException nfe) {
            nombre = 0;
        }
        // S'il y a un unique personnage
        // S'il y a autant de boxs d'une couleur que d'objectifs de cette même couleur
        // S'il y a au moins un duo box / objectif
        // Enfin, si le nombre de coup renseigné est bon => TRUE
        if(chara != 1) { text.setText("Un unique personnage !"); }
        else if(redB + blueB + greenB <= 0) { text.setText("Il n'y a pas de boxs !"); }
        else if(redG + greenG + blueG <= 0) { text.setText("Il n'y a pas d'objectifs !"); }
        else if(nombre == 0 || nombre > 100) { text.setText("Nombre de coups incorrect"); }
        // Les conditions sont donc toutes respectées :
        else if(redB == redG && blueB == blueG && greenB == greenG){ return true; }
        // Sinon FALSE
        return false;
    }

    // Override les méthodes suivantes
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
}