装修系统（SpringBoot+Vue+Redis+MySQL+MyBatis-Plus+ElementUI）
项目主要由社区+商城组成。后端Springboot，使用Shiro进行加密注册，登录拦截；使用OSS进行文件的存储，Redis处理商品的减库存，防止超卖。
实现文章收藏数的增减，避免用户多次收藏；使用基于HashMap的DFA算法实现简单的敏感词过滤。用户收藏文章，评论文章都有消息推送，后台也可推送消息。
前端使用Vue+ElementUI+Axios，使用Vuex存储用户信息到localStorage，用户不需要每次登录。
JDK 8
Springboot 2
MYSQL 5.7
Redis 3.2
VUE 2
