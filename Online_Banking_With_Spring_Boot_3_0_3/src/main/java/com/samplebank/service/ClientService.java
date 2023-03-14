package com.samplebank.service;

import com.samplebank.dto.ClientDto;
import com.samplebank.entity.Client;

public interface ClientService {

    Client createClient(ClientDto clientDto);
}
