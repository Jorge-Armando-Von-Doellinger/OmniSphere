SET search_path TO public;
DROP TABLE IF EXISTS tb_user, tb_user_block, update_user_table, tb_user_update_removed, tb_user_block_removed, tb_users_deleted CASCADE;

CREATE TABLE IF NOT EXISTS tb_user (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_user_block (
    block_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    block_reason VARCHAR(255),
    unblock_reason VARCHAR(255),
    blocked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    unblocked_at TIMESTAMP,
    CONSTRAINT fk_user_block_user FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);

CREATE TABLE IF NOT EXISTS tb_user_update (
    update_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_update_user_table_user FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);

CREATE TABLE IF NOT EXISTS tb_users_deleted (
    delete_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_user_update_removed (
    delete_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    update_id BIGINT NOT NULL,
    user_delete_id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_update_removed_user FOREIGN KEY (user_delete_id) REFERENCES tb_users_deleted(delete_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_user_block_removed (
    delete_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    block_id BIGINT NOT NULL,
    user_delete_id BIGINT NOT NULL,
    block_reason VARCHAR(255),
    unblock_reason VARCHAR(255),
    blocked_at TIMESTAMP,
    unblocked_at TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_block_removed_user FOREIGN KEY (user_delete_id) REFERENCES tb_users_deleted(delete_id) ON DELETE CASCADE
);


