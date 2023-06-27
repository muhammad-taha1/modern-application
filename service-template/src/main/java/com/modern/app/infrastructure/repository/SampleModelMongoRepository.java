package com.modern.app.infrastructure.repository;

import com.modern.app.infrastructure.dto.SampleModelDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleModelMongoRepository extends MongoRepository<SampleModelDto, Long> {
}
