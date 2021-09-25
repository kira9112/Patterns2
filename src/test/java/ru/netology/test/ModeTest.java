package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ModeTest {

    @BeforeEach
    void before() {
        open("http://localhost:9999");
    }

    @Test
    void inputValidLogPasStatActive() {
        val user = DataGenerator.dataName("active");
        DataGenerator.sendRequest(user);
        $("span[data-test-id='login'] input").setValue(user.getLogin());
        $("span[data-test-id='password'] input").setValue(user.getPassword());
        $("button[data-test-id='action-login']").click();
        $("h2.heading.heading_size_l.heading_theme_alfa-on-white").shouldHave(text("Личный кабинет"));
    }

    @Test
    void inputValidLogPasStatBlocked() {
        val user = DataGenerator.dataName("blocked");
        DataGenerator.sendRequest(user);
        $("span[data-test-id='login'] input").setValue(user.getLogin());
        $("span[data-test-id='password'] input").setValue(user.getPassword());
        $("button[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void inputNo_Valid_LogValidPasStatActive() {
        val user = DataGenerator.dataName("active");
        DataGenerator.sendRequest(user);
        $("span[data-test-id='login'] input").setValue(DataGenerator.noValidLog());
        $("span[data-test-id='password'] input").setValue(user.getPassword());
        $("button[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]").shouldHave(text("Неверно указан логин или пароль"));
    }
    @Test
    void inputValidLogNo_Valid_PasStatActive() {
        val user = DataGenerator.dataName("active");
        DataGenerator.sendRequest(user);
        $("span[data-test-id='login'] input").setValue(user.getLogin());
        $("span[data-test-id='password'] input").setValue(DataGenerator.noValidPass());
        $("button[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]").shouldHave(text("Неверно указан логин или пароль"));
    }
}