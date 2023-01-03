package com.demounittest.service.category;

import com.demounittest.model.Categories;
import com.demounittest.model.Outcomes;
import com.demounittest.repository.ICategoryRepository;
import com.demounittest.repository.IOutcomesRepository;
import com.demounittest.service.categories.CategoryService;
import com.demounittest.service.categories.ICategoryService;
import com.demounittest.service.outcome.IOutComesService;
import com.demounittest.service.outcome.OutComesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    private ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
//    Sau khi làm giả được CategoryRepository chúng ta tiêm nó vào trong hàm khởi tạo của CategoryService
    private ICategoryService categoryService = new CategoryService(categoryRepository);

    private IOutcomesRepository outcomesRepository = Mockito.mock(IOutcomesRepository.class);
    private IOutComesService outComesService = new OutComesService(outcomesRepository);

//    Phần này mình xác định dùng lại nhiều lần ở các Test nên mình sử đặt nó trong @BeforeEach
//    nghĩa là những lệnh chạy trong phương thức này sẽ được chạy lại trước mỗi Test.
    @BeforeEach
    void init(){
//        Đoạn mã này mình tạo Outcome giả để mình test
        Outcomes outcomes = new Outcomes();
        outcomes.setTitle("PHẦN 1: KIẾN THỨC VÀ KỸ NĂNG LẬP TRÌNH");
//        when dùng để giả lập một lời gọi hàm nào đó được sử dụng bên trong method đang được kiểm thử
//        Phương thức này thường đi kèm với thenReturn(), thenAnswer(), thenThrow() để chỉ định kết quả trả về
        doReturn(Optional.of(outcomes)).when(outcomesRepository).findById(1L);
        List<Outcomes> outcomesIterable = Arrays.asList(outcomes);
        doReturn(outcomesIterable).when(outcomesRepository).findAll();

        Categories category = new Categories();
        category.setCategoryId("1.1");
        category.setName("Lập trình cơ bản");
        category.setOutComes(outcomes);
        doReturn(Optional.of(category)).when(categoryRepository).findById(1L);
        doReturn(Optional.of(category)).when(categoryRepository).findByName("Lập trình cơ bản");
        List<Categories> categoriesList = Arrays.asList(category);
        doReturn(categoriesList).when(categoryRepository).findAll();
    }
// Chúng ta sử dụng @Test để đánh dấu phương thức Test nhé.
    @Test
    @DisplayName("findAll can return list is not null")
    public void whenFindAllNotNull() {
        assertThat(categoryService.findAll()).isNotNull();
    }
    @Test
    @DisplayName("findAll can return a list has 1 element")
    public void whenfindAll_thenReturnListHasOneElement() {
        Iterable<Categories> categories = categoryService.findAll();
        assertThat(categories).hasSize(1);
    }
    @Test
    @DisplayName("findbyID return category name Lap trinh co ban")
    public void whenfindById_thenReturnCategory() {
        String name = "Lập trình cơ bản";
        Optional<Categories> categories = categoryService.findById(1L);
        assertThat(categories.get().getName()).isEqualTo(name);
    }
    @Test
    @DisplayName("findbyID 2L return isPresent")
    public void whenfindById_thenReturnCategoryisPresent() {
        Optional<Categories> categories = categoryService.findById(2L);
        assertThat(categories.isPresent()).isFalse();
    }

    @Test
    @DisplayName("findbyName return category name Lap trinh co ban")
    public void whenfindByName_thenReturnCategory() {
        String name = "Lập trình cơ bản";
        Optional<Categories> categories = categoryService.findByName("Lập trình cơ bản");
        assertThat(categories.get().getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("findbyID 2L return isPresent")
    public void whenfindByName_thenReturnCategoryisPresent() {
        Optional<Categories> categories = categoryService.findByName("Lập trinh nâng cao 1");
        assertThat(categories.isPresent()).isFalse();
    }

    @Test
    @DisplayName("findAll by OutCome return list not null")
    void findAllByOutComes() {
        Outcomes outcomes = outComesService.findById(1L).get();
        List<Categories> categories = (List<Categories>) categoryService.findAllByOutComes(outcomes);
        assertThat(categories).isNotNull();
    }

    @Test
    @DisplayName("save Categories use 1 categoryRepository.save")
    void save() {
        Categories categories = new Categories();
        categories.setCategoryId("1.2");
        categories.setName("Lập trình nâng cao");
        categoryService.save(categories);
        verify(categoryRepository, times(1)).save(categories);
    }

    @Test
    @DisplayName("delete")
    void remove() {
        categoryService.remove(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
