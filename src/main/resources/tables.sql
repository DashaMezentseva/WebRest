CREATE CACHED TABLE "PUBLIC"."ROLE"(
    "ROLEID" BIGINT NOT NULL,
    "NAME" VARCHAR(255) NOT NULL
);
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ROLEID");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;
INSERT INTO "PUBLIC"."ROLE" VALUES
(1, 'user'),
(2, 'admin');
CREATE CACHED TABLE "PUBLIC"."USER"(
    "USERID" BIGINT NOT NULL,
    "BIRTHDAY" DATE NOT NULL,
    "EMAIL" VARCHAR(255) NOT NULL,
    "FIRSTNAME" VARCHAR(255) NOT NULL,
    "LASTNAME" VARCHAR(255) NOT NULL,
    "LOGIN" VARCHAR(255) NOT NULL,
    "PASSWORD" VARCHAR(255) NOT NULL,
    "ROLEID" BIGINT
);
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_27" PRIMARY KEY("USERID");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USER;
INSERT INTO "PUBLIC"."USER" VALUES
(16, DATE '1995-02-07', 'admin@gmail.com', 'Admin', 'Admin', 'admin', 'admin', 2),
(20, DATE '1999-08-08', 'dasha@gmail.com', 'Dasha', 'Mezentseva', 'dasha', 'dasha', 1);
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."UK7CGCTUXBNIYUJA9FLFHT3NJOE" UNIQUE("LOGIN", "EMAIL");
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."FK8YHL7WDO39N3EE04F8RPAJCES" FOREIGN KEY("ROLEID") REFERENCES "PUBLIC"."ROLE"("ROLEID") NOCHECK;