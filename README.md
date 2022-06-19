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

### 安装，请指定版本
```
<dependency>
    <groupId>com.github.Macrow</groupId>
    <artifactId>ucs-java-sdk</artifactId>
    <version>${ucs-java-sdk.version}</version>
</dependency>
```

### 创建连接UCS的客户端
```
Client client = new HttpClient("https://your.domain.com:port", yourAccessCode"); // Http方式
client.SetUserToken(TOKEN).SetClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
```

### UCS服务端验证Jwt
```
Result res = client.UserValidateJwt();
```

### UCS服务端验证操作码
```
Result res = client.UserValidatePermOperationByCode("UCS_O_CODE");
```

### UCS服务端验证接口
```
Result res = client.UserValidatePermAction("ucs", "/api/v1/ucs/users", "get");
```

### UCS服务端验证用户是否拥有机构权限
```
Result res = client.UserValidatePermOrgById("org_id_is_here");
```

### 向UCS服务端发起应用级调用
```
UcsResult<Object> clientRes = c.ClientRequest("POST", "/api/v1/ucs/client/validate", null, ClientAuthType.ID_AND_SECRET);
```