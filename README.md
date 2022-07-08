# Rick and Morty api app
Android intensive course project

Приложение разрабатывается в соотвествии с Clean Architecture и с архитектурой MVVM.

### Реализованы следующие основные требования к приложению:

1. Splash Screen - показывается как заставка при запуске приложения;
2. Пользовательский интерфейс основного экрана;
3. Domain слой из Clean Architecture;
4. Data слой из Clean Architecture;
5. Два Unit теста;
6. Пагинация на списке персонажей.</br></br>

## Подробная информация

### Реализация Splash Screen
Реализована отдельной Activity. В Манифесте тема и android.intent.category.LAUNCHER. 

### Реализация пользовательского интерфейса
В MainActivity фрагмент контейнер. 3 фрагмента для списков, в них Bottom Navigation. 3 фрагмента деталей. Навигация через FragmentManager.

### Реализация Unit тестов
Класс для тестировани корутин и класс для тестирования UI модели.

### Реализация Pagination
Использована библиотека Paging 3 при помощи RemoteMediator

### Реализация Domain слоя
Содержит Model, Repository, UseCases не были встроены

### Реализация Data слоя
Содержит реализацию Room, ApiService, RemoteMediators, RepositoryImpl, Mappers
