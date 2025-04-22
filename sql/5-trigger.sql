CREATE OR REPLACE FUNCTION trigger_mouvement_stock()
RETURNS TRIGGER AS $$
DECLARE
    mvt_id INTEGER;
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO mouvement_stock(date_mouvement, est_achat, reference)
        VALUES (NEW.date_achat, TRUE, NEW.id_achat)
        RETURNING id_mouvement INTO mvt_id;

        RAISE NOTICE 'Inserted into mouvement_stock, mvt_id = %', mvt_id;

    ELSIF (TG_OP = 'DELETE') THEN
        DELETE FROM mouvement_stock
        WHERE reference = OLD.id_achat AND est_achat = TRUE;

        RAISE NOTICE 'Deleted from mouvement_stock for achat id %', OLD.id_achat;

    ELSIF (TG_OP = 'UPDATE') THEN
        UPDATE mouvement_stock
        SET date_mouvement = NEW.date_achat
        WHERE reference = OLD.id_achat AND est_achat = TRUE;

        RAISE NOTICE 'Updated mouvement_stock for achat id %', NEW.id_achat;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_mouvement_stock
AFTER INSERT OR UPDATE OR DELETE ON achat
FOR EACH ROW EXECUTE FUNCTION trigger_mouvement_stock();


CREATE OR REPLACE FUNCTION trigger_mouvement_stock_details()
RETURNS TRIGGER AS $$
DECLARE
    mvt_id INTEGER;
BEGIN
    IF (TG_OP = 'INSERT') THEN
        -- Récupérer l'ID du mouvement associé à l'achat
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = NEW.id_achat AND est_achat = TRUE;

        INSERT INTO mouvement_stock_details(entree, sortie, id_produit, id_lot, id_mouvement)
        SELECT l.quantite_lot, 0, l.id_produit, l.id_lot, mvt_id
        FROM lot l
        WHERE l.id_lot = NEW.id_lot;

        RAISE NOTICE 'Inserted into mouvement_stock_details for achat id %, mvt_id = %', NEW.id_achat, mvt_id;

    ELSIF (TG_OP = 'DELETE') THEN
        -- Récupérer l'ID du mouvement associé à l'achat
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = OLD.id_achat AND est_achat = TRUE;

        DELETE FROM mouvement_stock_details
        WHERE id_mouvement = mvt_id AND id_lot = OLD.id_lot;

        RAISE NOTICE 'Deleted from mouvement_stock_details for achat id %, mvt_id = %', OLD.id_achat, mvt_id;

    ELSIF (TG_OP = 'UPDATE') THEN
        -- Récupérer l'ID du mouvement associé à l'achat
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = NEW.id_achat AND est_achat = TRUE;

        DELETE FROM mouvement_stock_details
        WHERE id_mouvement = mvt_id AND id_lot = OLD.id_lot;

        INSERT INTO mouvement_stock_details(entree, sortie, id_produit, id_lot, id_mouvement)
        SELECT l.quantite_lot, 0, l.id_produit, l.id_lot, mvt_id
        FROM lot l
        WHERE l.id_lot = NEW.id_lot;

        RAISE NOTICE 'Updated mouvement_stock_details for achat id %, mvt_id = %', NEW.id_achat, mvt_id;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_mouvement_stock_details
AFTER INSERT OR UPDATE OR DELETE ON achat_details
FOR EACH ROW EXECUTE FUNCTION trigger_mouvement_stock_details();




-- MOUVEMENT DE STOCK DURANT VENTE


CREATE OR REPLACE FUNCTION trigger_vente_mouvement_stock()
RETURNS TRIGGER AS $$
DECLARE
    mvt_id INTEGER;
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO mouvement_stock(date_mouvement, est_achat, reference)
        VALUES (NEW.date_vente, FALSE, NEW.id_vente)
        RETURNING id_mouvement INTO mvt_id;

        RAISE NOTICE 'Inserted into mouvement_stock, mvt_id = %', mvt_id;

    ELSIF (TG_OP = 'DELETE') THEN
        DELETE FROM mouvement_stock
        WHERE reference = OLD.id_vente AND est_achat = FALSE;

        RAISE NOTICE 'Deleted from mouvement_stock for vente id %', OLD.id_vente;

    ELSIF (TG_OP = 'UPDATE') THEN
        UPDATE mouvement_stock
        SET date_mouvement = NEW.date_vente
        WHERE reference = OLD.id_vente AND est_achat = FALSE;

        RAISE NOTICE 'Updated mouvement_stock for vente id %', NEW.id_vente;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_vente_mouvement_stock
AFTER INSERT OR UPDATE OR DELETE ON vente
FOR EACH ROW EXECUTE FUNCTION trigger_vente_mouvement_stock();



CREATE OR REPLACE FUNCTION trigger_vente_mouvement_stock_details()
RETURNS TRIGGER AS $$
DECLARE
    mvt_id INTEGER;
BEGIN
    IF (TG_OP = 'INSERT') THEN
        -- Récupérer l'ID du mouvement associé à la vente
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = NEW.id_vente AND est_achat = FALSE;

        INSERT INTO mouvement_stock_details(entree, sortie, id_produit, id_lot, id_mouvement)
        SELECT 0, NEW.quantite_vendue, l.id_produit, NEW.id_lot, mvt_id
        FROM lot l
        WHERE l.id_lot = NEW.id_lot;

        RAISE NOTICE 'Inserted into mouvement_stock_details for vente id %, mvt_id = %', NEW.id_vente, mvt_id;

    ELSIF (TG_OP = 'DELETE') THEN
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = OLD.id_vente AND est_achat = FALSE;

        DELETE FROM mouvement_stock_details
        WHERE id_mouvement = mvt_id AND id_lot = OLD.id_lot;

        RAISE NOTICE 'Deleted from mouvement_stock_details for vente id %, mvt_id = %', OLD.id_vente, mvt_id;

    ELSIF (TG_OP = 'UPDATE') THEN
        SELECT id_mouvement INTO mvt_id
        FROM mouvement_stock
        WHERE reference = NEW.id_vente AND est_achat = FALSE;

        DELETE FROM mouvement_stock_details
        WHERE id_mouvement = mvt_id AND id_lot = OLD.id_lot;

        INSERT INTO mouvement_stock_details(entree, sortie, id_produit, id_lot, id_mouvement)
        SELECT 0, NEW.quantite_vendue, l.id_produit, NEW.id_lot, mvt_id
        FROM lot l
        WHERE l.id_lot = NEW.id_lot;

        RAISE NOTICE 'Updated mouvement_stock_details for vente id %, mvt_id = %', NEW.id_vente, mvt_id;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trigger_vente_mouvement_stock_details
AFTER INSERT OR UPDATE OR DELETE ON vente_details
FOR EACH ROW EXECUTE FUNCTION trigger_vente_mouvement_stock_details();



CREATE OR REPLACE FUNCTION update_montant_total_achat()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE achat
    SET montant_total = (
        SELECT COALESCE(SUM(ad.prix_achat_unitaire * l.quantite_lot), 0)
        FROM achat_details ad
        JOIN lot l ON ad.id_lot = l.id_lot
        WHERE ad.id_achat = NEW.id_achat
    )
    WHERE id_achat = NEW.id_achat;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_montant_total_achat
AFTER INSERT OR UPDATE OR DELETE ON achat_details
FOR EACH ROW EXECUTE FUNCTION update_montant_total_achat();


CREATE OR REPLACE FUNCTION update_montant_total_vente()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE vente
    SET montant_total = (
        SELECT COALESCE(SUM(vd.prix_vente_unitaire * vd.quantite_vendue), 0)
        FROM vente_details vd
        WHERE vd.id_vente = NEW.id_vente
    )
    WHERE id_vente = NEW.id_vente;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_update_montant_total_vente
AFTER INSERT OR UPDATE OR DELETE ON vente_details
FOR EACH ROW EXECUTE FUNCTION update_montant_total_vente();
