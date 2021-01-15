create table sdb_course_history
(
    id               varchar(40)  not null,
    name             varchar(100) not null,
    remark           varchar(2000),
    overtime         int,
    overtime_dispose int,
    parent_course_id varchar(40),
    flow_id          varchar(40),
    is_judge         tinyint,
    is_freedom       tinyint,
    is_countersign   tinyint,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过程记录表';

create table sdb_course_condition_history
(
    id         varchar(40) not null,
    course_id  varchar(40) not null,
    type       int,
    value      varchar(8000),
    relation   int,
    field_name varchar(40),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='判断条件记录表';

create table sdb_course_person_history
(
    id        varchar(40)  not null,
    name      varchar(100) not null,
    type      int,
    point_id  varchar(40),
    dispose   int,
    date_time char(20),
    course_id varchar(40),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批人员记录表';

create table sdb_flow
(
    id        varchar(40)  not null,
    name      varchar(100) not null,
    remark    varchar(2000),
    view_id   varchar(40),
    folder_id varchar(40),
    datetime  char(20)      not null,
    person    varchar(100),
    sort      int default 0,
    is_stop   tinyint default 0,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程主体';

create table sdb_course
(
    id               varchar(40)  not null,
    name             varchar(100) not null,
    remark           varchar(2000),
    overtime         int,
    overtime_dispose int,
    parent_course_id varchar(40),
    flow_id          varchar(40),
    is_judge         tinyint,
    is_freedom       tinyint,
    is_countersign   tinyint,
    postion_x        real,
    postion_y        real,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过程表';

create table sdb_course_condition
(
    id         varchar(40) not null,
    course_id  varchar(40) not null,
    type       int,
    value      varchar(8000),
    relation   int,
    field_name varchar(40),
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过程判断表';

create table sdb_course_person
(
    id        varchar(40)  not null,
    name      varchar(100) not null,
    type      int,
    point_id  varchar(40),
    dispose   int,
    date_time datetime,
    course_id varchar(40),
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批人员表';

create table sdb_flow_approve
(
    id                varchar(40) not null,
    flow_instace_id   varchar(40) not null,
    course_history_id varchar(40) not null,
    send_user_id      varchar(40),
    send_user_name    varchar(100),
    accept_user_id    varchar(40),
    accept_user_name  varchar(40),
    send_datetime     datetime,
    read_datetime     datetime,
    approve_datetime  datetime,
    approve_content   varchar(8000),
    state             tinyint,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程审批表';

create table sdb_flow_folder
(
    id        varchar(40) not null,
    name      varchar(100),
    parent_id varchar(40),
    root      varchar(8000),
    remark    varchar(200),
    sort      varchar(100),
    datetime  varchar(20),
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程目录表';

create table sdb_flow_history
(
    id        varchar(40)  not null,
    name      varchar(100) not null,
    remark    varchar(2000),
    view_id   varchar(40),
    folder_id varchar(40),
    date      char(20)      not null,
    person    varchar(100),
    flow_id   varchar(40)   not null,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程主体使用记录表';

create table sdb_flow_instance
(
    id              varchar(40) not null,
    flow_history_id varchar(40),
    start_date      datetime not null,
    user_id         varchar(40),
    user_name       varchar(100),
    title           varchar(1000),
    remark          varchar(8000),
    module_type_id  varchar(40),
    module_id       varchar(40),
    state           tinyint,
    change_api      varchar(1000),
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程实例表';