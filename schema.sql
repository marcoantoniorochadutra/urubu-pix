
    create table accounts (
        id bigint not null auto_increment,
        balance float(53) not null,
        bank tinyint unsigned not null check (bank between 0 and 2),
        user_id bigint not null,
        account_identifier varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table transactions (
        id bigint not null auto_increment,
        tipo_transacao tinyint unsigned not null check (tipo_transacao between 0 and 2),
        value float(53) not null,
        account_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table users (
        id bigint not null auto_increment,
        email varchar(120) not null,
        hash_senha varchar(120) not null,
        nome varchar(120) not null,
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
