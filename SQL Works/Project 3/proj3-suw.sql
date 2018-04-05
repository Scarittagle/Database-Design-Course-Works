--  Before you try the code in this file from the psql client, you need to create your database NBA-xxx and copy data from NBA to it. For example,
--  createdb NBA-tuy
--  pg_dump -t player_rs_career NBA | psql NBA-tuy
--  Note that those should be done under the Linux console. Then you can log into NBA-xxx and try the following scripts.

--  The following line only needs to be executed once before you do anything at all with pgplsql functions
CREATE LANGUAGE 'plpgsql';


-- function 1 declaration

CREATE OR REPLACE FUNCTION fibonacci (lastN INTEGER) 
RETURNS int AS $$
BEGIN
    IF lastN < 0 OR lastN > 1000 THEN
        RETURN -1;
    END IF;
    IF lastN < 2 THEN
        RETURN lastN;
    END IF;
    RETURN fibonacci(lastN - 2) + fibonacci(lastN - 1);
END;
$$ LANGUAGE plpgsql;


-- function 2 declaration

CREATE OR REPLACE FUNCTION player_height_rank (firstname VARCHAR, lastname VARCHAR) 
RETURNS int AS $$   
DECLARE rankPosition INTEGER := 0;
BEGIN
    SELECT SUBDIVISION.RANK INTO rankPosition
    FROM (SELECT RANK() OVER(ORDER BY h_feet DESC, h_inches DESC) AS RANK, P.firstname, P.lastname, P.h_feet, P.h_inches FROM Players P)
    AS SUBDIVISION
    WHERE SUBDIVISION.firstname = $1 AND SUBDIVISION.lastname = $2;
    IF rankPosition IS NULL
        THEN RETURN 0;
    ELSE
        RETURN rankPosition;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- executing the above functions
select * from fibonacci(20);
select * from player_height_rank('Reggie', 'Miller');







