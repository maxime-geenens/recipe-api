--- UNIT_REFERENCE ---
-- FR --
INSERT INTO UNIT_REFERENCE (id, lang, symbol, name, description) VALUES 
	(1, 'FR', 'cc', 'Cuillière à café', '1 cc = 5 mL'),
	(2, 'FR', 'cs', 'Cuillière à soupe', '1 cs = 15 mL'),
	(3, 'FR', 'tasse', 'Tasse', '1 tasse = 250 mL = 25 cL'),
	(4, 'FR', 'verre', 'Verre', '1 verre = 200 mL = 20 cL'),
	(5, 'FR', 'vv', 'Verre à vin', '1 vv = 120 mL = 12 cL'),
	(6, 'FR', 'vl', 'Verre à liqueur (shooter)', '1 vl = 30 mL = 3 cL'),
	(7, 'FR', 'g', 'Gramme', 'USI'),
	(8, 'FR', 'kg', 'Kilogramme', 'USI'),
	(9, 'FR', 'mL', 'Millitre', 'USI'),
	(10, 'FR', 'cL', 'Centilitre', 'USI'),
	(11, 'FR', 'L', 'Litre', 'USI');
	
-- EN --
INSERT INTO UNIT_REFERENCE (id, lang, symbol, name, description) VALUES 
	(12, 'EN', 'tsp', 'Tea spoon', '1 ts = 5 mL'),
	(13, 'EN', 'tbs', 'Table spoon', '1 tbs = 15 mL'),
	(14, 'EN', 'cup', 'Cup', '1 cup = 250 mL = 25 cL'),
	(15, 'EN', 'glass', 'Glass', '1 glass = 200 mL = 20 cL'),
	(16, 'EN', 'wg', 'Wine glass', '1 wg = 120 mL = 12 cL'),
	(17, 'EN', 'lg', 'Liquor glass (shooter)', '1 vl = 30 mL = 3 cL'),
	(18, 'EN', 'g', 'Gram', 'USI'),
	(19, 'EN', 'kg', 'Kilogram', 'USI'),
	(20, 'EN', 'mL', 'Milliter', 'USI'),
	(21, 'EN', 'cL', 'Centiliter', 'USI'),
	(22, 'EN', 'L', 'Liter', 'USI');
	
-- PT --
INSERT INTO UNIT_REFERENCE (id, lang, symbol, name, description) VALUES 
	(23, 'PT', 'cc', 'Colher de chá', '1 cc = 5 mL'),
	(24, 'PT', 'cs', 'Colher de sopa', '1 cs = 15 mL'),
	(25, 'PT', 'xícara', 'Xícara', '1 xícara = 250 mL = 25 cL'),
	(26, 'PT', 'copo', 'Copo', '1 copo = 200 mL = 20 cL'),
	(27, 'PT', 'cv', 'Copo de vinho', '1 cv = 120 mL = 12 cL'),
	(28, 'PT', 'shot', 'Shot', '1 vl = 30 mL = 3 cL'),
	(29, 'PT', 'g', 'Gramma', 'USI'),
	(30, 'PT', 'kg', 'Kilogramma', 'USI'),
	(31, 'PT', 'mL', 'Millitro', 'USI'),
	(32, 'PT', 'cL', 'Centilitro', 'USI'),
	(33, 'PT', 'L', 'Litro', 'USI');
	

--- RECIPE_TYPE ---
-- FR --
INSERT INTO RECIPE_TYPE (id, lang, name) VALUES
	(1, 'FR', 'Entrée'),
	(2, 'FR', 'Plat Principal'),
	(3, 'FR', 'Dessert'),
	(4, 'FR', 'Apéritif'),
	(5, 'FR', 'Sauce'),
	(6, 'FR', 'Autre');
	
-- EN --
INSERT INTO RECIPE_TYPE (id, lang, name) VALUES
	(7, 'EN', 'Starter'),
	(8, 'EN', 'Main dish'),
	(9, 'EN', 'Dessert'),
	(10, 'EN', 'Aperitif'),
	(11, 'EN', 'Sauce'),
	(12, 'EN', 'Other');
	
-- PT --
INSERT INTO RECIPE_TYPE (id, lang, name) VALUES
	(13, 'PT', 'Entrada'),
	(14, 'PT', 'Prato principal'),
	(15, 'PT', 'Sobremesa'),
	(16, 'PT', 'Aperitivo'),
	(17, 'PT', 'Molho'),
	(18, 'PT', 'Outro');


--- INGREDIENT_TYPE ---
-- FR --
INSERT INTO INGREDIENT_TYPE (id, lang, name) VALUES
	(1, 'FR', 'Légume'),
	(2, 'FR', 'Fruit'),
	(3, 'FR', 'Viande'),
	(4, 'FR', 'Volaille'),
	(5, 'FR', 'Oeuf'),
	(6, 'FR', 'Poisson'),
	(7, 'FR', 'Fruit de mer'),
	(8, 'FR', 'Céréale'),
	(9, 'FR', 'Graine'),
	(10, 'FR', 'Farine'),
	(11, 'FR', 'Sucré'),
	(12, 'FR', 'Épice'),
	(13, 'FR', 'Herbe'),
	(14, 'FR', 'Condiment'),
	(15, 'FR', 'Sauce'),
	(16, 'FR', 'Alcool'),
	(17, 'FR', 'Vinaigre'),
	(18, 'FR', 'Huile'),
	(19, 'FR', 'Matière grasse'),
	(20, 'FR', 'Produit laitier'),
	(21, 'FR', 'Fromage'),
	(22, 'FR', 'Champignon'),
	(23, 'FR', 'Additif'),
	(24, 'FR', 'Ferment'),
	(25, 'FR', 'Féculent'),
	(26, 'FR', 'Pâtes'),
	(79, 'FR', 'Pain'),
	(82, 'FR', 'Autre');

-- EN --
INSERT INTO INGREDIENT_TYPE (id, lang, name) VALUES
	(27, 'EN', 'Vegetable'),
	(28, 'EN', 'Fruit'),
	(29, 'EN', 'Meat'),
	(30, 'EN', 'Poultry'),
	(31, 'EN', 'Egg'),
	(32, 'EN', 'Fish'),
	(33, 'EN', 'Seafood'),
	(34, 'EN', 'Cereal'),
	(35, 'EN', 'Seed'),
	(36, 'EN', 'Flour'),
	(37, 'EN', 'Sweet'),
	(38, 'EN', 'Spice'),
	(39, 'EN', 'Herb'),
	(40, 'EN', 'Condiment'),
	(41, 'EN', 'Sauce'),
	(42, 'EN', 'Alcohol'),
	(43, 'EN', 'Vinegar'),
	(44, 'EN', 'Oil'),
	(45, 'EN', 'Fat'),
	(46, 'EN', 'Milk product'),
	(47, 'EN', 'Cheese'),
	(48, 'EN', 'Mushroom'),
	(49, 'EN', 'Additive'),
	(50, 'EN', 'Ferment'),
	(51, 'EN', 'Starch'),
	(52, 'EN', 'Pastry'),
	(80, 'EN', 'Bread'),
	(83, 'EN', 'Other');


-- PT --
INSERT INTO INGREDIENT_TYPE (id, lang, name) VALUES
	(53, 'PT', 'Legume'),
	(54, 'PT', 'Fruta'),
	(55, 'PT', 'Carne'),
	(56, 'PT', 'Aves'),
	(57, 'PT', 'Ovo'),
	(58, 'PT', 'Peixe'),
	(59, 'PT', 'Fruto do mar'),
	(60, 'PT', 'Cereal'),
	(61, 'PT', 'Grão'),
	(62, 'PT', 'Farinha'),
	(63, 'PT', 'Doce'),
	(64, 'PT', 'Tempero'),
	(65, 'PT', 'Erva'),
	(66, 'PT', 'Condimento'),
	(67, 'PT', 'Molho'),
	(68, 'PT', 'Álcool'),
	(69, 'PT', 'Vinagre'),
	(70, 'PT', 'Óleo'),
	(71, 'PT', 'Gordura'),
	(72, 'PT', 'Produto lácteo'),
	(73, 'PT', 'Queijo'),
	(74, 'PT', 'Cogumelo'),
	(75, 'PT', 'Additivo'),
	(76, 'PT', 'Fermento'),
	(77, 'PT', 'Amiláceo'),
	(78, 'PT', 'Massa'),
	(81, 'PT', 'Pão'),
	(84, 'PT', 'Outro');


--- INGREDIENT_REFERENCE ---
-- FR --
INSERT INTO INGREDIENT_TYPE (id, lang, name, type) VALUES
	(1, 'FR', 'Poivron Vert', 1),
	(2, 'FR', 'Pomme', 2),
	(3, 'FR', 'Boeuf haché', 3),
	(4, 'FR', 'Filet de poulet', 4),
	(5, 'FR', 'Oeuf de poule', 5),
	(6, 'FR', 'Saumon', 6),
	(7, 'FR', 'Huître', 7),
	(8, 'FR', 'Blé', 8),
	(9, 'FR', 'Sésame', 9),
	(10, 'FR', 'Sarrasin', 10),
	(11, 'FR', 'Chocolat en poudre', 11),
	(12, 'FR', 'Curry', 12),
	(13, 'FR', 'Origan', 13),
	(14, 'FR', 'Moutarde', 14),
	(15, 'FR', 'Worcestershire', 15),
	(16, 'FR', 'Vin blanc', 16),
	(17, 'FR', 'Vinaigre balsamique', 17),
	(18, 'FR', "Huile d'olive", 18),
	(19, 'FR', 'Beurre', 19),
	(20, 'FR', 'Crème fraiche', 20),
	(21, 'FR', 'Maroilles', 21),
	(22, 'FR', 'Champignon de Paris', 22),
	(23, 'FR', 'Colorant alimentaire', 23),
	(24, 'FR', 'Levure de boulanger', 24),
	(25, 'FR', 'Pomme de terre', 25),
	(26, 'FR', 'Spaghetti', 26),
	(79, 'FR', 'Pain de mie', 79);

-- EN --
INSERT INTO INGREDIENT_TYPE (id, lang, name, type) VALUES
	(27, 'EN', 'Green Pepper', 27),
	(28, 'EN', 'Apple', 28),
	(29, 'EN', 'Ground beef', 29),
	(30, 'EN', 'Chicken fillet', 30),
	(31, 'EN', 'Chicken egg', 31),
	(32, 'EN', 'Salmon', 32),
	(33, 'EN', 'Oyster', 33),
	(34, 'EN', 'Wheat', 34),
	(35, 'EN', 'Sesame', 35),
	(36, 'EN', 'Buckwheat flour', 36),
	(37, 'EN', 'Chocolate powder', 37),
	(38, 'EN', 'Curry', 38),
	(39, 'EN', 'Oregano', 39),
	(40, 'EN', 'Mustard', 40),
	(41, 'EN', 'Worcestershire', 41),
	(42, 'EN', 'White wine', 42),
	(43, 'EN', 'Balsamic vinegar', 43),
	(44, 'EN', 'Olive oil', 44),
	(45, 'EN', 'Butter', 45),
	(46, 'EN', 'Sour cream', 46),
	(47, 'EN', 'Maroilles', 47),
	(48, 'EN', 'Paris mushroom', 48),
	(49, 'EN', 'Food coloring', 49),
	(50, 'EN', 'Bakers yeast', 50),
	(51, 'EN', 'Potatoe', 51),
	(52, 'EN', 'Spaghetti', 52),
	(80, 'EN', 'Soft Bread', 80);


-- PT --
INSERT INTO INGREDIENT_TYPE (id, lang, name, type) VALUES
	(53, 'PT', 'Pimentão verde', 53),
	(54, 'PT', 'Maçã', 54),
	(55, 'PT', 'Carne moída', 55),
	(56, 'PT', 'Filé de frango', 56),
	(57, 'PT', 'Ovo de galinha', 57),
	(58, 'PT', 'Salmão', 58),
	(59, 'PT', 'Ostra', 59),
	(60, 'PT', 'Trigo', 60),
	(61, 'PT', 'Sesame', 61),
	(62, 'PT', 'Farinha de trigo sarraceno', 62),
	(63, 'PT', 'Chocolate em pó', 63),
	(64, 'PT', 'Curry', 64),
	(65, 'PT', 'Oregano', 65),
	(66, 'PT', 'Mostarda', 66),
	(67, 'PT', 'Molho inglês', 67),
	(68, 'PT', 'Vinho branco', 68),
	(69, 'PT', 'Vinagre balsamico', 69),
	(70, 'PT', 'Azeite', 70),
	(71, 'PT', 'Manteiga', 71),
	(72, 'PT', 'Creme de leite', 72),
	(73, 'PT', 'Maroilles', 73),
	(74, 'PT', 'Cogumelo de Paris', 74),
	(75, 'PT', 'Corante alimentar', 75),
	(76, 'PT', 'Levedura', 76),
	(77, 'PT', 'Batata', 77),
	(78, 'PT', 'Espaguete', 78),
	(81, 'PT', 'Pão de forma', 81);

