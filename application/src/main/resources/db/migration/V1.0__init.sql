-- Create tables

CREATE TABLE Users
(
    id       VARCHAR(100) PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    UNIQUE (email)
);


CREATE TABLE Community
(
    id          VARCHAR(100) PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    status      VARCHAR(50)  NOT NULL,
    visibility  VARCHAR(50)  NOT NULL,
    UNIQUE (name)
);


CREATE TABLE Member
(
    id           VARCHAR(100) PRIMARY KEY,
    community_id VARCHAR(100) NOT NULL,
    user_id      VARCHAR(100) NOT NULL,
    is_admin     BOOLEAN DEFAULT FALSE,
    UNIQUE (community_id, user_id),
    FOREIGN KEY (community_id) REFERENCES Community (id),
    FOREIGN KEY (user_id) REFERENCES Users (id)
);


CREATE TABLE Post
(
    id           VARCHAR(100) PRIMARY KEY,
    community_id VARCHAR(100) NOT NULL,
    member_id    VARCHAR(100) NOT NULL,
    FOREIGN KEY (community_id) REFERENCES Community (id),
    FOREIGN KEY (member_id) REFERENCES Member (id)
);


CREATE TABLE Field_Definition
(
    id           VARCHAR(100) PRIMARY KEY,
    community_id VARCHAR(100) NOT NULL,
    name         VARCHAR(50)  NOT NULL,
    type         VARCHAR(50)  NOT NULL,
    optional     BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (community_id) REFERENCES Community (id)
);


CREATE TABLE Post_Field
(
    id                  VARCHAR(100) PRIMARY KEY,
    post_id             VARCHAR(100) NOT NULL,
    field_definition_id VARCHAR(100) NOT NULL,
    data                TEXT,
    FOREIGN KEY (post_id) REFERENCES Post (id),
    FOREIGN KEY (field_definition_id) REFERENCES Field_Definition (id)
);
