注册 --- done
登陆  -- done

联系人列表(未读)  -- done
  搜索用户   -- done
  添加联系人 -- 如果A和B互相不为联系人，A添加B，B是A的联系人，A不是B的联系人    -- done
  删除联系人 -- 如果A和B互为联系人，A删除B，B从A的联系人列表删除，A还在B的联系人列表中  -- done

聊天
  进入聊天页面  -- 未读置为0   -- done
  发送消息  -- done
  聊天历史 -- 不考虑历史消息长度问题，历史滑动初始位置不是最新记录 -- done 


未完成：
参数验证、错误处理、认证后直接调用接口联系人重复添加

注册完自动登录：
https://github.com/hellokoding/registration-login-spring-hsql/blob/master/src/main/java/com/hellokoding/auth/service/SecurityServiceImpl.java


实时刷新未读消息、实时刷新联系人、实时消息接收