INSERT INTO DEPARTMENTS (FULL_NAME, SHORT_CUT, CREATION_DATE)
VALUES ('Research and Development', 'R&D', '2020-01-15'),
       ('Preparation', 'D1', '2020-01-15'),
       ('Assembling', 'D2', '2020-01-15'),
       ('Finishing', 'D3', '2020-01-15');

INSERT INTO EMPLOYEES (NAME, SURNAME, JOB_POSITION, DATE_OF_BIRTH, HIRE_DATE, SHIFT, CONTACT_NUMBER, EMAIL, SALARY,
                       PERFORMANCE_RATING, ASSESMENT_DATE)
VALUES ('Anna', 'Nowak', 'MANAGER', '1978-11-23', '2018-03-05', 2, '987654321', 'anna.nowak@example.com', 9500.00, 5,
        '2023-02-20'),
       ('Piotr', 'Wiśniewski', 'MECHANIC', '1990-07-30', '2021-06-15', 3, '564738291', 'piotr.wisniewski@example.com',
        6000.00, 3, '2023-03-10'),
       ('Jan', 'Kowalski', 'AUTOMATION', '1985-05-15', '2020-01-10', 1, '123456789', 'jan.kowalski@example.com',
        7500.00, 4, '2023-01-15'),
       ('Katarzyna', 'Wójcik', 'SUBCONTRACTOR', '1982-02-14', '2019-09-01', 1, '112233445',
        'katarzyna.wojcik@example.com', 7000.00, 4, '2023-04-05');

INSERT INTO MACHINES (NAME, MANUFACTURER, PRODUCTION_DATE, LAST_MAINTENANCE_DATE, ENERGY_CONSUMPTION, department_id)
VALUES ('Lathe Machine', 'XYZ Corp', '2015-06-15', '2023-01-10', 1500.50, 1),
       ('CNC Machine', 'ABC Manufacturing', '2018-09-20', '2023-03-05', 2000.75, 2),
       ('3D Printer', 'PrintTech', '2020-11-30', '2023-02-15', 500.25, 3),
       ('Welding Machine', 'WeldMaster', '2017-04-10', '2023-04-20', 1200.00, 1),
       ('Drill Press', 'DrillWorks', '2019-08-25', '2023-05-10', 800.60, 2);

INSERT INTO BREAKDOWNS (EVENT_DESCRIPTION, START_DATE, END_DATE, SEVERITY, CAUSE, USED_PARTS, COMMENTS, machine_id)
VALUES ('Hydraulic system failure', '2024-01-15', '2024-01-20', 'HIGH', 'Leak in hydraulic line', 'Hydraulic hose, Oil',
        'Replaced hose and refilled oil', 1),
       ('Electrical short circuit', '2024-03-10', '2024-03-12', 'MEDIUM', 'Worn insulation on wiring',
        'Wiring, Insulation tape', 'Replaced wiring and insulated connections', 2),
       ('Bearing overheating', '2024-05-05', '2024-05-06', 'LOW', 'Lack of lubrication', 'Lubricant, Bearing',
        'Applied lubricant and monitored temperature', 3),
       ('Pump failure', '2024-06-18', '2024-06-20', 'HIGH', 'Internal leakage', 'Pump, Seals',
        'Replaced pump and seals', 1),
       ('Software glitch', '2024-08-22', '2024-08-23', 'MEDIUM', 'Firmware bug', 'Firmware update',
        'Updated firmware and tested functionality', 4),
       ('Valve malfunction', '2024-09-15', '2024-09-16', 'HIGH', 'Blocked valve', 'Valve, Cleaning solution',
        'Cleaned and replaced valve', 2),
       ('Sensor failure', '2024-10-10', '2024-10-11', 'LOW', 'Faulty sensor', 'Sensor',
        'Replaced sensor and recalibrated system', 5);

INSERT INTO BUDGET (BUDGET_MONTH, BUDGET_YEAR, BUDGET_AMOUNT_PLN)
VALUES (1, 2024, 1560000.00),
       (2, 2024, 1507005.50),
       (3, 2024, 2142000.75),
       (4, 2024, 1736000.00),
       (5, 2024, 1894000.25),
       (6, 2024, 1100320.00),
       (7, 2024, 1634000.50),
       (8, 2024, 1704300.75),
       (9, 2024, 183000.00),
       (10, 2024, 1290030.25),
       (11, 2024, 2021000.00),
       (12, 2024, 2100340.50);

INSERT INTO BREAKDOWN_EMPLOYEE (breakdown_id, employee_id)
VALUES (1, 1),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (5, 3),
       (5, 4),
       (6, 1),
       (7, 2),
       (7, 4);

INSERT INTO ROLES (ID, NAME)
VALUES (1, 'USER'),
       (2, 'ADMIN');
INSERT INTO USERS (EMAIL, PASSWORD, USERNAME)
VALUES ('admin@admin.pl', '$2a$10$BTOuwspZHJWHhz4PabaOFe3ejBFraRUkWWGJn3MJPizhOG7ZRNsMG', 'admin');
INSERT INTO USERS_ROLES
VALUES (1, 1), (2, 1);
INSERT INTO carousel_items (IMAGE_URL, TITLE, LINK, IS_VISIBLE)
VALUES ('https://cdn.pixabay.com/photo/2021/11/22/22/53/inventory-6817561_1280.png', 'Analizy i Raporty', '/analises', TRUE),
       ('https://cdn.pixabay.com/photo/2022/06/25/06/43/students-7282940_1280.png', 'Szkolenia i Dokumentacja', '/trainings', TRUE),
       ('https://cdn.pixabay.com/photo/2012/04/02/16/06/error-24842_1280.png', 'Awarie', '/breakdowns', TRUE),
       ('https://cdn.pixabay.com/photo/2016/07/31/20/54/calendar-1559935_1280.png', 'Harmonogramy', '/schedules', TRUE),
       ('https://cdn.pixabay.com/photo/2017/11/17/14/02/sign-2957512_1280.png', 'BHP', '/safety', TRUE),
       ('https://cdn.pixabay.com/photo/2013/07/13/11/27/ekg-158177_1280.png', 'Monitorowanie', '/monitoring', TRUE);

INSERT INTO FACTORIES (NAME, DESCRIPTION, CITY, CREATION_DATE, status, ACTIVITY_SCOPE) VALUES
    ('Fabryka Bielsko-Biała', 'Fabryka silników', 'Bielsko-Biała', CURRENT_DATE, 'ACTIVE', 'engines'),
    ('Fabryka Tychy', 'Fabryka pojazdów', 'Tychy', CURRENT_DATE, 'ACTIVE', 'vehicles'),
    ('Fabryka Warszawa', 'Fabryka części', 'Warsaw', CURRENT_DATE, 'ACTIVE', 'parts');

INSERT INTO CAR_MODEL (factory_id, model_name, model_type) VALUES
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Bielsko-Biała'), '1.6', 'Gas'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Bielsko-Biała'), '1.4 T-Jet', 'Turbodiesel'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Bielsko-Biała'), '1.4 Fire', 'Gas'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Tychy'), 'Panda', 'Fiat'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Bielsko-Biała'), '1.2 Fire', 'Gas'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Tychy'), 'Ypsilon', 'Lancia'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Tychy'), '500', 'Fiat'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Tychy'), '500', 'Abarth'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Warszawa'), 'Alternator', 'Electrical'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Warszawa'), 'Radiator', 'Cooling System'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Warszawa'), 'Brake Pads', 'Brake System'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Warszawa'), 'Suspension Spring', 'Suspension System'),
      ((SELECT id FROM FACTORIES WHERE name = 'Fabryka Warszawa'), 'Oil Filter', 'Engine System');