DROP PROCEDURE IF EXISTS create_fulltext_index $$
CREATE PROCEDURE create_fulltext_index()
BEGIN
    SET @index_exists = (SELECT COUNT(*) FROM INFORMATION_SCHEMA.STATISTICS
                         WHERE table_schema = 'moldunity_test' AND table_name = 'furniture' AND index_name = 'fulltext_index');

    IF @index_exists = 0 THEN
        ALTER TABLE furniture ADD FULLTEXT INDEX fulltext_index (title, description);
    END IF;

    SET @index_exists = (SELECT COUNT(*) FROM INFORMATION_SCHEMA.STATISTICS
                         WHERE table_schema = 'moldunity_test' AND table_name = 'immobile' AND index_name = 'fulltext_index');

    IF @index_exists = 0 THEN
        ALTER TABLE immobile ADD FULLTEXT INDEX fulltext_index (title, description);
    END IF;
END $$

CALL create_fulltext_index();

$$

















































