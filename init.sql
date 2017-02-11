DROP SCHEMA IF EXISTS mobipl CASCADE;
CREATE SCHEMA mobipl;
CREATE SEQUENCE mobipl.pk_partenaires;
CREATE SEQUENCE mobipl.pk_demandes;
CREATE SEQUENCE mobipl.pk_raison_annulations;
CREATE SEQUENCE mobipl.pk_mobilites;
CREATE SEQUENCE mobipl.pk_partenaires_sections;


-- Création table utilisateurs --
CREATE TABLE mobipl.utilisateurs(
	pseudo VARCHAR(20) PRIMARY KEY,
	mdp VARCHAR(166),
	nom VARCHAR(66),
	prenom VARCHAR(50),
	sexe CHAR(1),
	statut VARCHAR(10),
	date_de_naissance TIMESTAMP,
	civilite VARCHAR(3),
	rue VARCHAR(100),
	numero INTEGER,
	boite VARCHAR(5),
	region VARCHAR(25),
	code_postal VARCHAR(20),
	ville VARCHAR(50),
	nationalite VARCHAR(25),
	email VARCHAR(50),
	nbr_annee_superieur INTEGER,
	num_compte VARCHAR(25),
	titulaire_compte VARCHAR(100),
	nom_banque VARCHAR (25),
	bic VARCHAR(25),
	date_inscription TIMESTAMP,
	section CHAR(3),
	version BIGINT
);

-- Création table pays --
CREATE TABLE mobipl.pays(
	code_iso CHAR(2) NOT NULL PRIMARY KEY,
	nom VARCHAR(80),
	type_mobilite VARCHAR(8),
	version BIGINT
);

--Création table partenaires --

CREATE TABLE mobipl.partenaires(
	id_partenaire INTEGER PRIMARY KEY DEFAULT NEXTVAL('mobipl.pk_partenaires'),
	nom_legal VARCHAR(50),
	nom_affaire VARCHAR(50),
	nom_complet VARCHAR(50),
	departement VARCHAR(30),
	type_organisation VARCHAR(5),
	nbr_employe INTEGER,
	rue VARCHAR(100),
	numero INTEGER,
	boite VARCHAR(5),
	pays CHARACTER(2)REFERENCES mobipl.pays(code_iso),
	region VARCHAR(25),
	code_postal VARCHAR(20),
	ville VARCHAR(50),
	email VARCHAR(50),
	site_web VARCHAR(100),
	tel VARCHAR(20),
	ajout_par_eleve BOOLEAN,
	version BIGINT,
	dispo BOOLEAN
);

--Création table partenaires_sections--
CREATE TABLE mobipl.partenaires_sections(
	id_partenaires_sections INTEGER PRIMARY KEY DEFAULT NEXTVAL('mobipl.pk_partenaires_sections'),
	partenaire INTEGER REFERENCES mobipl.partenaires(id_partenaire),
	section section,
	version BIGINT
);

-- Création table raison_annulations --
CREATE TABLE mobipl.raison_annulations(
	id_raison INTEGER PRIMARY KEY DEFAULT NEXTVAL('mobipl.pk_raison_annulations'),
	raison VARCHAR(50),
	version BIGINT
);

-- Création table demandes --
CREATE TABLE mobipl.demandes(
	id_demande INTEGER PRIMARY KEY DEFAULT NEXTVAL('mobipl.pk_demandes'),
	etudiant VARCHAR(20) REFERENCES mobipl.utilisateurs(pseudo),
	partenaire INTEGER REFERENCES mobipl.partenaires(id_partenaire),
	type_mobilite VARCHAR(8),
	ordre_preference INTEGER,
	type_demande CHAR(3),
	periode VARCHAR(50),
	date_introduction TIMESTAMP,
	annee_academique INTEGER,
	version BIGINT
);

-- Création table mobilités --

CREATE TABLE mobipl.mobilites(
	id_mobilite INTEGER PRIMARY KEY DEFAULT NEXTVAL('mobipl.pk_mobilites'),
	choix INTEGER REFERENCES mobipl.demandes(id_demande),
	etudiant VARCHAR(20) REFERENCES mobipl.utilisateurs(pseudo),
	partenaire INTEGER REFERENCES mobipl.partenaires(id_partenaire),
	raison_annulation INTEGER REFERENCES mobipl.raison_annulations(id_raison),
	periode VARCHAR(50),
	etat VARCHAR(15),
	type_demande CHAR(3),
	date_debut TIMESTAMP,
	date_fin TIMESTAMP,
	version BIGINT
);

-- Création table dossiers --

CREATE TABLE mobipl.dossiers(
	mobilite INTEGER PRIMARY KEY REFERENCES mobipl.mobilites(id_mobilite),
	contrat_de_bourse BOOLEAN,
	convention BOOLEAN,
	charte_etudiant BOOLEAN,
	preuve_passage_linguistique_avant BOOLEAN,
	document_engagement BOOLEAN,
	attestation_sejour BOOLEAN,
	releve_de_notes BOOLEAN,
	certificat_stage BOOLEAN,
	rapport_final BOOLEAN,
	preuve_passage_linguistique_apres BOOLEAN,
	encode_mobi BOOLEAN,
	encode_pro_eco BOOLEAN,
	encode_mobility_tool BOOLEAN,
	version BIGINT
);

-- Insertion Pays -- 
INSERT INTO mobipl.pays VALUES ('ES','Espagne', 'Erasmus+',0);
INSERT INTO mobipl.pays	VALUES ('AF','Afghanistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AL','Albanie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('DZ','Algérie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AS','Samoa Américaine', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AD','Andorre', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AO','Angola', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AI','Anguilla', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AQ','Antarctique', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AG','Antigua-et-Barbuda', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AR','Argentine', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AM','Arménie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AW','Aruba', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AU','Australie','Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('AT','Autriche', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AZ','Azerbaédjan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BS','Bahamas', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BH','Bahreén', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BD','Bangladesh', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BB','Barbade', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BY','Bélarus', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BE','Belgique', 'Erabel',0);
INSERT INTO mobipl.pays VALUES ('BZ','Belize', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BJ','Bénin', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BM','Bermuda', 'Fame',0); 
INSERT INTO mobipl.pays VALUES ('BT','Bhoutan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BO','Bolivie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BA','Bosnie-et-Herzégovine', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BW','Botswana', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BV','Ble Bouvet', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BR','Brésil', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('IO','Territoire Britannique de l''Océan Indien', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BN','Brunéi', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BG','Bulgarie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('BF','Burkina Faso', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('BI','Burundi', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KH','Cambodge', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CM','Cameroun', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CA','Canada', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CV','Cap-Vert', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KY','les Caémanes', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CF','Centrafricaine, République', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TD','Tchad', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CL','Chili', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CN','Chine', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CX','Île Christmas', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CC','Îles Cocos (Keeling)', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CO','Colombie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KM','Comores', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CG','Congo', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CD','Congo, République démocratique du', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CK','Îles Cook', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CR','Costa Rica', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CI','Côte D''Ivoire', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HR','Croatie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('CU','Cuba', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CY','Chypre', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('CZ','République Tchéque','Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('DK','Danemark', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('DJ','Djibouti', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('DM','Dominique', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('DO','Dominicaine, République', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('EC','Equateur', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('EG','Egypte', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SV','El Salvador', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GQ','Guinée Equatoriale', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ER','Erythrée', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('EE','Estonie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('ET','Ethiopie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('FK','Îles (Malvinas) Falkland', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('FO','Îles Faroes', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('FJ','Fidji', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('FI','Finlande', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('FR','France', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('GF','Guyane Française', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PF','Polynésie Franéaise', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TF','Terres Australes Franéaises', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GA','Gabon', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GM','Gambie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GE','Géorgie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('DE','Allemagne', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('GH','Ghana', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GI','Gibraltar', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GR','Gréce', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('GL','Groenland', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GD','Grenade', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GP','Guadeloupe', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GU','Guam', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GT','Guatemala', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GN','Guinée', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GW','Guinée-Bissau', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GY','Guyana', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HT','Haéti', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HM','éles Heard et McDonald', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VA','Cété du Vatican', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HN','Honduras', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HK','Hong-Kong', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('HU','Hongrie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('IS','Islande', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('IN','Inde', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ID','Indonésie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('IR','Iran', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('IQ','Iraq', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('IE','Irlande', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('IL','Israél', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('IT','Italie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('JM','Jamaéque', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('JP','Japon', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('JO','Jordanie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KZ','Kazakhstan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KE','Kenya', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KI','Kiribati', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KP','Corée, République démocratique du', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KR','Corée, République de', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KW','Koweét', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KG','Kirghizistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LA','Laos', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LV','Lettonie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('LB','Liban', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LS','Lesotho', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LR','Libéria', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LY','Lybie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LI','Liechtenstein', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('LT','Lituanie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('LU','Luxembourg', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('MO','Macao', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MK','Ancienne République yougoslave de Macédoine(ERYDM)', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('MG','Madagascar', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MW','Malawi', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MY','Malaisie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MV','Maldives', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ML','Mali', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MT','Malte', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('MH','Marshall, éles', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MQ','Martinique', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MR','Mauritanie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MU','Maurice', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('YT','Mayotte', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MX','Mexique', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('FM','Micronésie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MD','Moldova', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MC','Monaco', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MN','Mongolie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MS','Montserrat', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MA','Maroc', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MZ','Mozambique', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MM','Myanmar', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NA','Namibie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NR','Nauru', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NP','Népal', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NL','Pays-Bas', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('AN','Antilles Néerlandaises', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NC','Nouvelle-Calédonie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NZ','Nouvelle-Zélande', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NI','Nicaragua', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NE','Niger', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NG','Nigéria', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NU','Niué', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NF','éle Norfolk', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('MP','éles Mariannes du Nord', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('NO','Norvége', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('OM','Oman', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PK','Pakistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PW','Palau', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PS','Territoire Palestinien Occupé', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PA','Panama', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PG','Papouasie-Nouvelle-Guinée', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PY','Paraguay', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PE','Pérou', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PH','Philippines', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PN','Pitcairn', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PL','Pologne', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('PT','Portugal', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('PR','Porto Rico', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('QA','Qatar', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('RE','Réunion', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('RO','Roumanie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('RU','Russie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('RW','Rwanda', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SH','Sainte-Héléne', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('KN','Saint-Kitts-etNevis', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LC','Saint-Lucie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('PM','Saint-Pierre-et-Miquelon', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VC','Saint Vincent and the Grenadines', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('WS','Samoa', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SM','Saint-Marin', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ST','Sao Tomé-et-Principe', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SA','Arabie Saoudite', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SN','Sénégal', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('CS','Serbie-et-Monténégro', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SC','Seychelles', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SL','Sierra Leone', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SG','Singapour', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SK','Slovaquie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('SI','Slovénie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('SB','éles Salomon', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SO','Somalie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ZA','Afrique du Sud', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GS','Géorgie du Sud et les éles Sandwich du Sud', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('LK','Sri Lanka', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SD','Soudan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SR','Suriname', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SJ','Svalbard et éle Jan Mayen', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SZ','Swaziland', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SE','Suéde', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('CH','Suisse', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('SY','Syrie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TW','Taéwan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TJ','Tadjikistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TZ','Tanzanie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TH','Thaélande', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TL','Timor-Leste', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TG','Togo', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TK','Tokelau', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TO','Tonga', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TT','Trinité-et-Tobago', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TN','Tunisie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TR','Turquie', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('TM','Turkménistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TC','éles Turks et Caéques', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('TV','Tuvalu', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('UG','Ouganda', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('UA','Ukraine', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('AE','émirats Arabes Unis', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('GB','Royaume-Uni', 'Erasmus+',0);
INSERT INTO mobipl.pays VALUES ('US','états-Unis', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('UM','éles Mineures éloignées des états-Unis', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('UY','Uruguay', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('UZ','Ouzbekistan', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VU','Vanuatu', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VE','Venezuela', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VN','Viet Nam', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VG','éles Vierges Britanniques', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('VI','éles Vierges des états-Unis', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('WF','Wallis et Futuna', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('EH','Sahara Occidental', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('YE','Yemen', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ZM','Zambie', 'Fame',0);
INSERT INTO mobipl.pays VALUES ('ZW','Zimbabwe', 'Fame',0);

