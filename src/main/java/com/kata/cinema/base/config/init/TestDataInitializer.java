package com.kata.cinema.base.config.init;


import com.kata.cinema.base.models.*;

import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.FolderMovieService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.RoleService;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

public class TestDataInitializer {

    private final GenreService genreService;
    private final MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FolderMovieService folderMovieService;

    private final CollectionService collectionService;

    private static final int countMovieList = 100;
    private static final int countCollection = 20;

    private static final int countUser = 25;
    private static final int countGenre = 10;
    private static final int ELEVEN_MONTHS = 11;
    private static final int ONE_MONTH = 1;
    private static final int TWENTY_SEVEN_DAYS = 27;
    private static final int ONE_DAY = 1;
    private static final String DESCRIPTION = "описание описание описание описание описание описание описание описание описание описание описание описание";

    private static final Random random = new Random();

    public TestDataInitializer(GenreService genreService, MovieService movieService, CollectionService collectionService) {
        this.genreService = genreService;
        this.movieService = movieService;
        this.collectionService = collectionService;
    }

    public void movieInit() {
        final int START_YEAR = 1990;
        final int LAST_YEAR = 2022;
        List<Genre> genreList = genreService.getAll();

        for (int i = 1; i <= countMovieList; i++) {
            Movie movie = new Movie();

            List<Genre> genreListMovie = new ArrayList<>();
            int randomNumberOfGenres = random.nextInt(2) + 1;
            for (int numberOfGenre = 0; numberOfGenre < randomNumberOfGenres; numberOfGenre++) {
                List<Genre> genres = new ArrayList<>(genreList);
                int randomIndex = random.nextInt(genres.size());
                Genre randomElement = genres.get(randomIndex);
                genreListMovie.add(randomElement);
                genres.remove(randomIndex);
            }
            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
            movie.setDateRelease(LocalDate.of(year, month, day));
            movie.setTime(String.valueOf(random.ints(100, 181)));
            movie.setMpaa(MPAA.values()[random.nextInt(MPAA.values().length)]);
            movie.setRars(RARS.values()[random.nextInt(RARS.values().length)]);
            movie.setGenres(genreListMovie);
            movie.setName(String.format("Фильм%s", i));
            movie.setDescription(DESCRIPTION);

            movieService.create(movie);
        }
    }

    public void genreInit() {
        for (int i = 1; i <= countGenre; i++) {
            Genre genre = new Genre();
            genre.setName(String.format("Жанр%s", i));
            genreService.create(genre);
        }
    }

    public void collectionInit() {
        Random random = new Random();
        Collection collection = new Collection();
        List<Boolean> collectionList;
        for (int i = 0; i < countCollection; i++) {
            if (i < 5) {
                collectionList = Collections.singletonList(false);
            } else {
                collectionList = Collections.singletonList(true);
            }
            collection.setName(String.valueOf(collectionList));
        }
        List<Movie> collectMovieList = new ArrayList<>();
        for (int i = 0; i < countCollection; i++) {
            i = random.nextInt(5, 16);
            collection.setMovies(collectMovieList);
        }
        collectionService.create(collection);
    }

    public void roleInit() {
        Role roleAdmin = new Role();
        roleAdmin.setRole("ADMIN");
        roleService.create(roleAdmin);

        Role roleUser = new Role();
        roleUser.setRole("USER");
        roleService.create(roleUser);

        Role rolePublicist = new Role();
        rolePublicist.setRole("PUBLICIST");
        roleService.create(rolePublicist);
    }

    public void userInit() {
        final int ONE_BEFORE_LAST_USER_IN_BASE = 24;
        final int LAST_USER_IN_BASE = 25;
        final int START_YEAR = 1970;
        final int LAST_YEAR = 2010;

        final Role ROLE_USER;
        final Role ROLE_ADMIN;
        final Role ROLE_PUBLICIST;
        final Optional<Role> roleUserOptional = roleService.getByName("USER");
        final Optional<Role> roleAdminOptional = roleService.getByName("ADMIN");
        final Optional<Role> rolePublicistOptional = roleService.getByName("PUBLICIST");
        ROLE_USER = roleUserOptional.orElseGet(Role::new);
        ROLE_ADMIN = roleAdminOptional.orElseGet(Role::new);
        ROLE_PUBLICIST = rolePublicistOptional.orElseGet(Role::new);

        List<Role> roles = new ArrayList<>();
        roles.add(ROLE_USER);

        for (int userNumber = 1; userNumber <= countUser; userNumber++) {
            User user = new User();
            user.setEmail(String.format("email%s@mail.ru", userNumber));
            user.setFirst_name(String.format("Имя%s", userNumber));
            user.setLast_name(String.format("Фамилия%s", userNumber));
            user.setPassword("password");
            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
            user.setBirthday(LocalDate.of(year, month, day));
            if (userNumber == ONE_BEFORE_LAST_USER_IN_BASE) {
                List<Role> oneBeforeLastUserRoles = new ArrayList<>();
                oneBeforeLastUserRoles.add(ROLE_USER);
                oneBeforeLastUserRoles.add(ROLE_ADMIN);
                user.setRole(oneBeforeLastUserRoles);
            } else if (userNumber == LAST_USER_IN_BASE) {
                List<Role> lastUserRoles = new ArrayList<>();
                lastUserRoles.add(ROLE_USER);
                lastUserRoles.add(ROLE_PUBLICIST);
                user.setRole(lastUserRoles);
            } else {
                user.setRole(roles);
            }
            userService.create(user);
            Optional<User> userOptional = userService.getByEmail("email" + userNumber + "@mail.ru");
            if (userOptional.isPresent()) {
                user.setAvatarUrl(String.format("/uploads/users/avatar/%s", userOptional.get().getId()));
                userService.update(user);
            }
        }
    }

    public void folderMovieInit() {
        List<Movie> allMovies = movieService.getAll();
        List<User> allUsers = userService.getAll();
        for (User user : allUsers) {
            for (int category = 0; category < Category.values().length - 1; category++) {
                FolderMovie folderMovie = new FolderMovie();
                folderMovie.setCategory(Category.values()[category]);
                folderMovie.setName(Category.values()[category].name());
                folderMovie.setPrivacy(Privacy.PUBLIC);
                folderMovie.setUser(user);
                folderMovie.setDescription(DESCRIPTION);
                List<Movie> movies = new ArrayList<>();
                int moviesCount = random.nextInt(20) + 5;
                for (int i = 0; i < moviesCount; i++) {
                    while (true) {
                        int uniqueRandomFilm = random.nextInt(allMovies.size());
                        if (!movies.contains(allMovies.get(uniqueRandomFilm))) {
                            movies.add(allMovies.get(uniqueRandomFilm));
                            break;
                        }
                    }
                }
                folderMovie.setMovies(movies);
                folderMovieService.create(folderMovie);
            }
        }
    }

    private void init() {
        roleInit();
        genreInit();
        movieInit();
        collectionInit();
        userInit();
        folderMovieInit();
    }
}
