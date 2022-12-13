# 1 Требования пользователя


## 1.1 Программные интерфейсы
Приложение обрабатывает данные из api НБРБ. 


## 1.2 Интерфейс пользователя

<details>
<summary>Главный экран.</summary>

![Главный экран](https://user-images.githubusercontent.com/70812017/199709589-0a0aa487-5bc1-4f06-8ecc-fa0d20c95226.jpg)

</details>
  
 <details>
<summary>Динамика изменения курса валюты.</summary>

![Динамика](https://user-images.githubusercontent.com/70812017/199709665-e801408b-0385-4815-a802-b436a02deb2c.jpg) 

</details>

  <details>
<summary>Экран настроек.</summary>

![Экран настройки](https://user-images.githubusercontent.com/70812017/199709707-c49f15a8-3980-47fb-b8bf-c339e9609f36.jpg)

</details>


## 1.3 Характеристики пользователей

### 1.3.1 Классы пользователей

| Класс пользователей | Описание |
|:---|:---|
| Анонимные пользователи | Все пользователи, приложение не требует авторизации |

# 2 Системные требования

<a name="functional_requirements"/>

## 2.1 Функциональные требования

<a name="main_functions"/>

### 2.1.1 Основные функции

<a name="user_logon_to_the_application"/>

#### 2.1.1.1 Вход пользователя в приложение
**Описание.** Приложение не требует авторизации и запускается сразу на главном экране.

<a name="download_products"/>

#### 2.1.1.2 Загрузка валют
**Описание.** После входа пользователя в приложение необходимо загрузить список валют и курсы к ним.

| Функция | Требования | 
|:---|:---|
| Загрузка списка валют и курсов | Приложение должно загрузить список валют и курсов к ним. В случае ошибки или отсутствия интеренета должно быть выведено соответствующее сообщение|

<a name="download_product"/>

#### 2.1.1.3 Просмотр информации о динамике изменения курса валюты
**Описание.** Пользователь имеет возможность просмотреть информацию о данамике изменения курса отдельной валюты в течении 30 дней.

| Функция | Требования | 
|:---|:---|
| Просмотр информации | Пользователь имеет возможность выбрать курс в таблице одинарным кликом по нему. Приложение должно отобразить его динамику изменения в течении 30 дней|

<a name="add_to_cart"/>

#### 2.1.1.4 Настройки отображения
**Описание.** Пользователь имеет возможность настроить под себя отображение курсов.

| Функция | Требования | 
|:---|:---|
| Настройка курсов | Пользователь имеет возможность настроить отображение курсов под себя. Включить или выключить их отображение на главное странице, а так же поменять их положение.|

### 2.1.2 Ограничения и исключения
1. Обновление происходит только при наличии подключения к Интернету;

<a name="non-functional_requirements"/>

## 2.2 Нефункциональные требования

<a name="quality_attributes"/>

### 2.2.1 Атрибуты качества

<a name="requirements_for_ease_of_use"/>

#### 2.2.1.1 Требования к удобству использования
1. Доступ к основным функциям приложения не более чем за одну операции;
2. Все функциональные элементы пользовательского интерфейса имеют названия и иконки, описывающие действие, которое произойдет при выборе элемента;
3. Обновление информации о курсах валют происходит при каждом новом запуске приложения.

<a name="external_interfaces"/>

### 2.2.2 Внешние интерфейсы
Окна приложения удобны для использования пользователями с плохим зрением:
  * размер шрифта не менее 14пт;
  * функциональные элементы контрастны фону окна.

<a name="restrictions"/>

### 2.2.3 Ограничения
1. Платформв Android с использованием языка Kotlin 1.7
2. Библиотека retrofit 2.9
3. Минимальная версия Android 6.0
4. Использование api НБРБ
5. База данных SQLite
6. Библиотека для базы данных room 2.4

# Use case

## 1 Актёры 

| Актёр | Описание |
|:--|:--|
| Пользователь | Анонимный пользователь |
| Клиентское приложение | Интерфейс |
| Сервер | Обработка запросов |
| База данных | База для хранения курсов валют на 2 дня |

## 2 Варианты использования

![Untitled (3)](https://user-images.githubusercontent.com/70812017/204878849-cf781109-4218-4323-90aa-974ca5991a97.png)

### 2.1 Просмотр курсов валют

 **Описание.** Вариант использования "Просмотр курсов валют" позволяет пользователю просмотреть курсы валют.
 
1. Пользователь открывает приложение;
2. Клиентское приложение отправляет запрос к базе данных;
3. Если в базе данных хранятся аткуальные данные, возвращает их клиентскому приложению;
4. Если в базе данных хранятся не актуальные данные, и если есть интернет соединение, отправляет запрос на сервер;
5. Сервер возвращает актуальные данные;
6. База данных сохраняет данные и возвращает их клиентскому приложению;
7. Если интернет соединение отстутствует, возвращает устаревшие данные;
8. Клиенсткое приложение выводит данные;
8. Конец варианта использования.

# Activity

Диаграмма активности в соответствии с вариантом использования.

![Activity vpd](https://user-images.githubusercontent.com/70812017/204910103-b1009a3a-9b44-4190-b569-aebf2781a249.png)

# Sequence

Диаграмма последовательности в соответствии с вариантом использования.

![image](https://user-images.githubusercontent.com/70812017/204915197-97270160-ba4a-4780-a723-14197542987e.png)

# State

Диаграмма состояний в соответствии с вариантом использования.

![image](https://user-images.githubusercontent.com/70812017/204915371-0da596ee-cebf-4951-8131-3b1a97bea4cf.png)

# Class daigram

Диграмма классов приложения 

![Untitled](https://user-images.githubusercontent.com/70812017/206169766-bae34e9b-fb7c-4e02-80a8-93259d208f47.png)

# Database schema

Схема базы данных приложения

![image](https://user-images.githubusercontent.com/70812017/204917232-f76be085-59bb-43ed-8dd1-ba6e884da802.png)

# Components

![Components vpd](https://user-images.githubusercontent.com/70812017/204994116-e29f7caa-6620-46e0-803c-da679d612d2d.png)
