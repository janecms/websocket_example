# websocket演示

包括若干个例子（例子多来自网络，以及apache,j2ee官方)

## 部署
- 环境:tomcat7+maven3+jdk7
- 建议在apache-tomcat-7.0.72;
- 个人测试apache-tomcat-7.0.54版本部分功能不正常
- 访问http://localhost:8080/wsexample/

##功能列表

 * 1.[Echo协议(echo)](#Echo协议)
 * 2.[响应多条信息(multiple)](#响应多条信息(multiple))
 * 3.[(XML)编码解密处理(codec)](#(XML)编码解密处理(codec))
 * 4.[(JSON)编码解密处理(codec)](#(XML)编码解密处理(codec))
 * 5.[(CRUD)应用](#(XML)编码解密处理(codec))
 * 6 [文件上传]（#文件上传）
 * 7 [在线聊天]（#在线聊天)
 * 8 [故事画板](#故事画板)
 * 9 [画板涂鸦](#画板涂鸦)
 * 10 [贪食蛇游戏](#贪食蛇游戏)

---

<h2 id="xml编码解密处理codec">(XML)编码解密处理(codec)</h2>
建议在apache-tomcat-7.0.72;
个人测试apache-tomcat-7.0.54版本不正常

## Echo协议
位置 | Path
---|---
Server | com.sample.websocket.endpoint.EchoEndpoint
Client | single_message.html
endpoint | /echo

来自Tomcat官方demo
位置 | Path
---|---
Server | websocket.echo.EchoAnnotation（Annotation)
Server | websocket.echo.EchoEndpoint(编程式声明）
Client | single_message.html
endpoint1 | /websocket/echoAnnotation  
endpoint2 | /websocket/echoProgrammatic

## 文件上传
待添加
## 在线聊天

位置 | Path
---|---
Client | websocket/chat.xhtml
Server  | websocket.chat.ChatAnnotation
endpoint | /websocket/chat



 ##相关资源（持续更新）
 * https://github.com/CCLooMi/FileUploadServer_2
 * https://github.com/TooTallNate/Java-WebSocket
 * https://github.com/nickytd/websocket-message-handlers-example
 * https://nickytd.wordpress.com/developers-perspective-on-java-api-for-websocket-jsr-356-part-iii/
 * https://nickytd.wordpress.com/developers-perspective-on-java-api-for-websocket-jsr-356-part-ii/
 * https://nickytd.wordpress.com/developers-perspective-on-java-api-for-websocket-jsr-356-part-i/
 * http://www.byteslounge.com/tutorials/java-ee-html5-websocket-example
 * http://cjihrig.com/blog/how-to-use-websockets/
 * https://blogs.oracle.com/arungupta/entry/websocket_client_and_server_endpoint
 * https://docs.oracle.com/javaee/7/tutorial/websocket.htm
 * https://html.spec.whatwg.org/multipage/index.html
 * https://tyrus.java.net/documentation/1.4/index/index.html
 * [WebSocket的原理，以及和Http的关系](http://www.cnblogs.com/Herzog3/p/5088130.html)
 * [从Http的连接到WebSocket](http://blog.csdn.net/fenglibing/article/details/7108982)