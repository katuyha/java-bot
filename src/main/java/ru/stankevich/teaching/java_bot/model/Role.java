package ru.stankevich.teaching.java_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;


@Table(name = "roles")
@Entity(name = "roles")
@Data
@Accessors(chain = true)
    public class Role implements GrantedAuthority {

        @Id
        @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
        private Long id;

        private String name;

        @Override
        public String getAuthority() {
            return name;
        }


        public String getName() {
            return name;
        }

        public Role setName(String name) {
            this.name = name;
            return this;
        }

}
