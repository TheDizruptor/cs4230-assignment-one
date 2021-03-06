CREATE TABLE CONTACT    (
                            ID VARCHAR(256) NOT NULL PRIMARY KEY,
                            FIRST_NAME VARCHAR(50) NOT NULL,
                            LAST_NAME VARCHAR(50) NOT NULL,
                            PHONE_NUMBER VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE ADDRESS(
                        ID VARCHAR(256) NOT NULL PRIMARY KEY,
                        CONTACT_ID VARCHAR(256) NOT NULL,
                        ADDRESS_TYPE varchar(50) NOT NULL,
                        ADDRESS_LINE varchar(256) NOT NULL,
                        COUNTRY varchar(256),
                        CITY varchar(256) NOT NULL,
                        STATE varchar(256) NOT NULL,
                        POSTAL_CODE varchar(20) NOT NULL,
                        FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT(ID)
) ENGINE=INNODB;