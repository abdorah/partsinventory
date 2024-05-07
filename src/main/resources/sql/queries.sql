LOGIN=SELECT * FROM users WHERE username=? AND password=?
ALL_PARTS=SELECT id, name, maker, description, price, quantity FROM parts
PART_BY_CRITERIA=SELECT id, name, maker, description, price, quantity FROM parts WHERE
ADD_PART=INSERT INTO parts( maker,name, description, price, quantity) VALUES(?, ?, ?, ?, ?)
PARTS_QUANTITY_DESC=SELECT id,name, maker, description, price, quantity FROM parts ORDER BY quantity DESC
PARTS_PRICE_DESC=SELECT id,name, maker, description, price, quantity FROM parts ORDER BY price DESC
UPDATE_PART=UPDATE parts SET name=?,maker=?, description=?, price=?, quantity=? WHERE id=?
DELETE_PART=DELETE FROM parts WHERE id=?
ALL_CATEGORIES=SELECT catId, catName, catDesc,catImage FROM categories
ADD_CATEGORY=INSERT INTO categories( catName, catDesc, catImage) VALUES(?, ?, ?)
DELETE_CATEGORY=DELETE FROM categories WHERE catId=?