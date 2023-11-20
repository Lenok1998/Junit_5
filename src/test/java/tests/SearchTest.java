package tests;

import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;



@DisplayName("Домашнее задание по Junit5")
public class SearchTest {

    @BeforeEach
    void setUp()
    {

        open("https://www.startpage.com/");
    }

    @ValueSource(strings = {
            "Selenide", "Junit 5", "Allure"
    })
    @ParameterizedTest(name="Поисковый запрос {0} не должен отдавать пустой список результатов")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmptyTest(String searchQuery){
        $("#q").setValue(searchQuery).pressEnter();
        $$("[class='w-gl__result__main']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Selenide, https://ru.selenide.org//",
            "Junit 5, https://junit.org/junit5/"
    })
    @ParameterizedTest(name="Для поискового запроса {0},в первом ответе должна быть ссылка{1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrlTest(String searchQuery, String expectedLink){
        $("#q").setValue(searchQuery).pressEnter();
        $("[class='w-gl__result-url result-link']")
                .shouldHave(text(expectedLink));
    }

    @Tag("BLOCKER")
    @EnumSource(Language.class)
    @ParameterizedTest (name="Проверка, что поисковая строка не пустая")
    void successfulSearchTwoTest(Language language){
        System.out.println(language.description);
        $("#q").setValue(language.description).pressEnter();
        $("#q").shouldHave(text(language.description));
    }

}