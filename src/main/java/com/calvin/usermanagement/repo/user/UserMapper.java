package com.calvin.usermanagement.repo.user;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM calvin_user")
    List<User> selectAll();

    @Insert("INSERT INTO calvin_user(user_id, email, password, create_at, update_at) VALUES(#{userId}, #{email}, #{password}, #{createAt}, #{updateAt})")
    void insert(@Param("userId") String userId, @Param("email") String email, @Param("password") String password,
            @Param("createAt") String createAt, @Param("updateAt") String updateAt);

    @Select("SELECT * FROM calvin_user WHERE email=#{email}")
    @Results({@Result(property = "userId", column = "user_id"), @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")})
    User selectByEmail(@Param("email") String email);

    @Select("SELECT * FROM calvin_user WHERE user_id=#{userId}")
    @Results({@Result(property = "userId", column = "user_id"), @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")})
    User selectByUserId(@Param("userId") String userId);

    @Update("UPDATE calvin_user SET nickname=#{nickname},update_at=#{updateAt} WHERE user_id=#{userId}")
    void updateNicknameByUserId(@Param("userId") String userId, @Param("nickname") String nickname,
            @Param("updateAt") String updateAt);

    @Update("UPDATE calvin_user SET address=#{address},update_at=#{updateAt} WHERE user_id=#{userId}")
    void updateAddressByUserId(@Param("userId") String userId, @Param("address") String address,
            @Param("updateAt") String updateAt);

    @Select("SELECT password FROM calvin_user WHERE user_id=#{userId}")
    String selectPwdByUserId(@Param("userId") String userId);

    @Update("UPDATE calvin_user SET password=#{password},update_at=#{updateAt} WHERE user_id=#{userId}")
    void updatePwdByUserId(@Param("userId") String userId, @Param("password") String password,
            @Param("updateAt") String updateAt);
}
