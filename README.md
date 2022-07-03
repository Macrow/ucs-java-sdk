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

### UCS服务端验证用户信息
```
Result res = client.userValidateJwt();
```

### UCS服务端验证客户端信息
```
Result res = client.clientValidate();
```

### UCS服务端验证操作或接口
```
Result res = client.userValidatePermByOperation("UCS_USER_LIST", true, true);
Result res = client.userValidatePermByAction("ucs", "get", "/api/v1/ucs/users", true, true);
```

### 向UCS服务端发起应用级调用
```
UcsResult<Object> clientRes = ucsHttpClient.clientRequest(Object.class, "GET", "/api/v1/ucs/client/validate", null, ClientAuthType.ID_AND_SECRET);
```