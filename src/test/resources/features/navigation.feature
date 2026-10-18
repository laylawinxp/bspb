Feature: Проверка навигационного меню

  Scenario Outline: Переход на страницу меню
    Given я на главной странице БСПБ
    When я нажимаю на "<название>"
    Then URL содержит "<частьURL>"

    Examples:
      | название    | частьURL              |
      | Вклады      | /retail/deposits      |
      | Карты       | /retail/cards/debit   |
      | Кредиты     | /retail/consumer-loan |
      | Ипотека     | /retail/mortgage      |
      | Премиум     | /retail/programs      |
      | Пенсионерам | /retail/pensionary    |