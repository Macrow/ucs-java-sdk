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
Client c = new UcsHttpClient("https://localhost:8019");
c = c.setAccessCode(ACCESS_CODE).setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
```

### UCS服务端验证用户信息
```
UcsResult res = c.setRandomKey(UcsHttpClient.generateRandomKey()).userValidateJwt();
```

### UCS服务端验证客户端信息
```
UcsResult res = c.setRandomKey(UcsHttpClient.generateRandomKey()).clientValidate(ClientAuthType.ID_AND_SECRET);
```

### UCS服务端验证操作或接口
```
UcsResult res = client.userValidatePermByOperation("UCS_USER_LIST", true, true);
UcsResult res = client.userValidatePermByAction("ucs", "get", "/api/v1/ucs/users", true, true);
```

### 向UCS服务端发起应用级调用
```
UcsResult<Object> clientRes = ucsHttpClient.clientRequest(Object.class, "GET", "/api/v1/ucs/client/validate", null, ClientAuthType.ID_AND_SECRET);
```