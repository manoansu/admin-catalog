package pt.amane.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void givenAnValidParams_whenCallNewCategory_thenInstantiateACategory() {

    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actutalCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    Assertions.assertNotNull(actutalCategory);
    Assertions.assertNotNull(actutalCategory.getId());
    Assertions.assertEquals(expectedName, actutalCategory.getName());
    Assertions.assertEquals(expectedDescription, actutalCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actutalCategory.isActive());
    Assertions.assertNotNull(actutalCategory.getCreatedAt());
    Assertions.assertNotNull(actutalCategory.getUpdatedAt());
    Assertions.assertNull(actutalCategory.getDeletedAt());
  }
}