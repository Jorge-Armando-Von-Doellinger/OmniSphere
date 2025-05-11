SET search_path TO public;
DROP TABLE IF EXISTS tb_user, tb_user_block, tb_old_user, tb_old_user_removed, tb_user_block_removed, tb_users_removed CASCADE;

CREATE TABLE IF NOT EXISTS tb_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_user_removed (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_old_user (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_old_user_table_user FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_old_user_removed (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    old_id BIGINT NOT NULL,
    remove_id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_old_user_removed_user FOREIGN KEY (remove_id) REFERENCES tb_user_removed(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_user_block (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id UUID NOT NULL,
    block_reason VARCHAR(255) NOT NULL,
    unblock_reason VARCHAR(255),
    blocked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    unblocked_at TIMESTAMP,
    CONSTRAINT fk_user_block_user FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_user_block_removed (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    remove_id BIGINT NOT NULL,
    block_id BIGINT NOT NULL,
    block_reason VARCHAR(255),
    unblock_reason VARCHAR(255),
    blocked_at TIMESTAMP,
    unblocked_at TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_block_removed_user FOREIGN KEY (remove_id) REFERENCES tb_user_removed(id) ON DELETE CASCADE
);


