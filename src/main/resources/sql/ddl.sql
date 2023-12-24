CREATE TABLE "users" (
	"id"	INTEGER NOT NULL UNIQUE,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"role"	TEXT NOT NULL,
	"firstname"	TEXT,
	"lastname"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
)

CREATE TABLE "parts" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"description"	TEXT NOT NULL,
	"image"	BLOB NOT NULL,
	"price"	REAL NOT NULL,
	"quantity"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
)

CREATE TABLE "facture" (
	"id"	INTEGER NOT NULL,
	"totalprice"	NUMERIC NOT NULL,
	"payed"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
)

CREATE TABLE "command" (
	"factureid"	INTEGER NOT NULL UNIQUE,
	"partid"	INTEGER NOT NULL UNIQUE,
	"quantity"	INTEGER NOT NULL,
	"priceconsidered"	INTEGER NOT NULL,
	PRIMARY KEY("factureid","partid")
)