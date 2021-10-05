/* Create Table for host info*/
CREATE TABLE IF NOT EXISTS PUBLIC.host_info (

                                                id               INT PRIMARY KEY,
                                                hostname         VARCHAR NOT NULL,
                                                cpu_number       INT NOT NULL,
                                                cpu_archetecture VARCHAR NOT NULL,
                                                cpu_model        VARCHAR NOT NULL,
                                                cpu_mhz          DOUBLE PRECISION NOT NULL,
                                                L2_cache         INT NOT NULL,
                                                total_mem        INT NOT NULL,
                                                timestamp        VARCHAR NOT NULL
);
/* Create Table for host usage*/
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp"    TIMESTAMP NOT NULL,
    host_id        SERIAL NOT NULL,
    memory_free    VARCHAR NOT NULL,
    cpu_idle       INT NOT NULL,
    cpu_kernal     INT NOT NULL,
    disk_io        VARCHAR NOT NULL,
    disk_available INT NOT NULL
);
