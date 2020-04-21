package org.apframework.dozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserData.java 和上面的UserModel.java相比，密码属性名字不一样。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String userId;
    private String nickname;
    private String passwd;

    private UserModelInner inner;
}
