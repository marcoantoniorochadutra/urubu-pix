
    create table accounts (
        id bigint not null auto_increment,
        balance decimal(13,2) not null,
        user_id bigint not null,
        account_identifier varchar(30) not null,
        primary key (id)
    ) engine=InnoDB;

    create table transactions (
        id bigint not null auto_increment,
        tipo_transacao tinyint unsigned not null,
        value decimal(13,2) not null,
        account_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table users (
        id bigint not null auto_increment,
        email varchar(255) not null,
        hash_senha varchar(255) not null,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;
    alter table accounts
       add constraint uk_account_identifier unique (account_identifier);

    alter table accounts
       add constraint uk_user_account unique (user_id);

    alter table users
       add constraint uk_user_email unique (email);

    alter table accounts
       add constraint fk_user_account
       foreign key (user_id)
       references users (id);

    alter table transactions
       add constraint fk_account_transactions
       foreign key (account_id)
       references accounts (id);

    alter table transactions
       add constraint fk_user_transactions
       foreign key (user_id)
       references users (id);