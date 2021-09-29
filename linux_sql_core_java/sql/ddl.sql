CREATE TABLE IF NOT EXISTS PUBLIC.host_info (
                                                id               SERIAL NOT NULL,
                                                hostname         VARCHAR NOT NULL,
    -- add more columns
    -- primary key constraint
    -- unique hostname constraint
                                                cpu_number       INT NOT NULL,
                                                cpu_archetecture VARCHAR NOT NULL,
                                                cpu_model        VARCHAR NOT NULL,
                                                cpu_mhz          DOUBLE PRECISION NOT NULL,
                                                L2_cache         INT NOT NULL,
                                                total_mem        INT NOT NULL,
                                                timestamp        VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp"    TIMESTAMP NOT NULL,
    host_id        SERIAL NOT NULL,
    memory_free    VARCHAR NOT NULL,
    cpu_idle       INT NOT NULL,
    cpu_kernal     INT NOT NULL,
    disk_io        VARCHAR NOT NULL,
    disk_available INT NOT NULL
    -- add more columns
    -- add foreign key constraint
);
