<!-- <
#import "parts/common.ftl" as c>
<
#import "parts/n.ftl" as l>

<
@c.page>
    <div class="mb-1">Регистрация</div>
    $
    {message?ifExists}
    <
    @l.login "/registration" true />
<
/@c.page>