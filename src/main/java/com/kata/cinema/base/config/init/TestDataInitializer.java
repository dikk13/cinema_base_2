package com.kata.cinema.base.config.init;


import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.*;
import com.kata.cinema.base.service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class TestDataInitializer {

    private final GenreService genreService;
    private final MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FolderMovieService folderMovieService;

    @Autowired
    private ProductionStudioService productionStudioService;

    @Autowired
    private ProductionMovieStudioService productionMovieStudioService;

    @Autowired
    private StudioPerformanceService studioPerformanceService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private MoviePersonService moviePersonService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReactionReviewService reactionReviewService;
    @Autowired
    private AvailableOnlineMovieService availableOnlineMovieService;
    @Autowired
    private PurchasedMovieService purchasedMovieService;
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentsService commentsService;

    private final CollectionService collectionService;
    private final PasswordEncoder encoder;
    private static final int countMovieList = 100;
    private static final int countCollection = 20;

    private static final int countUser = 25;
    private static final int countGenre = 10;
    private static final int countProductionStudio = 20;
    private static final int countNews = 25;
    private static final int countQuestion = 5;
    private static final int countAnswer = 4;
    private static final int countResult = 4;
    private static final int ELEVEN_MONTHS = 11;
    private static final int ONE_MONTH = 1;
    private static final int TWENTY_SEVEN_DAYS = 27;
    private static final int ONE_DAY = 1;
    private static final String DESCRIPTION = "описание описание описание описание описание описание описание описание описание описание описание описание";
    private static final String HTML_DESCRIPTION = "html страница html страница html страница html страница html страница html страница";

    private static final int countReview = 500;

    private static final Random random = new Random();

    public TestDataInitializer(GenreService genreService, MovieService movieService, CollectionService collectionService, PasswordEncoder encoder) {
        this.genreService = genreService;
        this.movieService = movieService;
        this.collectionService = collectionService;
        this.encoder = encoder;
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
            movie.setTime(random.nextInt(100, 181));
            movie.setMpaa(MPAA.values()[random.nextInt(MPAA.values().length)]);
            movie.setRars(RARS.values()[random.nextInt(RARS.values().length)]);
            movie.setGenres(genreListMovie);
            movie.setName(String.format("Фильм%s", i));
            movie.setOriginalName(String.format("Оригинальное название%s", i));
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
        List<Collection> collections = new ArrayList<>(countCollection);
        List<Movie> movies = movieService.getAll();
        for (int i = 0; i < countCollection; i++) {
            List<Movie> foldingMovieList = new ArrayList<>(movies);
            List<Movie> proxyMovieList = new ArrayList<>();
            collections.add(i, new Collection());
            collections.get(i).setName("Подборка " + i);
            collections.get(i).setEnable(i >= 5);
            for (int j = 0; j < countMovieList / 4; j++) {
                proxyMovieList.add(foldingMovieList.remove(random.nextInt(0, foldingMovieList.size() - 1)));
            }
            collections.get(i).setMovies(proxyMovieList);
            collectionService.create(collections.get(i));
        }
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

        Set<Role> roles = new HashSet<>();
        roles.add(ROLE_USER);

        for (int userNumber = 1; userNumber <= countUser; userNumber++) {
            User user = new User();
            user.setEmail(String.format("email%s@mail.ru", userNumber));
            user.setFirstName(String.format("Имя%s", userNumber));
            user.setLastName(String.format("Фамилия%s", userNumber));
            user.setPassword(encoder.encode("password"));
            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
            user.setBirthday(LocalDate.of(year, month, day));
            if (userNumber == ONE_BEFORE_LAST_USER_IN_BASE) {
                Set<Role> oneBeforeLastUserRoles = new HashSet<>();
                oneBeforeLastUserRoles.add(ROLE_USER);
                oneBeforeLastUserRoles.add(ROLE_ADMIN);
                user.setRole(oneBeforeLastUserRoles);
            } else if (userNumber == LAST_USER_IN_BASE) {
                Set<Role> lastUserRoles = new HashSet<>();
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
        for (int userN=1; userN<=countUser; userN++) {
            for (int category = 0; category < Category.values().length-1; category++) {
                FolderMovie folderMovie = new FolderMovie();
                folderMovie.setCategory(Category.values()[category]);
                folderMovie.setName(Category.values()[category].name());
                folderMovie.setPrivacy(Privacy.PUBLIC);
                folderMovie.setUser(userService.getAll().get(userN-1));
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

    public void studioProductionInit() {
        StudioPerformance studioProduction = new StudioPerformance();
        studioProduction.setName("Производство");
        studioPerformanceService.create(studioProduction);
        StudioPerformance studioSpecialEffects = new StudioPerformance();
        studioSpecialEffects.setName("Спецэффекты");
        studioPerformanceService.create(studioSpecialEffects);
        StudioPerformance studioRental = new StudioPerformance();
        studioRental.setName("Прокат");
        studioPerformanceService.create(studioRental);
        StudioPerformance studioDubbing = new StudioPerformance();
        studioDubbing.setName("Дубляж");
        studioPerformanceService.create(studioDubbing);
    }

    public void productionStudioInit() {
        String description = " Описание студии описание студии описание студии описание студии описание студии описание студии описание студии";
        int minDay = (int) LocalDate.of(1960, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();

        for (int i = 1; i <= countProductionStudio; i++) {
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            LocalDate randomDateFoundation = LocalDate.ofEpochDay(randomDay);
            ProductionStudio productionStudio = new ProductionStudio();
            productionStudio.setName(String.format("Студия%s", i));
            productionStudio.setDescription(description);
            productionStudio.setDateFoundation(randomDateFoundation);
            int index = random.nextInt(studioPerformanceService.getAll().size());
            productionStudio.setPerformance(studioPerformanceService.getAll().get(index));
            productionStudioService.create(productionStudio);
        }

    }

    public void productionStudioMovieInit() {
        for (int j = 1; j <= countMovieList / 2; j++) {
            int index = random.nextInt(movieService.getAll().size());
            for (int i = 0; i < 3; i++) {
                ProductionStudioMovie productionStudioMovie = new ProductionStudioMovie();
                productionStudioMovie.setMovie(movieService.getAll().get(index));
                int index2 = random.nextInt(productionStudioService.getAll().size());
                if (productionStudioMovie.getStudio() == null) {
                    productionStudioMovie.setStudio(productionStudioService.getAll().get(index2));
                } else if (!productionStudioMovie.getStudio().equals(productionStudioService.getAll().get(index2))) {
                    productionStudioMovie.setStudio(productionStudioService.getAll().get(index2));
                }
                productionMovieStudioService.create(productionStudioMovie);
            }
        }


    }

    private void personInit() {
        final int PERSON_COUNT = 50;
        final int START_YEAR = 1970;
        final int LAST_YEAR = 2010;
        final double MIN_HEIGHT = 1.50;
        final double MAX_HEIGHT = 2.20;
        final String photoUrl = "/uploads/persons/photos/%s";

        for (int personNumber = 1; personNumber <= PERSON_COUNT; personNumber++) {
            Person person = new Person();
            person.setFirstName(String.format("Имя%s", personNumber));
            person.setLastName(String.format("Фамилия%s", personNumber));
            person.setOriginalFirstName(String.format("Name%s", personNumber));
            person.setOriginalLastName(String.format("lastName%s", personNumber));
            person.setHeight(random.nextDouble(MIN_HEIGHT, MAX_HEIGHT));
            int year = random.nextInt(LAST_YEAR - START_YEAR) + START_YEAR;
            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
            person.setDateOfBirth(LocalDate.of(year, month, day));
            personService.create(person);
            Optional<Person> personOptional = personService.getById((long) personNumber);
            if (personOptional.isPresent()) {
                personService.update(person);
            }

        }

    }

    private void professionInit() {
        Profession professionDirector = new Profession();
        professionDirector.setName("Режиссер");
        professionService.create(professionDirector);

        Profession professionScreenwriter = new Profession();
        professionScreenwriter.setName("Сценарист");
        professionService.create(professionScreenwriter);

        Profession professionProducer = new Profession();
        professionProducer.setName("Продюсер");
        professionService.create(professionProducer);

        Profession professionOperator = new Profession();
        professionOperator.setName("Оператор");
        professionService.create(professionOperator);

        Profession professionComposer = new Profession();
        professionComposer.setName("Композитор");
        professionService.create(professionComposer);

        Profession professionArtist = new Profession();
        professionArtist.setName("Художник");
        professionService.create(professionArtist);

        Profession professionEditor = new Profession();
        professionEditor.setName("Монтажер");
        professionService.create(professionEditor);

        Profession professionActor = new Profession();
        professionActor.setName("Актер");
        professionService.create(professionActor);
    }

    private void moviePersonInit() {
        final int COUNT_MAIN_CHARACTER = 3;
        final int COUNT_MINOR_CHARACTER = 3;
        final int COUNT_NO_CHARACTER_MOVIE = 4;

        List<Person> persons;
        List<Profession> professions = professionService.getAll();
        Profession actor = professionService.getByName("Актер").orElseThrow();
        professions.removeIf(profession -> profession.getName().equals("Актер"));

        List<Movie> movies = movieService.getAll();

        for (Movie movie : movies) {

            persons = personService.getAll();

            for (int mainCharacterCount = 0; mainCharacterCount < COUNT_MAIN_CHARACTER; mainCharacterCount++) {
                MoviePerson mainMovieActor = new MoviePerson();
                mainMovieActor.setProfession(actor);
                mainMovieActor.setMovie(movie);
                int randomPersonId = random.nextInt(persons.size() - 1);
                mainMovieActor.setPerson(persons.get(randomPersonId));
                persons.remove(randomPersonId);
                mainMovieActor.setType(CharacterType.MAIN_CHARACTER);
                moviePersonService.create(mainMovieActor);
            }

            for (int minorCharacterCount = 0; minorCharacterCount < COUNT_MINOR_CHARACTER; minorCharacterCount++) {
                MoviePerson minorMovieActor = new MoviePerson();
                minorMovieActor.setProfession(actor);
                minorMovieActor.setMovie(movie);
                int randomPersonId = random.nextInt(persons.size() - 1);
                minorMovieActor.setPerson(persons.get(randomPersonId));
                persons.remove(randomPersonId);
                minorMovieActor.setType(CharacterType.MINOR_CHARACTER);
                moviePersonService.create(minorMovieActor);
            }

            for (int notAnActorCount = 0; notAnActorCount < COUNT_NO_CHARACTER_MOVIE; notAnActorCount++) {
                MoviePerson moviePerson = new MoviePerson();
                int index = random.nextInt(professions.size());
                moviePerson.setProfession(professions.get(index));
                moviePerson.setMovie(movie);
                int randomPersonId = random.nextInt(persons.size() - 1);
                moviePerson.setPerson(persons.get(randomPersonId));
                persons.remove(randomPersonId);
                moviePerson.setType(CharacterType.NO_CHARACTER_MOVIE);
                moviePersonService.create(moviePerson);
            }
        }
    }

    public void newsInit() {

        for (int i = 1; i <= countNews; i++) {
            User user=userService.getByRole("PUBLICIST");
            News news = new News();
            LocalDateTime end = LocalDateTime.now();
            long days = ChronoUnit.DAYS.between(LocalDateTime.now().minusWeeks(1), end);
            LocalDateTime randomDate = end.minusDays(new Random().nextInt((int) days));
            news.setDate(randomDate);
            int randomNumber = new Random().nextInt(Rubric.values().length);
            if (i <= 20) {
                news.setRubric(Rubric.values()[randomNumber]);
            } else {
                news.setRubric(Rubric.valueOf("TESTS"));
            }
            news.setTitle(String.format("Заголовок %s", i));
            news.setHtmlBody(HTML_DESCRIPTION);
            news.setPreviewUrl(String.format("/upload/news/preview/%s", i));
            news.setUser(user);
                newsService.create(news);
            }
        }
    public void questionInit() {
        List<News> listNews = newsService.getAll().stream()
                .filter(i -> i.getRubric().equals(Rubric.valueOf("TESTS"))).toList();

        for (News news : listNews) {
            for (int i = 1; i <= countQuestion; i++) {
                Question question = new Question();
                question.setPosition(i);
                question.setQuestion(String.format("Вопрос %s", i));
                question.setNews(news);
                questionService.create(question);
            }
        }
    }

    public void answerInit() {
        List<Question> listQuestion = questionService.getAll();

        for (Question question : listQuestion) {
            for (int i = 1; i <= countAnswer; i++) {
                Answer answer = new Answer();
                answer.setIsRight(i == 1);
                answer.setAnswer(String.format("Ответ %s", i));
                answer.setQuestion(question);
                answerService.create(answer);
            }
        }
    }

    public void resultInit() {
        List<Question> listQuestion = questionService.getAll();

        for (Question question : listQuestion) {
            for (int i = 1; i <= countResult; i++) {
                Result result = new Result();
                result.setCountRightAnswer(i); // почему? в таблице ответов всегда 1 правильный, 3 неправильных
                result.setResult(String.format("Результат %s", i));
                result.setQuestion(question);
                resultService.create(result);
            }
        }
    }

    public void reviewInit() {
        int increment = 0;
        for (int k = 1; k <= countMovieList; k++) {
            int[] typeReviewNumbers = new int[]{0, 0, 1, 1, 2};
            for (int i = 1; i <= 5; i++) {
                Review review = new Review();
                LocalDate end = LocalDate.now();
                long days = ChronoUnit.DAYS.between(LocalDate.now().minusMonths(1), end);
                LocalDate randomDate = end.minusDays(new Random().nextInt((int) days));
                review.setTitle(String.format("Заголовок %s", ++increment));
                review.setDescription(DESCRIPTION);
                review.setDate(randomDate);
                review.setUser(userService.getAll().get(i - 1));
                review.setMovie(movieService.getAll().get(k - 1));
                review.setTypeReview(TypeReview.values()[typeReviewNumbers[i - 1]]);
                reviewService.create(review);
            }
        }
    }

    public void reactionReviewInit() {
        for (int j = 1; j <= (countReview/2); j++) {
            for (int i = 1; i <= 5; i++) {
                ReactionReview reactionReview = new ReactionReview();
                reactionReview.setRating(TypeRating.LIKE);
                reactionReview.setUser(userService.getAll().get(i-1));
                reactionReview.setReview(reviewService.getAll().get(j));
                reactionReviewService.create(reactionReview);
            }
        }
    }

    public void scoreInit() {
        for (int k = 1; k <= countMovieList; k++) {
            for (int i = 1; i <= 20; i++) {
                Score score = new Score();
                score.setScore(random.nextInt(1, 11));
                score.setUser(userService.getAll().get(i - 1));
                score.setMovie(movieService.getAll().get(k - 1));
                int minDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
                int maxDay = (int) LocalDate.of(2022, 1, 11).toEpochDay();
                long randomDay = minDay + random.nextInt(maxDay - minDay);
                LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                score.setDate(randomDate);
                scoreService.create(score);
            }
        }
    }

    public void availableOnlineMovieInit() {

        for (int count = 1; count <= countMovieList; count++) {
            AvailableOnlineMovie availableOnlineMovie = new AvailableOnlineMovie();
            availableOnlineMovie.setMovie(movieService.getAll().get(count - 1));
            availableOnlineMovie.setRentalPrice(2000);
            availableOnlineMovie.setBuyPrice(3000);
            availableOnlineMovie.setAvailablePlus(true);
            availableOnlineMovie.setEnabled(true);
            availableOnlineMovieService.create(availableOnlineMovie);
        }
    }

    private void purchasedMovieInit() {
        for (int count1 = 1; count1 <= countUser; count1++) {
            PurchasedMovie purchasedMovie = new PurchasedMovie();
            purchasedMovie.setUser(userService.getAll().get(count1 - 1));
            User user = userService.getAll().get(count1 - 1);
            for (int p = 1; p <= 5; p++) {
                user.setPurchasedMovie(purchasedMovieService.getAll());
            }
            int index1 = random.nextInt(availableOnlineMovieService.getAll().size());
            purchasedMovie.setAvailableOnlineMovie(availableOnlineMovieService.getAll().get(index1));
            int year = random.nextInt(2022);
            int month = random.nextInt(ELEVEN_MONTHS) + ONE_MONTH;
            int day = random.nextInt(TWENTY_SEVEN_DAYS) + ONE_DAY;
            purchasedMovie.setEndDate(LocalDate.of(year, month, day));
            purchasedMovie.setPurchase(PurchaseType.values()[random.nextInt(PurchaseType.values().length)]);
            purchasedMovieService.create(purchasedMovie);
        }
    }

    private void init() {
        roleInit();
        genreInit();
        movieInit();
        collectionInit();
        userInit();
        folderMovieInit();
        studioProductionInit();
        productionStudioInit();
        productionStudioMovieInit();
        personInit();
        professionInit();
        moviePersonInit();
        newsInit();
        questionInit();
        answerInit();
        resultInit();
        reviewInit();
        reactionReviewInit();
        scoreInit();
        availableOnlineMovieInit();
        purchasedMovieInit();
    }
}
