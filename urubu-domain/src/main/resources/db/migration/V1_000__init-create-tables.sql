
    create table account (
        balance float(53) not null,
        bank tinyint unsigned not null check (bank between 0 and 2),
        id bigint not null auto_increment,
        user_id bigint not null,
        account_identifier varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table transaction (
        tipo_transacao tinyint unsigned not null check (tipo_transacao between 0 and 2),
        value float(53) not null,
        id bigint not null auto_increment,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table user (
        id bigint not null auto_increment,
        email varchar(255) not null,
        hash_senha varchar(255) not null,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table account 
       add constraint uk_account_identifier unique (account_identifier);

    alter table account 
       add constraint UK_h6dr47em6vg85yuwt4e2roca4 unique (user_id);

    alter table user 
       add constraint uk_user_email unique (email);

    alter table account 
       add constraint FK7m8ru44m93ukyb61dfxw0apf6 
       foreign key (user_id) 
       references user (id);

    alter table transaction 
       add constraint FKsg7jp0aj6qipr50856wf6vbw1 
       foreign key (user_id) 
       references user (id);
