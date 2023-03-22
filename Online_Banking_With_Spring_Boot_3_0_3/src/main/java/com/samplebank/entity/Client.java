package com.samplebank.entity;

import com.samplebank.dto.ClientDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "clients")
@Getter
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Email
    private String emailAddress;

    @OneToOne(mappedBy = "client")
    private User user;

    private Client() {
    }

    void upDateUser(User thisUser) {
        this.user = thisUser;
    }

    enum Gender {
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

    enum ClientStatus {
        ACTIVE, CLOSED
    }

    public static Client createClient(ClientDto clientDto) {
        return new Client(null, clientDto.getFirstName(), clientDto.getLastName(), LocalDate.now(), LocalDate.now(), LocalDate.now(), Client.Gender.fromString(clientDto.getGender()), Client.ClientStatus.ACTIVE, clientDto.getClientGovernmentId(), clientDto.getCellPhone(), clientDto.getEmailAddress(), null);
    }

}
