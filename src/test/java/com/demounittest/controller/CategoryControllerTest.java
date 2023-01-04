package com.demounittest.controller;

import com.demounittest.model.Categories;
import com.demounittest.model.Skills;
import com.demounittest.service.categories.ICategoryService;
import com.demounittest.service.skills.ISkillsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.demounittest.model.StaticVariable.asJsonString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Chúng ta có thể chỉ định chú thích @SpringBootTest trên một lớp thử nghiệm chạy các bài kiểm tra dựa trên Spring Boot.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
//    đưa ra những feature cần thiết cho web application
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISkillsService skillsService;

//    MockMvc là điểm vào chính cho hỗ trợ kiểm tra Spring MVC phía máy chủ
    private MockMvc mvc;

    @BeforeEach
    private void init(){
        Categories categories1 = new Categories();
        categories1.setCategoryId("1.1");
        categories1.setName("Lập trình cơ bản");
        Categories categories2 = new Categories();
        categories2.setCategoryId("1.2");
        categories2.setName("Lập trình nâng cao");
        categoryService.save(categories1);
        categoryService.save(categories2);

        Skills skills = new Skills();
        skills.setSkillId("1.1.1");
        skills.setName("Áp dụng được các kỹ năng giải quyết vấn đề với các bài toán thông dụng");
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("find all return status 200 with role admin")
    void findAll_whenGetCategoriesWithRoleAdmin_thenReturnStatus200() throws Exception {
        mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "coach", roles = "COACH")
    void whengetCategoriesWithRoleCoach_thenReturnStatus200() throws Exception {
        mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("create status 200 with role admin")
    void whenCreateCategoryWithRoleAdmin_thenReturnStatus200() throws Exception {
        Categories categories = new Categories();
        categories.setCategoryId("1.3");
        categories.setName("Lập trình Hướng Đối tượng");
        mvc.perform(post("/categories")
                        .content(asJsonString(categories))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(username = "coach", roles = "COACH")
    @DisplayName("create status 403 with role Coach")
    void whenCreateCategoryWithRoleCoach_thenReturnStatus403() throws Exception {
        Categories categories = new Categories();
        categories.setCategoryId("1.3");
        categories.setName("Lập trình Hướng Đối tượng");
        mvc.perform(post("/categories")
                        .content(asJsonString(categories))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "coach", roles = "COACH")
    @DisplayName("edit status 403 with role Coach")
    void whenEditCategoryWithRoleCoach_thenReturnStatus403() throws Exception {
        Categories categories = new Categories();
        categories.setName("Cấu trúc dữ liệu và giải thuật");
        categories.setCategoryId("1.1");
        mvc.perform(put("/categories/{id}", 1L)
                .content(asJsonString(categories)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("edit status 200 with role Admin")
    void whenEditCategoryWithRoleAdmin_thenReturnStatus200() throws Exception {
        Categories categories = new Categories();
        categories.setName("Cấu trúc dữ liệu và giải thuật");
        categories.setCategoryId("1.1");
        mvc.perform(put("/categories/{id}", 1L)
                .content(asJsonString(categories)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("delete status 200 with role Admin")
    void whenDeleteCategoryWithRoleAdmin_thenReturnStatus200() throws Exception {
        mvc.perform(delete("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("delete status 404 with role Admin")
    void whenDeleteCategoryWithRoleAdminWithId3_thenReturnStatus404() throws Exception {
        mvc.perform(delete("/categories/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "coach", roles = "COACH")
    @DisplayName("delete status 403 with role Coach")
    void whenDeleteCategoryWithRoleAdminWithId1_thenReturnStatus403() throws Exception {
        mvc.perform(delete("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }


    @Test
    @DisplayName("find All Skill By Categories with role Admin status 200")
    void whenfindAllSkillByCategoriesWithRoleAdmin_thenReturn200() {

    }
}
