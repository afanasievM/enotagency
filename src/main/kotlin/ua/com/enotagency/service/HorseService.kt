package ua.com.enotagency.service

import org.springframework.stereotype.Service
import ua.com.enotagency.repository.HorseRepository

interface HorseService : DescriptionService

@Service
class HorseServiceImpl(
    horseRepository: HorseRepository,
    trelloService: TrelloService
) : HorseService, DescriptionServiceImpl(horseRepository, trelloService)
