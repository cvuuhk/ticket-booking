# URL设计

角色说明：

- user：普通用户，消费者
- admin：管理员，由超级管理员设定
- root：超级管理员，只有一名

| URL                  | 角色要求 | 方法   | 作用                                         |
| -------------------- | -------- | ------ | -------------------------------------------- |
| /                    | *        | get    | 主页，普通用户登录后跳转至此                 |
| /register            | *        | get    | 用户注册界面，注册成功后跳转主页             |
| /login               | *        | get    | 登录界面，成功登录则根据权限自动跳转相应页面 |
| /dologin             | *        | post   | 发送登录请求                                 |
| /product/{productId} | *        | get    | 产品详情页面                                 |
| /user                | user     | get    | 访问用户个人信息页面                         |
| /user                | user     | put    | 更新用户个人信息                             |
| /user/unregister     | user     | post   | 删除账户，要求密码验证                       |
| /user/accountReal           | user     | get    | 访问实名认证页面                             |
| /user/accountReal           | user     | post   | 新建实名认证信息                             |
| /user/accountReal           | user     | put    | 更新实名认证信息                             |
| /user/accountReal           | user     | delete | 删除实名认证信息                             |
| /user/buy            | user     | get    | 进行选座等操作                               |
| /user/buy            | user     | post   | 提交订单信息，要求密码验证                   |
| /user/ticket         | user     | get    | 查看订单列表                                 |
|                      |          |        |                                              |
| /admin               | admin    | get    | 访问商品管展示页面，管理员登录后默认跳转至此 |
| /admin               | admin    | post   | 新增商品条目                                 |
| /admin               | admin    | delete | 删除商品条目                                 |
| /admin               | admin    | put    | 更新商品条目                                 |
| /root                | root     | get    | 访问管理员主页，超级管理员登录后默认跳转至此 |
| /root                | root     | post   | 新增管理员                                   |
| /root                | root     | delete | 删除管理员                                   |
| /root                | root     | put    | 更新管理员信息                               |
| /statistic           | admin    | get    | 访问统计页面                                 |

