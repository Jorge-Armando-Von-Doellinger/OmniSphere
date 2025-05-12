SET search_path TO public;

DROP TRIGGER IF EXISTS trg_user_audit ON tb_user;

CREATE OR REPLACE FUNCTION fn_user_audit()
RETURNS TRIGGER AS
$$
DECLARE
    v_user_delete_id BIGINT;
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO tb_user_removed (user_id, username, email, password, created_at, updated_at)
        VALUES (OLD.id, OLD.username, OLD.email, OLD.password, OLD.created_at, OLD.updated_at)
        RETURNING id INTO v_user_delete_id;

        INSERT INTO tb_user_block_removed (remove_id, block_id, block_reason, unblock_reason, blocked_at, unblocked_at)
        SELECT v_user_delete_id, b.id, b.block_reason, b.unblock_reason, b.blocked_at, b.unblocked_at
        FROM tb_user_block b
        WHERE b.user_id = OLD.id;

        INSERT INTO tb_old_user_removed (old_id, remove_id, username, email, password, created_at, updated_at)
        SELECT u.id, v_user_delete_id, u.username, u.email, u.password, u.created_at, u.updated_at
        FROM tb_old_user u
        WHERE u.user_id = OLD.id;

        RETURN OLD;

    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO tb_old_user (user_id, username, email, password, created_at, updated_at)
        VALUES (OLD.id, OLD.username, OLD.email, OLD.password, OLD.created_at, OLD.updated_at);


        RETURN NEW;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_user_audit
BEFORE UPDATE OR DELETE ON tb_user
FOR EACH ROW
EXECUTE FUNCTION fn_user_audit();
