package com.samplebank.client.service;

import com.samplebank.client.domain.ClientRepositoryWrapper;
import com.samplebank.client.dto.ClientDto;
import com.samplebank.shared.exceptions.DataRulesViolationException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepositoryWrapper clientRepositoryWrapper;

    @Transactional
    @Override
    public void createClient(ClientDto clientDto) {
            try {
                this.clientRepositoryWrapper.createClientWithAssociatedUser(clientDto);
            } catch (final JpaSystemException | DataIntegrityViolationException dve) {
                handleException(dve);
            } catch (final PersistenceException dve) {
                Throwable throwable = ExceptionUtils.getRootCause(dve.getCause());
                handleException((Exception) throwable);
            }
    }

    @Override public ClientDto findClientById(Long clientId) {
        return this.clientRepositoryWrapper.findClientById(clientId);
    }

    @Override
    public ClientDto findClientByUser(Authentication authentication) {
        return this.clientRepositoryWrapper.findClientByUser(authentication);
    }

    @Override
    public List<ClientDto> getAvailableClients() {
        return this.clientRepositoryWrapper.getAvailableClients();
    }

    private void handleException(final Exception ex){
        var message = ex.getMessage();
        if (message.contains("email_address_unique")){
            throw new DataRulesViolationException("Email Address already in use by another client!!");
        }
    }

}
