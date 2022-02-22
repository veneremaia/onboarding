package com.onboarding.api.repository.impl;

import com.onboarding.api.repository.IngredientRepository;
import com.onboarding.api.repository.entity.Ingredient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(prefix = "repository", name = "system", havingValue = "csv")
public class IngredientCSVRepository implements IngredientRepository {

    private final String FILE_NAME = "ingredients";

    private CSVParser parser;

    public IngredientCSVRepository() throws IOException {
        parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(System.getProperty("user.dir") + "\\csv\\" + FILE_NAME + ".csv"));
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        return parser.stream()
                .filter(i -> Integer.parseInt(i.get("id")) == id)
                .findFirst().map(this::mapToEntity);
    }

    @Override
    public Page<Ingredient> findAll(Pageable pageable) {
        List<Ingredient> result = parser.stream().map(this::mapToEntity).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Ingredient save(Ingredient entity) {
        return entity;
    }

    private Ingredient mapToEntity(CSVRecord csv) {
        return new Ingredient(
                Integer.parseInt(csv.get("id")),
                csv.get("description"),
                Double.parseDouble(csv.get("price")),
                LocalDate.parse(csv.get("expirationDate")));
    }

}
