# README #

## Task1 ##
    Многомодульный проект
        бизнес модуль с контролами.
        Фронт на анджинсе.
        Сервис формирования отчетов.
        Уведомления через почту – или через бизнес движок
        Личный кабинет моб оператора – сам пишу. Отдельный с отдельной базой. Заглушка. Можно не с постгрес стандлеон. через сокеты BDO.
        Внутренний апи клиента заглушка.    
        BDO. rest controls
    
    вебфлакс
    реактивное программирование. Транзитвиное проджект реактор.
    к пн накидать мультимодульный проект.
    строго говоря заглушки – как будто мы сами пишем будем поднимать. Эврика.
    Микросервисы. Спринг. Пострест. Вебфлакс. Реактив. Проджект менеджером.
    мавен. Тестирование. (Unit). Кукумбер. 

### Техническое задание ###
    Многомодульный проект
        eureka-server:
        internal-company-database(BDO):
            webflux<-automated-workstation
        mobile-operator-personal-account(личный кабинет мобильного оператора)
            webflux<->automated-workstation
        automated-workstation:
        internal-api-client:
        