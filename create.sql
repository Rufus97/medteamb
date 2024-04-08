
    create table appointment (
        appointmentid integer not null auto_increment,
        patient_patientid integer,
        appointment_date_time datetime(6) not null,
        medical_provision varchar(255),
        place varchar(255),
        tax_code varchar(255),
        status enum ('CANCELLED','EXPIRED','PASSED','TO_DO'),
        primary key (appointmentid)
    ) engine=InnoDB;

    create table medic (
        medicid integer not null auto_increment,
        primary key (medicid)
    ) engine=InnoDB;

    create table patient (
        patientid integer not null auto_increment,
        email varchar(255),
        phone_number varchar(255),
        surname varchar(255),
        username varchar(255),
        primary key (patientid)
    ) engine=InnoDB;

    create table secretary (
        secretaryid integer not null auto_increment,
        primary key (secretaryid)
    ) engine=InnoDB;

    alter table appointment 
       add constraint UK_4j1c4rm2hfwo2kbgolvux9od6 unique (appointment_date_time);

    alter table appointment 
       add constraint FKotdlkl44o5if17km8l085n8yt 
       foreign key (patient_patientid) 
       references patient (patientid);

    create table appointment (
        appointmentid integer not null auto_increment,
        patient_patientid integer,
        appointment_date_time datetime(6) not null,
        medical_provision varchar(255),
        place varchar(255),
        tax_code varchar(255),
        status enum ('CANCELLED','EXPIRED','PASSED','TO_DO'),
        primary key (appointmentid)
    ) engine=InnoDB;

    create table medic (
        medicid integer not null auto_increment,
        primary key (medicid)
    ) engine=InnoDB;

    create table patient (
        patientid integer not null auto_increment,
        email varchar(255),
        phone_number varchar(255),
        surname varchar(255),
        username varchar(255),
        primary key (patientid)
    ) engine=InnoDB;

    create table secretary (
        secretaryid integer not null auto_increment,
        email varchar(255),
        phone_number varchar(255),
        surname varchar(255),
        username varchar(255),
        primary key (secretaryid)
    ) engine=InnoDB;

    alter table appointment 
       add constraint UK_4j1c4rm2hfwo2kbgolvux9od6 unique (appointment_date_time);

    alter table appointment 
       add constraint FKotdlkl44o5if17km8l085n8yt 
       foreign key (patient_patientid) 
       references patient (patientid);
