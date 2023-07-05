SET @fr = 'FR';
SET @pt = 'PT';
SET @en = 'EN';

--- UNIT_REFERENCE ---
-- FR --
INSERT INTO UNIT_REFERENCE (lang, symbol, name, description) VALUES 
	(@fr, 'cc', 'Cuillière à café', '1 cc = 5 mL'),
	(@fr, 'cs', 'Cuillière à soupe', '1 cs = 15 mL'),
	(@fr, 'tasse', 'Tasse', '1 tasse = 250 mL = 25 cL'),
	(@fr, 'verre', 'Verre', '1 verre = 200 mL = 20 cL'),
	(@fr, 'vv', 'Verre à vin', '1 vv = 120 mL = 12 cL'),
	(@fr, 'vl', 'Verre à liqueur (shooter)', '1 vl = 30 mL = 3 cL'),
	(@fr, 'g', 'Gramme', 'USI'),
	(@fr, 'kg', 'Kilogramme', 'USI'),
	(@fr, 'mL', 'Millitre', 'USI'),
	(@fr, 'cL', 'Centilitre', 'USI'),
	(@fr, 'L', 'Litre', 'USI'),
	(@fr, '-', '-', 'Sans unité de mesure');
	
-- EN --
INSERT INTO UNIT_REFERENCE (lang, symbol, name, description) VALUES 
	(@en, 'tsp', 'Tea spoon', '1 ts = 5 mL'),
	(@en, 'tbs', 'Table spoon', '1 tbs = 15 mL'),
	(@en, 'cup', 'Cup', '1 cup = 250 mL = 25 cL'),
	(@en, 'glass', 'Glass', '1 glass = 200 mL = 20 cL'),
	(@en, 'wg', 'Wine glass', '1 wg = 120 mL = 12 cL'),
	(@en, 'lg', 'Liquor glass (shooter)', '1 vl = 30 mL = 3 cL'),
	(@en, 'g', 'Gram', 'USI'),
	(@en, 'kg', 'Kilogram', 'USI'),
	(@en, 'mL', 'Milliter', 'USI'),
	(@en, 'cL', 'Centiliter', 'USI'),
	(@en, 'L', 'Liter', 'USI'),
	(@en, '-', '-', 'Without measure unit');
	
-- PT --
INSERT INTO UNIT_REFERENCE (lang, symbol, name, description) VALUES 
	(@pt, 'cc', 'Colher de chá', '1 cc = 5 mL'),
	(@pt, 'cs', 'Colher de sopa', '1 cs = 15 mL'),
	(@pt, 'xícara', 'Xícara', '1 xícara = 250 mL = 25 cL'),
	(@pt, 'copo', 'Copo', '1 copo = 200 mL = 20 cL'),
	(@pt, 'cv', 'Copo de vinho', '1 cv = 120 mL = 12 cL'),
	(@pt, 'shot', 'Shot', '1 vl = 30 mL = 3 cL'),
	(@pt, 'g', 'Gramma', 'USI'),
	(@pt, 'kg', 'Kilogramma', 'USI'),
	(@pt, 'mL', 'Millitro', 'USI'),
	(@pt, 'cL', 'Centilitro', 'USI'),
	(@pt, 'L', 'Litro', 'USI'),
	(@pt, '-', '-', 'Sem unidade de medida');
	

--- RECIPE_TYPE ---
-- FR --
INSERT INTO RECIPE_TYPE (lang, name) VALUES
	(@fr, 'Entrée'),
	(@fr, 'Plat Principal'),
	(@fr, 'Dessert'),
	(@fr, 'Apéritif'),
	(@fr, 'Sauce'),
	(@fr, 'Autre');
	
-- EN --
INSERT INTO RECIPE_TYPE (lang, name) VALUES
	(@en, 'Starter'),
	(@en, 'Main dish'),
	(@en, 'Dessert'),
	(@en, 'Aperitif'),
	(@en, 'Sauce'),
	(@en, 'Other');
	
-- PT --
INSERT INTO RECIPE_TYPE (lang, name) VALUES
	(@pt, 'Entrada'),
	(@pt, 'Prato principal'),
	(@pt, 'Sobremesa'),
	(@pt, 'Aperitivo'),
	(@pt, 'Molho'),
	(@pt, 'Outro');


--- INGREDIENT_TYPE ---
-- FR --
INSERT INTO INGREDIENT_TYPE (lang, name) VALUES
	(@fr, 'Légume'),
	(@fr, 'Fruit'),
	(@fr, 'Viande'),
	(@fr, 'Volaille'),
	(@fr, 'Oeuf'),
	(@fr, 'Poisson'),
	(@fr, 'Fruit de mer'),
	(@fr, 'Céréale'),
	(@fr, 'Graine'),
	(@fr, 'Farine'),
	(@fr, 'Sucré'),
	(@fr, 'Épice'),
	(@fr, 'Herbe'),
	(@fr, 'Condiment'),
	(@fr, 'Sauce'),
	(@fr, 'Alcool'),
	(@fr, 'Vinaigre'),
	(@fr, 'Huile'),
	(@fr, 'Matière grasse'),
	(@fr, 'Produit laitier'),
	(@fr, 'Fromage'),
	(@fr, 'Champignon'),
	(@fr, 'Additif'),
	(@fr, 'Ferment'),
	(@fr, 'Féculent'),
	(@fr, 'Pâtes'),
	(@fr, 'Pain'),
	(@fr, 'Autre');

-- EN --
INSERT INTO INGREDIENT_TYPE (lang, name) VALUES
	(@en, 'Vegetable'),
	(@en, 'Fruit'),
	(@en, 'Meat'),
	(@en, 'Poultry'),
	(@en, 'Egg'),
	(@en, 'Fish'),
	(@en, 'Seafood'),
	(@en, 'Cereal'),
	(@en, 'Seed'),
	(@en, 'Flour'),
	(@en, 'Sweet'),
	(@en, 'Spice'),
	(@en, 'Herb'),
	(@en, 'Condiment'),
	(@en, 'Sauce'),
	(@en, 'Alcohol'),
	(@en, 'Vinegar'),
	(@en, 'Oil'),
	(@en, 'Fat'),
	(@en, 'Milk product'),
	(@en, 'Cheese'),
	(@en, 'Mushroom'),
	(@en, 'Additive'),
	(@en, 'Ferment'),
	(@en, 'Starch'),
	(@en, 'Pastry'),
	(@en, 'Bread'),
	(@en, 'Other');

--- TOOL ---
-- FR --


-- EN --


-- PT --




-- PT --
INSERT INTO INGREDIENT_TYPE (lang, name) VALUES
	(@pt, 'Legume'),
	(@pt, 'Fruta'),
	(@pt, 'Carne'),
	(@pt, 'Aves'),
	(@pt, 'Ovo'),
	(@pt, 'Peixe'),
	(@pt, 'Fruto do mar'),
	(@pt, 'Cereal'),
	(@pt, 'Grão'),
	(@pt, 'Farinha'),
	(@pt, 'Doce'),
	(@pt, 'Tempero'),
	(@pt, 'Erva'),
	(@pt, 'Condimento'),
	(@pt, 'Molho'),
	(@pt, 'Álcool'),
	(@pt, 'Vinagre'),
	(@pt, 'Óleo'),
	(@pt, 'Gordura'),
	(@pt, 'Produto lácteo'),
	(@pt, 'Queijo'),
	(@pt, 'Cogumelo'),
	(@pt, 'Additivo'),
	(@pt, 'Fermento'),
	(@pt, 'Amiláceo'),
	(@pt, 'Massa'),
	(@pt, 'Pão'),
	(@pt, 'Outro');


--- INGREDIENT_REFERENCE ---
-- FR --
INSERT INTO INGREDIENT_REFERENCE (lang, name, type) VALUES
	(@fr, 'Poivron Vert', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Légume'),
	(@fr, 'Pomme', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Fruit'),
	(@fr, 'Boeuf haché', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Viande'),
	(@fr, 'Filet de poulet', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Volaille'),
	(@fr, 'Oeuf de poule', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Oeuf'),
	(@fr, 'Saumon', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Poisson'),
	(@fr, 'Huître', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Fruit de mer'),
	(@fr, 'Blé', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Céréale'),
	(@fr, 'Sésame', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Graine'),
	(@fr, 'Sarrasin', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Farine'),
	(@fr, 'Chocolat en poudre', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Sucré'),
	(@fr, 'Curry', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Épice'),
	(@fr, 'Origan', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Herbe'),
	(@fr, 'Ail', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Condiment'),
	(@fr, 'Worcestershire', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Sauce'),
	(@fr, 'Vin blanc', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Alcool'),
	(@fr, 'Vinaigre balsamique', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Vinaigre'),
	(@fr, 'Huile d olive', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Huile'),
	(@fr, 'Beurre', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Matière grasse'),
	(@fr, 'Crème fraiche', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Produit laitier'),
	(@fr, 'Maroilles', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Fromage'),
	(@fr, 'Champignon de Paris', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Champignon'),
	(@fr, 'Colorant alimentaire', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Additif'),
	(@fr, 'Levure de boulanger', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Ferment'),
	(@fr, 'Pomme de terre', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Féculent'),
	(@fr, 'Spaghetti', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Pâtes'),
	(@fr, 'Pain de mie', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @fr AND it.name = 'Pain');

-- EN --
INSERT INTO INGREDIENT_REFERENCE (lang, name, type) VALUES
	(@en, 'Green Pepper', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Vegetable'),
	(@en, 'Apple', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Fruit'),
	(@en, 'Ground beef', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Meat'),
	(@en, 'Chicken fillet', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Poultry'),
	(@en, 'Chicken egg', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Egg'),
	(@en, 'Salmon', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Fish'),
	(@en, 'Oyster', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Seafood'),
	(@en, 'Wheat', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Cereal'),
	(@en, 'Sesame', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Seed'),
	(@en, 'Buckwheat flour', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Flour'),
	(@en, 'Chocolate powder', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Sweet'),
	(@en, 'Curry', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Spice'),
	(@en, 'Oregano', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Herb'),
	(@en, 'Garlic', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Condiment'),
	(@en, 'Worcestershire', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Sauce'),
	(@en, 'White wine', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Alcohol'),
	(@en, 'Balsamic vinegar', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Vinegar'),
	(@en, 'Olive oil', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Oil'),
	(@en, 'Butter', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Fat'),
	(@en, 'Sour cream', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Milk product'),
	(@en, 'Maroilles', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Cheese'),
	(@en, 'Paris mushroom', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Mushroom'),
	(@en, 'Food coloring', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Additive'),
	(@en, 'Bakers yeast', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Ferment'),
	(@en, 'Potatoe', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Starch'),
	(@en, 'Spaghetti', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Pastry'),
	(@en, 'Soft Bread', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @en AND it.name = 'Bread');


-- PT --
INSERT INTO INGREDIENT_REFERENCE (lang, name, type) VALUES
	(@pt, 'Pimentão verde', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Legume'),
	(@pt, 'Maçã', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Fruta'),
	(@pt, 'Carne moída', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Carne'),
	(@pt, 'Filé de frango', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Aves'),
	(@pt, 'Ovo de galinha', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Ovo'),
	(@pt, 'Salmão', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Peixe'),
	(@pt, 'Ostra', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Fruto do mar'),
	(@pt, 'Trigo', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Cereal'),
	(@pt, 'Sesame', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Grão'),
	(@pt, 'Farinha de trigo sarraceno', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Farinha'),
	(@pt, 'Chocolate em pó', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Doce'),
	(@pt, 'Curry', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Tempero'),
	(@pt, 'Oregano', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Erva'),
	(@pt, 'Alho', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Condimento'),
	(@pt, 'Molho inglês', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Molho'),
	(@pt, 'Vinho branco', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Álcool'),
	(@pt, 'Vinagre balsamico', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Vinagre'),
	(@pt, 'Azeite', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Óleo'),
	(@pt, 'Manteiga', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Gordura'),
	(@pt, 'Creme de leite', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Produto lácteo'),
	(@pt, 'Maroilles', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Queijo'),
	(@pt, 'Cogumelo de Paris', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Cogumelo'),
	(@pt, 'Corante alimentar', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Additivo'),
	(@pt, 'Levedura', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Fermento'),
	(@pt, 'Batata', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Amiláceo'),
	(@pt, 'Espaguete', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Massa'),
	(@pt, 'Pão de forma', SELECT it.id FROM INGREDIENT_TYPE it WHERE it.lang = @pt AND it.name = 'Pão');


--- RECIPE | INGREDIENT | STEP (Complete recipe) ---
-- FR --



-- EN --



-- PT --






















