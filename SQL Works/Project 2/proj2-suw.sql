------------------------ #1------------------------

CREATE OR REPLACE FUNCTION get_height (firstn VARCHAR, lastn VARCHAR) RETURNS float AS $$
DECLARE
   height float := 0.0;
BEGIN

select into height (P.h_feet *12*2.54 + P.h_inches *2.54) 
from players as P 
where P.firstname=$1 and P.lastname=$2;

--check for null result
if height IS NULL
THEN
return 0.0;

ELSE
return height;

END IF;

END;
$$ LANGUAGE plpgsql;


---------------#2---------------


CREATE OR REPLACE FUNCTION get_coach_rank (year INTEGER,firstn VARCHAR, lastn VARCHAR) RETURNS INTEGER AS $$
DECLARE
   rank INTEGER := 0;
   offset INTEGER := 0;
   tempValue INTEGER :=NULL;
   r record;
BEGIN

FOR r IN SELECT (CS.season_win + CS.playoff_win - CS.season_loss - CS.playoff_loss) as netWin,CS.firstname, CS.lastname , CS.year
	FROM coaches_season CS
	WHERE CS.year = $1
	ORDER BY netwin DESC
    LOOP
    
	IF r.netWin = tempValue then
		offset := offset +1;
	ELSE
		rank := rank + offset + 1;
		offset := 0;
		tempValue := r.netWin;
	END IF;

        IF r. lastname = $3 AND r.firstname = $2 THEN
		RETURN rank;
	END IF;
    END LOOP;

--not in DB    
RETURN -1;

END;
$$ LANGUAGE plpgsql;

--1. Find all the coaches who have coached exactly TWO teams. List their first names followed by their last names;
SELECT lastname, firstname  FROM coaches_season
GROUP BY lastname, firstname
HAVING COUNT(DISTINCT tid) = 2;

--2. Find all the players who played in a Boston team or a Denver team. List their first names only. 
SELECT firstname
FROM player_rs
WHERE tid = 'BOS' OR tid = 'DEN';

--3. Find those who happened to be a coach and a player in the same team in the same season. List their first names, last names, the team where this happened, and the year(s) when this happened.
SELECT DISTINCT P.firstname, P.lastname
FROM players P, coaches_season CS
WHERE CS.year BETWEEN P.first_season AND P.last_season AND CS.cid = P.ilkid;

--4. Find the average height (in centimeters) of each team coached by Phil Jackson in each season. Print the team name, season and the average height value (in centimeters), and sort the results by the average height.
SELECT CS.tid, CS.yr_order, AVG((P.h_feet*30.4) + (P.h_inches*2.54)) AS Average
FROM coaches_season CS, player_rs PR, players P
WHERE CS.firstname = 'Phil' AND CS.lastname = 'Jackson' AND CS.tid = PR.tid AND PR.ilkid = P.ilkid
GROUP BY CS.tid, CS.yr_order
ORDER BY Average DESC;

--5. Find the coach(es) (first name and last name) who have coached the largest number of players in year 2003.
SELECT firstname, lastname
FROM (SELECT CS.firstname, CS.lastname, COUNT(PR.ilkid)
	  FROM coaches_season CS, player_rs PR
	  WHERE CS.year = 2003 AND PR.year = 2003 AND CS.tid = PR.tid
	  GROUP BY CS.firstname, CS.lastname
	  HAVING COUNT(PR.ilkid) > 1
	  ORDER BY COUNT DESC LIMIT 1) AS T1;

--6. Find the coaches who coached in ALL leagues. List their first names followed by their last names. 
SELECT DISTINCT CS.firstname, CS.lastname
FROM coaches_season CS, player_rs PR
WHERE CS.tid = PR.tid AND league = 'N' OR league = 'A';

--7. Find those who happened to be a coach and a player in the same season, but in different teams. List their first names, last names, the season and the teams this happened. 
SELECT CS.firstname, CS.lastname, CS.year, T.name
FROM coaches_season CS, teams T, player_rs PR
WHERE CS.year = PR.year AND
	UPPER(CS.tid) != UPPER(PR.tid) AND
	UPPER(CS.firstname) = UPPER(PR.firstname) AND
	UPPER(CS.lastname) = UPPER(PR.lastname) AND
	UPPER(T.tid) = UPPER(CS.tid)

--8
SELECT firstname, lastname, pts
FROM player_rs_career
WHERE pts > (SELECT pts FROM player_rs_career WHERE firstname = 'Michael' AND lastname = 'Jordan');

--9
SELECT firstname, lastname
FROM (SELECT firstname, lastname, COUNT(season_win/(season_win + season_loss))
FROM coaches_season
GROUP BY firstname, lastname
HAVING COUNT(season_win/(season_win + season_loss)) > 1
ORDER BY COUNT DESC LIMIT 1) AS T1;

--10
SELECT T1.draft_from, T1.count            
FROM (SELECT draft_from, COUNT(draft_from)
     FROM draft
     GROUP BY draft_from
     HAVING COUNT(draft_from) > 1
     ORDER BY COUNT DESC LIMIT 2) AS T1
     ORDER BY COUNT LIMIT 1;