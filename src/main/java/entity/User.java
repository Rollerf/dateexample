package entity;

import java.util.Date;

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
    private Date fechaNacimiento;
    @Column
    private Date fechaRegistro;

    public Integer getEdad(){
        Date fechaActual = new Date();
        long diferenciaEnMillis = fechaActual.getTime() - fechaNacimiento.getTime();
        long edadEnMillis = 1000L * 60 * 60 * 24 * 365;
        int edad = (int) (diferenciaEnMillis / edadEnMillis);

        return edad;
    }
}
