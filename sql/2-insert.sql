INSERT INTO domaine (id_domaine, nom_domaine) VALUES
(1, 'Infectiologie'),
(2, 'Parasitologie'),
(3, 'Cardiologie'),
(4, 'Neurologie'),
(5, 'Dermatologie'),
(6, 'Endocrinologie'),
(7, 'Pediatrie'),
(8, 'Gastroenterologie'),
(9, 'Orthopedie'),
(10, 'Psychiatrie'),
(11, 'Ophtalmologie'),
(12, 'Pneumologie'),
(13, 'Rhumatologie'),
(14, 'Gynecologie'),
(15, 'Allergologie');

INSERT INTO classe (id_classe, nom_classe) VALUES
(1, 'Antibiotiques'),
(2, 'Antiviraux'),
(3, 'Antifongiques'),
(4, 'Antiparasitaires'),
(5, 'Anti-inflammatoires non steroidiens'),
(6, 'Antihistaminiques'),
(7, 'Antihypertenseurs'),
(8, 'Anticoagulants'),
(9, 'Analgesiques'),
(10, 'Antispasmodiques'),
(11, 'Antidiabetiques'),
(12, 'Vaccins'),
(13, 'Medicaments gastro-intestinaux');

-- Insertion dans la table typeagent
INSERT INTO typeagent (id_typeagent, nom_typeagent) VALUES
(1, 'Virus'),
(2, 'Parasite');

-- Insertion dans la table agentpathogene
INSERT INTO agentpathogene (id_agentpathogene, nom_agentpathogene, id_typeagent) VALUES
(1, 'Influenza Grippe', 1), 
(2, 'Plasmodium falciparum', 2);

-- Insertion dans la table symptome
INSERT INTO symptome (id_symptome, nom_symptome, description_symptome) VALUES
(1, 'Fievre', 'Temperature corporelle elevee'),
(2, 'Toux', 'Expulsion forcee dair par la bouche'),
(3, 'Frissons', 'Sensation de froid accompagnee de tremblements'),
(4, 'Sueur nocturne', 'Transpiration excessive pendant la nuit'),
(5, 'Maux tete', 'Douleur au niveau de la tete');

-- Insertion pour la table `unite`
INSERT INTO unite (nom_unite, abreviation) VALUES
('Milligramme', 'mg'),
('Grammes', 'g'),
('Litres', 'L'),
('Millilitres', 'ml'),
('Comprime', 'cmp'),
('Capsule', 'cap');

-- Insertion pour la table `laboratoire`
INSERT INTO laboratoire (nom_laboratoire, contact_laboratoire, adresse_laboratoire) VALUES
('Laboratoire Pharmaceutique A', '0123456789', '123 Rue Principale'),
('Laboratoire Biomedic', '0987654321', '456 Avenue Centrale'),
('Laboratoire Medica', '0111222333', '789 Rue des Sciences'),
('Laboratoire Generique', NULL, '1234 Parc Industriel'),
('Laboratoire Natura', '0222333444', NULL);

-- Insertion pour la table `forme`
INSERT INTO forme (nom_forme) VALUES
('Comprime'),
('Capsule'),
('Sirop'),
('Poudre'),
('Injection'),
('Gel'),
('Creme');

-- Insertion pour la table `trancheage`
INSERT INTO trancheage (age_min, age_max, nom_tranche) VALUES
(0, 1, 'Nourrissons'),
(1, 12, 'Enfants'),
(13, 18, 'Adolescents'),
(19, 64, 'Adultes'),
(65, 120, 'Personnes Agees');

-- Insertion pour la table `genre`
INSERT INTO genre (nom_genre) VALUES
('Masculin'),
('Feminin'),
('Unisexe');

-- Insertion pour la table `typeproduit`
INSERT INTO typeproduit (nom_typeproduit) VALUES
('Medicament'),
('Complement Alimentaire'),
('Produit Cosmetique'),
('Materiel Medical');

-- Insertion pour la table `typesubstance`
INSERT INTO typesubstance (nom_typesubstance) VALUES
('Actif'),
('Excipient'),
('Additif'),
('Colorant'),
('Conservateur');

INSERT INTO substance (nom_substance, description_substance, id_typesubstance)
VALUES 
('Paracetamol', 'Utilise pour traiter la douleur et la fievre.', 1),
('Ibuprofene', 'Anti-inflammatoire et analgesique.', 1);


-- Insertion dans la table maladie
INSERT INTO maladie (id_maladie, nom_maladie, code_cim, id_agentpathogene, id_domaine) VALUES
(1, 'Grippe', 'J10', 1, 1),
(2, 'Paludisme', 'B50', 2, 2);

-- Insertion dans la table maladie_symptome
INSERT INTO maladie_symptome (id_symptome, id_maladie) VALUES
-- Symptomes pour la Grippe
(1, 1), -- Fievre
(2, 1), -- Toux
(3, 1), -- Frissons
-- Symptomes pour le Paludisme
(1, 2), -- Fievre
(3, 2), -- Frissons
(4, 2), -- Sueur nocturne
(5, 2); -- Maux tete



INSERT INTO achat (id_achat, description_achat, date_achat, etat_achat, montant_total, id_laboratoire, id_fournisseur) 
VALUES (3, 'Approvisionnement Sopharmad du Février 2025', '2025-02-01 00:00:00', NULL, NULL, NULL, 1);


INSERT INTO achat_details (id_achat_details, prix_achat_unitaire, prix_achat_total, id_lot, id_achat) VALUES
    (5, 4500.00, NULL, 3, 3), 
    (6, 2500.00, NULL, 2, 3); 
