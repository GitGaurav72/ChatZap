CREATE TABLE CHAT_APP.CT_USER
        (
                "ID"         NUMBER(19,0) NOT NULL ENABLE,
                "CREATED_AT" TIMESTAMP (6)               ,
                "EMAIL"      VARCHAR2(255 CHAR)          ,
                "PASSWORD"   VARCHAR2(255 CHAR)          ,
                "PROFILE_PICTURE" RAW(255)               ,
                "STATUS"     VARCHAR2(255 CHAR)              ,
                "UPDATED_AT" TIMESTAMP (6)                   ,
                "USER_NAME"  VARCHAR2(255 CHAR)              ,
                "FIRST_NAME" VARCHAR2(20 BYTE)               ,
                "LAST_NAME"  VARCHAR2(20 BYTE)               ,
                PRIMARY KEY ("ID")
        )

CREATE TABLE "CHAT_APP"."CT_USER_FRIENDS"
        (
                "ID"         NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                         ,
                "CREATED_AT" TIMESTAMP (6) NOT NULL ENABLE                                                                                                                                                                                                                                                        ,
                "STATUS"     VARCHAR2(255 CHAR) NOT NULL ENABLE                                                                                                                                                                                                                                                   ,
                "FRIEND_ID"  NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                         ,
                "USER_ID"    NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                         ,
                CONSTRAINT "CT_USER_FRIEND_ID" FOREIGN KEY ("FRIEND_ID") REFERENCES "CHAT_APP"."CT_USER" ("ID") ENABLE                                                                                                                                                                                            ,
                CONSTRAINT "CT_USER_USER_ID" FOREIGN KEY ("USER_ID") REFERENCES "CHAT_APP"."CT_USER" ("ID") ENABLE
        )
        
        
        

CREATE TABLE "CHAT_APP"."CT_CONVERSATION"
        (
                "CONVERSATION_ID" NUMBER(19,0) NOT NULL ENABLE,
                "CREATED_AT"      TIMESTAMP (6)               ,
                "IS_GROUP"        NUMBER(1,0)                 ,
                "NAME"            VARCHAR2(255 CHAR)          ,
                "UPDATED_AT"      TIMESTAMP (6)               ,
                PRIMARY KEY ("CONVERSATION_ID")
        )

CREATE TABLE "CHAT_APP"."CT_CONVR_PRTCPNTENTITY"
        (
                "ID"              NUMBER(19,0) NOT NULL ENABLE                                                                                             ,
                "JOINED_AT"       TIMESTAMP (6)                                                                                                            ,
                "CONVERSATION_ID" NUMBER(19,0) NOT NULL ENABLE                                                                                             ,
                "USER_ID"         NUMBER(19,0) NOT NULL ENABLE                                                                                             ,
                CONSTRAINT "CT_CONVR_PRTCPNTENTITY_CONVERSATION" FOREIGN KEY ("CONVERSATION_ID") REFERENCES "CHAT_APP"."CT_CONVERSATION" ("CONVERSATION_ID") ENABLE,
                CONSTRAINT "CT_CONVR_PRTCPNTENTITY_USER_ID" FOREIGN KEY ("USER_ID") REFERENCES "CHAT_APP"."CT_USER" ("ID") ENABLE
        )
        
CREATE TABLE "CHAT_APP"."CT_MESSAGE"
        (
                "MESSAGE_ID"      NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                            ,
                "CONTENT"         VARCHAR2(255 CHAR)                                                                                                                                                                                                                                                                      ,
                "IS_READ"         NUMBER(1,0)                                                                                                                                                                                                                                                                             ,
                "TIMESTAMP"       TIMESTAMP (6)                                                                                                                                                                                                                                                                           ,
                "CONVERSATION_ID" NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                            ,
                "RECEIVER_ID"     NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                            ,
                "SENDER_ID"       NUMBER(19,0) NOT NULL ENABLE                                                                                                                                                                                                                                                            ,
                PRIMARY KEY ("MESSAGE_ID")                                                                                                                                                                                                                                                                                ,
                CONSTRAINT "CT_MESSAGE_CONVERSATION_ID" FOREIGN KEY ("CONVERSATION_ID") REFERENCES "CHAT_APP"."CT_CONVERSATION" ("CONVERSATION_ID") ENABLE                                                                                                                                                                ,
                CONSTRAINT "CT_MESSAGE_USER" FOREIGN KEY ("RECEIVER_ID") REFERENCES "CHAT_APP"."CT_USER" ("ID") ENABLE                                                                                                                                                                                                    ,
                CONSTRAINT "CT_MESSAGE_CT_USER" FOREIGN KEY ("SENDER_ID") REFERENCES "CHAT_APP"."CT_USER" ("ID") ENABLE
        )
		
		
		
		
--SEquence
-- CONV_PARTCPNT_SEQ
CREATE SEQUENCE "CHAT_APP"."CONV_PARTCPNT_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50
START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE GLOBAL;
--CONV_SEQ
CREATE SEQUENCE "CHAT_APP"."CONV_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50
START WITH 1001 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE GLOBAL;
--ID_SEQ
CREATE SEQUENCE "CHAT_APP"."ID_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50
START WITH 3001 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE GLOBAL;
--MSG_SEQ
CREATE SEQUENCE "CHAT_APP"."MSG_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50
START WITH 1001 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE GLOBAL;
--USER_FRIENDS_SEQ
CREATE SEQUENCE "CHAT_APP"."USER_FRIENDS_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50
START WITH 1001 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE GLOBAL;



































INSERT INTO CT_USER_FRIENDS (USER_ID, CREATED_AT, FRIEND_ID, STATUS)
SELECT u1.ID AS USER_ID, CURRENT_TIMESTAMP AS CREATED_AT, u2.ID AS FRIEND_ID, 'active' AS STATUS
FROM (
    SELECT ID FROM ct_user
) u1
CROSS JOIN (
    SELECT ID FROM ct_user
) u2
WHERE u1.ID <> u2.ID;

ALTER TABLE CT_USER_FRIENDS DROP CONSTRAINT SYS_C007439;


BEGIN
    FOR i IN 1..462 LOOP
        INSERT INTO CT_CONVERSATION (
            CONVERSATION_ID, CREATED_AT, IS_GROUP, NAME, UPDATED_AT
        ) VALUES (
            CONV_SEQ.NEXTVAL, 
            CURRENT_TIMESTAMP, 
             0, 
            'CONV' || TO_CHAR(LPAD(i, 6, '0')), 
            NULL
        );
    END LOOP;
END;
/




DECLARE
    v_conversation_id NUMBER(19,0);
    v_user_id_1 NUMBER(19,0);
    v_user_id_2 NUMBER(19,0);
BEGIN
    FOR i IN 1..462 LOOP
        -- Fetch a conversation ID that hasn't been used yet
        SELECT CONVERSATION_ID
        INTO v_conversation_id
        FROM CT_CONVERSATION
        WHERE CONVERSATION_ID NOT IN (SELECT CONVERSATION_ID FROM CT_CONVR_PRTCPNTENTITY)
        AND ROWNUM = 1;
        
        -- Fetch two user IDs (pairing them)
        SELECT USER_ID
        INTO v_user_id_1
        FROM CT_USER
        WHERE USER_ID NOT IN (SELECT USER_ID FROM CT_CONVR_PRTCPNTENTITY WHERE CONVERSATION_ID = v_conversation_id)
        AND ROWNUM = 1;

        SELECT USER_ID
        INTO v_user_id_2
        FROM CT_USER
        WHERE USER_ID NOT IN (SELECT USER_ID FROM CT_CONVR_PRTCPNTENTITY WHERE CONVERSATION_ID = v_conversation_id)
        AND USER_ID != v_user_id_1
        AND ROWNUM = 1;

        -- Insert the first user and conversation pair
        INSERT INTO CT_CONVR_PRTCPNTENTITY (ID, JOINED_AT, CONVERSATION_ID, USER_ID)
        VALUES (CONV_PARTCPNT_SEQ.NEXTVAL, CURRENT_TIMESTAMP, v_conversation_id, v_user_id_1);

        -- Insert the second user and conversation pair
        INSERT INTO CT_CONVR_PRTCPNTENTITY (ID, JOINED_AT, CONVERSATION_ID, USER_ID)
        VALUES (CONV_PARTCPNT_SEQ.NEXTVAL, CURRENT_TIMESTAMP, v_conversation_id, v_user_id_2);

    END LOOP;
END;
/





insert Into table CT_CONVR_PRTCPNTENTITY ( ID, JOINED_AT, CONVERSATION_ID, USER_ID )
Values 
(CONV_PARTCPNT_SEQ.NEXTVAL, CURRENT_TIMESTAMP , 10001, 1)
insert Into table CT_CONVR_PRTCPNTENTITY ( ID, JOINED_AT, CONVERSATION_ID, USER_ID )
Values 
(CONV_PARTCPNT_SEQ.NEXTVAL, CURRENT_TIMESTAMP , 10001, 2);


TRUNCATE TABLE CT_CONVR_PRTCPNTENTITY;












SELECT u.id, u.email, u.first_name, u.last_name, u.status, u.user_name, u.profile_picture
            FROM CT_USER u 
            LEFT OUTER JOIN ct_user_friends uf
            ON u.id = uf.frll iend_id 
            WHERE uf.user_id = :userId AND uf.status = :status;
            
            
            SELECT * FROM CT_USER_FRIENDS uf WHERE uf.status = :status AND uf.user_id = :userId;
    




