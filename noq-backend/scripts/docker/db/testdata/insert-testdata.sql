INSERT INTO HOST (HOST_ID, NAME, ADDRESS_1, ADDRESS_2, CITY, ADDRESS_POSTCODE, EMAIL, COUNT_OF_AVAILABLE_PLACES, TOTAL_PLACES, FACILITIES, TARGET_AUDIENCE)
VALUES
    (gen_random_uuid(), 'Boende Härbärge 1', 'Address 1', 'Street 1', 'stockholm', '111 20', 'host1@example.com', 10, 50, 'Boende, Mat, Dusch, Tvätt, Samtalsstöd', 'Vuxna hemlösa, inklusive personer med funktionsnedsättning och psykisk ohälsa'),
    (gen_random_uuid(), 'Mat Hjälp Härbärge 2', 'Address 2', 'Street 2', 'stockholm', '111 22', 'host2@example.com', 15, 40, 'Mat, Dusch, Tvätt, Samtalsstöd', 'Vuxna hemlösa'),
    (gen_random_uuid(), 'Säker Plats Härbärge 3', 'Address 3', 'Street 3', 'stockholm', '111 24', 'host3@example.com', 20, 60, 'Boende, Tvätt, Samtalsstöd', 'Vuxna hemlösa kvinnor'),
    (gen_random_uuid(), 'Hjälp Hand Härbärge 4', 'Address 4', 'Street 4', 'stockholm', '111 26', 'host4@example.com', 8, 30, 'Boende, Mat, Dusch, Tvätt', 'Vuxna hemlösa män'),
    (gen_random_uuid(), 'Stöd Linje Härbärge 5', 'Address 5', 'Street 5', 'stockholm', '111 28', 'host5@example.com', 12, 45, 'Boende, Dusch, Tvätt, Samtalsstöd', 'EU endast');
---
INSERT INTO HOST (
    HOST_ID, NAME, ADDRESS_1, ADDRESS_2, CITY, ADDRESS_POSTCODE, EMAIL,
    COUNT_OF_AVAILABLE_PLACES, TOTAL_PLACES, FACILITIES, TARGET_AUDIENCE
) VALUES (
    '550e8400-e29b-41d4-a716-446655440000', 'Boende Härbärge 1', '123 Street', 'Apt 101', 'City1', '12345',
    'host1@example.com', 5, 10, 'WiFi, Parking', 'Families'
);

INSERT INTO HOST (
    HOST_ID, NAME, ADDRESS_1, ADDRESS_2, CITY, ADDRESS_POSTCODE, EMAIL,
    COUNT_OF_AVAILABLE_PLACES, TOTAL_PLACES, FACILITIES, TARGET_AUDIENCE
) VALUES (
    '550e8400-e29b-41d4-a716-446655440001', 'Säker Plats Härbärge', 'Vintergatan', 'Suite 202', 'City2', '67890',
    'host2@example.com', 3, 8, 'Laundry, Kitchen', 'Single Adults'
);

INSERT INTO USERS (
    USER_ID, FIRST_NAME, LAST_NAME, GENDER, UNOKOD, DATE_OF_BIRTH, PHONE, EMAIL,
    KOMMUN, PLACE, PAYING_CHARGES, CASE_MANAGER, UNDERSTANDS_SWEDISH,
    LANGUAGES_KNOWN, IS_FLAGGED, REASONS_FOR_HOMELESSNESS, GOALS, COMMENTS_AND_REMARKS
) VALUES (
    '550e8400-e29b-41d4-a716-446655440010', 'John', 'Doe', 'Male', 'ABC123', '1990-05-15',
    '1234567890', 'john@example.com', 'Kommun1', 'Place1', true, 'CaseManager1', true,
    'English, Spanish', false, 'Financial issues', 'Find stable job and housing', 'No specific comments'
);

INSERT INTO USERS (
    USER_ID, FIRST_NAME, LAST_NAME, GENDER, UNOKOD, DATE_OF_BIRTH, PHONE, EMAIL,
    KOMMUN, PLACE, PAYING_CHARGES, CASE_MANAGER, UNDERSTANDS_SWEDISH,
    LANGUAGES_KNOWN, IS_FLAGGED, REASONS_FOR_HOMELESSNESS, GOALS, COMMENTS_AND_REMARKS
) VALUES (
    '550e8400-e29b-41d4-a716-446655440011', 'Alice', 'Smith', 'Female', 'DEF456', '1985-09-20',
    '9876543210', 'alice@example.com', 'Kommun2', 'Place2', false, 'CaseManager2', false,
    'Swedish, French', true, 'Family issues', 'Improve education and get a job', 'Has some medical requirements'
);

INSERT INTO BOOKING (
    BOOKING_ID, START_DATE_TIME, END_DATE_TIME, CASE_MANAGER_NAME, CASE_MANAGER_EMAIL,
    HOST_ID, USER_ID, BOOKING_STATUS, APPROVAL_STATUS
) VALUES (
    '550e8400-e29b-41d4-a716-446655440100', '2023-12-01 08:00:00', '2023-12-07 12:00:00',
    'Manager1', 'manager1@example.com', '550e8400-e29b-41d4-a716-446655440000',
    '550e8400-e29b-41d4-a716-446655440010', '0', '1'
);

INSERT INTO BOOKING (
    BOOKING_ID, START_DATE_TIME, END_DATE_TIME, CASE_MANAGER_NAME, CASE_MANAGER_EMAIL,
    HOST_ID, USER_ID, BOOKING_STATUS, APPROVAL_STATUS
) VALUES (
    '550e8400-e29b-41d4-a716-446655440101', '2023-11-25 10:00:00', '2023-11-30 15:00:00',
    'Manager2', 'manager2@example.com', '550e8400-e29b-41d4-a716-446655440001',
    '550e8400-e29b-41d4-a716-446655440011', '0', '1'
);
