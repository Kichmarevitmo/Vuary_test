<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <!-- -->

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Почта:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="example@example.com" />
            </div>
        </div>
        <!-- -->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль" />
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Фото профиля:</label>
                <div class="col-sm-6">
                    <input type="file" name="avatar" accept="image/*" class="form-control-file" />
                </div>
            </div>
            <!-- -->
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Имя :</label>
                <div class="col-sm-6">
                    <input type="text" name="username" class="form-control" placeholder="Имя" />
                </div>
            </div>
            <!-- -->
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Пол:</label>
                <div class="col-sm-6">
                    <select name="gender" class="form-control">
                        <option value="MALE">Мужской</option>
                        <option value="FEMALE">Женский</option>
                        <option value="OTHER">Другой</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Фамилия:</label>
                <div class="col-sm-6">
                    <input type="text" name="lastname" class="form-control" placeholder="Фамилия" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Статус работы с приложением:</label>
                <div class="col-sm-6">
                    <select name="workerRole" class="form-control">
                        <option value="RETAIL_CUSTOMER">Розничный покупатель</option>
                        <option value="SELLER">Продавец</option>
                        <option value="INSTALLER">Монтажник</option>
                        <option value="GRO_SPECIALIST">Специалист ГРО</option>
                        <option value="ACS_SPECIALIST">Специалист АСЦ</option>
                    </select>
                </div>
            </div>

            <#if _csrf??>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </#if>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Город:</label>
                <div class="col-sm-6">
                    <input type="text" name="city" class="form-control" placeholder="Город" />
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Создать<#else>Войти</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>