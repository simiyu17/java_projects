package com.samplebank.client.domain;

import com.samplebank.client.dto.ClientDto;
import com.samplebank.shared.entity.BaseEntity;
import com.samplebank.account.domain.ClientAccount;
import com.samplebank.auth.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "clients", uniqueConstraints = { @UniqueConstraint(columnNames = { "email_address" }, name = "email_address_UNIQUE")})
@Getter
public class Client extends BaseEntity {

    private String firstName;

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
    private String emailAddress;

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<ClientAccount>  clientAccounts;

    protected Client() {
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

    public void upDateUser(User thisUser) {
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
        return new Client(clientDto.firstName(), clientDto.lastName(), LocalDate.now(), LocalDate.now(), LocalDate.now(), Client.Gender.fromString(clientDto.gender()), Client.ClientStatus.ACTIVE, clientDto.clientGovernmentId(), clientDto.cellPhone(), clientDto.emailAddress(), null);
    }

    public void addAccount(ClientAccount account){
        this.clientAccounts.add(account);
        account.updateClient(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Client client)){
            return false;
        }
        return new EqualsBuilder()
                .append(this, obj)
                .append(getId(), client.getId())
                .append(getUser(), client.getUser()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(getId())
                .append(getUser())
                .toHashCode();
    }
}
