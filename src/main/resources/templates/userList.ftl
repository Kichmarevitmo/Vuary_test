<#import "parts/common.ftl" as c>

<@c.page>
    Список пользователей:

    <table style="border-collapse: collapse; width: 100%;">
        <thead>
        <tr style="border-bottom: 1px solid #ddd;">
            <th style="padding: 10px;">Идентификатор</th>
            <th style="padding: 10px;">Имя</th>
            <th style="padding: 10px;">Почта</th>
            <th style="padding: 10px;">Роль</th>
            <th style="padding: 10px;">Пол</th>
            <th style="padding: 10px;">Город</th>
            <th style="padding: 10px;">Фамилия</th>
            <th style="padding: 10px;">Статус работы</th>
            <th style="padding: 10px;"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr style="border-bottom: 1px solid #ddd;">
                <td style="padding: 10px;">${user.id! "null"}</td>
                <td style="padding: 10px;">${user.username! "null"}</td>
                <td style="padding: 10px;">${user.getEmail()! "null"}</td>
                <td style="padding: 10px;"><#list user.roles as role>${role}<#sep>, </#list></td>
                <td style="padding: 10px;">${user.getGender()! "null"}</td>
                <td style="padding: 10px;">${user.getCity()! "null"}</td>
                <td style="padding: 10px;">${user.getLastName()! "null"}</td>
                <td style="padding: 10px;"><#list user.workerRoles as workerRoles>${workerRoles}<#sep>, </#list></td>
                <td style="padding: 10px;"><a href="/user/${user.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
