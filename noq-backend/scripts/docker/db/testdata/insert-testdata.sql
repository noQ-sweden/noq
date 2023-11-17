INSERT INTO HOST (HOST_ID, NAME, ADDRESS_1, ADDRESS_2, CITY, ADDRESS_POSTCODE, EMAIL, COUNT_OF_AVAILABLE_PLACES, TOTAL_PLACES, FACILITIES, TARGET_AUDIENCE)
VALUES
    (gen_random_uuid(), 'Boende Härbärge 1', 'Address 1', 'Street 1', 'Stockholm', '111 20', 'host1@example.com', 10, 50, 'Boende, Mat, Dusch, Tvätt, Samtalsstöd', 'Vuxna hemlösa, inklusive personer med funktionsnedsättning och psykisk ohälsa'),
    (gen_random_uuid(), 'Mat Hjälp Härbärge 2', 'Address 2', 'Street 2', 'Stockholm', '111 22', 'host2@example.com', 15, 40, 'Mat, Dusch, Tvätt, Samtalsstöd', 'Vuxna hemlösa'),
    (gen_random_uuid(), 'Säker Plats Härbärge 3', 'Address 3', 'Street 3', 'Stockholm', '111 24', 'host3@example.com', 20, 60, 'Boende, Tvätt, Samtalsstöd', 'Vuxna hemlösa kvinnor'),
    (gen_random_uuid(), 'Hjälp Hand Härbärge 4', 'Address 4', 'Street 4', 'Stockholm', '111 26', 'host4@example.com', 8, 30, 'Boende, Mat, Dusch, Tvätt', 'Vuxna hemlösa män'),
    (gen_random_uuid(), 'Stöd Linje Härbärge 5', 'Address 5', 'Street 5', 'Stockholm', '111 28', 'host5@example.com', 12, 45, 'Boende, Dusch, Tvätt, Samtalsstöd', 'EU endast');
