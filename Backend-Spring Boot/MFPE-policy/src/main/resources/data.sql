INSERT INTO hospital(provider_id, hospital_name, location, policy_id ) VALUES (1011, 'Apollo hospital','Indore',101);
INSERT INTO hospital(provider_id, hospital_name, location, policy_id ) VALUES (1012, 'AIIMS hospital', 'Delhi',102);
INSERT INTO hospital(provider_id, hospital_name, location, policy_id ) VALUES (1013, 'Kokilaben hospital', 'Mumbai',103);
INSERT INTO hospital(provider_id, hospital_name, location, policy_id ) VALUES (1014, 'Civil hospital', 'Ahemdabad',104);

INSERT INTO policy(policy_id,benefits,premium,tenure) VALUES (101,10000,10000,10000);

INSERT INTO member_policy(member_id,cap_amount,name,policy_id,premium_due_date,subs_date) VALUES (1,30,'dheeraj',101,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY') );

