package com.samplebank.entity;

import com.samplebank.dto.ClientDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;

@Entity
@Table(name = "clients", uniqueConstraints = { @UniqueConstraint(columnNames = { "email_address" }, name = "email_address_UNIQUE")})
@Getter
public class Client extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate joinedDate;

    private LocalDate activationDate;

    private LocalDate closedDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    private String clientGovernmentId;

    private String cellPhone;

    @Column(name = "email_address")
    @Email
    private String emailAddress;

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<ClientAccount>  clientAccounts;

    private Client() {
    }

    private Client(String firstName, String lastName, LocalDate joinedDate, LocalDate activationDate, LocalDate closedDate, Gender gender,
            ClientStatus clientStatus, String clientGovernmentId, String cellPhone, String emailAddress, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinedDate = joinedDate;
        this.activationDate = activationDate;
        this.closedDate = closedDate;
        this.gender = gender;
        this.clientStatus = clientStatus;
        this.clientGovernmentId = clientGovernmentId;
        this.cellPhone = cellPhone;
        this.emailAddress = emailAddress;
        this.user = user;
    }

    void upDateUser(User thisUser) {
        this.user = thisUser;
    }

    public enum Gender {
        MALE, FEMALE;

        public static Gender fromString(String gender) {
            if (gender.equalsIgnoreCase("MALE")) {
                return MALE;
            } else if (gender.equalsIgnoreCase("FEMALE")) {
                return FEMALE;
            } else {
                throw new IllegalArgumentException("Invalid Gender !!!");
            }
        }
    }

    public enum ClientStatus {
        ACTIVE, CLOSED
    }

    public static Client createClient(ClientDto clientDto) {
        return new Client(clientDto.getFirstName(), clientDto.getLastName(), LocalDate.now(), LocalDate.now(), LocalDate.now(), Client.Gender.fromString(clientDto.getGender()), Client.ClientStatus.ACTIVE, clientDto.getClientGovernmentId(), clientDto.getCellPhone(), clientDto.getEmailAddress(), null);
    }

    public void addAccount(ClientAccount account){
        this.clientAccounts.add(account);
        account.updateClient(this);
    }

}
