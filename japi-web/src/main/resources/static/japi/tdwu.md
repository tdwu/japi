### 新特性
1 支持排序
2 支持信息配置
3 支持MarkDown

##### MarkDown使用方法
1 配置系统的README信息
* 在resources/static/japi/目录下创建markdown文件
* 配置mark down文件地址：
  A yml中通过japi.ream-me:设置markdown名称，或路径（基于resources/static/japi/）
  B 重写JApiConfigurer。如下：
   ```java
  @Configuration
  public class ApiConfigurer extends JApiConfigurer {
  
      @Bean
      public ApiDocumentationScanner documentationScanner() {
          this.apiConfigInfo().setReadMe("API.md");
          this.apiConfigInfo().setTitle("XXX系统接口");
          return new ApiDocumentationScanner(this.apiConfigInfo());
      }
  }
  ```
  
2 Method中使用MarkDown
* APIMethod注解中设置markDown属性，置顶MarkDown文件路径（基于resources/static/japi/）
* 可设置MarkDown和之前的描述信息的存在关系，1 为只显示MarkDown信息，2 MarkDown信息和原始注解数据共存
   设置方式支持配置文件japi.mark-down:1/2 和重写JApiConfigurer
   
### 配置项如下：
```java
    @Value("${japi.title:系统接口文档}")
    private String title;//html title
    @Value("${japi.company:重庆品目网络}")
    private String company;//公司名称
    @Value("${japi.base-path:}")
    private String basePath = "";//接口地址
    @Value("${japi.web-site:http://www.pmwangluo.com}")
    private String webSite;//公司站点
    @Value("${japi.read-me:tdwu.md}")
    private String readMe;
    @Value("${japi.mark-down:1}")
    private int markDown = 1;//0 不显示mark down 1 只显示mark down， 2 markdown和接口描述都显示
```



