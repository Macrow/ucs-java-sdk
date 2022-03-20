# ucs-java-sdk
[![](https://jitpack.io/v/Macrow/ucs-java-sdk.svg)](https://jitpack.io/#Macrow/ucs-java-sdk)

用于集成```ucs```的开发包

## 快速开始

### 添加安装源
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### 安装
```
dependency>
    <groupId>com.github.Macrow</groupId>
    <artifactId>ucs-java-sdk</artifactId>
    <version>1.3.0</version>
</dependency>
```

### 验证Jwt
```
Validator v = new Validator(KEY);
JwtUser jwtUser = v.ValidateJwt(TOKEN);
```

### 创建连接UCS的客户端
```
Client client = new RpcClient("your.domain.com:port"); // Rpc方式
// Client client = new RpcClient(certFile, "your.domain.com:port") // TLS连接，需要UCS服务也同时开启
// Client client = new HttpClient("https://your.domain.com:port", yourAccessCode"); // Http方式
client.SetToken(token)
```

### 如果令牌过期了，重新获取令牌
```
Result res = client.RenewToken();
```

### UCS服务端验证Jwt
```
Result res = client.ValidateJwt();
```

### UCS服务端验证操作码
```
Result res = client.ValidatePermOperationByCode("UCS_O_CODE");
```

### UCS服务端验证接口
```
Result res = client.ValidatePermAction("ucs", "/api/v1/ucs/users", "get");
```

### UCS服务端验证用户是否拥有机构权限
```
Result res = client.ValidatePermOrgById("org_id_is_here");
```
