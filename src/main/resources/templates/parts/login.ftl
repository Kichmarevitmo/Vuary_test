<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Avatar:</label>
                <div class="col-sm-6">
                    <input type="file" name="avatar" accept="image/*" class="form-control-file" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Gender:</label>
                <div class="col-sm-6">
                    <select name="gender" class="form-control">
                        <option value="MALE">Мужской</option>
                        <option value="FEMALE">Женский</option>
                        <option value="OTHER">Другой</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Last Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="lastname" class="form-control" placeholder="Last name" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Worker Role:</label>
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
                <label class="col-sm-2 col-form-label">City:</label>
                <div class="col-sm-6">
                    <input type="text" name="city" class="form-control" placeholder="City" />
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>