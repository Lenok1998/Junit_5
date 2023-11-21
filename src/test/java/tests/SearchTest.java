package tests;

import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
public class SearchTest extends TestBase {

    @BeforeEach
    void setUp()
    {

        open("https://www.startpage.com/");
    }

    @ValueSource(strings = {
            "React", "Vue", "Node.js"
    })
    @ParameterizedTest(name="Поисковый запрос {0} не должен отдавать пустой список результатов")
    @Tag("SMOKE")
    @Tag("MOBILE")
    void searchResultsShouldNotBeEmptyTest(String searchQuery){
        $("#q").setValue(searchQuery).pressEnter();
        $$("[class='w-gl__result__main']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "React, https://react.dev/",
            "Vue, https://vuejs.org/",
            "Node.js, https://nodejs.org/en"
    })
    @ParameterizedTest(name="Для поискового запроса {0},в первом ответе должна быть ссылка{1}")
    @Tag("WEB")
    @Tag("Regression")
    void searchResultsShouldContainExpectedUrlTest(String searchQuery, String expectedLink){
        $("#q").setValue(searchQuery).pressEnter();
        $("[class='w-gl__result-url result-link']")
                .shouldHave(text(expectedLink));
    }

 
    @Tag("REGRESSION")
    @Tag("WEB")
    @EnumSource(Language.class)
    @ParameterizedTest (name="Проверка, что поисковая строка не пустая")
    void successfulSearchTwoTest(Language language){
        System.out.println(language.description);
        $("#q").setValue(language.description).pressEnter();
        $("[class='w-gl__result__main']").shouldHave(text(language.description));
    }

}
