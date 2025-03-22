package pt.amane.application.category.create;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pt.amane.application.UseCaseTest;
import pt.amane.domain.category.CategoryGateway;

public class CreateCategoryUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultCreateCategoryUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(categoryGateway);
  }

  //TDD
  // Happy path text
  @Test
  void givenAValidCommand_whenCallCreatedCategory_shouldReturnCategoryId() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    // When create an instantiation for category, then return the first params of category.
    when(categoryGateway.create(any()))
        .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    assertNotNull(actualOutput);
    assertNotNull(actualOutput.id());

    verify(categoryGateway, times(1)).create(argThat(aCategory ->
        Objects.equals(expectedName, aCategory.getName())
            && Objects.nonNull(aCategory.getId())
            && Objects.equals(expectedDescription, aCategory.getDescription())
            && Objects.equals(expectedIsActive, aCategory.isActive())
            && Objects.nonNull(aCategory.getCreatedAt())
            && Objects.nonNull(aCategory.getUpdatedAt())
            && Objects.isNull(aCategory.getDeletedAt())

    ));
  }

  //Passing an invalid property (name) test
  @Test
  void givenAInvalidName_whenCallCreatedCategory_shouldReturnDomainException() {

    final String expectedName = null;
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    verify(categoryGateway, times(0)).create(any());

  }

  // Creating an inactive category test
  @Test
  void givenAValidCommandWithInactiveCategory_whenCallCreatedCategory_shouldReturnInactiveCategoryId() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    // When create an instantiation for category, then return the first params of category.
    when(categoryGateway.create(any()))
        .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    assertNotNull(actualOutput);
    assertNotNull(actualOutput.id());

    verify(categoryGateway, times(1)).create(argThat(aCategory ->
        Objects.equals(expectedName, aCategory.getName())
            && Objects.nonNull(aCategory.getId())
            && Objects.equals(expectedDescription, aCategory.getDescription())
            && Objects.equals(expectedIsActive, aCategory.isActive())
            && Objects.nonNull(aCategory.getCreatedAt())
            && Objects.nonNull(aCategory.getUpdatedAt())
            && Objects.nonNull(aCategory.getDeletedAt())

    ));
  }

  //Simulating a generic error coming from the gateway test
  @Test
  void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAnException() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "Gateway error";

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    when(categoryGateway.create(any()))
        .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    verify(categoryGateway, times(1)).create(argThat(aCategory ->
        Objects.equals(expectedName, aCategory.getName())
            && Objects.nonNull(aCategory.getId())
            && Objects.equals(expectedDescription, aCategory.getDescription())
            && Objects.equals(expectedIsActive, aCategory.isActive())
            && Objects.nonNull(aCategory.getCreatedAt())
            && Objects.nonNull(aCategory.getUpdatedAt())
            && Objects.isNull(aCategory.getDeletedAt())

    ));

  }
}
