<!-- <
#include "security.ftl">
<
#
import "n.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Kotitonttu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Messages</a>
            </li>
#if isAdmin
                <li class="nav-item">
                    <a class="nav-link" href="/userEntity">Список пользователей / котлы</a>
                </li>
/#if
        </ul>

        <div class="navbar-text mr-3">$
        {name}</div>
        <
        @l.logout />
    </div>
</nav>