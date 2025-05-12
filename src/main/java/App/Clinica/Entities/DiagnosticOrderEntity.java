package App.Clinica.Entities;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
@DiscriminatorValue("DIAGNOSTIC")
public class DiagnosticOrderEntity extends OrderEntity {

    @OneToMany(mappedBy = "diagnosticOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiagnosticHelpEntity> exams;

    public DiagnosticOrderEntity() {
        super();
    }

    public DiagnosticOrderEntity(String numeroOrden, PatientEntity patient, UserEntity doctor, LocalDateTime fecha) {
        super(numeroOrden, patient, doctor, fecha);
    }

    public void addExam(DiagnosticHelpEntity exam) {
        validateItemNumber(exam.getItemNumber());
        exam.setDiagnosticOrder(this);
        exams.add(exam);
    }

    public List<DiagnosticHelpEntity> getExams() {
        return exams;
    }

    public void setExams(List<DiagnosticHelpEntity> exams) {
        this.exams = exams;
    }
    
    
}
