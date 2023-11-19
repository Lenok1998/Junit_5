package tests;

import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SstuTest {

    @BeforeEach
    void setUp()
    {
        open("https://www.rambler.ru");
    }
    @ValueSource(strings = {
            "Selenide", "JUnit 5", "Allure"
    })
    @ParameterizedTest(name="For a search query, {0} should not give an empty list of cards")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery){
        $("#q").setValue(searchQuery).pressEnter();
        $$("[class='w-gl__result__main']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Selenide, https://selenide.org/",
            "JUnit 5, https://junit.org"
    })
    @ParameterizedTest(name="For a search query {0}, the first card must have a link {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink){
        $("#q").setValue(searchQuery).pressEnter();
        $("[class='w-gl__result-url result-link']")
                .shouldHave(text(expectedLink));
    }

    @Tag("BLOCKER")
    @EnumSource(Language.class)
    @ParameterizedTest (name="When searching, the search bar should not be empty")
    void successfulSearchTwo(Language language){
        System.out.println(language.description);
        $("#q").setValue(language.description).pressEnter();
        $("#q").shouldHave(text(language.description));
    }

}