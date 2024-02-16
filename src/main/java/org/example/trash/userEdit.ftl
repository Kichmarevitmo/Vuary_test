<!-- <
#import "parts/common.ftl" as c>


c.page>
    <div style="max-width: 600px; margin: auto;">
        <h2 style="text-align: center;">Редактирование пользователя:</h2>

        <form action="/userEntity" method="post" style="margin-top: 20px;">
            <input type="hidden" value="$
            {userEntity.id}" name="userId">
            <input type="hidden" value="$
            {_csrf.token}" name="_csrf">

            <div style="margin-bottom: 10px;">
                <label for="username">Имя пользователя:</label>
                <input type="text" id="username" name="username" value="$
                {userEntity.username}" style="width: 100%;">
            </div>

            <div style="margin-bottom: 10px;">
                <label for="email">Почта:</label>
                <input type="text" id="email" name="email" value="$
                {userEntity.getEmail()?default("")}" style="width: 100%;">
            </div>

            <div style="margin-bottom: 10px;">
                <label for="gender">Пол:</label>
                <select id="gender" name="gender" style="width: 100%;">
                    <option value="MALE" <
                    #if (userEntity.getGender()!("")) == "MALE">selected<
                    /#if>>Мужской</option>
                    <option value="FEMALE" <
                    #if (userEntity.getGender()!("")) == "FEMALE">selected<
                    /#if>>Женский</option>
                </select>
            </div>

            <div style="margin-bottom: 10px;">
                <label for="city">Город:</label>
                <input type="text" id="city" name="city" value="$
                {userEntity.getCity()?default("")}" style="width: 100%;">
            </div>

            <div style="margin-bottom: 10px;">
                <label for="lastName">Фамилия:</label>
                <input type="text" id="lastName" name="lastName" value="$
                {userEntity.getLastName()?default("")}" style="width: 100%;">
            </div>

            <div style="margin-bottom: 10px;">
                <label for="workerRoles">Роли работника:</label>
                <select id="workerRoles" name="workerRoles" style="width: 100%;">
                    <option value="RETAIL_CUSTOMER" <
                    #if (userEntity.getWorkerRoles()?first!("")) == "RETAIL_CUSTOMER">selected<
                    /#if>>Розничный покупатель</option>
                    <option value="SELLER" <
                    #if (userEntity.getWorkerRoles()?first!("")) == "SELLER">selected<
                    /#if>>Продавец</option>
                    <option value="INSTALLER" <
                    #if (userEntity.getWorkerRoles()?first!("")) == "INSTALLER">selected<
                    /#if>>Монтажник</option>
                    <option value="GRO_SPECIALIST" <
                    #if (userEntity.getWorkerRoles()?first!("")) == "GRO_SPECIALIST">selected<
                    /#if>>Специалист ГРО</option>
                    <option value="ACS_SPECIALIST" <
                    #if (userEntity.getWorkerRoles()?first!("")) == "ACS_SPECIALIST">selected<
                    /#if>>Специалист АСЦ</option>
                </select>
            </div>

            <div style="text-align: center;">
                <button type="submit" style="padding: 10px 20px; font-size: 16px;">Сохранить</button>
            </div>
        </form>
    </div>
<
/@c.page>
