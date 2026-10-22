package org.example.tests.ui;

import org.assertj.core.api.SoftAssertions;
import org.example.components.DepositCard;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;

public class DepositCalculatorTest extends BaseTest {
    @Test
    public void testDepositCalculatorDisplaysValidData() {
        DepositCard depositCard = new MainPage(driver).getDepositCard();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(depositCard.isButtonOpenEnabled())
                    .as("Кнопка 'Оформить' в карточке вклада должна быть активна")
                    .isTrue();

            softly.assertThat(depositCard.isButtonMoreDetailsEnabled())
                    .as("Кнопка 'Подробнее' в карточке вклада должна быть активна")
                    .isTrue();

            softly.assertThat(depositCard.getIncomeFieldValue())
                    .as("Значение поля 'Доход' должно быть в формате суммы с рублями (например, '15 956 ₽')")
                    .matches("\\d{1,3}( \\d{3})* ₽");

            softly.assertThat(depositCard.getRateFieldValue())
                    .as("Значение поля 'Ставка' должно быть в формате процентов (например, '12.8 %')")
                    .matches("\\d{1,2}([.]\\d{1,2})? %");
        });
    }
}