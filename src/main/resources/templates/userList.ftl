<#import "parts/common.ftl" as c>

<@c.page>
    Список пользователей:

    <table style="border-collapse: collapse; width: 100%;">
        <thead>
        <tr style="border-bottom: 1px solid #ddd;">
            <th style="padding: 10px;">Идентификатор</th>
            <th style="padding: 10px;">Фото профиля</th>
            <th style="padding: 10px;">Имя</th>
            <th style="padding: 10px;">Почта</th>
            <th style="padding: 10px;">Роль</th>
            <th style="padding: 10px;">Пол</th>
            <th style="padding: 10px;">Город</th>
            <th style="padding: 10px;">Фамилия</th>
            <th style="padding: 10px;">Статус работы</th>
            <th style="padding: 10px;">Дата рождения</th>
            <th style="padding: 10px;"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr style="border-bottom: 1px solid #ddd;">
                <td style="padding: 10px;">${user.id! "null"}</td>
                <td style="padding: 10px;">
                    <#if user.getFirstImage()?exists && user.getFirstImage().name?has_content>
                        <img src="/user/image/${user.getFirstImage().name}" alt="Изображение котла" style="max-width: 100%; margin-top: 10px;">
                    </#if>
                </td>
                <td style="padding: 10px;">${user.username! "null"}</td>
                <td style="padding: 10px;">${user.getEmail()! "null"}</td>
                <td style="padding: 10px;"><#list user.roles as role>${role}<#sep>, </#list></td>
                <td style="padding: 10px;">${user.getGender()! "null"}</td>
                <td style="padding: 10px;">${user.getCity()! "null"}</td>
                <td style="padding: 10px;">${user.getLastName()! "null"}</td>
                <td style="padding: 10px;"><#list user.workerRoles as workerRoles>${workerRoles}<#sep>, </#list></td>
                <td style="padding: 10px;">
                    <#if user.getDateOfBirth()?has_content>
                        ${user.getDateOfBirth()?string("dd.MM.yyyy")}
                    <#else>
                        Нет данных
                    </#if>
                </td>
                <td style="padding: 10px;"><a href="/user/${user.id}">Редактировать</a></td>
                <td style="padding: 10px;"><a href="/user/delete/${user.id}" style="color: red;">Удалить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <!----------------->
    <form action="/user/addProductTOIVO" method="post" accept-charset="UTF-8" enctype="multipart/form-data"
          style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="максМинТепловаяМощностьОтопление">Макс/Мин Тепловая Мощность (Отопление):</label>
        <input type="text" id="максМинТепловаяМощностьОтопление" name="максМинТепловаяМощностьОтопление" required>

        <label for="максМинТепловаяМощностьГВС">Макс/Мин Тепловая Мощность (ГВС):</label>
        <input type="text" id="максМинТепловаяМощностьГВС" name="максМинТепловаяМощностьГВС" required>

        <label for="кпд">КПД:</label>
        <input type="text" id="кпд" name="кпд" required>

        <label for="максРасходГаза">Максимальный расход газа:</label>
        <input type="text" id="максРасходГаза" name="максРасходГаза" required>

        <label for="давлениеВоздушнойПолости">Давление воздушной полости:</label>
        <input type="text" id="давлениеВоздушнойПолости" name="давлениеВоздушнойПолости" required>

        <label for="объемРасширительногоБака">Объем расширительного бака:</label>
        <input type="text" id="объемРасширительногоБака" name="объемРасширительногоБака" required>

        <label for="давлениеВСистемеОтопления">Давление в системе отопления:</label>
        <input type="text" id="давлениеВСистемеОтопления" name="давлениеВСистемеОтопления" required>

        <label for="диапазонТемпературы">Диапазон температуры:</label>
        <input type="text" id="диапазонТемпературы" name="диапазонТемпературы" required>

        <label for="производительностьНагревГВС25">Производительность нагрева ГВС (25 градусов):</label>
        <input type="text" id="производительностьНагревГВС25" name="производительностьНагревГВС25" required>

        <label for="производительностьНагревГВС30">Производительность нагрева ГВС (30 градусов):</label>
        <input type="text" id="производительностьНагревГВС30" name="производительностьНагревГВС30" required>

        <label for="минПусковойНапорВоды">Минимальный пусковой напор воды:</label>
        <input type="text" id="минПусковойНапорВоды" name="минПусковойНапорВоды" required>

        <label for="максМинДавлениеВКонтуреГВС">Макс/Мин давление в контуре ГВС:</label>
        <input type="text" id="максМинДавлениеВКонтуреГВС" name="максМинДавлениеВКонтуреГВС" required>

        <label for="присоединительныйРазмерГазовойМагистрали">Присоединительный размер газовой магистрали:</label>
        <input type="text" id="присоединительныйРазмерГазовойМагистрали" name="присоединительныйРазмерГазовойМагистрали"
               required>

        <label for="патрубкиПодающейОбратнойЛинийОтопления">Патрубки подающей/обратной линий отопления:</label>
        <input type="text" id="патрубкиПодающейОбратнойЛинийОтопления" name="патрубкиПодающейОбратнойЛинийОтопления"
               required>

        <label for="патрубкиПодключенияХолоднойВоды">Патрубки подключения холодной воды:</label>
        <input type="text" id="патрубкиПодключенияХолоднойВоды" name="патрубкиПодключенияХолоднойВоды" required>

        <label for="номинальноеНапряжениеЧастота">Номинальное напряжение/частота:</label>
        <input type="text" id="номинальноеНапряжениеЧастота" name="номинальноеНапряжениеЧастота" required>

        <label for="потребляемаяЭлМощность">Потребляемая электрическая мощность:</label>
        <input type="text" id="потребляемаяЭлМощность" name="потребляемаяЭлМощность" required>

        <label for="присоединительныйРазмерДымохода">Присоединительный размер дымохода:</label>
        <input type="text" id="присоединительныйРазмерДымохода" name="присоединительныйРазмерДымохода" required>

        <label for="классИУровеньЗащиты">Класс и уровень защиты:</label>
        <input type="text" id="классИУровеньЗащиты" name="классИУровеньЗащиты" required>

        <label for="типДымоудаления">Тип Дымоудаления:</label>
        <input type="text" id="типДымоудаления" name="типДымоудаления" required>
        <label for="типTOIVO">Тип TOIVO:</label>
        <select id="типTOIVO" name="типTOIVO" required>
            <option value="ONE_CIRCUIT_CLOSED_CHAMBER_NO_THREE_WAY_VALVE">Одноконтурные (с закрытой камерой) без
                трёхходового клапана
            </option>
            <option value="ONE_CIRCUIT_CLOSED_CHAMBER_WITH_THREE_WAY_VALVE">Одноконтурные (с закрытой камерой) с
                трёхходовым клапаном
            </option>
            <option value="TWO_CIRCUIT_CLOSED_CHAMBER_FOR_APARTMENT_HEATING">Двухконтурные (с закрытой камерой) для
                поквартирного отопления
            </option>
        </select>

        <label for="image">Фотографии:</label>
        <input type="file" id="image" name="image" accept="image/*" required>
        <style>
            label {
                display: block;
                margin-top: 10px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
        <button type="submit"
                style="background-color: #4CAF50; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">
            Добавить TOIVO
        </button>
    </form>




    <!------------------------------------------------------------------------------------------------------------->



    <form action="/user/addProductSUARI" method="post" accept-charset="UTF-8" enctype="multipart/form-data"
          style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="типКамерыСгорания">Тип камеры сгорания:</label>
        <input type="text" id="типКамерыСгорания" name="типКамерыСгорания" required>

        <label for="модуляцияПламени">Модуляция пламени:</label>
        <input type="text" id="модуляцияПламени" name="модуляцияПламени" required>

        <label for="номинальнаяТепловаяМощность">Номинальная тепловая мощность:</label>
        <input type="text" id="номинальнаяТепловаяМощность" name="номинальнаяТепловаяМощность" required>

        <label for="коэффициентПолезногоДействияНеМенее">Коэффициент полезного действия, не менее:</label>
        <input type="text" id="коэффициентПолезногоДействияНеМенее" name="коэффициентПолезногоДействияНеМенее" required>

        <label for="номинальноеДавлениеПриродногоГаза">Номинальное давление природного газа:</label>
        <input type="text" id="номинальноеДавлениеПриродногоГаза" name="номинальноеДавлениеПриродногоГаза" required>

        <label for="номинальноеДавлениеСжиженногоГаза">Номинальное давление сжиженного газа:</label>
        <input type="text" id="номинальноеДавлениеСжиженногоГаза" name="номинальноеДавлениеСжиженногоГаза" required>

        <label for="номинальныйРасходПриродногоГаза">Номинальный расход природного газа:</label>
        <input type="text" id="номинальныйРасходПриродногоГаза" name="номинальныйРасходПриродногоГаза" required>

        <label for="номинальныйРасходСжиженногоГаза">Номинальный расход сжиженного газа:</label>
        <input type="text" id="номинальныйРасходСжиженногоГаза" name="номинальныйРасходСжиженногоГаза" required>

        <label for="давлениеПодводимойВодыДляНормальнойРаботыАппарата">Давление подводимой воды для нормальной работы
            аппарата:</label>
        <input type="text" id="давлениеПодводимойВодыДляНормальнойРаботыАппарата"
               name="давлениеПодводимойВодыДляНормальнойРаботыАппарата" required>

        <label for="минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки">Минимальный расход воды, необходимый для
            зажигания горелки:</label>
        <input type="text" id="минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки"
               name="минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки" required>

        <label for="расходВодыПриНагревеНа25">Расход воды при нагреве на ΔT = 25°C:</label>
        <input type="text" id="расходВодыПриНагревеНа25" name="расходВодыПриНагревеНа25" required>

        <label for="температураПродуктовСгоранияНеМенее">Температура продуктов сгорания, не менее:</label>
        <input type="text" id="температураПродуктовСгоранияНеМенее" name="температураПродуктовСгоранияНеМенее" required>

        <label for="зажигание">Зажигание:</label>
        <input type="text" id="зажигание" name="зажигание"
               required>

        <label for="типИНапряжениеЭлементовПитания">Тип и напряжение элементов питания:</label>
        <input type="text" id="типИНапряжениеЭлементовПитания" name="типИНапряжениеЭлементовПитания"
               required>

        <label for="напряжениеИЧастота">Напряжение и частота:</label>
        <input type="text" id="напряжениеИЧастота" name="напряжениеИЧастота" required>

        <label for="входХолоднойВоды">Вход холодной воды:</label>
        <input type="text" id="входХолоднойВоды" name="входХолоднойВоды" required>

        <label for="выходГорячейВоды">Выход горячей воды:</label>
        <input type="text" id="выходГорячейВоды" name="выходГорячейВоды" required>

        <label for="входГаза">Вход газа:</label>
        <input type="text" id="входГаза" name="входГаза" required>

        <label for="массаБрутто">Масса, брутто:</label>
        <input type="text" id="массаБрутто" name="массаБрутто" required>

        <label for="габаритныеРазмеры">Габаритные размеры (высота, ширина, глубина):</label>
        <input type="text" id="габаритныеРазмеры" name="габаритныеРазмеры" required>

        <label for="внутреннийДиаметрПатрубкаДымохода">Внутренний диаметр патрубка дымохода:</label>
        <input type="text" id="внутреннийДиаметрПатрубкаДымохода" name="внутреннийДиаметрПатрубкаДымохода" required>

        <label for="типSUARI">Тип SUARI:</label>
        <select id="типSUARI" name="типSUARI" required>
            <option value="CLASSIC_MODELS_MECHANICAL_CONTROL">Классические модели с механической регулировкой</option>
            <option value="CLASSIC_MODELS_ELECTRONIC_FLAME_MODULATION">Классические модели с электронной модуляцией
                пламени
            </option>
            <option value="SEMI_TURBO">Полутурбо</option>
            <option value="TURBO">Турбо</option>
        </select>

        <label for="imageSUARI">Фотографии:</label>
        <input type="file" id="imageSUARI" name="imageSUARI" accept="image/*" required>
        <style>
            label {
                display: block;
                margin-top: 10px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
        <button type="submit"
                style="background-color: #4CAF50; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">
            Добавить SUARI
        </button>
    </form>




    <!---------------------------------------------------------------------------------- -->

    <form action="/user/addProductSALMI" method="post" accept-charset="UTF-8" enctype="multipart/form-data"
          style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="объем">Объем:</label>
        <input type="text" id="объем" name="объем" required>

        <label for="подключениеКСетиВодоснабжения">Подключение к сети водоснабжения:</label>
        <input type="text" id="подключениеКСетиВодоснабжения" name="подключениеКСетиВодоснабжения" required>

        <label for="мощность">Мощность:</label>
        <input type="text" id="мощность" name="мощность" required>

        <label for="напряжениеИЧастотаSALMI">Напряжение и частота:</label>
        <input type="text" id="напряжениеИЧастотаSALMI" name="напряжениеИЧастотаSALMI" required>

        <label for="силаТока">Сила тока:</label>
        <input type="text" id="силаТока" name="силаТока" required>

        <label for="рабочееДавлениеТеплоносителя">Рабочее давление теплоносителя:</label>
        <input type="text" id="рабочееДавлениеТеплоносителя" name="рабочееДавлениеТеплоносителя" required>

        <label for="максимальнаяТемпература">Максимальная температура:</label>
        <input type="text" id="максимальнаяТемпература" name="максимальнаяТемпература" required>

        <label for="термостат">Термостат:</label>
        <input type="text" id="термостат" name="термостат" required>

        <label for="аварийныйТермодатчик">Аварийный термодатчик:</label>
        <input type="text" id="аварийныйТермодатчик"
               name="аварийныйТермодатчик" required>

        <label for="уровеньВлагозащиты">Уровень влагозащиты:</label>
        <input type="text" id="уровеньВлагозащиты"
               name="уровеньВлагозащиты" required>

        <label for="нагревательныйЭлемент">Нагревательный элемент:</label>
        <input type="text" id="нагревательныйЭлемент" name="нагревательныйЭлемент" required>

        <label for="размерАнода">Размер анода:</label>
        <input type="text" id="размерАнода" name="размерАнода" required>

        <label for="входХолоднойВоды">Вход холодной воды:</label>
        <input type="text" id="входХолоднойВоды" name="входХолоднойВоды"
               required>

        <label for="выходГорячейВоды">Выход горячей воды:</label>
        <input type="text" id="выходГорячейВоды" name="выходГорячейВоды"
               required>

        <label for="габаритныеРазмеры">Габаритные размеры (высота, ширина, глубина):</label>
        <input type="text" id="габаритныеРазмеры" name="габаритныеРазмеры" required>

        <label for="типSALMI">Тип SALMI:</label>
        <select id="типSALMI" name="типSALMI" required>
            <option value="SMALL_CAPACITY">Малый литраж</option>
            <option value="ROUND_ECONOMY">Круглые эконом
            </option>
            <option value="ROUND_COMFORT">Круглые комфорт</option>
            <option value="FLAT_COMFORT">Плоские комфорт</option>
            <option value="FLAT_PREMIUM">Плоские премиум</option>
            <option value="LARGE_CAPACITY">Большой литраж</option>
        </select>

        <label for="imageSALMI">Фотографии:</label>
        <input type="file" id="imageSALMI" name="imageSALMI" accept="image/*" required>
        <style>
            label {
                display: block;
                margin-top: 10px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
        <button type="submit"
                style="background-color: #4CAF50; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">
            Добавить SALMI
        </button>
    </form>



    <!---------------------------------------------------------------------------------- -->

    <form action="/user/addProductAINOVA" method="post" accept-charset="UTF-8" enctype="multipart/form-data"
          style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="мощностьAINOVA">Мощность:</label>
        <input type="text" id="мощностьAINOVA" name="мощностьAINOVA" required>

        <label for="напряжениеИЧастота">Напряжение и частота:</label>
        <input type="text" id="напряжениеИЧастота" name="напряжениеИЧастота" required>

        <label for="количествоСтупенейМощности">Количество ступеней мощности:</label>
        <input type="text" id="количествоСтупенейМощности" name="количествоСтупенейМощности" required>

        <label for="номинальныйТокАвтоматическогоВыключателя">Номинальный ток автоматического выключателя:</label>
        <input type="text" id="номинальныйТокАвтоматическогоВыключателя" name="номинальныйТокАвтоматическогоВыключателя" required>

        <label for="сечениеТокопроводящейЖилы">Сечение токопроводящей жилы:</label>
        <input type="text" id="сечениеТокопроводящейЖилы" name="сечениеТокопроводящейЖилы" required>

        <label for="wiFi">Wi-Fi:</label>
        <input type="text" id="wiFi" name="wiFi" required>

        <label for="кпд">Коэффициент полезного действия, не менее:</label>
        <input type="text" id="кпд" name="кпд" required>

        <label for="способУправления">Способ управления:</label>
        <input type="text" id="способУправления" name="способУправления" required>

        <label for="использованиеВСистемахТёплыйПол">Использование в системах «Тёплый пол»:</label>
        <input type="text" id="использованиеВСистемахТёплыйПол"
               name="использованиеВСистемахТёплыйПол" required>

        <label for="функционалГВС">Функционал ГВС:</label>
        <input type="text" id="функционалГВС"
               name="функционалГВС" required>

        <label for="встроенныйВоздухоотводчик">Встроенный воздухоотводчик:</label>
        <input type="text" id="встроенныйВоздухоотводчик" name="встроенныйВоздухоотводчик" required>

        <label for="встроенныйПредохранительныйКлапан">Встроенный предохранительный клапан:</label>
        <input type="text" id="встроенныйПредохранительныйКлапан" name="встроенныйПредохранительныйКлапан" required>

        <label for="встроенныйЦиркуляционныйНасос">Встроенный циркуляционный насос:</label>
        <input type="text" id="встроенныйЦиркуляционныйНасос" name="встроенныйЦиркуляционныйНасос"
               required>

        <label for="расширительныйБак">Расширительный бак:</label>
        <input type="text" id="расширительныйБак" name="расширительныйБак"
               required>

        <label for="настройкаРасписанияAINOVA">Настройка расписания:</label>
        <input type="text" id="настройкаРасписанияAINOVA" name="настройкаРасписанияAINOVA" required>

        <label for="погодозависимоеУправление">Погодозависимое управление:</label>
        <input type="text" id="погодозависимоеУправление" name="погодозависимоеУправление" required>

        <label for="возможностьПодключенияОборудования">Возможность подключения оборудования:</label>
        <input type="text" id="возможностьПодключенияОборудования" name="возможностьПодключенияОборудования" required>

        <label for="рабочееДавлениеТеплоносителя">Рабочее давление теплоносителя:</label>
        <input type="text" id="рабочееДавлениеТеплоносителя" name="рабочееДавлениеТеплоносителя" required>

        <label for="диапазонРегулированияТемпературыТеплоносителя">Диапазон регулирования температуры теплоносителя:</label>
        <input type="text" id="диапазонРегулированияТемпературыТеплоносителя" name="диапазонРегулированияТемпературыТеплоносителя" required>

        <label for="диапазонРегулированияТемпературыВоды">Диапазон регулирования температуры воды:</label>
        <input type="text" id="диапазонРегулированияТемпературыВоды" name="диапазонРегулированияТемпературыВоды" required>

        <label for="классВлагозащищенности">Класс влагозащищенности:</label>
        <input type="text" id="классВлагозащищенности" name="классВлагозащищенности" required>

        <label for="контурОтопленияПодающаяЛиния">Контур отопления подающая линия:</label>
        <input type="text" id="контурОтопленияПодающаяЛиния" name="контурОтопленияПодающаяЛиния" required>

        <label for="контурОтопленияОбратнаяЛиния">Контур отопления обратная линия:</label>
        <input type="text" id="контурОтопленияОбратнаяЛиния" name="контурОтопленияОбратнаяЛиния" required>

        <label for="массаАппаратаБрутто">Контур отопления Масса аппарата, брутто:</label>
        <input type="text" id="массаАппаратаБрутто" name="массаАппаратаБрутто" required>

        <label for="габаритныеРазмеры">Габаритные размеры (высота, ширина, глубина)</label>
        <input type="text" id="габаритныеРазмеры" name="габаритныеРазмеры" required>

        <label for="типAINOVA">Тип AINOVA:</label>
        <select id="типAINOVA" name="типAINOVA" required>
            <option value="STANDARD">Стандартные
            </option>
            <option value="MINI_BOILERS">Мини-котлы</option>
        </select>

        <label for="imageAINOVA">Фотографии:</label>
        <input type="file" id="imageAINOVA" name="imageAINOVA" accept="image/*" required>
        <style>
            label {
                display: block;
                margin-top: 10px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
        <button type="submit"
                style="background-color: #4CAF50; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">
            Добавить AINOVA
        </button>
    </form>
    <!------------------------------------------------------------------->
    <h4>Котлы AINOVA настенные электрические список:</h4>

    <#if allAinova?exists>
        <#list allAinova as ainova>
            <div style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
                <p>Мощность: ${ainova.мощность}</p>
                <p>Напряжение и частота: ${ainova.напряжениеИЧастота}</p>
                <p>Количество ступеней мощности: ${ainova.напряжениеИЧастота}</p>
                <p>Номинальный ток автоматического выключателя: ${ainova.номинальныйТокАвтоматическогоВыключателя}</p>
                <p>Сечение токопроводящей жилы: ${ainova.сечениеТокопроводящейЖилы}</p>
                <p>Wi-Fi: ${ainova.wiFi}</p>
                <p>Коэффициент полезного действия, не менее: ${ainova.коэффициентПолезногоДействия}</p>
                <p>Способ управления: ${ainova.способУправления}</p>
                <p>Использование в системах «Тёплый пол»: ${ainova.использованиеВСистемахТеплыйПол}</p>
                <p>Функционал ГВС: ${ainova.функционалГВС}</p>
                <p>Встроенный воздухоотводчик: ${ainova.встроенныйВоздухоотводчик}</p>
                <p>Встроенный предохранительный клапан: ${ainova.встроенныйПредохранительныйКлапан}</p>
                <p>Встроенный циркуляционный насос: ${ainova.встроенныйЦиркуляционныйНасос}</p>
                <p>Расширительный бак: ${ainova.расширительныйБак}</p>
                <p>Настройка расписания: ${ainova.настройкаРасписания}</p>
                <p>Погодозависимое управление: ${ainova.погодозависимоеУправление}</p>
                <p>Возможность подключения оборудования: ${ainova.возможностьПодключенияОборудования}</p>
                <p>Рабочее давление теплоносителя: ${ainova.рабочееДавлениеТеплоносителя}</p>
                <p>Диапазон регулирования температуры теплоносителя: ${ainova.диапазонРегулированияТемпературыТеплоносителя}</p>
                <p>Диапазон регулирования температуры воды: ${ainova.диапазонРегулированияТемпературыВоды}</p>
                <p>Класс влагозащищенности: ${ainova.классВлагозащищенности}</p>
                <p>Контур отопления подающая линия: ${ainova.контурОтопленияПодающаяЛиния}</p>
                <p>Контур отопления обратная линия: ${ainova.контурОтопленияОбратнаяЛиния}</p>
                <p>Масса аппарата, брутто: ${ainova.масса}</p>
                <p>Габаритные размеры (высота, ширина, глубина): ${ainova.габаритныеРазмеры}</p>
            </div>
        </#list>
    <#else>
        <p>${message}</p>
    </#if>
    <!------------------------------------------------------------------->
    <h4>Водонагреватели SALMI электрические накопительные:</h4>

    <#if allSalmi?exists>
        <#list allSalmi as salmi>
            <div style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
                <#if salmi.getFirstImage()?exists && salmi.getFirstImage().name?has_content>
                    <img src="/user/image/${salmi.getFirstImage().name}" alt="Изображение котла" style="max-width: 100%; margin-top: 10px;">
                </#if>
                <p>Объем: ${salmi.объем}</p>
                <p>Подключение к сети водоснабжения: ${salmi.подключениеКСетиВодоснабжения}</p>
                <p>Мощность: ${salmi.напряжениеИЧастота}</p>
                <p>Напряжение и частота: ${salmi.напряжениеИЧастота}</p>
                <p>Сила тока: ${salmi.силаТока}</p>
                <p>Рабочее давление теплоносителя: ${salmi.рабочееДавлениеТеплоносителя}</p>
                <p>Максимальная температура: ${salmi.максимальнаяТемпература}</p>
                <p>Термостат: ${salmi.термостат}</p>
                <p>Аварийный термодатчик: ${salmi.аварийныйТермодатчик}</p>
                <p>Уровень влагозащиты: ${salmi.уровеньВлагозащиты}</p>
                <p>Нагревательный элемент: ${salmi.нагревательныйЭлемент}</p>
                <p>Размер анода: ${salmi.размерАнода}</p>
                <p>Вход холодной воды: ${salmi.входХолоднойВоды}</p>
                <p>Выход горячей воды: ${salmi.выходГорячейВоды}</p>
                <p>Габаритные размеры (высота, ширина, глубина): ${salmi.габаритныеРазмеры}</p>
            </div>
        </#list>
    <#else>
        <p>${message}</p>
    </#if>
    <!------------------------------------------------------------------->
    <h4>Водонагреватели SUARI проточные газовые:</h4>

    <#if allSuari?exists>
        <#list allSuari as suari>
            <div style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
                <#if suari.getFirstImage()?exists && suari.getFirstImage().name?has_content>
                    <img src="/user/image/${suari.getFirstImage().name}" alt="Изображение котла" style="max-width: 100%; margin-top: 10px;">
                </#if>
                <p>Тип камеры сгорания: ${suari.типКамерыСгорания}</p>
                <p>Модуляция пламени: ${suari.модуляцияПламени}</p>
                <p>Номинальная тепловая мощность: ${suari.номинальнаяТепловаяМощность}</p>
                <p>Коэффициент полезного действия, не менее: ${suari.коэффициентПолезногоДействия}</p>
                <p>Номинальное давление природного газа: ${suari.номинальноеДавлениеПриродногоГаза}</p>
                <p>Номинальное давление сжиженного газа: ${suari.номинальноеДавлениеСжиженногоГаза}</p>
                <p>Номинальный расход природного газа: ${suari.номинальныйРасходПриродногоГаза}</p>
                <p>Номинальный расход сжиженного газа: ${suari.номинальныйРасходСжиженногоГаза}</p>
                <p>Давление подводимой воды для нормальной работы аппарата: ${suari.давлениеПодводимойВоды}</p>
                <p>Минимальный расход воды, необходимый для зажигания горелки: ${suari.минимальныйРасходВодыДляЗажигания}</p>
                <p>Расход воды при нагреве на ΔT = 25°C: ${suari.расходВодыПриНагревеНаДельтаT25}</p>
                <p>Температура продуктов сгорания, не менее: ${suari.температураПродуктовСгорания}</p>
                <p>Зажигание: ${suari.зажиганиеАвтоматическое}</p>
                <p>Тип и напряжение элементов питания: ${suari.типИНапряжениеЭлементовПитания}</p>
                <p>Напряжение и частота: ${suari.напряжениеИЧастота}</p>
                <p>Вход холодной воды: ${suari.входХолоднойВоды}</p>
                <p>Выход горячей воды: ${suari.выходГорячейВоды}</p>
                <p>Вход газа: ${suari.входГаза}</p>
                <p>Масса, брутто: ${suari.масса}</p>
                <p>Габаритные размеры (высота, ширина, глубина): ${suari.габаритныеРазмеры}</p>
                <p>Внутренний диаметр патрубка дымохода: ${suari.внутреннийДиаметрПатрубкаДымохода}</p>
            </div>
        </#list>
    <#else>
        <p>${message}</p>
    </#if>
    <!------------------------------------------------------------------->
    <h4>Котлы TOIVO настенные газовые:</h4>

    <#if allToivo?exists>
        <#list allToivo as toivo>
            <div style="max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; margin: 20px 0 0 20px;">
                <#if toivo.getFirstImage()?exists && toivo.getFirstImage().name?has_content>
                    <img src="/user/image/${toivo.getFirstImage().name}" alt="Изображение котла" style="max-width: 100%; margin-top: 10px;">
                </#if>
                <p>Тип дымоудаления: ${toivo.типДымоудаления}</p>
                <p>Макс./мин. тепловая мощность в режиме отопление: ${toivo.максМинТепловаяМощностьОтопление}</p>
                <p>Макс./мин. тепловая мощность в режиме ГВС: ${toivo.максМинТепловаяМощностьГВС}</p>
                <p>КПД не менее: ${toivo.кпд}</p>
                <p>Максимальный расход природного газа: ${toivo.максРасходГаза}</p>
                <p>Давление в воздушной полости расширительного бака: ${toivo.давлениеВоздушнойПолости}</p>
                <p>Объем расширительного бака: ${toivo.объемРасширительногоБака}</p>
                <p>Давление в системе отопления: ${toivo.давлениеВСистемеОтопления}</p>
                <p>Диапазон регулировки температуры бытовой горячей воды: ${toivo.диапазонТемпературы}</p>
                <p>Производительность по нагреву горячей воды (при △T=25℃): ${toivo.производительностьНагревГВС25}</p>
                <p>Производительность по нагреву горячей воды (при △T=30℃): ${toivo.производительностьНагревГВС30}</p>
                <p>Минимальный пусковой напор воды: ${toivo.минПусковойНапорВоды}</p>
                <p>Максимальное давление в контуре бойлера косвенного нагрева: ${toivo.максМинДавлениеВКонтуреГВС}</p>
                <p>Присоединительный размер газовой магистрали: ${toivo.присоединительныйРазмерГазовойМагистрали}</p>
                <p>Патрубки подключения подающей и обратной линий системы отопления: ${toivo.патрубкиПодающейОбратнойЛинийОтопления}</p>
                <p>Патрубки подключения холодной воды и бойлера косвенного нагрева: ${toivo.патрубкиПодключенияХолоднойВоды}</p>
                <p>Номинальное напряжение/частота: ${toivo.номинальноеНапряжениеЧастота}</p>
                <p>Потребляемая эл. мощность: ${toivo.потребляемаяЭлМощность}</p>
                <p>Присоединительный размер дымохода: ${toivo.присоединительныйРазмерДымохода}</p>
                <p>Класс и уровень защиты: ${toivo.классИУровеньЗащиты}</p>
            </div>
        </#list>
    <#else>
        <p>${message}</p>
    </#if>
    <!------------------------------------------------------------------->
</@c.page>
