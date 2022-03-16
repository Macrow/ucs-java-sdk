# ucs-java-sdk

用于集成```ucs```的开发包

## 快速开始

### 安装
```
<dependency>
    <groupId>com.zyys</groupId>
    <artifactId>ucs-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 验证Jwt
```
Validator v = new Validator(KEY);
JwtUser jwtUser = v.ValidateJwt(TOKEN);
```

### 创建连接UCS的客户端
```
Client client = new Client("your.domain.com", yourPort);
// Client client = new Client(certFile, "your.domain.com", yourPort) // TLS连接，需要UCS服务也同时开启
client.SetToken(token)
```

### UCS服务端验证Jwt
```
ValidateResult res = client.ValidateJwt();
```

### UCS服务端验证操作码
```
ValidateResult res = client.ValidatePermOperationByCode("UCS_O_CODE");
```

### UCS服务端验证接口
```
ValidateResult res = client.ValidatePermAction("ucs", "/api/v1/ucs/users", "get");
```

### UCS服务端验证用户是否拥有机构权限
```
ValidateResult res = client.ValidatePermOrgById("org_id_is_here");
```
