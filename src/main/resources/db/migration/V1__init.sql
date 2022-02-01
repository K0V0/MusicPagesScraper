CREATE SCHEMA IF NOT EXISTS music-pages-scraper;

CREATE TABLE IF NOT EXISTS `platform_entity`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `name`          varchar(64) DEFAULT NULL,
    `platform`      varchar(64) DEFAULT NULL,
    `description`   TEXT DEFAULT NULL
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `bands_cache_entity`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `json`          MEDIUMTEXT DEFAULT NULL,
    `page`          SMALLINT,
    `platform`      varchar(64) DEFAULT NULL,
    `query`         TEXT DEFAULT NULL,
    `update_time`   DATETIME DEFAULT NULL
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `band_cache_entity`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `json`          MEDIUMTEXT DEFAULT NULL,
    `slug`          varchar(255) DEFAULT NULL,
    `platform`      varchar(64) DEFAULT NULL,
    `update_time`   DATETIME DEFAULT NULL
    PRIMARY KEY (`id`)
);



