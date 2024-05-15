BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER ,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"role"	TEXT NOT NULL,
	"firstname"	TEXT,
	"lastname"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "bills" (
	"id"	INTEGER,
	"totalprice"	NUMERIC NOT NULL,
	"clientname"	TEXT NOT NULL,
	"clientphone"	TEXT NOT NULL,
	"date"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "commands" (
	"billid"	INTEGER NOT NULL UNIQUE,
	"partid"	INTEGER NOT NULL UNIQUE,
	"quantity"	INTEGER NOT NULL,
	"priceconsidered"	INTEGER NOT NULL,
	FOREIGN KEY("partid") REFERENCES "parts"("id"),
	FOREIGN KEY("billid") REFERENCES "bills"("id")
);
CREATE TABLE IF NOT EXISTS "categories" (
	"id"	INTEGER ,
	"name"	TEXT,
	"description"	TEXT,
	"image"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "parts" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"maker"	TEXT NOT NULL,
	"description"	TEXT NOT NULL,
	"image"	BLOB,
	"price"	REAL NOT NULL,
	"quantity"	INTEGER NOT NULL,
	"catid"	INTEGER NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("catid") REFERENCES "categories"("id")
);
INSERT INTO "users" ("username","password","role","firstname","lastname") VALUES ('kotbi','pass','admin',NULL,NULL);
INSERT INTO "categories" ("name","description","image") VALUES ('new test cat','new test cat','coffe.jpg'),
 ('test category','new test cat','Brake_pads.png'),
 ('colling system','colling system','Cooling_system.png'),
 ('suspension','suspension parts','Suspension.png'),
 ('Engine','Engine parts','Engine.png');
INSERT INTO "parts" ("name","maker","description","image","price","quantity","catid") VALUES ('susp','Mahle','suspension pugeot',NULL,23.5,100,4);
INSERT INTO "bills" ("totalprice","clientname","clientphone") VALUES (0,'null','null');
INSERT INTO "commands" ("billid","partid","quantity","priceconsidered") VALUES (1,1,23.5);
COMMIT;
