SET search_path TO public;

CREATE OR REPLACE FUNCTION fn_user_audit()
RETURNS TRIGGER AS
$$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO tb_users_deleted (user_id, username, email, password, created_at, deleted_at)
        VALUES (OLD.user_id, OLD.username, OLD.email, OLD.password, OLD.created_at, now());
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO tb_user_update (user_id, username, email, password, created_at, updated_at)
        VALUES (OLD.user_id, OLD.username, OLD.email, OLD.password, OLD.created_at, now());
        RETURN NEW;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_user_audit ON tb_user;

CREATE TRIGGER trg_user_audit
AFTER INSERT OR UPDATE OR DELETE ON tb_user
FOR EACH ROW
EXECUTE FUNCTION fn_user_audit();
