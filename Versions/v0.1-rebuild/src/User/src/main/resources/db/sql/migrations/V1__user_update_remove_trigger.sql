SET search_path TO public;

CREATE OR REPLACE FUNCTION fn_user_update_audit()
RETURNS TRIGGER AS
$$
DECLARE
    v_user_delete_id LONG;
BEGIN
        INSERT INTO tb_users_deleted (user_id, username, email, password, created_at, deleted_at)
        VALUES (OLD.user_id, OLD.username, OLD.email, OLD.password, OLD.created_at, now());
        RETURNING user_delete_id INTO v_user_delete_id;

        INSERT INTO tb_user_update_removed (update_id, user_delete_id, username, email, password, created_at, updated_at)
        VAlUES ()
        RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_user_audit ON tb_user;

CREATE TRIGGER trg_user_update_audit
BEFORE DELETE ON tb_user_update_removed
FOR EACH ROW
EXECUTE FUNCTION fn_user_update_audit();

