package mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dto.CreateUserDto;
import dto.GetUserDto;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromCreate(CreateUserDto createUser) {
        return User.builder().nombre(createUser.nombre())
                .fechaNacimiento(formatDate(createUser.fechaNacimiento()))
                .fechaRegistro(LocalDateTime.now()).build();
    }

    @Override
    public GetUserDto fromUser(User user) {
        return new GetUserDto(user.getNombre(),
                formatDate(user.getFechaNacimiento()),
                formatDateTime(user.getFechaRegistro()),
                user.getEdad(),
                user.getDiasParaCumpleanhos(),
                formatDateTime(user.getFechaRegistroBrasil()));
    }

    private String formatDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
