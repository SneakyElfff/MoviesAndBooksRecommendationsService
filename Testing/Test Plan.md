# План тестирования

# Содержание
1 [Введение](#introduction)<br>
2 [Объект тестирования](#test_items)<br>
3 [Риски](#risk_issues)<br>
4 [Аспекты тестирования](#features_to_be_tested)<br>
5 [Подходы к тестированию](#test_approach)<br>
6 [Представление результатов](#pass_fall_criteria)<br>
7 [Выводы](#conclusion)

<a name="introduction"></a>

## 1 Введение

Данный документ описывает план тестирования приложения ["YourCupOfTea"](https://github.com/SneakyElfff/MoviesAndBooksRecommendationsService/tree/main), представляющего собой сервис рекоммендаций фильмов и книг. Документ предназначен для людей, проводящих тестирование данного проекта.<br> 
Цель тестирования - проверка соответствия реального поведения программы проекта и ее ожидаемого поведения, которое приведено в [требованиях](https://github.com/SneakyElfff/MoviesAndBooksRecommendationsService/blob/main/Documentation/Documentation.md).

<a name="test_items"></a>

## 2 Объект тестирования

В качестве объектов тестирования можно выделить следующие функциональные требования:

* Просмотр рекомендаций
* Поиск
* Просмотр информации о фильмах и книгах
* Аутентификация
* Создание и редактирование профиля
* Добавление в личную библиотеку

Атрибуты качества, которыми должен обладать проект:

1. Функциональность:
   - функциональная полнота: приложение должно выполнять все заявленные функции;
   - функциональная корректность: приложение должно выполнять все заявленные функции корректно;
2. Удобство использования:
   - все функциональные элементы пользовательского интерфейса имеют описания в виде названий или поясняющих строк, определяющие действие, которое произойдет при взаимодействии с данным элементом;
   - все некорректные действия пользователя должны сопровождаться сообщениями, которые разъяснят, как правильно взаимодействовать с функциональным элементом.

<a name="risk_issues"></a>

## 3 Риски

В контексте данного приложения к рискам можно отнести:

* отсутствие Интернет-соединения у пользователя;
* ошибки серверов, предоставляющих информацию о фильмах и книгах.

<a name="features_to_be_tested"></a>

## 4 Аспекты тестирования

В ходе тестирования планируется проверить реализацию основных функций приложения. В соотвествии с которыми можно определить следующие аспекты тестирования:
* Запуск приложения
* Вход в аккаунт
* Использование приложения в качестве посетителя
* Получение рекоммендаций фильмов и книг
* Просмотр информации о понравившихся фильмах/книгах
* Поиск информации о фильме/книге
* Добавление фильма/книги в список избранного
* Просмотр коллекций "избранных" фильмов/книг на персональной странице зарегистрированного пользователя

### Запуск приложения
Этот вариант использования небходимо протестировать на:

* запуск приложения и отображение интерфейса.

### Вход в аккаунт
Этот вариант использования небходимо протестировать на:

* корректную обработку персональных данных;
* успешное прохождение авторизации при верных введенных данных.

### Использование приложения в качестве посетителя
Этот вариант использования небходимо протестировать на:

* доступность всего заявленного функционала для посетителя.

### Получение рекоммендаций фильмов и книг
Этот вариант использования небходимо протестировать на:

* отображение на экране рекоммендуемых фильмов и книг;
* наличие "кликабельных" ссылок, обеспечивающих переход на страницы с информацией по выбранным фильмам/книгам.

### Просмотр информации о понравившихся фильмах/книгах
Этот вариант использования небходимо протестировать на:

* осуществление перехода на страницу с информацией о соотвествующем фильме/книге после краткого нажатия по названию фильма/книги.

### Поиск информации о фильме/книге
Этот вариант использования небходимо протестировать на:

* реакцию системы на незаполненные пользователем поисковые поля;
* реакцию системы на отсутствие информации о требуемых пользователем фильмах/книгах;
* осуществление перехода на страницу с информацией о соотвествующем фильме/книге.

### Добавление фильма/книги в список избранного
Этот вариант использования небходимо протестировать на:

* корректное функционирование кнопки "Like";
* осуществление проверки прав пользователя на осуществление данного действия;
* проведение анализа имеющихся коллекций на наличие выбранного объекта;
* включение выбранного объекта в соответствующую коллекцию активного пользователя.

### Просмотр коллекций "избранных" фильмов/книг на персональной странице зарегистрированного пользователя
Этот вариант использования небходимо протестировать на:

* корректное отображение таблиц с коллекциями фильмов и книг активного пользователя.

Также планируется проверить реализацию основных функций приложения

<a name="test_approach"></a>

## 5 Подходы к тестированию

Для тестирования приложения будет использован ручной подход.

<a name="pass_fall_criteria"></a>

## 6 Представление результатов

Результаты тестирования представлены в документе ["Представление результатов"]().

<a name="conclusion"></a>

## 7 Выводы

Данный тестовый план позволяет протестировать основной функционал приложения. Успешное прохождение всех тестов не гарантирует полной работоспособности, однако позволяет полагать, что данное программное обеспечение работает корректно. Данное тестирование должно быть проведено повторно согласно приведенному плану после окончания разработки проекта.