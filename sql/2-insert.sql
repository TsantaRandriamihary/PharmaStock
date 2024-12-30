-- Insertion dans la table domaine
INSERT INTO domaine (id_domaine, nom_domaine) VALUES
(1, 'Infectiologie'),
(2, 'Parasitologie');

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
