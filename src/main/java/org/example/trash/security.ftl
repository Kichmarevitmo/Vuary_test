<!-- <
#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<
#if known>
    <
    #assign
    userEntity = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = userEntity.getUsername()
    isAdmin = userEntity.isAdmin()
    >
<
#else>
    <
    #assign
    name = "unknown"
    isAdmin = false
    >
<
/#if>
-->