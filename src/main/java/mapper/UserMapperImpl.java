package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                .fechaRegistro(new Date()).build();
    }

    @Override
    public GetUserDto fromUser(User user) {
        return new GetUserDto(user.getNombre(),
                formatDate(user.getFechaNacimiento()),
                formatDateTime(user.getFechaRegistro()));
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    private String formatDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }

    private Date formatDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
