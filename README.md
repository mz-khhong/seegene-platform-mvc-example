# seegene-platform-mvc-example

# KeyCloak Info  
- `Version : 20.0.5`
- `Realm : seegene-platform`
- `Client : platform-client, insilico-client`
- `Roles ... `
- Configuration > {http://localhost:9080}/realms/seegene-platform/.well-known/openid-configuration
- Token 발급 > {http://localhost:9080}/realms/seegene-platform/protocol/openid-connect/token
> [Command] \
>  curl -X POST "http://localhost:18080/auth/realms/my_realm/protocol/openid-connect/token" \
--header "Content-Type: application/x-www-form-urlencoded" \
--data-urlencode "grant_type=password" \
--data-urlencode "client_id={my_client}" \
--data-urlencode "client_secret={secretKey}" \
--data-urlencode "username={user}" \
--data-urlencode "password={xxxxx}" |

> [Postman] \
> Content-Type: x-www-form-urlencoded \
> key: value \
> grant_type: password \
> scope: openid \
> client_id: {my_client} \
> client_secret: {secretKey} \
> username: {user} \
> password: {xxxxx}

# [TOBE] OTP Info..  