package com.urubu.domain.entity;

import com.urubu.core.domain.base.Selectable;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "accountIdentifier", name = "uk_account_identifier"),
        @UniqueConstraint(columnNames = "user_id", name = "uk_user_account"),
})
@Entity(name = "accounts")
public class Account implements Selectable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String accountIdentifier;

    @NotNull
    private Double balance;

    @NotNull
    @OneToOne
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "fk_user_account"))
    @ToString.Exclude
    private User user;

    @Override
    public String getKey() {
        return id.toString();
    }

    @Override
    public String getValue() {
        return null;
    }
}
