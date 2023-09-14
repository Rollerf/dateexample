package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private LocalDateTime fechaRegistro;

    public Integer getEdad() {
        LocalDate fechaActual = LocalDate.now();
        Period period = Period.between(fechaNacimiento, fechaActual);

        return period.getYears();
    }

    public Long getDiasParaCumpleanhos() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaCumpleanhos = fechaNacimiento.withYear(fechaActual.getYear());

        if (fechaCumpleanhos.isBefore(fechaActual) || fechaCumpleanhos.isEqual(fechaActual)) {
            fechaCumpleanhos = fechaCumpleanhos.plusYears(1);
        }

        long daysUntilNextBirthday = ChronoUnit.DAYS.between(fechaActual, fechaCumpleanhos);

        return daysUntilNextBirthday;
    }

    public LocalDateTime getFechaRegistroBrasil() {
        ZoneId zonaBrasil = ZoneId.of("America/Sao_Paulo");
        Instant fechaRegistroInstant = fechaRegistro
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return LocalDateTime.ofInstant(fechaRegistroInstant, zonaBrasil);
    }
}
