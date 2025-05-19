-- Add medication_id column to medicine_entity table
ALTER TABLE medicine_entity
ADD COLUMN medication_id INT;

-- Add foreign key constraint
ALTER TABLE medicine_entity
ADD CONSTRAINT fk_medicine_medication
FOREIGN KEY (medication_id)
REFERENCES medications(id);

-- Make the column not nullable after adding the constraint
ALTER TABLE medicine_entity
ALTER COLUMN medication_id SET NOT NULL; 