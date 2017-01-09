-------------------------------------------------------------------------------------
------------------------------ V   I   N   Y   L ------------------------------------
-------------------------------------------------------------------------------------
-- CREATE MAIN VINYL TABLE
CREATE TABLE VINYL (ID INT AUTO_INCREMENT,  TITLE VARCHAR(100) NOT NULL,  ARTIST VARCHAR(100) NOT NULL,  EDITED VARCHAR(4), GENRE1_ID INT, GENRE2_ID INT, IMAGE_FRONT BLOB, IMAGE_BACK BLOB);

-- ADD PRIMARY KEY VINYL TABLE
ALTER TABLE VINYL ADD PRIMARY KEY (TITLE, ARTIST);

-------------------------------------------------------------------------------------
------------------------------ G   E   N   R   E ------------------------------------
-------------------------------------------------------------------------------------
-- CREATE GENRES TABLE 
CREATE TABLE GENRE (ID INT AUTO_INCREMENT, NAME VARCHAR(50));

-- ADD PRIMARY KEY GENRES TABLE
ALTER TABLE GENRE ADD PRIMARY KEY (ID);

-- ADD FOREIGN KEY GENRE
ALTER TABLE VINYL ADD FOREIGN KEY (GENRE1_ID) REFERENCES GENRE(ID);

-- ADD FOREIGN KEY GENRE
ALTER TABLE VINYL ADD FOREIGN KEY (GENRE2_ID) REFERENCES GENRE(ID);


COMMIT;


