package com.github.dadjokes;

import com.github.dadjokes.model.DadJoke;
import javassist.NotFoundException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DadJokeService {
    private final Mapper mapper = new DozerBeanMapper();
    private final DadJokeRepository dadJokeRepository;

    public DadJokeService(DadJokeRepository dadJokeRepository) {
        this.dadJokeRepository = dadJokeRepository;
    }

    public DadJoke getDadJoke(Long id) throws NotFoundException {
        DadJokeEntity dadJokeEntity = dadJokeRepository.getById(id);
        if (dadJokeEntity == null) {
            throw new NotFoundException("Could not find dad joke with ID " + id);
        }
        return mapper.map(dadJokeEntity, DadJoke.class);
    }

    public DadJoke createDadJoke(DadJoke dadJoke) {
        DadJokeEntity dadJokeEntity = dadJokeRepository.getDadJokeEntityByQuestionIgnoreCaseAndAnswerIgnoreCase(dadJoke.getQuestion(), dadJoke.getAnswer());
        if (dadJokeEntity == null) {
            dadJokeEntity = mapper.map(dadJoke, DadJokeEntity.class);
            dadJokeEntity = dadJokeRepository.save(dadJokeEntity);
        }
        return mapper.map(dadJokeEntity, DadJoke.class);
    }
}
