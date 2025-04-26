
CREATE OR REPLACE FUNCTION fn_user_audit()
RETURNS TRIGGER AS
$$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO tb_users_deleted (user_id, username, email, password)
        VALUES (OLD.user_id, OLD.username, OLD.email, OLD.password);

        INSERT INTO tb_user_block_removed (block_id, user_id, block_reason, unblock_reason, blocked_at, unblocked_at, deleted_at)
        SELECT b.user_block_id, b.user_id, b.block_reason, b.unblock_reason, b.blocked_at, b.unblocked_at, now()
        FROM tb_user_block b
        WHERE b.user_id = OLD.user_id;

        DELETE FROM tb_user_block WHERE user_id = OLD.user_id;

        RETURN OLD;

    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO update_user_table (user_id, username, email, password, updated_at)
        VALUES (NEW.user_id, NEW.username, NEW.email, NEW.password, now());

        RETURN NEW;

    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO update_user_table (user_id, username, email, password, updated_at)
        VALUES (NEW.user_id, NEW.username, NEW.email, NEW.password, now());

        RETURN NEW;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_user_audit ON tb_user;

CREATE TRIGGER trg_user_audit
AFTER INSERT OR UPDATE OR DELETE ON tb_user
FOR EACH ROW
EXECUTE FUNCTION fn_user_audit();
