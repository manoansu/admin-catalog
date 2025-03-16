package pt.amane.domain.category;

import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.amane.domain.exception.DomainException;
import pt.amane.domain.validaion.handler.ThrowsValidationHandler;

class CategoryTest {

  @Test
  void givenAnValidParams_whenCallNewCategory_thenInstantiateACategory() {

    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actutalCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    Assertions.assertNotNull(actutalCategory);
    Assertions.assertNotNull(actutalCategory.getId());
    Assertions.assertEquals(expectedName, actutalCategory.getName());
    Assertions.assertEquals(expectedDescription, actutalCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actutalCategory.isActive());
    Assertions.assertNotNull(actutalCategory.getCreatedAt());
    Assertions.assertNotNull(actutalCategory.getUpdatedAt());
    Assertions.assertNull(actutalCategory.getDeletedAt());
  }

  @Test
  void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldRecieveError() {

    final String expectedName = null;
    final var expectedErrorCount =  1 ;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory = getNewCategory(expectedName, expectedDescription,
        expectedIsActive);

    final var actualException = Assertions.assertThrows(DomainException.class,
        () -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

  }

  @Test
  void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldRecieveError() {
    final var expectedName = " ";
    final var expectedErrorCount =  1 ;
    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());

  }

  @Test
  void givenAnInvalidNameLessThen3_whenCallNewCategoryAndValidate_thenShouldRecieveError() {
    final String expectedName = "An ";
    final var expectedErrorCount =  1 ;
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());

  }

  @Test
  void givenAnInvalidNameMoreThen255_whenCallNewCategoryAndValidate_thenShouldRecieveError() {
    final var expectedName = """
            O Fabuloso Gerador de Lero-lero v2.0 é capaz de gerar qualquer quantidade de texto vazio e prolixo, ideal para engrossar uma tese de mestrado, 
            impressionar seu chefe ou preparar discursos capazes de curar a insônia da platéia. 
            Basta informar um título pomposo qualquer (nos moldes do que está sugerido aí embaixo)
            e a quantidade de frases desejada. Voilá! Em dois nano-segundos você terá um texto - ou mesmo um livro inteiro - pronto para impressão. 
            Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada. 
            """;
    final var expectedErrorCount =  1 ;
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());

  }

  @Test
  void givenAnValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = " ";
    final var expectedIsActive = true;

    final var actualCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(getaCategoryUpdatedAt(actualCategory));
    Assertions.assertNull(actualCategory.getDeletedAt());
  }

  @Test
  void givenAnValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = " ";
    final var expectedIsActive = false;

    final var actualCategory = getNewCategory(expectedName, expectedDescription, expectedIsActive);

    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(getaCategoryUpdatedAt(actualCategory));
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }

//  @Test
//  void givenAnValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
//
//    final var expectedName = "Filmes";
//    final var expectedDescription = "A categoria mais assistida";
//    final var expectedIsActive = false;
//
//    final var aCategory = getNewCategory(expectedName, expectedDescription, true);
//
//    Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));
//
//    final var createAt = aCategory.getCreatedAt();
//    final var updateAt = getaCategoryUpdatedAt(aCategory);
//
//    Assertions.assertTrue(aCategory.isActive());
//    Assertions.assertNull(aCategory.getDeletedAt());
//    final var actualCategory = aCategory.deactivate();
//
//    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));
//
//    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//    Assertions.assertEquals(expectedName, actualCategory.getName());
//    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
//    Assertions.assertEquals(createAt, actualCategory.getCreatedAt());
//    Assertions.assertTrue(getaCategoryUpdatedAt(actualCategory).isAfter(updateAt));
//    Assertions.assertNotNull(actualCategory.getDeletedAt());
//  }
//
//  @Test
//  void givenAnValidIncativeCategory_whenCallActivate_thenReturnCategoryInactivated() {
//
//    final var expectedName = "Filmes";
//    final var expectedDescription = "A categoria mais assistida";
//    final var expectedIsActive = true;
//
//    final var aCategory = getNewCategory(expectedName, expectedDescription, true);
//
//    Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));
//
//    final var createdAt = aCategory.getCreatedAt();
//    final var updateAt = getaCategoryUpdatedAt(aCategory);
//
//    Assertions.assertTrue(aCategory.isActive());
//    Assertions.assertNull(aCategory.getDeletedAt());
//    final var actualCategory = aCategory.activate();
//
//    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));
//
//    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//    Assertions.assertEquals(expectedName, actualCategory.getName());
//    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
//    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
//    Assertions.assertTrue(getaCategoryUpdatedAt(actualCategory).isAfter(updateAt));
//    Assertions.assertNull(actualCategory.getDeletedAt());
//  }
//
//  @Test
//  void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
//
//    final var expectedName = "Filmes";
//    final var expectedDescription = "A categoria mais assistida";
//    final var expectedIsActive = true;
//
//    final var aCategory = getNewCategory("Filmes", "A categoria", expectedIsActive);
//
//    Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));
//
//    final var createdAt = aCategory.getCreatedAt();
//    final var updateAt = getaCategoryUpdatedAt(aCategory);
//
//    final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);
//
//    Assertions.assertTrue(aCategory.isActive());
//    Assertions.assertNull(aCategory.getDeletedAt());
//
//    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));
//
//    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//    Assertions.assertEquals(expectedName, actualCategory.getName());
//    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
//    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
//    Assertions.assertTrue(getaCategoryUpdatedAt(actualCategory).isAfter(updateAt));
//    Assertions.assertNull(actualCategory.getDeletedAt());
//  }
//
//  @Test
//  void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
//
//    final var expectedName = "Filmes";
//    final var expectedDescription = "A categoria mais assistida";
//    final var expectedIsActive = false;
//
//    final var aCategory = getNewCategory("Filmes", "A categoria", true);
//
//    Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));
//    Assertions.assertTrue(aCategory.isActive());
//    Assertions.assertNull(aCategory.getDeletedAt());
//
//    final var createdAt = aCategory.getCreatedAt();
//    final var updateAt = getaCategoryUpdatedAt(aCategory);
//
//    final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);
//
//    Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));
//
//    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//    Assertions.assertEquals(expectedName, actualCategory.getName());
//    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//    Assertions.assertFalse(aCategory.isActive());
//    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
//    Assertions.assertTrue(getaCategoryUpdatedAt(aCategory).isAfter(updateAt));
//    Assertions.assertNotNull(actualCategory.getDeletedAt());
//  }
//
//  @Test
//  void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
//
//    final String expectedName = null;
//    final var expectedDescription = "A categoria mais assistida";
//    final var expectedIsActive = true;
//
//    final var aCategory = getNewCategory("Filmes", "A categoria", true);
//
//    Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));
//
//    final var createdAt = aCategory.getCreatedAt();
//    final var updateAt = getaCategoryUpdatedAt(aCategory);
//
//    final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);
//
//    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//    Assertions.assertEquals(expectedName, actualCategory.getName());
//    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//    Assertions.assertTrue(aCategory.isActive());
//    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
//    Assertions.assertTrue(getaCategoryUpdatedAt(actualCategory).isAfter(updateAt));
//    Assertions.assertNull(actualCategory.getDeletedAt());
//  }

  private static Instant getaCategoryUpdatedAt(Category aCategory) {
    return aCategory.getUpdatedAt();
  }
  
  private static Category getNewCategory(String expectedName, String expectedDescription,
      boolean expectedIsActive) {
    return Category.newCategory(expectedName, expectedDescription, expectedIsActive);
  }
}