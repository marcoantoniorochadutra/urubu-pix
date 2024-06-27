package com.urubu.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "uk_user_email"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 120)
    private String name;

    @NotNull
    @Column(length = 120)
    private String email;

    @NotNull
    @Column(length = 120)
    private String hashPass;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_details"))
    private UserDetails userDetails;

}
