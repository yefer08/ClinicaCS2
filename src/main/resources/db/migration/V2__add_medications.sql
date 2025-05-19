-- Create medications table if it doesn't exist
CREATE TABLE IF NOT EXISTS medications (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    dosage_form VARCHAR(50) NOT NULL,
    concentration VARCHAR(50) NOT NULL,
    indications VARCHAR(1000),
    contraindications VARCHAR(1000)
);

-- Insert initial medications
INSERT INTO medications (id, name, description, dosage_form, concentration, indications, contraindications) VALUES
(1, 'Loratadina', 'Antihistamínico de segunda generación', 'Tableta', '10 mg', 
    'Tratamiento de síntomas alérgicos como rinitis y urticaria', 
    'Hipersensibilidad al medicamento. Precaución en pacientes con insuficiencia hepática'),

(2, 'Acetaminofén', 'Analgésico y antipirético', 'Tableta', '500 mg', 
    'Alivio del dolor leve a moderado y reducción de la fiebre', 
    'Enfermedad hepática grave, hipersensibilidad al paracetamol'),

(3, 'Ibuprofeno', 'Antiinflamatorio no esteroideo (AINE)', 'Tableta', '400 mg', 
    'Dolor, inflamación y fiebre', 
    'Úlcera péptica activa, trastornos de coagulación, último trimestre del embarazo'),

(4, 'Dolex', 'Analgésico y antipirético', 'Tableta', '500 mg', 
    'Alivio temporal del dolor leve a moderado y fiebre', 
    'Hipersensibilidad al acetaminofén, enfermedad hepática grave'),

(5, 'Buscapina', 'Antiespasmódico', 'Tableta', '10 mg', 
    'Dolor y espasmos abdominales, cólicos', 
    'Glaucoma, hipertrofia prostática, miastenia gravis'),

(6, 'Omeprazol', 'Inhibidor de la bomba de protones', 'Cápsula', '20 mg', 
    'Tratamiento de úlceras, reflujo gastroesofágico', 
    'Hipersensibilidad al omeprazol u otros benzimidazoles'),

(7, 'Amoxicilina', 'Antibiótico betalactámico', 'Cápsula', '500 mg', 
    'Tratamiento de infecciones bacterianas', 
    'Alergia a penicilinas, mononucleosis infecciosa'),

(8, 'Cetirizina', 'Antihistamínico', 'Tableta', '10 mg', 
    'Alivio de síntomas alérgicos', 
    'Insuficiencia renal grave, hipersensibilidad a la cetirizina'),

(9, 'Naproxeno', 'Antiinflamatorio no esteroideo', 'Tableta', '250 mg', 
    'Dolor, artritis, tendinitis', 
    'Úlcera péptica, insuficiencia renal grave, embarazo'),

(10, 'Metformina', 'Antidiabético oral', 'Tableta', '850 mg', 
    'Control de glucosa en diabetes tipo 2', 
    'Insuficiencia renal, acidosis láctica, enfermedad hepática grave'); 