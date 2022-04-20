--changeset vovchello:1
DROP TABLE IF EXISTS dp_categories CASCADE;
CREATE TABLE dp_categories
(
    id               char(36) NOT NULL,
    name             char(36) NOT NULL,
    type             char(36) NOT NULL,
    raw_data varchar(255),
    PRIMARY KEY (id),
    CONSTRAINT UC_dp_category_name UNIQUE (name)
);

CREATE INDEX IDX_dp_category_name ON dp_categories (name);
--rollback DROP TABLE dp_categories IF EXISTS;

DROP TABLE IF EXISTS dp_units CASCADE;
CREATE TABLE dp_units
(
    id               char(36) NOT NULL,
    name             char(36) NOT NULL,
    flavour          char(36),
    flavour_raw_data              varchar(255),
    ean              varchar(255),
    category_id  char(36) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_dp_units_TO_dp_categories FOREIGN KEY (category_id) REFERENCES dp_categories (id),
    CONSTRAINT UC_dp_unit_name UNIQUE (name),
    CONSTRAINT UC_dp_unit_ean UNIQUE (ean)
);

CREATE INDEX IDX_dp_units_name ON dp_units (name);


DROP TABLE IF EXISTS dp_mixtures CASCADE;
CREATE TABLE dp_mixtures
(
    id               char(36) NOT NULL,
    name             char(36) NOT NULL,
    description varchar(255),
    PRIMARY KEY (id),
    CONSTRAINT UC_dp_mixture_name UNIQUE (name)
);

CREATE INDEX IDX_dp_mixture_name ON dp_mixtures (name);
--rollback DROP TABLE dp_mixtures IF EXISTS;

DROP TABLE IF EXISTS dp_ingredients CASCADE;
CREATE TABLE dp_ingredients
(
    mixture_id         char(36) NOT NULL,
    unit_id            char(36) NOT NULL,
    portion            varchar(255) NOT NULL,

    PRIMARY KEY (mixture_id, unit_id),
    CONSTRAINT FK_dp_ingredients_TO_dp_mixtures FOREIGN KEY (mixture_id) REFERENCES dp_mixtures (id),
    CONSTRAINT FK_dp_ingredients_TO_dp_units FOREIGN KEY (unit_id) REFERENCES dp_units (id)
);

--rollback DROP TABLE dp_ingredients IF EXISTS;