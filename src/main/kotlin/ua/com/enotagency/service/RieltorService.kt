package ua.com.enotagency.service

import org.springframework.stereotype.Service
import ua.com.enotagency.repository.RieltorRepository

interface RieltorService : DescriptionService

@Service
class RieltorServiceImpl(
    rieltorRepository: RieltorRepository,
    trelloService: TrelloService
) : RieltorService, DescriptionServiceImpl(rieltorRepository, trelloService)
