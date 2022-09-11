package by.hackaton.bookcrossing.configuration;

import by.hackaton.bookcrossing.dto.BookDto;
import by.hackaton.bookcrossing.entity.Book;
import by.hackaton.bookcrossing.repository.AccountRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Autowired
    private AccountRepository repository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper() {
            @Override
            public <D> D map(Object source, Class<D> destinationType) {
                Object tmpSource = source;
                if (source == null) {
                    tmpSource = new Object();
                }
                return super.map(tmpSource, destinationType);
            }
        };
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        modelMapper.createTypeMap(Book.class, BookDto.class).addMappings(bookToDto());
        modelMapper.createTypeMap(BookDto.class, Book.class).addMappings(dtoToBook());
        return modelMapper;
    }

    private ExpressionMap<Book, BookDto> bookToDto() {
        return (m) -> {
            m.map(book -> book.getOwner().getUsername(), BookDto::setOwner);
        };
    }

    private ExpressionMap<BookDto, Book> dtoToBook() {
        return (m) -> {
            m.skip(Book::setOwner);
        };
    }
}
