DELETE FROM produit;
DELETE FROM categorie;

INSERT INTO categorie (libelle) VALUES
('Électronique'),
('Informatique'),
('Téléphonie'),
('Accessoires'),
('Périphériques');

INSERT INTO produit (nom, categorie_id, quantite, prix_unitaire, statut) VALUES
('Ordinateur portable HP', 2, 15, 899.99, 'DISPONIBLE'),
('Souris sans fil Logitech', 4, 50, 29.99, 'DISPONIBLE'),
('iPhone 13', 3, 10, 999.99, 'DISPONIBLE');