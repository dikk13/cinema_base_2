package com.kata.cinema.base.webapp.controllers.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.ProductionStudioRequestDto;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.abstracts.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Transactional
//@DatabaseSetup("/dataset.xml")
@DatabaseSetup("/empty_dataset.xml")
@DatabaseTearDown(value = "/empty_dataset.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
class AdminProductionStudioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                //name
                "asdstudio",
                //description
                "studio description",
                //dateFoundation
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(post("/api/admin/studios")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                //name
                "changed",
                //description
                "updated studio description",
                //dateFoundation
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(put("/api/admin/studios/100")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    void deleteProductionStudio() throws Exception {
        entityManager.createQuery("delete from ProductionStudioMovie").executeUpdate();
        this.mockMvc.perform(delete("/api/admin/studios/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }


    /////////////////////////////////////////////////////////////////////////
//    @Autowired
//    private MovieService movieService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private FolderMovieService folderMovieService;
//    @Autowired
//    private GenreService genreService;
//
//    private static final Random random = new Random();
//    private static final int countMovieList = 100;
//    private static final int countUser = 25;
//    private static final int countGenre = 10;
//
//    @Test
//    void folderMovieInitTest() {
//
//        roleInit();
//        genreInit();
//        usersInit();
//        movieInit();
//        initFolders();
//
//        Assertions.assertEquals(3, roleService.getAll().size());
//        Assertions.assertEquals(10, genreService.getAll().size());
//        Assertions.assertEquals(25, userService.getAll().size());
//        Assertions.assertEquals(100, movieService.getAll().size());
//        Assertions.assertEquals(userService.getAll().size() * 3, folderMovieService.getAll().size());
//    }
//
//    public void movieInit() {
//        final int START_YEAR = 1990;
//        final int LAST_YEAR = 2022;
//        final int ELEVEN_MONTHS = 11;
//        final int ONE_MONTH = 1;
//        final int TWENTY_SEVEN_DAYS = 27;
//        final int ONE_DAY = 1;
//        List<Genre> genreList = genreService.getAll();
//        final String DESCRIPTION = "описание описание описание описание описание описание описание описание описание описание описание описание";
//
//        for (int i = 1; i <= countMovieList; i++) {
//            Movie movie = new Movie();
//
//            List<Genre> genreListMovie = new ArrayList<>();
//            int randomNumberOfGenres = random.nextInt(2) + 1;
//            for (int numberOfGenre = 0; numberOfGenre < randomNumberOfGenres; numberOfGenre++) {
//                List<Genre> genres = new ArrayList<>(genreList);
//                int randomIndex = random.nextInt(genres.size());
//                Genre randomElement = genres.get(randomIndex);
//                genreListMovie.add(randomElement);
//                genres.remove(randomIndex);
//            }
//            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
//            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
//            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
//            movie.setDateRelease(LocalDate.of(year, month, day));
//            movie.setTime(String.valueOf(random.ints(100, 181)));
//            movie.setMpaa(MPAA.values()[random.nextInt(MPAA.values().length)]);
//            movie.setRars(RARS.values()[random.nextInt(RARS.values().length)]);
//            movie.setGenres(genreListMovie);
//            movie.setName(String.format("Фильм%s", i));
//            movie.setDescription(DESCRIPTION);
//
//            movieService.create(movie);
//        }
//    }
//
//    public void roleInit() {
//        Role roleAdmin = new Role();
//        roleAdmin.setRole("ADMIN");
//        roleService.create(roleAdmin);
//
//        Role roleUser = new Role();
//        roleUser.setRole("USER");
//        roleService.create(roleUser);
//
//        Role rolePublicist = new Role();
//        rolePublicist.setRole("PUBLICIST");
//        roleService.create(rolePublicist);
//    }
//
//    public void usersInit() {
//        final int ONE_BEFORE_LAST_USER_IN_BASE = 24;
//        final int LAST_USER_IN_BASE = 25;
//        final int START_YEAR = 1970;
//        final int LAST_YEAR = 2010;
//        final int ELEVEN_MONTHS = 11;
//        final int ONE_MONTH = 1;
//        final int TWENTY_SEVEN_DAYS = 27;
//        final int ONE_DAY = 1;
//        final Role ROLE_USER = roleService.getByName("USER").get();
//        final Role ROLE_ADMIN = roleService.getByName("ADMIN").get();
//        final Role ROLE_PUBLICIST = roleService.getByName("PUBLICIST").get();
//
//        List<Role> roles = new ArrayList<>();
//        roles.add(ROLE_USER);
//
//        for (int userNumber = 1; userNumber <= countUser; userNumber++) {
//            User user = new User();
//            user.setEmail(String.format("email%s@mail.ru", userNumber));
//            user.setFirst_name(String.format("Имя%s", userNumber));
//            user.setLast_name(String.format("Фамилия%s", userNumber));
//            user.setPassword("password");
//            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
//            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
//            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
//            user.setBirthday(LocalDate.of(year, month, day));
//            if (userNumber == ONE_BEFORE_LAST_USER_IN_BASE) {
//                List<Role> oneBeforeLastUserRoles = new ArrayList<>();
//                oneBeforeLastUserRoles.add(ROLE_USER);
//                oneBeforeLastUserRoles.add(ROLE_ADMIN);
//                user.setRole(oneBeforeLastUserRoles);
//            } else if (userNumber == LAST_USER_IN_BASE) {
//                List<Role> lastUserRoles = new ArrayList<>();
//                lastUserRoles.add(ROLE_USER);
//                lastUserRoles.add(ROLE_PUBLICIST);
//                user.setRole(lastUserRoles);
//            } else {
//                user.setRole(roles);
//            }
//            userService.create(user);
//            user.setAvatarUrl(String.format("/uploads/users/avatar/%s", userService.getByEmail("email" + userNumber + "@mail.ru").get().getId()));
//            userService.update(user);
//        }
//        List<User> list = userService.getAll();
//        list.stream().forEach(user -> {
//            try {
//                System.out.println(objectMapper.writeValueAsString(user));
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    public void genreInit() {
//        for (int i = 1; i <= countGenre; i++) {
//            Genre genre = new Genre();
//            genre.setName(String.format("Жанр%s", i));
//            genreService.create(genre);
//        }
//    }
//
//    public void initFolders() {
//        final String DESCRIPTION = "описание описание описание описание описание описание описание описание ";
//        List<Movie> allMovies = movieService.getAll();
//        List<User> usersList = userService.getAll();
//        for (User user : usersList) {
//            for (int category = 0; category < 3; category++) {
//                FolderMovie folderMovie = new FolderMovie();
//                folderMovie.setCategory(Category.values()[category]);
//                folderMovie.setName(Category.values()[category].name());
//                folderMovie.setPrivacy(Privacy.PUBLIC);
//                folderMovie.setUser(user);
//                folderMovie.setDescription(DESCRIPTION);
//                List<Movie> movies = new ArrayList<>();
//                int moviesCount = random.nextInt(20) + 5;
//                for (int i = 0; i < moviesCount; i++) {
//                    while (true) {
//                        int uniqueRandomFilm = random.nextInt(allMovies.size());
//                        if (!movies.contains(allMovies.get(uniqueRandomFilm))) {
//                            movies.add(allMovies.get(uniqueRandomFilm));
//                            break;
//                        }
//                    }
//                }
//                folderMovie.setMovies(movies);
//                folderMovieService.create(folderMovie);
//            }
//        }
//    }
}