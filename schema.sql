
    create table accounts (
        balance float(53) not null,
        id bigint not null auto_increment,
        user_id bigint not null,
        account_identifier varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table transactions (
        amount float(53) not null,
        payment_method tinyint unsigned not null check (payment_method between 0 and 2),
        payment_status tinyint unsigned not null check (payment_status between 0 and 4),
        trasaction_type tinyint unsigned not null check (trasaction_type between 0 and 2),
        account_id bigint not null,
        id bigint not null auto_increment,
        user_id bigint not null,
        bank_slip_bar_code varchar(255),
        bank_slip_numeric_line varchar(255),
        payment_link varchar(255),
        pix_qr_code varchar(255),
        transaction_identifier varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table user_details (
        active bit not null,
        id integer unsigned not null auto_increment,
        locale varchar(20) not null,
        refresh_token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users (
        id bigint not null auto_increment,
        user_details_id integer unsigned not null,
        email varchar(120) not null,
        hash_pass varchar(120) not null,
        name varchar(120) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table accounts 
       add constraint uk_account_identifier unique (account_identifier);

    alter table accounts 
       add constraint uk_user_account unique (user_id);

    alter table users 
       add constraint uk_user_email unique (email);

    alter table users 
       add constraint UK_4ai7rrtrvwtgtqavv8okpxrul unique (user_details_id);

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

    alter table users 
       add constraint fk_user_details 
       foreign key (user_details_id) 
       references user_details (id);
