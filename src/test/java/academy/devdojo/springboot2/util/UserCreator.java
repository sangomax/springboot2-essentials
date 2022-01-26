package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.domain.DevDojoUser;

public class UserCreator {

    public static final DevDojoUser USER = DevDojoUser.builder()
            .name("DevDojo")
            .username("devdojo")
            .password("{bcrypt}$2a$10$DyXrT5vs8.od9H9.4M9iyO1VfSU9jx0x.se94dw6J2U9SPRpqqk3C")
            .authorities("ROLE_USER")
            .build();

    public static final DevDojoUser ADMIN = DevDojoUser.builder()
            .name("Adriano Gaiotto")
            .username("adriano")
            .password("{bcrypt}$2a$10$DyXrT5vs8.od9H9.4M9iyO1VfSU9jx0x.se94dw6J2U9SPRpqqk3C")
            .authorities("ROLE_USER,ROLE_ADMIN")
            .build();
}
