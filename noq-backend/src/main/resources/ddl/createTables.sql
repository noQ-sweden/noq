CREATE TABLE IF NOT EXISTS USERS (
    USER_ID UUID PRIMARY KEY NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME VARCHAR(255) NOT NULL,
    GENDER VARCHAR(255),
    UNOKOD VARCHAR(255) NOT NULL,
    DATE_OF_BIRTH VARCHAR(255) NOT NULL,
    PHONE VARCHAR(20),
    EMAIL VARCHAR(255),
    KOMMUN VARCHAR(255),
    PLACE VARCHAR(255),
    PAYING_CHARGES BOOLEAN,
    CASE_MANAGER VARCHAR(255) NOT NULL,
    UNDERSTANDS_SWEDISH BOOLEAN,
    LANGUAGES_KNOWN TEXT,
    IS_FLAGGED BOOLEAN,
    REASONS_FOR_HOMELESSNESS TEXT,
    GOALS TEXT,
    COMMENTS_AND_REMARKS TEXT,
    RESERVATION_ID UUID
);

CREATE TABLE HOST (
    HOST_ID UUID PRIMARY KEY NOT NULL,
    NAME VARCHAR(255),
    ADDRESS1 VARCHAR(255),
    ADDRESS2 VARCHAR(255),
    CITY VARCHAR(255),
    ADDRESS_POSTCODE VARCHAR(20),
    EMAIL VARCHAR(255),
    COUNT_OF_AVAILABLE_PLACES INTEGER,
    TOTAL_PLACES INTEGER,
    FACILITIES TEXT,
    TARGET_AUDIENCE TEXT
);