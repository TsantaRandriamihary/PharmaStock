CREATE DATABASE pharmacie;
\c pharmacie;

CREATE TABLE unite(
   id_unite SERIAL,
   nom_unite VARCHAR(255)  NOT NULL,
   abreviation VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_unite)
);

CREATE TABLE laboratoire(
   id_laboratoire SERIAL,
   nom_laboratoire VARCHAR(255)  NOT NULL,
   contact_laboratoire VARCHAR(255) ,
   adresse_laboratoire VARCHAR(255) ,
   PRIMARY KEY(id_laboratoire)
);

CREATE TABLE domaine(
   id_domaine SERIAL,
   nom_domaine VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_domaine)
);

CREATE TABLE forme(
   id_forme SERIAL,
   nom_forme VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_forme)
);

CREATE TABLE trancheage(
   id_trancheage SERIAL,
   age_min INTEGER NOT NULL,
   age_max INTEGER NOT NULL,
   nom_tranche VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_trancheage)
);

CREATE TABLE genre(
   id_genre SERIAL,
   nom_genre VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_genre)
);

CREATE TABLE typeproduit(
   id_typeproduit SERIAL,
   nom_typeproduit VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_typeproduit)
);

CREATE TABLE typesubstance(
   id_typesubstance SERIAL,
   nom_typesubstance VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_typesubstance)
);

CREATE TABLE substance(
   id_substance SERIAL,
   nom_substance VARCHAR(255)  NOT NULL,
   description_substance TEXT,
   id_typesubstance INTEGER NOT NULL,
   PRIMARY KEY(id_substance),
   FOREIGN KEY(id_typesubstance) REFERENCES typesubstance(id_typesubstance)
);

CREATE TABLE classe(
   id_classe SERIAL,
   nom_classe VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_classe)
);

CREATE TABLE typeagent(
   id_typeagent SERIAL,
   nom_typeagent VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_typeagent)
);

CREATE TABLE agentpathogene(
   id_agentpathogene SERIAL,
   nom_agentpathogene VARCHAR(255)  NOT NULL,
   id_typeagent INTEGER NOT NULL,
   PRIMARY KEY(id_agentpathogene),
   FOREIGN KEY(id_typeagent) REFERENCES typeagent(id_typeagent)
);

CREATE TABLE symptome(
   id_symptome SERIAL,
   nom_symptome VARCHAR(255)  NOT NULL,
   description_symptome TEXT NOT NULL,
   PRIMARY KEY(id_symptome)
);

CREATE TABLE maladie(
   id_maladie SERIAL,
   nom_maladie VARCHAR(255)  NOT NULL,
   code_cim VARCHAR(255) ,
   id_agentpathogene INTEGER,
   id_domaine INTEGER,
   PRIMARY KEY(id_maladie),
   FOREIGN KEY(id_agentpathogene) REFERENCES agentpathogene(id_agentpathogene),
   FOREIGN KEY(id_domaine) REFERENCES domaine(id_domaine)
);

CREATE TABLE produit(
   id_produit SERIAL,
   nom_produit VARCHAR(255)  NOT NULL,
   description_produit TEXT,
   quantite_unitaire INTEGER,
   dose NUMERIC(5, 2)(15,2)  ,
   etat_produit INTEGER,
   prix NUMERIC(15,2)   NOT NULL,
   id_forme INTEGER NOT NULL,
   id_classe INTEGER,
   id_laboratoire INTEGER NOT NULL,
   id_typeproduit INTEGER NOT NULL,
   id_unite INTEGER,
   id_domaine INTEGER,
   PRIMARY KEY(id_produit),
   FOREIGN KEY(id_forme) REFERENCES forme(id_forme),
   FOREIGN KEY(id_classe) REFERENCES classe(id_classe),
   FOREIGN KEY(id_laboratoire) REFERENCES laboratoire(id_laboratoire),
   FOREIGN KEY(id_typeproduit) REFERENCES typeproduit(id_typeproduit),
   FOREIGN KEY(id_unite) REFERENCES unite(id_unite),
   FOREIGN KEY(id_domaine) REFERENCES domaine(id_domaine)
);

CREATE TABLE lot(
   id_lot SERIAL,
   date_peremption TIMESTAMP,
   date_fabrication TIMESTAMP,
   quantite_lot INTEGER NOT NULL,
   nom_lot VARCHAR(255)  NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_lot),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE fournisseur(
   id_fournisseur SERIAL,
   nom_fournisseur VARCHAR(255)  NOT NULL,
   contact_fournisseur VARCHAR(255) ,
   adresse_fournisseur VARCHAR(255) ,
   PRIMARY KEY(id_fournisseur)
);

CREATE TABLE client(
   id_client SERIAL,
   nom_client VARCHAR(255)  NOT NULL,
   contact_client VARCHAR(255) ,
   adresse_client VARCHAR(255) ,
   PRIMARY KEY(id_client)
);

CREATE TABLE achat(
   id_achat SERIAL,
   description_achat TEXT,
   date_achat TIMESTAMP NOT NULL,
   etat_achat INTEGER,
   montant_total NUMERIC(15,2),
   id_laboratoire INTEGER,
   id_fournisseur INTEGER,
   PRIMARY KEY(id_achat),
   FOREIGN KEY(id_laboratoire) REFERENCES laboratoire(id_laboratoire),
   FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id_fournisseur)
);

CREATE TABLE achat_details(
   id_achat_details SERIAL,
   prix_achat_unitaire NUMERIC(15,2)   NOT NULL,
   prix_achat_total NUMERIC(15,2)  ,
   id_lot INTEGER NOT NULL,
   id_achat INTEGER NOT NULL,
   PRIMARY KEY(id_achat_details),
   FOREIGN KEY(id_lot) REFERENCES lot(id_lot),
   FOREIGN KEY(id_achat) REFERENCES achat(id_achat)
);

CREATE TABLE vente(
   id_vente SERIAL,
   date_vente TIMESTAMP NOT NULL,
   etat_vente INTEGER,
   montant_total NUMERIC(15,2)  ,
   description_vente TEXT,
   id_client INTEGER NOT NULL,
   PRIMARY KEY(id_vente),
   FOREIGN KEY(id_client) REFERENCES client(id_client)
);


CREATE TABLE vendeur(
   id_vendeur SERIAL,
   nom_vendeur VARCHAR(255)  NOT NULL,
   id_genre INTEGER NOT NULL,
   PRIMARY KEY(id_vendeur),
   FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE commission(
   id_commision SERIAL,
   pourcent_commission NUMERIC(15,2)   NOT NULL,
   min_chiffre_affaire NUMERIC(15,2)   NOT NULL,
   PRIMARY KEY(id_commision)
);

CREATE TABLE vente(
   id_vente SERIAL,
   date_vente TIMESTAMP NOT NULL,
   etat_vente INTEGER,
   montant_total NUMERIC(15,2)  ,
   description_vente TEXT,
   id_vendeur INTEGER  NOT NULL,
   id_client INTEGER NOT NULL,
   PRIMARY KEY(id_vente),
   FOREIGN KEY(id_vendeur) REFERENCES vendeur(id_vendeur),
   FOREIGN KEY(id_client) REFERENCES client(id_client)
);
CREATE TABLE vente_details(
   id_vente_details SERIAL,
   quantite_vendue INTEGER NOT NULL,
   prix_vente_unitaire NUMERIC(15,2)   NOT NULL,
   id_lot INTEGER NOT NULL,
   id_vente INTEGER NOT NULL,
   PRIMARY KEY(id_vente_details),
   FOREIGN KEY(id_lot) REFERENCES lot(id_lot),
   FOREIGN KEY(id_vente) REFERENCES vente(id_vente)
);

CREATE TABLE mouvement_stock(
   id_mouvement SERIAL,
   date_mouvement TIMESTAMP NOT NULL,
   est_achat BOOLEAN,
   reference INTEGER,
   PRIMARY KEY(id_mouvement)
);

CREATE TABLE mouvement_stock_details(
   id_mouvement_details SERIAL,
   entree INTEGER,
   sortie INTEGER,
   id_produit INTEGER NOT NULL,
   id_lot INTEGER NOT NULL,
   id_mouvement INTEGER NOT NULL,
   PRIMARY KEY(id_mouvement_details),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit),
   FOREIGN KEY(id_lot) REFERENCES lot(id_lot),
   FOREIGN KEY(id_mouvement) REFERENCES mouvement_stock(id_mouvement)
);

CREATE TABLE historique_prix(
   id_historique_prix SERIAL,
   date_changement TIMESTAMP NOT NULL,
   prix NUMERIC(15,2)   NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_historique_prix),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE agent_substance(
   id_substance INTEGER,
   id_agentpathogene INTEGER,
   PRIMARY KEY(id_substance, id_agentpathogene),
   FOREIGN KEY(id_substance) REFERENCES substance(id_substance),
   FOREIGN KEY(id_agentpathogene) REFERENCES agentpathogene(id_agentpathogene)
);

CREATE TABLE substance_symptome(
   id_substance INTEGER,
   id_symptome INTEGER,
   PRIMARY KEY(id_substance, id_symptome),
   FOREIGN KEY(id_substance) REFERENCES substance(id_substance),
   FOREIGN KEY(id_symptome) REFERENCES symptome(id_symptome)
);

CREATE TABLE maladie_symptome(
   id_symptome INTEGER,
   id_maladie INTEGER,
   degre NUMERIC(5,2),
   PRIMARY KEY(id_symptome, id_maladie),
   FOREIGN KEY(id_symptome) REFERENCES symptome(id_symptome),
   FOREIGN KEY(id_maladie) REFERENCES maladie(id_maladie)
);

CREATE TABLE contre_indication(
   id_symptome INTEGER,
   id_produit INTEGER,
   description_contre_indication TEXT,
   PRIMARY KEY(id_symptome, id_produit),
   FOREIGN KEY(id_symptome) REFERENCES symptome(id_symptome),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE produit_trancheage(
   id_trancheage INTEGER,
   id_produit INTEGER,
   PRIMARY KEY(id_trancheage, id_produit),
   FOREIGN KEY(id_trancheage) REFERENCES trancheage(id_trancheage),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE produit_genre(
   id_genre INTEGER,
   id_produit INTEGER,
   PRIMARY KEY(id_genre, id_produit),
   FOREIGN KEY(id_genre) REFERENCES genre(id_genre),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE produit_substance(
   id_substance INTEGER,
   id_produit INTEGER,
   degre NUMERIC(5,2),
   PRIMARY KEY(id_substance, id_produit),
   FOREIGN KEY(id_substance) REFERENCES substance(id_substance),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE produit_symptome(
   id_symptome INTEGER,
   id_produit INTEGER,
   PRIMARY KEY(id_symptome, id_produit),
   FOREIGN KEY(id_symptome) REFERENCES symptome(id_symptome),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);


CREATE TABLE type_selection(
   id_type_selection SERIAL,
   nom_selection VARCHAR(50) ,
   PRIMARY KEY(id_type_selection)
);

CREATE TABLE selection(
   id_selection SERIAL,
   date_debut VARCHAR(50) ,
   date_fin VARCHAR(50) ,
   description VARCHAR(50) ,
   id_type_selection INTEGER,
   id_produit INTEGER,
   PRIMARY KEY(id_selection),
   FOREIGN KEY(id_type_selection) REFERENCES type_selection(id_type_selection),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);
