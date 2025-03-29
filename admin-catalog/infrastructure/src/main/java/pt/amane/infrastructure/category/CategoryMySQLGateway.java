package pt.amane.infrastructure.category;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pt.amane.domain.category.Category;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.category.CategoryID;
import pt.amane.domain.pagination.Pagination;
import pt.amane.domain.pagination.SearchQuery;
import pt.amane.infrastructure.category.persistence.CategoryJpaEntity;
import pt.amane.infrastructure.category.persistence.CategoryRepository;
import pt.amane.infrastructure.utils.SpecificationUtils;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

  private final CategoryRepository repository;

  public CategoryMySQLGateway(final CategoryRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  @Override
  public Category create(final Category aCategory) {
    return save(aCategory);
  }

  @Override
  public void deleteById(final CategoryID anId) {
    final var anIdValue = anId.getValue();
    if (this.repository.existsById(anIdValue)){
      this.repository.deleteById(anIdValue);
    }
  }

  @Override
  public Optional<Category> findById(final CategoryID anId) {
    return this.repository.findById(anId.getValue())
        .map(CategoryJpaEntity::toAggregate);
  }

  @Override
  public Category update(final Category aCategory) {
    return save(aCategory);
  }

  @Override
  public Pagination<Category> findAll(final SearchQuery aQuery) {
    //Pagination
    final var page = PageRequest.of(
        aQuery.page(),
        aQuery.perPage(),
        Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
    );

    // Busca dinamica pelo criaterio terms (name ou description)
    final var specifications = Optional.ofNullable(aQuery.terms())
        .filter(str -> !str.isBlank())
//        .map(this::assembleSpecification)
        .map(str -> {
            final Specification<CategoryJpaEntity> nameLike = SpecificationUtils.like("name", str);
            final Specification<CategoryJpaEntity> descriptionLike = SpecificationUtils.like("description", str);
            return nameLike.or(descriptionLike);
  })
        .orElse(null);

    final var pageResult =
        this.repository.findAll(Specification.where(specifications), page);

    return new Pagination<>(
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        pageResult.map(CategoryJpaEntity::toAggregate).toList()
    );

  }

  @Override
  public List<CategoryID> existsByIds(final Iterable<CategoryID> categoryIDs) {
    // comvert Iterable para stream existe o metodo StreamSupport que tem dois paramentro
    final var ids = StreamSupport.stream(categoryIDs.spliterator(), false)
        .map(CategoryID::getValue)
        .toList();
    return this.repository.existsByIds(ids).stream()
        .map(CategoryID::from)
        .toList();
  }

  private Category save(final Category aCategory) {
    return repository.save(CategoryJpaEntity
        .from(aCategory)).toAggregate();
  }
}
